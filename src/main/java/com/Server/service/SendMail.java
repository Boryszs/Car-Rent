package com.Server.service;

import com.Server.dto.Request.UserRequest;

/**
 * Interface Service to send mail for client email.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */
public interface SendMail {
    void sendMail(Long id, String mess);
}
