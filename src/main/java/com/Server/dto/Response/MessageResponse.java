package com.Server.dto.Response;

/**
 * Class DTO get Message text.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

public class MessageResponse {
    /**message*/
    String message;

    /**
     * Constructor
     * @param message message
     */
    public MessageResponse(String message) {
        this.message = message;
    }

    /**
     *
     * @return Message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message setMessage
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
