package com.cn.hnust.service;

public interface SendMailService {

	/**
	 * 
	 * @Description:普通文本发邮件形式
	 * @param subject 主题
	 * @param content 正文
	 * @param toMail 收件人邮箱
	 * void
	 * @exception:
	 * @author: polo
	 * @time:2018年8月6日 
     */
	public void sendSimpleMail(String subject,String content,String toMail);
	
	

	/**
	 * 
	 * @Description:html发邮件形式
	 * @param subject 主题
	 * @param content 正文
	 * @param toMail 收件人邮箱
	 * void
	 * @exception:
	 * @author: polo
	 * @time:2018年8月6日 
     */
	public void sendHtmlMail(String subject,String content,String toMail);
	
	
	
	/**
	 * 
	 * @Description:带附件发邮件形式
	 * @param subject 标题
	 * @param content 正文
	 * @param toMail 收件人邮箱
	 * @param accessoryPath 附件路径
	 * @param accessoryName 附件名
	 * void
	 * @exception:
	 * @author: polo
	 * @time:2018年8月6日 
     */
	public void sendMailTakeAccessory(String subject,String content,String toMail,String accessoryPath,String accessoryName);
	
}
