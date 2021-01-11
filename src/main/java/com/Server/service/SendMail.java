package com.Server.service;

import com.Server.dto.Request.RegisterRequest;

public interface SendMail {
    void sendMail(RegisterRequest registerRequest, String mess);
}
