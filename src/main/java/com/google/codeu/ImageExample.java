package com.google.codeu;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.codeu.data.Message;
import com.google.protobuf.ByteString;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *Diagnostic tool to check if ML analysis is working.
 */
public class ImageExample {
  /**
  * Reads image, assigns labels to it.
  */
  public static void main(String[] args) throws IOException {
    String filePath = "/path/to/file.jpg";
    ByteString imageBytes = ByteString.readFrom(new FileInputStream(filePath));
    Image image = Image.newBuilder().setContent(imageBytes).build();

    Feature feature = Feature.newBuilder().setType(Type.LABEL_DETECTION).build();
    AnnotateImageRequest request =
        AnnotateImageRequest.newBuilder().addFeatures(feature).setImage(image).build();
    List<AnnotateImageRequest> requests = new ArrayList<>();
    requests.add(request);

    ImageAnnotatorClient client = ImageAnnotatorClient.create();
    BatchAnnotateImagesResponse batchResponse = client.batchAnnotateImages(requests);
    List<AnnotateImageResponse> imageResponses = batchResponse.getResponsesList();
    AnnotateImageResponse imageResponse = imageResponses.get(0);

    if (imageResponse.hasError()) {
      System.out.println("Error: " + imageResponse.getError().getMessage());
    }

    for (EntityAnnotation annotation : imageResponse.getLabelAnnotationsList()) {
      System.out.println(annotation.getDescription() + ": " + annotation.getScore());
    }

    client.close();
  }
}