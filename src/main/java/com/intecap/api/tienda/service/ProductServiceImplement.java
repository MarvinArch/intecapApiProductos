package com.intecap.api.tienda.service;

import com.intecap.api.tienda.model.Dao.IProductDao;
import com.intecap.api.tienda.model.ProductModel;
import com.intecap.api.tienda.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@Service
public class ProductServiceImplement implements IProductService{
    private static final Logger log = Logger.getLogger(ProductServiceImplement.class.getName());

    @Autowired
    private IProductDao iProductDao;


    @Override
    @Transactional(readOnly=true)
    public ResponseEntity<ProductResponseRest> searchProduct() {
        ProductResponseRest response = new ProductResponseRest();
        log.info("Funcion Buscar todos los Productos");
        try{
            List<ProductModel> productModels = (List<ProductModel>)(iProductDao.findAll());
            response.getProductResponse().setProductModels(productModels);
            response.setMetadata("OK", "200", "Productos Encontrados");
        }catch (Exception e){
            log.severe("Error al Consultar Base de datos");
            e.getStackTrace();
            response.setMetadata("Error DB", "500", "Fallo consulta DB");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponseRest> searchProductbyId(Long id) {
        log.info("Funcion buscar productor por id");
        ProductResponseRest response = new ProductResponseRest();
        try{
            List<ProductModel>productModels= new ArrayList<>();
            Optional<ProductModel> productId = iProductDao.findById(id);
            if (productId.isPresent()){
                productModels.add(productId.get());
                response.getProductResponse().setProductModels(productModels);
                response.setMetadata("OK", "200", "Producto Encontrado");
            }else{
                log.severe("El producto no fue encontrado");
                response.setMetadata("Error", "500", "Producto no Encontrado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            log.severe("Error al Consultar la base de datos");
            response.setMetadata("Error", "500", "Error al consultar la DB");
        }

        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponseRest> crear(ProductModel productModel) {
        log.info("Funcion Guardar Nuevo");
        ProductResponseRest response = new ProductResponseRest();

        try{
            ProductModel productsave = iProductDao.save(productModel);
            if (productsave!=null){
                List<ProductModel> productModels = new ArrayList<>();
                productModels.add(productsave);
                response.getProductResponse().setProductModels(productModels);
                response.setMetadata("Ok", "200", "Producto Guardado con Exito");
            }else{
                log.severe("Ocurrio un error al guardar Producto");
                response.setMetadata("Error", "500", "Error al Guardar Producto");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            log.severe("Error de Comunicacion DB");
            response.setMetadata("Error", "500", "Error de comunicacion DB");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponseRest> actualizar(Long id, ProductModel productModel) {
        log.info("Funcion Actualizar Producto");
        ProductResponseRest response = new ProductResponseRest();
        List<ProductModel> productModels = new ArrayList<>();
        try{
            Optional<ProductModel> productId = iProductDao.findById(id);
            if (productId.isPresent()){
                productId.get().setNombre(productModel.getNombre());
                productId.get().setPrecio(productModel.getPrecio());
                if (productModel.getMakerModel()!=null){
                    productId.get().setMakerModel(productModel.getMakerModel());
                }
                iProductDao.save(productId.get());
                productModels.add(productId.get());
                response.getProductResponse().setProductModels(productModels);
                response.setMetadata("OK", "200", "Producto Actualizado");
            }else{
                log.severe("Error al Actualizar el Producto");
                response.setMetadata("Error", "500", "Error al Actualizar el Producto");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            log.severe("Error de Comunicaion en la Basse de datos");
            response.setMetadata("Error", "500", "Eroro de comunicacion DB");
            e.getStackTrace();
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponseRest> eliminar(Long id) {
        log.info("Funcion Eliminar");
        ProductResponseRest response = new ProductResponseRest();
        try{
            Optional<ProductModel> productfind=iProductDao.findById(id);
            if (productfind.isPresent()){
                iProductDao.delete(productfind.get());
                response.setMetadata("OK", "200", "Producto Eliminado");
            }else{
                log.severe("Error Producto no Encontrado");
                response.setMetadata("Error", "500", "No se Encontro el Producto");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            log.severe("Erro al Conectar con DB");
            response.setMetadata("Error", "500", "Erro de Conexion DB");
            e.getStackTrace();
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
