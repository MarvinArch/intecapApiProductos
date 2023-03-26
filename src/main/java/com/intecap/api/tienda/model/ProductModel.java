package com.intecap.api.tienda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity()
@Table(name = "Product")
public class ProductModel implements Serializable {

    private static final Long serialVersionUID = 1L; //identificador unico de la clase autogenerado

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nombre;
    private double precio;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // // ignora los atributos de la clase categoria que no se van a utilizar en la clase libro

    private MakerModel makerModel;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public MakerModel getMakerModel() {
        return makerModel;
    }

    public void setMakerModel(MakerModel makerModel) {
        this.makerModel = makerModel;
    }
}
