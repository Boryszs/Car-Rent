package com.Server.service;

import com.Server.dto.Request.UserRequest;

public interface SendMail {
    void sendMail(UserRequest userRequest, String mess);
}
