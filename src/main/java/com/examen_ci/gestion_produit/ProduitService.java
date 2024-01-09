package com.examen_ci.gestion_produit;

import java.util.List;

public class ProduitService {

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

}