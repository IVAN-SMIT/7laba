package auxiliary;

/**
 * Все сообщения в консоль осуществляются отсюда
 */

public class Messager {
    String msg;
    public void println(String message, boolean flag) throws Exception {
        if(flag){
        this.msg = message;
        System.out.println(msg);
        }

    }
    public String getMessage(){
        System.out.println(msg);
        return msg;
    }
}
