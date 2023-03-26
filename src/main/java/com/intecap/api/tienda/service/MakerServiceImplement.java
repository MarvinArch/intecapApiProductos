package com.intecap.api.tienda.service;

import com.intecap.api.tienda.model.Dao.IMakerModelDao;
import com.intecap.api.tienda.model.MakerModel;
import com.intecap.api.tienda.response.MakerResponse;
import com.intecap.api.tienda.response.MakerResponseRest;
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
public class MakerServiceImplement implements IMakerService {
    private static final Logger log = Logger.getLogger(MakerServiceImplement.class.getName());
    @Autowired
    private IMakerModelDao iMakerModelDao;
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<MakerResponseRest> findMaker() {
        log.info("Inicio metodo findMaker()");
        MakerResponseRest  response = new MakerResponseRest();
        try {
            List<MakerModel> makerModels = (List<MakerModel>) (iMakerModelDao.findAll());
            response.getMakerResponse().setMakerModels(makerModels);
            response.setMetadata("Ok", "200", "Lista Fabricantes");
        }catch (Exception e) {
            log.info("Error al consultar las Fabricantes " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Error", "500", "Fallo de Conexion");
            return new ResponseEntity<MakerResponseRest>(response,HttpStatus.OK);
        }
        return new ResponseEntity<MakerResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<MakerResponseRest> findMakerById(Long id) {
        log.info("Inicio metodo findMakerById()");
        MakerResponseRest  response = new MakerResponseRest();
        List<MakerModel> makerModels = new ArrayList<>();

        try {
            Optional<MakerModel> find= iMakerModelDao.findById(id);
            if (find.isPresent()){
                makerModels.add(find.get());
                response.getMakerResponse().setMakerModels(makerModels);
                response.setMetadata("Ok", "200", "Fabricante Encontrado");
            }
        }catch (Exception e){
            log.info("Error al consultar el Fabricante " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Error ", "500", "Error al consultar el Fabricante");
            return new ResponseEntity<MakerResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // error 500
        }
        return new ResponseEntity<MakerResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<MakerResponseRest> crear(MakerModel makerModel) {
        log.info("Inicio metodo crear()");
        MakerResponseRest response= new MakerResponseRest();
        List<MakerModel> makerModels = new ArrayList<>();
        try {
            MakerModel makersave = iMakerModelDao.save(makerModel);
            if (makersave != null ){
                makerModels.add(makersave);
                response.getMakerResponse().setMakerModels(makerModels);
                response.setMetadata("OK", "200", "Fabricante Guardado con Exito");
            }else {
                log.severe("Error al guardar el Fabricante");
                response.setMetadata("Error al guardar el Fbricante", "500", "Error al guardar el libro");
                return new ResponseEntity<MakerResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            log.severe("Error al guardar el Fabricante");
            response.setMetadata("Error al guardar el Fbricante", "500", "Error al guardar el libro");
            e.getStackTrace();
            return new ResponseEntity<MakerResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<MakerResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MakerResponseRest> actualizar(MakerModel makerModel, Long id) {
        MakerResponseRest response = new MakerResponseRest();
        log.info("Inicio metodo crear()");
        List<MakerModel> makerModels = new ArrayList<>();

        try {
            Optional<MakerModel> makerfindById= iMakerModelDao.findById(id);
            if (makerfindById.isPresent()){
                makerfindById.get().setNombreFabricante(makerModel.getNombreFabricante());

                MakerModel makerUpdate = iMakerModelDao.save(makerfindById.get());
                if (makerUpdate!=null){
                    makerModels.add(makerUpdate);
                    response.getMakerResponse().setMakerModels(makerModels);
                }else {
                    log.info("Error al Actualizar el Fabricante");
                    response.setMetadata("Error", "500", "Error al Actualizar fabricante");
                    return new ResponseEntity<MakerResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }catch (Exception e){
            log.info("Error al Actualizar el Fabricante");
            e.getStackTrace();
            response.setMetadata("Error", "500", "Error al Actualizar fabricante");
            return new ResponseEntity<MakerResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setMetadata("Ok","200", "Fabricante Actualizado");
        return new ResponseEntity<MakerResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MakerResponseRest> eliminar(Long id) {
        MakerResponseRest response = new MakerResponseRest();
        try{
         Optional<MakerModel> makerFind = iMakerModelDao.findById(id);
         if (makerFind.isPresent()){
             iMakerModelDao.delete(makerFind.get());
             response.setMetadata("OK", "200", "Fabricante Elimnado");
         }else{
             log.severe("Fabricante no encontrado");
             response.setMetadata("error", "500", "Error Fabricante no Encontrado");
             return new ResponseEntity<MakerResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
         }
        }catch (Exception e){
            log.severe("erro al eliminas Fabricante ");
            e.getStackTrace();
            response.setMetadata("error", "500", "Error eliminar Fabricante");
            return new ResponseEntity<MakerResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<MakerResponseRest>(response, HttpStatus.OK);
    }
}
