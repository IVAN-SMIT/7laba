package connection;

import java.io.Serializable;

/**
 * отклик
 */

public class Response implements Serializable {
    private String textResponse;
    private String password;
    private String username;

    public Response(String textResponse, String username, String password){

        this.textResponse = textResponse;
        this.username = username;
        this.password = password;
    }

    public String gettextResponse() {
        return this.textResponse;
    }

    public String getUsername() {return this.username;}

    public String getPassword() {return this.password;}

}