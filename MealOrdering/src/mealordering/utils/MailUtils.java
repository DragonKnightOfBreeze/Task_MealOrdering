package mealordering.utils;

import org.jetbrains.annotations.NotNull;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

/**
 * 发送邮件的工具类
 * @noinspection unused, WeakerAccess
 */
@Deprecated
public class MailUtils {

	/**
	 * 发送邮件。
	 * @param emailFrom 发送者的邮箱地址
	 * @param emailTo 接受者的邮箱地址
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 */
	public static void sendMail(@NotNull String emailFrom, @NotNull String emailTo, @NotNull String subject, @NotNull String content) throws MessagingException, IOException {
		//得到一个属性对象
		Properties props = new Properties();
		props.load(MailUtils.class.getResourceAsStream("/props/mailProps.properties"));
		//创建验证器
		Authenticator auth = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("windea.qq.com", "******");
			}
		};
		sendMail(props, auth, emailFrom, emailTo, subject, content);
	}

	/**
	 * 发送邮件。
	 * TODO 提取参数prop,auth
	 * @param emailFrom 发送者的邮箱地址
	 * @param emailTo 接受者的邮箱地址
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 */
	public static void sendMail(Properties props, Authenticator auth, @NotNull String emailFrom, @NotNull String emailTo, @NotNull String subject, @NotNull String content) throws MessagingException {
//		//创建一个属性对象
//		Properties props = new Properties();
//		//设置邮件传输协议为SMTP
//		props.setProperty("mail.transport.protocol", "SMTP");
//		//设置SMTP服务器地址
//		props.setProperty("mail.host", "smtp.qq.com");
//		//设置SMTP服务器是否需要用户验证，需要验证设置为true
//		props.setProperty("mail.smtp.auth", "true");
//
//		//创建验证器
//		Authenticator auth = new Authenticator() {
//			public PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication("windea.qq.com", "******");
//			}
//		};

		//1.创建一个程序与邮件服务器的会话对象
		Session session = Session.getInstance(props, auth);
		//2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);
		//设置发送者
		message.setFrom(new InternetAddress(emailFrom));
		//设置发送方式与接收者
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
		//设置邮件主题
		message.setSubject(subject);
		message.setContent(content, "text/html;charset=utf-8");
		//3.创建 Transport用于将邮件发送
		Transport.send(message);
	}


}
