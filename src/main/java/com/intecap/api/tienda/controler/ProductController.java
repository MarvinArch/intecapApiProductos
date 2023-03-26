package com.intecap.api.tienda.controler;

import com.intecap.api.tienda.model.ProductModel;
import com.intecap.api.tienda.response.ProductResponse;
import com.intecap.api.tienda.response.ProductResponseRest;
import com.intecap.api.tienda.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    IProductService iProductService;

    @GetMapping("/productos")
    public ResponseEntity<ProductResponseRest> buscarProductos() {
        return iProductService.searchProduct();
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<ProductResponseRest> buscarProductosById(@PathVariable Long id) {
        return iProductService.searchProductbyId(id);
    }

    @PostMapping("/productos")
    public ResponseEntity<ProductResponseRest> guardarProducto(@RequestBody ProductModel productModel) {
        return iProductService.crear(productModel);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<ProductResponseRest> actualizarProducto(@RequestBody ProductModel productModel, @PathVariable Long id) {
        return iProductService.actualizar(id, productModel);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<ProductResponseRest> eliminarProducto(@PathVariable Long id){
        return iProductService.eliminar(id);
    }
}
