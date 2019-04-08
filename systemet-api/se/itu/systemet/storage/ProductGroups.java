package se.itu.systemet.storage;

import java.util.Map;
import java.util.HashMap;

import se.itu.systemet.domain.Product;

public class ProductGroups {

  private static Map<String, Integer> productGroups = new HashMap<>();
  private static int maxId = 0;

  /**
   * Returns a unique ID for the given product group.
   * @param group The product group to get the ID for
   * @return The uniq ID for the product group given
   */
  public static Integer idFromProductGroup(String group) {
    if (productGroups.containsKey(group)) {
      return productGroups.get(group);
    } else {
      maxId++;
      productGroups.put(group, maxId);
      return maxId;
    }
  }
}
