package com.intecap.api.tienda.model.Dao;

import com.intecap.api.tienda.model.ProductModel;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.GeneratedValue;

public interface IProductDao extends CrudRepository<ProductModel, Long> {
}
