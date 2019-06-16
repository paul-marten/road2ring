package com.r2r.road2ring.modules.pdf;

import static com.r2r.road2ring.modules.common.Static.BASE_URL;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.r2r.road2ring.modules.transaction.Transaction;
import com.r2r.road2ring.modules.transaction.TransactionDetail;
import com.r2r.road2ring.modules.transaction.TransactionService;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Component
public class PdfService {

  TransactionService transactionService;

  @Autowired
  private TemplateEngine templateEngine;

  @Autowired
  public void setTransactionService(TransactionService transactionService){
    this.transactionService = transactionService;
  }

  public ByteArrayOutputStream generatePdf(String transactionCodeId) {

    Transaction transaction = transactionService.getTransactionPdf(transactionCodeId);
    List<TransactionDetail> list = transactionService.getListTransactionDetailPdf(transaction.getId());


    Document document = new Document();
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try
    {
      PdfWriter writer = PdfWriter.getInstance(document, out);
      document.open();
      document.addTitle("Invoice "+ transactionCodeId);

      Font titleFont = new Font(Font.FontFamily.HELVETICA, 15,
          Font.BOLD, new BaseColor(34,76,152));

      String imageUrl = "http://road2ring.com/img/assets/logo-pdf.png";

      PdfPTable table = new PdfPTable(2); // 2 columns.
      table.setWidthPercentage(100); //Width 100%
      table.setSpacingBefore(10f); //Space before table
      table.setSpacingAfter(10f);

      float[] columnWidths = {1f, 0.2f};
      table.setWidths(columnWidths);

      PdfPCell cell1 = new PdfPCell(new Paragraph(
         "INVOICE " + transaction.getCode().toUpperCase(),titleFont));
      cell1.setBorderColor(BaseColor.WHITE);
      cell1.setPaddingLeft(0);
      cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
      cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

      PdfPCell cell2 = new PdfPCell(
          Image.getInstance(new URL(imageUrl)), true
      );
      cell2.setBorderColor(BaseColor.WHITE);
      cell2.setPaddingLeft(0);
      cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
      cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

      table.addCell(cell1);
      table.addCell(cell2);
      table.getDefaultCell().setBorderColor(BaseColor.WHITE);
      document.add(table);


//      PdfPTable tableDescription = new PdfPTable(2); // 2 columns.
//      tableDescription.setWidthPercentage(100); //Width 100%
//      tableDescription.setSpacingBefore(10f); //Space before table
//      tableDescription.setSpacingAfter(10f);
//
//      float[] columnWidthsDescription = {0.1f, 1f};
//      table.setWidths(columnWidthsDescription);
//
//      Image image = Image.getInstance(new URL(BASE_URL + transaction.getTrip().getCoverLandscape()));
//      image.scaleToFit(100,100);
//
//      PdfPCell cell1Description = new PdfPCell(
//          image, true
//      );
//      cell1Description.setBorderColor(BaseColor.WHITE);
//      cell1Description.setPaddingLeft(0);
//      cell1Description.setHorizontalAlignment(Element.ALIGN_RIGHT);
//      cell1Description.setVerticalAlignment(Element.ALIGN_MIDDLE);
//
//      Phrase phrase = new Phrase();
//      phrase.add(
//          new Chunk("aaaaaaaaa",  new Font(FontFamily.HELVETICA, 15, Font.BOLD))
//      );
//      phrase.add( Chunk.NEWLINE );
//      phrase.add(new Chunk("bbbbbbbbb", new Font()));
//      phrase.add( Chunk.NEWLINE );
//      phrase.add( Chunk.NEWLINE );
//
//      PdfPCell cell2Description = new PdfPCell(phrase);
//      cell2Description.setBorderColor(BaseColor.WHITE);
//
//      tableDescription.addCell(cell1Description);
//      tableDescription.addCell(cell2Description);
//      tableDescription.getDefaultCell().setBorderColor(BaseColor.WHITE);
//      document.add(tableDescription);


      document.add(new Paragraph("DETAIL"));
//      document.add(new Phrase(Chunk.NEWLINE));

//    Pdf Create Detail
      PdfPTable tableDetail = new PdfPTable(2); // 2 columns.
      tableDetail.setWidthPercentage(100); //Width 100%
      tableDetail.setSpacingBefore(10f); //Space before table
      tableDetail.setSpacingAfter(10f);

      float[] columnWidthsDetail = {1f, 1f};
      tableDetail.setWidths(columnWidthsDetail);
      tableDetail.addCell(bindCellTripDetailPhrase(bindTripDetail(transaction.getTrip().getTitle(), "")));
      tableDetail.addCell(bindCellTripDetailText(transaction.getPrice().toString()));
      tableDetail.addCell(bindCellTripDetailPhrase(bindTripDetail("ADDITIONAL COST : MOTORCYCLE",list.get(1).getTitle())));
      tableDetail.addCell(bindCellTripDetailText(list.get(1).getPrice().toString()));
      tableDetail.addCell(bindCellTripDetailPhrase(bindTripDetail("ADDITIONAL COST : HELMET",list.get(0).getTitle())));
      tableDetail.addCell(bindCellTripDetailText(list.get(0).getPrice().toString()));
      tableDetail.addCell(bindCellTotalPricePhrase(bindTotalPrice("GRAND","TOTAL")));
      tableDetail.addCell(bindCellTripDetailText(transaction.getPrice().toString()));
      document.add(tableDetail);

//      PDF CALLING PERSON
      document.add(new Paragraph("if you want to ask something, please contact our costumer service via Whatsapp on +1723871278371"));

      document.add(new Phrase(new Chunk(Chunk.NEWLINE)));
      document.add(new Phrase(new Chunk(Chunk.NEWLINE)));

      document.add(new Paragraph("if you want to ask something, please contact our costumer service via Whatsapp on +1723871278371"));

      document.close();
      writer.close();
    } catch (DocumentException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return out;
  }

  public PdfPCell bindCellTripDetailPhrase(Phrase elements){
    PdfPCell result = new PdfPCell(elements);
    result.setBorderColor(BaseColor.WHITE);
    result.setPaddingLeft(0);
    result.setHorizontalAlignment(Element.ALIGN_LEFT);
    result.setVerticalAlignment(Element.ALIGN_MIDDLE);
    return result;
  }

  public PdfPCell bindCellTripDetailText(String elements){
    PdfPCell result = new PdfPCell(bindPrice(elements));
    result.setBorderColor(BaseColor.WHITE);
    result.setPaddingLeft(0);
    result.setHorizontalAlignment(Element.ALIGN_RIGHT);
    result.setVerticalAlignment(Element.ALIGN_MIDDLE);
    return result;
  }

  public Phrase bindPrice(String price){
    Phrase phrase = new Phrase();
    phrase.add(
        new Chunk(price,  new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD))
    );
    return phrase;
  }

  public Phrase bindTripDetail(String title, String description){
    Phrase phrase = new Phrase();
    phrase.add(
        new Chunk(title,  new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD))
    );
    phrase.add( Chunk.NEWLINE );
    phrase.add(new Chunk(description, new Font()));
    phrase.add( Chunk.NEWLINE );
    phrase.add( Chunk.NEWLINE );
    return phrase;
  }

  public PdfPCell bindCellTotalPricePhrase(Phrase elements){
    PdfPCell result = new PdfPCell(elements);
    result.setBorderColor(BaseColor.WHITE);
    result.setPaddingLeft(0);
    result.setHorizontalAlignment(Element.ALIGN_RIGHT);
    result.setVerticalAlignment(Element.ALIGN_MIDDLE);
    return result;
  }

  public Phrase bindTotalPrice(String title, String description){
    Phrase phrase = new Phrase();
    phrase.add(
        new Chunk(title,  new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD))
    );
    phrase.add( Chunk.NEWLINE );
    phrase.add(
        new Chunk(description,  new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD))
    );
    phrase.add( Chunk.NEWLINE );
    phrase.add( Chunk.NEWLINE );
    return phrase;
  }


  public void createPdf() throws Exception {
    Map model = new HashMap();
    model.put("message", "Naik motor");

    Context ctx = new Context();
    ctx.setVariables(model);

    String processedHtml = templateEngine.process("admin/pdf/invoice", ctx);
    FileOutputStream os = null;
    String fileName = UUID.randomUUID().toString();
    try {
      final File outputFile = File.createTempFile(fileName, ".pdf");
      os = new FileOutputStream(outputFile);

      ITextRenderer renderer = new ITextRenderer();
      renderer.setDocumentFromString(processedHtml);
      renderer.layout();
      renderer.createPDF(os, false);
      renderer.finishPDF();
      System.out.println("PDF created successfully");
    }
    finally {
      if (os != null) {
        try {
          os.close();
        } catch (IOException e) { /*ignore*/ }
      }
    }
  }

}
