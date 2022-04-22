package connection;

import java.io.Serializable;

/**
 * отклик
 */

public class Response implements Serializable {
    private String textResponse;

    public Response(String textResponse){
        this.textResponse = textResponse;
    }

    public String gettextResponse() {
        return this.textResponse;
    }
}