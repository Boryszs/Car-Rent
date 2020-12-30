package com.Server.service.impl;

import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Class SendMail use to send mail.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

@Service
public class SendMail {
    /**Constructor*/
    public SendMail() {
    }

    /**
     * Method send mail.
     * @param username username.
     * @param mess mesage to send.
     */
    public void sendMail(String username, String mess) {
        String from = "pizza.projekt06@gmail.com";
        String to = "pizza.projekt06@gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(props);

        try {
            InternetAddress fromAddress = new InternetAddress(from);
            InternetAddress toAddress = new InternetAddress(to);

            Message message = new MimeMessage(session);
            message.setFrom(fromAddress);
            message.setRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject("Car-Sharing");

            String sb = "<head>" +
                    "<style type=\"text/css\">" +
                    "  .red { color: #f00; }" +
                    "</style>" +
                    "</head>" +
                    "<h1 class=\"black\" style=\"background-color:#57ff14;\">" + "Car-Sharing" + "</h1>" +
                    "<body style=\"background-color:#57ff14;\">\n" +
                    "\n" +
                    "<img src=\"https://i.wpimg.pl/730x0/m.autokult.pl/55937440-616487475464085-b315b3f.jpg\" width=\"270\" + height=\"180\"/>\n" +
                    "\n" +
                    "\n<p><b>" + "Car-Sharing" + "</b></p>\n" +
                    "<h4>Username:" + username + "</h4>\n" +
                    "<p>" + mess + "</p>" +
                    "\n" +
                    "</body>";
            message.setContent(sb, "text/html; charset=utf-8");
            message.saveChanges();


            Transport.send(message, "pizza.projekt06", "pizza12345678");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}