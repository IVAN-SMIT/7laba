package connection;


import java.io.Serializable;

/**
 * запрос
 */

public class Request implements Serializable {
    private String command;
    private String username;
    private String password;

    public Request(String command, String username, String password){

        this.command = command;
        this.username = username;
        this.password = password;
    }


    public String getCommand() {
        return this.command;
    }
}