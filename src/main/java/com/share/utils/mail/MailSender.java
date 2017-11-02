package com.share.utils.mail;

import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * ClassName:MailSender
 *
 * @author ch
 * @version Ver 1.0
 * @Function: TODO ADD FUNCTION
 * @Date 2012-12-6 上午11:00:37
 * @see
 */

public class MailSender {

    private MailInfo mailInfo;

    public MailSender(String subject, String content, String[] attachFileNames, String[] toAddress) {
        super();
        this.mailInfo = new MailInfo(subject, content, attachFileNames, toAddress);
    }

    public static void main(String[] args) {
        MailSender ms = new MailSender("a", "b", null, new String[]{"1547913526@qq.com"});
        boolean str = ms.sendTextMail();
        System.out.println("邮件是否发送成功：" + str);
    }

    /**
     * 以文本格式发送邮件
     *
     * @param mailInfo 待发送的邮件的信息
     */
    public boolean sendTextMail() {
        // 判断是否需要身份认证
        MyAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        if (mailInfo.isValidate()) {
            // 如果需要身份认证，则创建一个密码验证器
            authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getInstance(pro, authenticator);
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            // Address to = new InternetAddress(mailInfo.getToAddress());
            // mailMessage.setRecipient(Message.RecipientType.TO,to);
            // 创建邮件的接收地址（数组）
            String[] to = mailInfo.getToAddress();
            InternetAddress[] sendTo = new InternetAddress[to.length];
            for (int i = 0; i < to.length; i++) {
//				System.out.println("发送到:" + to[i]);
                sendTo[i] = new InternetAddress(to[i]);
            }
            mailMessage.setRecipients(javax.mail.internet.MimeMessage.RecipientType.TO, sendTo);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容
            String mailContent = mailInfo.getContent();
           // mailMessage.setText(mailContent);
            mailMessage.setContent(mailContent,"text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 以HTML格式发送给多人邮件 (可带多个附件)
     *
     * @param mailInfo 待发送的邮件信息
     */
    public boolean sendHtmlMail() {
        // 判断是否需要身份认证
        MyAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        // 如果需要身份认证，则创建一个密码验证器
        if (mailInfo.isValidate()) {
            authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getInstance(pro, authenticator);
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            /*
			 * // 创建邮件的接收者地址，并设置到邮件消息中 ----此可发送给一人 Address to = new
			 * InternetAddress(mailInfo.getToAddress());
			 *
			 * // Message.RecipientType.TO属性表示接收者的类型为TO
			 * mailMessage.setRecipient(Message.RecipientType.TO,to);
			 */
            // 创建邮件的接收地址（数组）
            String[] to = mailInfo.getToAddress();
            InternetAddress[] sendTo = new InternetAddress[to.length];
            for (int i = 0; i < to.length; i++) {
//				System.out.println("发送到:" + to[i]);
                sendTo[i] = new InternetAddress(to[i]);
            }
            mailMessage.setRecipients(javax.mail.internet.MimeMessage.RecipientType.TO, sendTo);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容 建立第一部分： 文本正文
            html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容 建立第二部分：附件
            mailMessage.setContent(mainPart);
//			System.out.println("附件：");
            if (mailInfo.getAttachFileNames() != null && mailInfo.getAttachFileNames().length > 0) {
                for (int i = 0; i < mailInfo.getAttachFileNames().length; i++) {
                    if (!mailInfo.getAttachFileNames()[i].equals("")) {
                        // 建立第二部分：附件
                        html = new MimeBodyPart();
                        // 获得附件
                        DataSource source = new FileDataSource(mailInfo.getAttachFileNames()[i]);
                        // 设置附件的数据处理器
                        html.setDataHandler(new DataHandler(source));
                        // 设置附件文件名
                        String fileName = mailInfo.getAttachFileNames()[i].substring(mailInfo.getAttachFileNames()[i].lastIndexOf("\\") + 1);
                        String hou = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                        try {
                            // if (i == 0) {
                            // html.setFileName(MimeUtility.encodeWord("申请单" +
                            // hou, "utf-8", null));
                            // } else {
                            int hao = i + 1;
                            html.setFileName(MimeUtility.encodeWord("相关材料" + hao + hou, "utf-8", null));
                            // }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        System.out.println(html.getFileName());
                        // 加入第二部分
                        mainPart.addBodyPart(html);
                    }
                }
            }
            // 发送邮件
//			System.out.println("正在发送");
            Transport.send(mailMessage);
//			System.out.println("发送成功");
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }
        return false;
    }
}

class MyAuthenticator extends Authenticator {
    String userName = null;
    String password = null;

    public MyAuthenticator() {
    }

    public MyAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}