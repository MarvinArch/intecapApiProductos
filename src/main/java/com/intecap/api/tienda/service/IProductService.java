package com.intecap.api.tienda.service;

import com.intecap.api.tienda.model.Dao.IProductDao;
import com.intecap.api.tienda.model.ProductModel;
import com.intecap.api.tienda.response.ProductResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    public ResponseEntity<ProductResponseRest>  searchProduct();
    public ResponseEntity<ProductResponseRest> searchProductbyId(Long id);

    public ResponseEntity<ProductResponseRest> crear(ProductModel productModel);

    public ResponseEntity<ProductResponseRest> actualizar(Long id, ProductModel productModel);

    public ResponseEntity<ProductResponseRest> eliminar(Long id);
}
