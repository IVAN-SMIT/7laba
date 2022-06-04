package connection;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.sql.Connection;
import java.util.Iterator;

import City.*;
import auxiliary.Commander;
import database.DatabaseManager;

import java.util.Stack;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * принимает запросы с клиентов
 */
public class ServerManager {
    private int port = 0;
    private final ServerSocketChannel channel;
    private final Selector selector;
    private final ByteBuffer buf = ByteBuffer.allocate(256);
    private final ReentrantLock lock = new ReentrantLock();

    public ServerManager(int port) throws IOException {
        this.port = port;
        channel = ServerSocketChannel.open();
        channel.socket().bind(new InetSocketAddress("localhost", port));
        channel.configureBlocking(false);
        selector = Selector.open();

        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void starting(Stack<City> cityCollection, Connection myDatabase) {
        try {
            System.out.printf("Сервер запущен в порте %d\n\n", port);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (channel.isOpen()) {
                if (reader.ready()) {
                    String line = reader.readLine();

                    if ("exit".equals(line)) {
                        DatabaseManager.saveCollection(cityCollection, myDatabase, "authosave");
                     //  new FileManager().saveCollection(cityCollection, null);
                       System.exit(0);
                       break;
                    } else if ("save".equals(line)) {
                        DatabaseManager.saveCollection(cityCollection, myDatabase, "authosave");
                    }
                }

                if (selector.selectNow() != 0) {
                    Iterator<SelectionKey> iter = selector.selectedKeys().iterator();

                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        
                        if (key.isAcceptable()) {
                            Runnable job = () -> {
                                try {
                                    handleAccept(key);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            };
                            ExecutorService service = Executors.newFixedThreadPool(5);
                            service.execute(job);

                        }
                        if (key.isReadable()) {
                            lock.lock();
                            try {
                                cityCollection = handleRead(key, cityCollection, myDatabase);
                            } finally {
                                lock.unlock();
                            }
                        }

                        iter.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleAccept(SelectionKey key) throws IOException {
        SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();

        if (sc != null) {
            String address = (new StringBuilder(sc.socket().getInetAddress().toString())).append(":").append(sc.socket().getPort()).toString();

            sc.configureBlocking(false);
            sc.register(selector, SelectionKey.OP_READ, address);
            System.out.printf("Установлено соединение с пользователем: %s\n", address);
        }
    }

    public Stack<City> handleRead(SelectionKey key, Stack<City> cityCollection, Connection myDatabase) throws IOException {
        SocketChannel ch = (SocketChannel) key.channel();
        ByteArrayOutputStream sb = new ByteArrayOutputStream();

        buf.clear();
        int read = 0;

        try {
            while ((read = ch.read(buf)) > 0) {
                System.out.printf("Прочитано: %d\n", read);

                buf.flip();
                byte[] bytes = new byte[buf.limit()];
                buf.get(bytes);

                sb.write(bytes, 0, bytes.length);
                buf.clear();
            }
        } catch (IOException e) {
            System.out.println(key.attachment() + " покинул сервер.\n");
            ch.close();
            return cityCollection;
        }

        if (read < 0) {
            System.out.println("Пользователь "+key.attachment() + " покинул сервер.");
            ch.close();
        } else {
            ObjectInput objectInput = new ObjectInputStream(new ByteArrayInputStream(sb.toByteArray()));

            Response response = null;
            try {
                Object obj = objectInput.readObject();

                if (obj instanceof Request) {
                    Request req = (Request) obj;
                    Commander.command = req.getCommand();
                    cityCollection = new Commander().readCommand(cityCollection, req.getUsername(), req.getPassword(), myDatabase);;
                    //Commander.readCommand(cityCollection);
                    response = new Response(new Commander().response);
                } else
                    System.out.println("Сообщение повреждено");
            } catch (Exception e) {
               e.printStackTrace();
                System.out.println(e);
            }
            sb.reset();
            ObjectOutput objectOutput = new ObjectOutputStream(sb);
            objectOutput.writeObject(response);

            ch.write(ByteBuffer.wrap(sb.toByteArray()));
        }

        return cityCollection;
    }




}
