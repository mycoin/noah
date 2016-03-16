package org.ionnic.biz.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public final class Email {
    private static String __smtpserv = null;
    private static String __userName = null;
    private static String __passWord = null;
    private static String __address = null;

    private static Email __instance = null;

    synchronized public static Object getInstance() {
        if (__instance == null)
            __instance = new Email();
        return __instance;
    }

    public static void initJMail() {

    }

    public boolean sendMail(String subject, String text, String to) {
        initJMail();
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", __smtpserv);
            props.put("mail.smtp.auth", "true");
            Session ssn = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(__userName, __passWord);
                }
            });
            MimeMessage msg = new MimeMessage(ssn);// 由邮件会话新建一个消息对象
            msg.setFrom(new InternetAddress(__address));// From
            InternetAddress toAddress = new InternetAddress(to);
            msg.addRecipient(Message.RecipientType.TO, toAddress);// toAddress
            msg.setSubject(subject);// set subject
            msg.setText(text);// set Content
            msg.setSentDate(new Date());// set time

            Transport trans = ssn.getTransport("smtp");
            trans.connect(__smtpserv, __userName, __passWord);
            trans.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
            trans.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}