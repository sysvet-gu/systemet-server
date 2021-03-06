package se.itu.systemet.storage;

import java.io.*; // IOException, BufferedReader
import java.nio.charset.StandardCharsets;
import java.nio.file.*; //Files, Paths;
import java.util.ArrayList;
import java.util.List;

import se.itu.systemet.domain.Product;

public class CSVHelper {
  // How can the servlet find this?
  // Winstone is started from the directory above webroot
  // (in our case)
  private static String fileName = "webroot/WEB-INF/db/products.csv";
  public static List<Product> productsFromCSV() {
    List<Product> products = new ArrayList<>();
    try {
      BufferedReader reader =
        Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8);
      String line = null;
      while ((line = reader.readLine()) != null) {
        String name = null;
        String productGroup = null;
        String type = "";
        int nr = 0;
        int volume = 0;
        double alcohol = 0.0;
        double price = 0.0;
        String fields[] = line.split(",");
        try {
          name = fields[0];
          price = Double.parseDouble(fields[1]);
          alcohol = Double.parseDouble(fields[2]);
          volume = Integer.parseInt(fields[3]);
          nr = Integer.parseInt(fields[4]);
          productGroup = fields[5];
          if (fields.length == 7) {
            type = fields[6];
          }
        } catch (NumberFormatException nfe) {
          System.err.println("Parse error: " + nfe.getMessage());
          continue; // skip this product after reporting to system.err
        }
        Product product = new Product.Builder()
          .name(name)
          .price(price)
          .alcohol(alcohol)
          .volume(volume)
          .nr(nr)
          .productGroup(productGroup)
          .type(type)
          .build();
        products.add(product);
      }
    } catch (IOException ioe) {
      System.out.println("Error opening file: " + ioe.getMessage());
    }
    return products;
  }
}
