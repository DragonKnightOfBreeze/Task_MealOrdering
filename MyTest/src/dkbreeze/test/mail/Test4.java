package dkbreeze.test.mail;

// 需要用户名密码邮件发送实例
//本实例以QQ邮箱为例，你需要在qq后台设置

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Test4 {
	public static void main(String[] args) {
		// 收件人电子邮箱
		String to = "xxx@qq.com";

		// 发件人电子邮箱
		String from = "xxx@qq.com";

		// 指定发送邮件的主机为 localhost
		String host = "smtp.qq.com";  //QQ 邮件服务器

		// 获取系统属性
		Properties properties = System.getProperties();

		// 设置邮件服务器
		properties.setProperty("mail.smtp.host", host);

		properties.put("mail.smtp.auth", "true");
		// 获取默认session对象
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("xxx@qq.com", "qq邮箱密码"); //发件人邮件用户名、密码
			}
		});

		try {
			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);

			// Set From: 头部头字段
			message.setFrom(new InternetAddress(from));

			// Set To: 头部头字段
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to));

			// Set Subject: 头部头字段
			message.setSubject("This is the Subject Line!");

			// 设置消息体
			message.setText("This is actual message");

			// 发送消息
			Transport.send(message);
			System.out.println("Sent message successfully....from shouce.ren");
		} catch(MessagingException mex) {
			mex.printStackTrace();
		}
	}
}

