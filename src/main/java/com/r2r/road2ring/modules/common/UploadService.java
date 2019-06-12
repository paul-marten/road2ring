package com.r2r.road2ring.modules.common;

import static com.r2r.road2ring.modules.common.Static.IMAGE_ASSETS;
import static com.r2r.road2ring.modules.common.Static.SIZE_100KB;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Date;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

  R2rTools r2rTools;

  @Value("${r2r.upload.path}")
  String imagePath;

  @Autowired
  public void setR2rTools(R2rTools r2rTools){
    this.r2rTools = r2rTools;
  }

  @Autowired
  private HttpServletRequest request;

  public String uploadImagePicture(MultipartFile file, String typeImage)
      throws IOException, FileSizeLimitExceededException {
    String linkUrl;
    String type = file.getContentType().split("/")[1];
    Long date = new Date().getTime();

    byte[] bytes;

    if (file.getSize() > SIZE_100KB) {
      throw new FileSizeLimitExceededException("File is too Big", file.getSize(), SIZE_100KB);
    }
    linkUrl = date + r2rTools.generateRandomCode(8) + "." + type;

    String pathtoUploads =  request.getServletContext().getRealPath("/uploads/");

    String pathImageWebApp = pathtoUploads + linkUrl;
    File picture = new File(pathImageWebApp);
    file.transferTo(picture);

    this.uploadImagePicture(linkUrl, 600,400);
    this.deleteBaseImage(pathImageWebApp);

    return linkUrl;

  }

  private void deleteBaseImage(String name){
    File file = new File(name);
    file.delete();
  }

  private void uploadImagePicture(String name, Integer imgWitdh, Integer imgHeight) throws IOException {
    String pathtoUploads =  request.getServletContext().getRealPath("/uploads/");

    File inputFile = new File(pathtoUploads + name);
    BufferedImage inputImage = ImageIO.read(inputFile);

    String outputImagePath = IMAGE_ASSETS + name;

    // creates output image
    BufferedImage outputImage = new BufferedImage(imgWitdh,
        imgHeight, inputImage.getType());

    // scales the input image to the output image
    Graphics2D g2d = outputImage.createGraphics();
    g2d.drawImage(inputImage, 0, 0, imgWitdh , imgHeight, null);
    g2d.dispose();

    // extracts extension of output file
    String formatName = outputImagePath.substring(outputImagePath
        .lastIndexOf(".") + 1);

    // writes to output file
    File editedFile = new File(outputImagePath);
//    editedFile.setExecutable(true,false); 
//    editedFile.setReadable(true,false); 
//    editedFile.setWritable(true,false);
    ImageIO.write(outputImage, formatName, editedFile);
    if (editedFile.exists()) {
//	editedFile.setExecutable(true,false);
        editedFile.setReadable(true,false);
//        editedFile.setWritable(true,false);
    }
//    File outFile = new File(outputImagePath);
//    ImageIO.write(outputImage, formatName, outFile);
//    Set<PosixFilePermission> ownerWritable = PosixFilePermissions.fromString("rw-r--r--");
//    Files.setPosixFilePermissions(outFile.toPath(),ownerWritable);
  }

  public String uploadIconPicture(MultipartFile file, String typeImage)
      throws IOException, FileSizeLimitExceededException {
    String linkUrl;
    String type = file.getContentType().split("/")[1];
    Long date = new Date().getTime();

    byte[] bytes;

    if (file.getSize() > SIZE_100KB) {
      throw new FileSizeLimitExceededException("File is too Big", file.getSize(), SIZE_100KB);
    }
    linkUrl = date + r2rTools.generateRandomCode(8)+"."+type;

    String pathtoUploads =  request.getServletContext().getRealPath("/uploads/");

    String pathImageWebApp = pathtoUploads + linkUrl;

    File picture = new File(pathImageWebApp);
//    bytes = file.getBytes();
//
//    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(picture));
//    stream.write(bytes);
//    stream.close();
    file.transferTo(picture);

    this.uploadIconPicture(linkUrl);
    this.deleteBaseImage(pathImageWebApp);
    return linkUrl;
  }

  private void uploadIconPicture(String name) throws IOException {
    String pathtoUploads =  request.getServletContext().getRealPath("/uploads/");

    File inputFile = new File(pathtoUploads + name);
    BufferedImage inputImage = ImageIO.read(inputFile);
    String outputImagePath = IMAGE_ASSETS +name;

    // creates output image
    BufferedImage outputImage = new BufferedImage(inputImage.getWidth(),
        inputImage.getHeight(), inputImage.getType());

    // scales the input image to the output image
    Graphics2D g2d = outputImage.createGraphics();
    g2d.drawImage(inputImage, 0, 0, inputImage.getWidth() , inputImage.getHeight(), null);
    g2d.dispose();

    // extracts extension of output file
    String formatName = outputImagePath.substring(outputImagePath
        .lastIndexOf(".") + 1);

    // writes to output file
//    ImageIO.write(outputImage, formatName, new File(outputImagePath));

      File editedFile = new File(outputImagePath);
    ImageIO.write(outputImage, formatName, editedFile);
    if (editedFile.exists()) {
        editedFile.setReadable(true,false);
    }

//    File outFile = new File(outputImagePath);
//    ImageIO.write(outputImage, formatName, outFile);
//    Set<PosixFilePermission> ownerWritable = PosixFilePermissions.fromString("rw-r--r--");
//    Files.setPosixFilePermissions(outFile.toPath(),ownerWritable);
  }

}
