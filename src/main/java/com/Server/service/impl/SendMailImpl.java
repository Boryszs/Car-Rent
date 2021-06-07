package com.Server.service.impl;

import com.Server.entiy.User;
import com.Server.repository.UserRepository;
import com.Server.service.SendMail;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * Class SendMail use to send mail.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

@Service
public class SendMailImpl implements SendMail {

    /**
     * User repository
     */

    private final UserRepository userRepository;

    /**Constructor
     * @param userRepository*/
    public SendMailImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method send mail.
     * @param id id user.
     * @param mess message to send.
     */
    public void sendMail(Long id, String mess) {
        String from = "pizza.projekt06@gmail.com";
        User user = userRepository.findById(id).get();
        String to = user.getEmail();

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback","false");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port",465);//send email via SSL connection
        props.put("mail.smtp.auth", "true");
        props.put("mail.store.protocol","pop3");
        props.put("mail.transport.protocol","smtp");
        props.put("mail.smtp.starttls.enable","true");

        Session session = Session.getDefaultInstance(props);

        try {
            InternetAddress fromAddress = new InternetAddress(from);
            InternetAddress toAddress = new InternetAddress(to);

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            message.setSubject("Testing Subject");

            MimeMultipart multipart = new MimeMultipart("related");

            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText =
                    "<head>" +
                    "<style type=\"text/css\">" +
                    "  .red { color: #f00; }" +
                    "</style>" +
                    "</head>" +
                    "<body style=\"background-color:#57ff14;\">\n" +
                    "\n" +
                    "<center><img src=\"cid:image\" width=\"470\" + height=\"340\"/></center>\n" +
                    "\n" +
                    "\n<p><b>" + "Car-Sharing" + "</b></p>\n" +
                    "<h4>Username:" + user.getUsername() + "</h4>\n" +
                    "<p>" + mess + "</p>" +
                    "\n" +
                    "<p><strong>"+"Thanks,"+"</strong></p>"+
                    "<p>"+"Support Car-Rental"+"</p>"+
                    "</body>";

            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource("foto.png");

            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");

            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);


            Transport.send(message, "pizza.projekt06", "pizza12345678");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
