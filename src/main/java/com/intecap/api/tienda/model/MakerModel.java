package com.intecap.api.tienda.model;

import javax.persistence.*;

@Entity
@Table(name = "maker_Product")
public class MakerModel {
    private static final long serialVersionUID = 1L; // identificador unico de la clase autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_Fabricante")
    private Long codigoFabricante;
    @Column(name = "nombre_Fabricante")
    private String nombreFabricante;

    public Long getCodigoFabricante() {
        return codigoFabricante;
    }

    public void setCodigoFabricante(Long codigoFabricante) {
        this.codigoFabricante = codigoFabricante;
    }

    public String getNombreFabricante() {
        return nombreFabricante;
    }

    public void setNombreFabricante(String nombreFabricante) {
        this.nombreFabricante = nombreFabricante;
    }
}
