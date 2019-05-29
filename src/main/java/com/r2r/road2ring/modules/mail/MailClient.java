package com.r2r.road2ring.modules.mail;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Service
public class MailClient {

  private JavaMailSender mailSender;

  @Autowired
  private TemplateEngine templateEngine;

  @Autowired
  public MailClient(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void prepareAndSend(String recipient, String messages) throws MessagingException {
    //TODO implement
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);

    Map model = new HashMap();
    model.put("message", "Naik motor si besi Tua");

    Context context = new Context();
    context.setVariables(model);
    String html = templateEngine.process("admin/email/checkout", context);

    helper.setFrom("bolalobintern@gmail.com");
    helper.setText(html, true);
    helper.setSubject("Sample mail subject");
    helper.setTo("paulmartensimanjuntak19@gmail.com");

//    mailSender.send(message);

    try {
      mailSender.send(message);
    } catch (MailException e) {
      // runtime exception; compiler will not force you to handle it
    }
  }

  public void sendPaidEmail(String recipient, String consumerName, int ridersNeeded) throws MessagingException {
    //TODO implement
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);

    Map model = new HashMap();
    model.put("consumerName", consumerName);
    model.put("ridersNeeded", ridersNeeded);

    Context context = new Context();
    context.setVariables(model);
    String html = templateEngine.process("admin/email/paid", context);

    helper.setTo(recipient);
    helper.setText(html, true);
    helper.setSubject("Paid Transaction Road2Ring");

    /*Change email*/
    helper.setFrom(new InternetAddress("bolalobintern@gmail.com"));
    try {
      mailSender.send(message);
    } catch (MailException e) {
      // runtime exception; compiler will not force you to handle it
    }
  }

  public void sendEmail(String recipient, String consumerName, int ridersNeeded) {

    MimeMessagePreparator messagePreparator = mimeMessage -> {
      MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
      message.setTo(recipient);
      message.setFrom(new InternetAddress("bolalobintern@gmail.com"));
      message.setSubject("Paid Transaction Road2Ring");

      Map model = new HashMap();
      model.put("consumerName", consumerName);
      model.put("ridersNeeded", ridersNeeded);

      Context context = new Context();
      context.setVariables(model);
      String html = templateEngine.process("admin/email/paid", context);

      message.setText(html, true);

//      if (attachmentPath != null) {
//        message.addAttachment(attachmentName, attachmentPath);
//      }
    };

    mailSender.send(messagePreparator);

//    if (attachmentPath != null) {
//      attachmentPath.delete();
//    }
  }



}
