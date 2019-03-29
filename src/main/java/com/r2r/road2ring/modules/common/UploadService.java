package com.r2r.road2ring.modules.common;

import static com.r2r.road2ring.modules.common.Static.IMAGE_ASSETS;
import static com.r2r.road2ring.modules.common.Static.SIZE_100KB;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

  public String generateRandomCode(int length) {
    String key = RandomStringUtils
        .random(length, "34i12q23lea0ug9ab0adji05r9lw8j91kyzd1r27icq3kdfadftp448dsaqwd75y785t".toCharArray());
    return key;
  }

  public String uploadImagePicture(MultipartFile file, String typeImage)
      throws IOException, FileSizeLimitExceededException {
    String linkUrl;
    Long date = new Date().getTime();

    byte[] bytes;

    if (file.getSize() > SIZE_100KB) {
      throw new FileSizeLimitExceededException("File is too Big", file.getSize(), SIZE_100KB);
    }
    linkUrl = date + this.generateRandomCode(8);
    File picture = new File(linkUrl + ".jpg");
    bytes = file.getBytes();

    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(picture));
    stream.write(bytes);
    stream.close();

    this.uploadImagePicture(linkUrl+".jpg", 600,400);

    return linkUrl;

  }

  private void uploadImagePicture(String name, Integer imgWitdh, Integer imgHeight) throws IOException {
    File inputFile = new File(name);
    BufferedImage inputImage = ImageIO.read(inputFile);
    String outputImagePath = IMAGE_ASSETS +name;

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
    ImageIO.write(outputImage, formatName, new File(outputImagePath));
  }

}
