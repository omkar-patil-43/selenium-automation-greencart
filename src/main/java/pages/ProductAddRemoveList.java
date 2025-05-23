package pages;

import java.util.ArrayList;
import java.util.List;

public class ProductAddRemoveList {

    static private List<String> prodList = new ArrayList<String>();
    static private List<String> removeprodList = new ArrayList<String>();

    static {
        // Products to be added to cart
        prodList.add("Apple iPhone 13 (Midnight, 128 GB)");
        prodList.add("SAMSUNG Galaxy M35 5G (Moonlight Blue, 128 GB)");
        prodList.add("Motorola G85 5G (Olive Green, 128 GB)");

        // Products to be removed from cart
        removeprodList.add("Apple iPhone 13 (Midnight, 128 GB)");
        removeprodList.add("Motorola G85 5G (Olive Green, 128 GB)");
        removeprodList.add("SAMSUNG Galaxy M35 5G (Moonlight Blue, 128 GB)");
    }

    public static List<String> getProdList() {
        return prodList;
    }

    public static List<String> getRemoveProdList() {
        return removeprodList;
    }
}
