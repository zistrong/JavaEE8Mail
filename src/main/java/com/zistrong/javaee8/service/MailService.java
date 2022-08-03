package com.zistrong.javaee8.service;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Stateless
public class MailService {
    private static final String SMTP_HOST = "mail.smtp.host";
    private static final String SMTP_AUTH = "mail.smtp.auth";
    private static final String SMTP_PORT = "mail.smtp.port";
    private static final String SMTP_TIMEOUT = "mail.smtp.timeout";
    private static final String SMTP_TIMEOUT_VALUE = "10000";
    private static final String SMTP_DEBUG = "mail.smtp.debug";
    private static final String SMTP_SSL_PROTOCOLS = "mail.smtp.ssl.protocols";
    private static final String STRING_TRUE = "true";

    private static final String SMPT_163 = "smtp.163.com";
    public static final String SSL_PROTOCOL_LEVEL_2 = "TLSv1.2";
    private String receiver = "abc@163.com";
    private String sender = "cb@163.com";
    private String port = "994";
    String userAccount = "abc@163.com";
    String decodePass = "ad@121@#";

    public void testMail() {


        Session session = getMailSession();

        try {
            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setFrom(new InternetAddress(sender));
            message.setSubject("abc" + new Date());
            message.setText("text");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    private static class MailAuthenticator extends Authenticator {
        private final String account;
        private final String pass;

        public MailAuthenticator(String account, String pass) {
            this.account = account;
            this.pass = pass;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(account, pass);
        }
    }

    private Session getMailSession() {
        Session session = Session.getInstance(getTSLProperties(port), new MailAuthenticator(userAccount, decodePass));
        return session;
    }

    private Properties getSSLProperties(String serverPort) {
        Properties props = new Properties();
        props.put(SMTP_HOST, SMPT_163);
        props.put("mail.smtp.socketFactory.port", serverPort);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put(SMTP_AUTH, true);
        props.put(SMTP_PORT, serverPort);
        props.put(SMTP_TIMEOUT, SMTP_TIMEOUT_VALUE);
        props.put(SMTP_DEBUG, STRING_TRUE);
        return props;
    }

    private Properties getTSLProperties(String serverPort) {
        Properties props = new Properties();
        props.put(SMTP_AUTH, true);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable", "true");
        props.put(SMTP_SSL_PROTOCOLS, SSL_PROTOCOL_LEVEL_2);
        props.put(SMTP_TIMEOUT, SMTP_TIMEOUT_VALUE);
        props.put(SMTP_HOST, SMPT_163);
        props.put(SMTP_PORT, serverPort);
        props.put(SMTP_DEBUG, STRING_TRUE);
        return props;
    }
}
