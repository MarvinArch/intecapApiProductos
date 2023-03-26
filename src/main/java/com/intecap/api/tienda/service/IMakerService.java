package com.intecap.api.tienda.service;

import com.intecap.api.tienda.model.MakerModel;
import com.intecap.api.tienda.response.MakerResponseRest;
import org.springframework.http.ResponseEntity;

public interface IMakerService {

    public ResponseEntity<MakerResponseRest> findMaker(); //metodo para buscar todos los libros de la base de datos

    //buscar por id
    public ResponseEntity<MakerResponseRest> findMakerById(Long id); //metodo para buscar un libro por su id

    //guardar un libro
    public ResponseEntity<MakerResponseRest> crear(MakerModel makerModel);

    //actualizar un libro
    public ResponseEntity<MakerResponseRest> actualizar(MakerModel makerModel, Long id);

    //eliminar un libro
    public ResponseEntity<MakerResponseRest> eliminar(Long id);

}
