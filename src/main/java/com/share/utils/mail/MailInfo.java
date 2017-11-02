package com.share.utils.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.ResourceBundle;

/**
 * ClassName:MailSenderInfo
 *
 * @author ch
 * @version Ver 1.0
 * @Function: TODO ADD FUNCTION
 * @Date 2012-12-6 上午10:56:49
 * @see
 */
public class MailInfo {

    /**
     * <p>
     * Copyright ® 2017 wjl。
     * </p>
     */
    // 发送邮件的服务器的IP和端口
    private String mailServerHost;
    private String mailServerPort;
    // 邮件发送者的地址
    private String fromAddress;
    // 登陆邮件发送服务器的用户名和密码
    private String userName;
    private String password;
    // 是否需要身份验证
    private boolean validate = true;
    // 邮件主题
    private String subject = "";
    // 邮件的文本内容
    private String content = "";
    // 邮件附件的文件名
    private String[] attachFileNames;
    // 邮件接收者的地址
    private String[] toAddress;

    public MailInfo(){

    }
    public MailInfo(String mailServerHost, String mailServerPort, String fromAddress, String userName, String password, boolean validate,
                    String subject, String content, String[] attachFileNames, String[] toAddress) {
        super();
        this.mailServerHost = mailServerHost;
        this.mailServerPort = mailServerPort;
        this.fromAddress = fromAddress;
        this.userName = userName;
        this.password = password;
        this.validate = validate;
        this.subject = subject;
        this.content = content;
        this.attachFileNames = attachFileNames;
        this.toAddress = toAddress;
    }

    public MailInfo(String subject, String content, String[] attachFileNames, String[] toAddress) {
        super();
        this.mailServerHost = ResourceBundle.getBundle("mail").getString("mailServerHost");
        this.mailServerPort = ResourceBundle.getBundle("mail").getString("mailServerPort");
        this.fromAddress = ResourceBundle.getBundle("mail").getString("fromAddress");
        this.userName = ResourceBundle.getBundle("mail").getString("userName");
        this.password = ResourceBundle.getBundle("mail").getString("password");
        this.subject = subject;
        this.content = content;
        this.attachFileNames = attachFileNames;
        this.toAddress = toAddress;
    }

    /**
     * 获得邮件会话属性
     */
    public Properties getProperties() {
        Properties p = new Properties();
        p.put("mail.smtp.host", ResourceBundle.getBundle("mail").getString("mailServerHost"));
        p.put("mail.smtp.port",ResourceBundle.getBundle("mail").getString("mailServerPort"));
       // p.put("mail.smtp.auth", validate ? "true" : "false");
        p.put("mail.smtp.auth", "true");
        return p;
    }

    public String getMailServerHost() {
        return mailServerHost;
    }

    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    public String getMailServerPort() {
        return mailServerPort;
    }

    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String[] getAttachFileNames() {
        return attachFileNames;
    }

    public void setAttachFileNames(String[] fileNames) {
        this.attachFileNames = fileNames;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String textContent) {
        this.content = textContent;
    }

    public String[] getToAddress() {
        return toAddress;
    }

    public void setToAddress(String[] toAddress) {
        this.toAddress = toAddress;
    }
}
