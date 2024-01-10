package com.examen_ci.gestion_produit;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class ProduitService {

    private static final String JSON_FILE_PATH = "products.json";
    private static final Gson gson = new Gson();

    public static void create(Produit produit) {
        List<Produit> productList = read();
        if (!valid(produit.getPrix(), produit.getQuantité())) {
            throw new IllegalArgumentException("invalid");
        } else if (exist(produit.getId(), produit.getNom(), productList)) {
            throw new IllegalArgumentException("exist");
        } else {
            productList.add(produit);
            saveToJSON(productList);
        }
    }

    public static List<Produit> read() {
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Produit>>() {}.getType();
            List<Produit> productList = gson.fromJson(reader, listType);
            if (productList == null) {
                return new ArrayList<>();
            }
            return productList;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }


    public static boolean exist(Long productId, String productName, List<Produit> productList){
        for (Produit product : productList) {
            if (product.getId() == productId || product.getNom().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean valid(double productPrice, int productQuantity){
        if (productPrice > 0 && productQuantity > 0) {
            return true;
        }
        return false;
    }

    private static void saveToJSON(List<Produit> productList) {
        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            gson.toJson(productList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}