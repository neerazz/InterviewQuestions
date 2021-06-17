import java.util.*;
import java.io.*;

/**
 * Created on:  Jun 16, 2021
 * Ref:
 */

public class FeaturedProducts {
    public static void main(String[] args) {
        String popularProduct =  featuredProduct(List.of("redShirt", "greenPants", "redShirt", "orangeShoes", "blackPants", "blackPants"));
        System.out.println(popularProduct);
    }

    public static String featuredProduct(List<String> products) {
        Map<String, Integer> productSoldCountMap = new HashMap<>();
        //loop through the list and populate a map with the product name as the key and count as value -> (increment count if value match as we loop through the list)
        for (String product : products) {
//            Integer currentProductCount = productSoldCountMap.get(product);
//            if (currentProductCount == null) {
//                productSoldCountMap.put(product, 1);
//            } else {
//                int updatedProductCount = productSoldCountMap.get(product) + 1;
//                productSoldCountMap.put(product, updatedProductCount);
//            }

            productSoldCountMap.computeIfPresent(product , (k,v) -> ++v );
            productSoldCountMap.putIfAbsent(product, 1);
        }

//        System.out.println(productSoldCountMap);

        int highestCount = 0;
        List<String> highestProductList = new ArrayList<>();
        // Loop through map -> keep track of highestCount and mapKey to list ->
        for (Map.Entry<String, Integer> entry : productSoldCountMap.entrySet()) {
            String entryKey = entry.getKey();
            int entryValue = entry.getValue();

            // if next count == highestCount add next value to list
            if (entryValue == highestCount) {
                highestProductList.add(entryKey);
            } else if (entryValue > highestCount) {
                // else if greater, update highestCount, remove everything in list and add new key with highest value
                highestCount = entryValue;
                highestProductList.clear();
                highestProductList.add(entryKey);
            }
        }

        highestProductList.sort(Comparator.naturalOrder());
        return highestProductList.get(highestProductList.size()-1);
    }
}
