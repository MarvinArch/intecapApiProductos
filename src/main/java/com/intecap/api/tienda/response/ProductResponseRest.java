package com.intecap.api.tienda.response;

public class ProductResponseRest extends ResponseRest{
    private ProductResponse productResponse = new ProductResponse();

    public ProductResponse getProductResponse() {
        return productResponse;
    }

    public void setProductResponse(ProductResponse productResponse) {
        this.productResponse = productResponse;
    }
}
