package com.intecap.api.tienda.response;

import com.intecap.api.tienda.model.ProductModel;

import java.util.List;

public class ProductResponse {
    private List<ProductModel> productModels;

    public List<ProductModel> getProductModels() {
        return productModels;
    }

    public void setProductModels(List<ProductModel> productModels) {
        this.productModels = productModels;
    }
}
