package com.r2r.road2ring.modules.mail;

import static com.r2r.road2ring.modules.common.Static.BASE_URL;

import com.r2r.road2ring.modules.accessory.Accessory;
import com.r2r.road2ring.modules.accessory.AccessoryRepository;
import com.r2r.road2ring.modules.motor.Motor;
import com.r2r.road2ring.modules.transaction.Transaction;
import com.r2r.road2ring.modules.transaction.TransactionRepository;
import com.r2r.road2ring.modules.trip.TripPrice;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailClient {

  private JavaMailSender mailSender;

  AccessoryRepository accessoryRepository;

  TransactionRepository transactionRepository;

  @Autowired
  private TemplateEngine templateEngine;

  @Autowired
  public MailClient(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Autowired
  public void setAccessoryRepository(AccessoryRepository accessoryRepository){
    this.accessoryRepository = accessoryRepository;
  }

  @Autowired
  public void setTransactionRepository(TransactionRepository transactionRepository){
    this.transactionRepository = transactionRepository;
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

  public void sendCheckoutEmail(String recipient, String consumerName, Transaction transaction,
      Motor motor, List<Accessory> accessories, TripPrice tripPrice) {

    Transaction resultTransaction = transactionRepository.findOne(transaction.getTrip().getId());

    MimeMessagePreparator messagePreparator = mimeMessage -> {
      MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
      message.setTo(recipient);
      message.setFrom(new InternetAddress("bolalobintern@gmail.com"));

      message.setSubject("Congratulation for your transaction");
      Map model = new HashMap();
      model.put("username", consumerName);
      model.put("image", BASE_URL + resultTransaction.getTrip().getCoverLandscape());
      model.put("iconCover", BASE_URL + resultTransaction.getTrip().getIconCover());
      model.put("startDate", tripPrice.getStartTrip());
      model.put("endDate", tripPrice.getFinishTrip());
      model.put("meetingPoint", resultTransaction.getTrip().getMeetingPoint());
      model.put("motorName", motor.getTitle());
      model.put("motorImage", BASE_URL + motor.getPicture());
      model.put("expiredDate", transaction.getExpiredPaymentDate());
      model.put("tripTitle", resultTransaction.getTrip().getTitle());
      model.put("tripPrice", tripPrice.getPrice());
      model.put("invoiceId", transaction.getCode().toUpperCase());
      model.put("expiredPaymentDate", transaction.getExpiredPaymentDate());

      for(int i = 0; i < accessories.size(); i++) {
        Accessory accessory = accessoryRepository.findOne(accessories.get(i).getId());
        if(accessory.getAccessoryCategory().getTitle().toLowerCase().equalsIgnoreCase("helm")){
          model.put("accossoriesTitle", accessory.getTitle());
          model.put("accossoriesImage", BASE_URL + accessory.getPicture());
          model.put("accessoriesSize", accessories.get(0).getSize());
        }
      }
      Context context = new Context();
      context.setVariables(model);
      String html = templateEngine.process("admin/email/checkout", context);

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

  public void sendRegistrationEmail(String recipient, String consumerName, String codeVerify) {

    MimeMessagePreparator messagePreparator = mimeMessage -> {
      MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
      message.setTo(recipient);
      message.setFrom(new InternetAddress("bolalobintern@gmail.com"));
      message.setSubject("Welcome to Road2Ring");

      Map model = new HashMap();
      model.put("consumerName", consumerName);

      /*CODE VERIFYNYA HARUS DIUBAH LINK*/
      model.put("codeVerify", codeVerify);

      Context context = new Context();
      context.setVariables(model);
      String html = templateEngine.process("admin/email/register", context);

      message.setText(html, true);
    };

    mailSender.send(messagePreparator);
  }
}
