//package com.cn.hnust.service.impl;
//
//import java.io.File;
//
//import javax.annotation.Resource;
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import com.cn.hnust.service.SendMailService;
//@Service
//public class SendMailServiceImpl implements SendMailService {
//
//
//    private static final Logger logger = LoggerFactory.getLogger(SendMailServiceImpl.class);
// 
//	@Resource
//	JavaMailSender mailSender;
//	@Resource
//	SimpleMailMessage simpleMailMessage;
//	
//	@Override
//	public void sendSimpleMail(String subject, String content, String toMail) {
//		simpleMailMessage.setSubject(subject);
//    	simpleMailMessage.setText(content);
//    	simpleMailMessage.setTo(toMail);
//    	mailSender.send(simpleMailMessage);
//    	logger.info("邮件发送成功.."); 
//
//	}
//
//	@Override
//	public void sendHtmlMail(String subject, String content, String toMail) {
//		MimeMessage mailMessage = mailSender.createMimeMessage(); 
//	    MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage); 
//	    try {
//			messageHelper.setTo(toMail);
//			messageHelper.setSubject(subject); 
//			messageHelper.setText("<html><head></head><body><h1>"+content+"</h1></body></html>",true);
//			mailSender.send(mailMessage); 
//		    logger.info("邮件发送成功.."); 
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		} 
//
//
//	}
//
//	@Override
//	public void sendMailTakeAccessory(String subject, String content,
//			String toMail, String accessoryPath, String accessoryName) {
//		  MimeMessage mailMessage = mailSender.createMimeMessage(); 
//		    MimeMessageHelper messageHelper;
//			try {
//				messageHelper = new MimeMessageHelper(mailMessage,true,"utf-8");
//			    messageHelper.setTo(toMail);
//				messageHelper.setSubject(subject); 
//			    messageHelper.setText("<html><head></head><body><h1>"+content+"</h1></body></html>",true); 
//			    FileSystemResource file = new FileSystemResource(new File(accessoryPath)); 
//			    messageHelper.addAttachment(accessoryName,file);
//			    mailSender.send(mailMessage); 
//			    logger.info("邮件发送成功.."); 
//			} catch (MessagingException e) {
//				e.printStackTrace();
//			} 
//
//
//	}
//
//}
