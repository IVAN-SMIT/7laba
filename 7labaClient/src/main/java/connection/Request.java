package connection;

import java.io.Serializable;

/**
 * запрос
 */

public class Request implements Serializable {
    private String command;

    public Request(String command){
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }
}

