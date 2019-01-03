/*
发送一封HTML E-mail
和上一个例子很相似，除了我们要使用setContent()方法来通过第二个参数为"text/html"，来设置内容来指定要发送HTML内容。

 */

package windea.test.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Test2 {
	public static void main(String[] args) {

		// 收件人电子邮箱
		String to = "abcd@gmail.com";
		// 发件人电子邮箱
		String from = "web@gmail.com";
		// 指定发送邮件的主机为 localhost
		String host = "localhost";

		// 获取系统属性
		Properties properties = System.getProperties();
		// 设置邮件服务器
		properties.setProperty("mail.smtp.host", host);
		// 获取默认的 Session 对象。
		Session session = Session.getDefaultInstance(properties);

		try {
			// 创建默认的 MimeMessage 对象。
			MimeMessage message = new MimeMessage(session);
			// Set From: 头部头字段
			message.setFrom(new InternetAddress(from));
			// Set To: 头部头字段
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// Set Subject: 头字段
			message.setSubject("This is the Subject Line!");
			// 发送 HTML 消息, 可以插入html标签
			message.setContent("<h1>This is actual message</h1>", "text/html");

			// 发送消息
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch(MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
