package connection;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.util.Iterator;

import City.*;
import auxiliary.Commander;
import commands.ExitCommand;
import commands.SaveCommand;
import fileManager.FileManager;


import java.util.Stack;

/**
 * принимает запросы с клиентов
 */
public class ServerManager {
    private int port = 0;
    private final ServerSocketChannel channel;
    private final Selector selector;
    private final ByteBuffer buf = ByteBuffer.allocate(256);

    public ServerManager(int port) throws IOException {
        this.port = port;
        channel = ServerSocketChannel.open();
        channel.socket().bind(new InetSocketAddress("localhost", port));
        channel.configureBlocking(false);
        selector = Selector.open();

        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void starting(Stack<City> cityCollection) {
        try {
            System.out.printf("Сервер запущен в порте %d\n\n", port);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (channel.isOpen()) {
                if (reader.ready()) {
                    String line = reader.readLine();

                    if ("exit".equals(line)) {
                       new FileManager().saveCollection(cityCollection, null);
                       new ExitCommand().run();
                       break;
                    } else if ("save".equals(line)) {
                        new SaveCommand().run(null, cityCollection);
                       // new FileManager().saveCollection(cityCollection, null);
                    }
                }

                if (selector.selectNow() != 0) {
                    Iterator<SelectionKey> iter = selector.selectedKeys().iterator();

                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();

                        if (key.isAcceptable())
                            handleAccept(key);
                        if (key.isReadable())
                           cityCollection = handleRead(key, cityCollection);

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

        String address = (new StringBuilder(sc.socket().getInetAddress().toString())).append(":").append(sc.socket().getPort()).toString();

        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_READ, address);

        System.out.printf("Установлено соединение с пользователем: %s\n", address);
    }

    public Stack<City> handleRead(SelectionKey key, Stack<City> cityCollection) throws IOException {
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
                    Commander.readCommand(cityCollection);
                    response = new Response(new Commander().response);
                } else
                    System.out.println("Сообщение повреждено");
            } catch (Exception e) {
                e.printStackTrace();
            }
            sb.reset();
            ObjectOutput objectOutput = new ObjectOutputStream(sb);
            objectOutput.writeObject(response);

            ch.write(ByteBuffer.wrap(sb.toByteArray()));
        }

        return cityCollection;
    }



}
