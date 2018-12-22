/*
 * Copyright (c) $today.year.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of Wind Fairy.
 */

package dk_breeze.utils;

import dk_breeze.utils.ext.ArrayExt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * 发送邮件的工具类
 * @noinspection unused, WeakerAccess
 */
public class MailUtils {
	/**
	 * 邮件内容类型
	 */
	public enum ContentType {
		text, html
	}

	/**
	 * 发送文本邮件。服务器地址为localhost。
	 * @param fromEmail 发件人邮箱
	 * @param toEmail 收件人邮箱
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param authInfo 验证信息，[userName,password]，默认为null
	 * @param attachInfo 附件信息，[filePath...,fileName]，默认为null
	 */
	public static void send(@NotNull String fromEmail, @NotNull String toEmail, @NotNull String subject, @NotNull String content, @Nullable String[] authInfo, @Nullable String[] attachInfo) {
		send("localhost", fromEmail, toEmail, subject, content, authInfo, attachInfo);
	}

	/**
	 * 发送文本邮件。
	 * @param host 服务器地址
	 * @param fromEmail 发件人邮箱
	 * @param toEmail 收件人邮箱
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param authInfo 验证信息，[userName,password]，默认为null
	 * @param attachInfo 附件信息，[filePath...,fileName]，默认为null
	 */
	public static void send(@NotNull String host, @NotNull String fromEmail, @NotNull String toEmail, @NotNull String subject, @NotNull String content, @Nullable String[] authInfo, @Nullable String[] attachInfo) {
		send(host, fromEmail, toEmail, subject, content, ContentType.text, authInfo, attachInfo);
	}


	/**
	 * 发送HTML邮件。服务器地址为localhost。
	 * @param fromEmail 发件人邮箱
	 * @param toEmail 收件人邮箱
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param authInfo 验证信息，[userName,password]，默认为null
	 * @param attachInfo 附件信息，[filePath...,fileName]，默认为null
	 */
	public static void sendHTML(@NotNull String fromEmail, @NotNull String toEmail, @NotNull String subject, @NotNull String content, @Nullable String[] authInfo, @Nullable String[] attachInfo) {
		sendHTML("localhost", fromEmail, toEmail, subject, content, authInfo, attachInfo);
	}

	/**
	 * 发送HTML邮件。
	 * @param host 服务器地址
	 * @param fromEmail 发件人邮箱
	 * @param toEmail 收件人邮箱
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param authInfo 验证信息，[userName,password]，默认为null
	 * @param attachInfo 附件信息，[filePath...,fileName]，默认为null
	 */
	public static void sendHTML(@NotNull String host, @NotNull String fromEmail, @NotNull String toEmail, @NotNull String subject, @NotNull String content, @Nullable String[] authInfo, @Nullable String[] attachInfo) {
		send(host, fromEmail, toEmail, subject, content, ContentType.html, authInfo, attachInfo);
	}


	/**
	 * 发送邮件。服务器地址为localhost。
	 * @param fromEmail 发件人邮箱
	 * @param toEmail 收件人邮箱
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param contentType 邮件内容类型，默认为text
	 * @param authInfo 验证信息，[userName,password]，默认为null
	 * @param attachInfo 附件信息，[filePath...,fileName]，默认为null
	 */
	public static void send(@NotNull String fromEmail, @NotNull String toEmail, @NotNull String subject, @NotNull String content, ContentType contentType, @Nullable String[] authInfo, @Nullable String[] attachInfo) {
		send("localhost", fromEmail, toEmail, subject, content, contentType, authInfo, attachInfo);
	}

	/**
	 * 发送邮件。
	 * @param host 服务器地址
	 * @param fromEmail 发件人邮箱
	 * @param toEmail 收件人邮箱
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param contentType 邮件内容类型，默认为text
	 * @param authInfo 验证信息，[userName,password]，默认为null
	 * @param attachInfo 附件信息，[filePath...,fileName]，默认为null
	 */
	public static void send(@NotNull String host, @NotNull String fromEmail, @NotNull String toEmail, @NotNull String subject, @NotNull String content, ContentType contentType, String[] authInfo, String[] attachInfo) {
		// 获取系统属性
		Properties properties = System.getProperties();
		//设置邮件传输协议为SMTP
		properties.setProperty("mail.transport.protocol", "SMTP");
		// 设置邮件服务器，指定发送邮件的主机
		properties.setProperty("mail.smtp.host", host);

		Session session;
		if(!ArrayExt.orLessE(authInfo, 2)) {
			//设置为需要进行身份验证
			properties.put("mail.smtp.auth", "true");
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(authInfo[0], authInfo[1]);
				}
			};
			session = Session.getDefaultInstance(properties, auth);
		} else {
			//设置为不需要进行身份验证
			session = Session.getDefaultInstance(properties);
		}

		try {
			// 创建默认的MimeMessage对象
			MimeMessage message = new MimeMessage(session);
			//设置发送者
			message.setFrom(new InternetAddress(fromEmail));
			//设置发送方式和接收者
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

			//设置邮件主题
			message.setSubject(subject);
			// 创建多重消息
			Multipart multipart = new MimeMultipart();

			//设置文本内容
			BodyPart bodyPart1 = new MimeBodyPart();
			if(contentType == ContentType.text) {
				message.setText(content);
			} else {
				message.setContent(content, "text/" + contentType.toString());
			}
			multipart.addBodyPart(bodyPart1);

			// 设置附件内容
			if(!ArrayExt.orEmpty(attachInfo)) {
				BodyPart bodyPart2 = new MimeBodyPart();
				String fileName = FileUtils.join(attachInfo);
				bodyPart2.setDataHandler(new DataHandler(new FileDataSource(fileName)));
				bodyPart2.setFileName(fileName);
				multipart.addBodyPart(bodyPart2);
			}

			// 设置邮件内容
			message.setContent(multipart);

			// 发送消息
			Transport.send(message);
		} catch(MessagingException e) {
			e.printStackTrace();
		}
	}

}
