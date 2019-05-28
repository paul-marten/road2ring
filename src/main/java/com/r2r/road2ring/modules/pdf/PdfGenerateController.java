package com.r2r.road2ring.modules.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.r2r.road2ring.modules.mail.MailClient;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
@RequestMapping(value = "/api/pdf")
public class PdfGenerateController {

  PdfService pdfService;

  MailClient mailClient;

  @Autowired
  public void setPdfService(PdfService pdfService){
    this.pdfService = pdfService;
  }

  @Autowired
  public void setMailClient(MailClient mailClient){
    this.mailClient = mailClient;
  }

//  @GetMapping(value = "/test/{transactionCodeId}", produces = MediaType.APPLICATION_PDF_VALUE)
//  public ResponseEntity<InputStreamResource> generatedPdf (
//      @PathVariable (value = "transactionCodeId") String transactionCodeId) throws IOException {
//    ByteArrayInputStream bis = new ByteArrayInputStream(pdfService.generatePdf(transactionCodeId).toByteArray());
//    HttpHeaders headers = new HttpHeaders();
//    headers.add("Content-Disposition", "inline; filename=road2ring-transaction.pdf");
//
//    try{
////      mailClient.sendEmail();
//      mailClient.prepareAndSend("aa","a");
//    } catch (MailException e){
//      //catch error
//      System.out.println(e);
//    } catch (MessagingException e) {
//      e.printStackTrace();
//    }
//
//    return ResponseEntity
//        .ok()
//        .headers(headers)
//        .contentType(MediaType.APPLICATION_PDF)
//        .body(new InputStreamResource(bis));
//  }

  @GetMapping(value = "/test/{transactionCodeId}", produces = MediaType.APPLICATION_PDF_VALUE)
  public void generatedPdf (
      @PathVariable (value = "transactionCodeId") String transactionCodeId) {
    try{
      pdfService.createPdf();
    } catch (Exception e){
      //catch error
      System.out.println(e);
    }
  }
}
