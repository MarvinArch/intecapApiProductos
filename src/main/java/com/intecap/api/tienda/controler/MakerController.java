package com.intecap.api.tienda.controler;

import com.intecap.api.tienda.model.MakerModel;
import com.intecap.api.tienda.response.MakerResponseRest;
import com.intecap.api.tienda.service.IMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MakerController {
    @Autowired
    IMakerService iMakerService;

    @GetMapping("/fabricantes")
    public ResponseEntity<MakerResponseRest> buscarFabricantes(){
        return iMakerService.findMaker();
    }

    @GetMapping("/fabricantes/{id}")
    public ResponseEntity<MakerResponseRest> buscarFabricanteById(@PathVariable Long id){
        return iMakerService.findMakerById(id);
    }

    @PostMapping("/fabricantes")
    public ResponseEntity<MakerResponseRest> crearFabricante(@RequestBody MakerModel makerModel){
        return iMakerService.crear(makerModel);
    }

    @PutMapping("/fabricantes/{id}")
    public ResponseEntity<MakerResponseRest> actualizarFabricante (@RequestBody MakerModel makerModel, @PathVariable Long id){
        return iMakerService.actualizar(makerModel, id);
    }

    @DeleteMapping("fabricantes/{id}")
    public ResponseEntity<MakerResponseRest> EliminarFabricante (@PathVariable Long id){
        return iMakerService.eliminar(id);
    }

}
