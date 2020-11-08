package mx.uady.sicei.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.uady.sicei.model.Equipo;
import mx.uady.sicei.model.request.EquipoRequest;
import mx.uady.sicei.service.EquipoService;

@RestController
@RequestMapping("/api")
public class EquipoRest {

    @Autowired
    private EquipoService equipoService;

    @GetMapping("/equipos")
    public ResponseEntity<List<Equipo>> getEquipos() {
        List<Equipo> equipos = equipoService.getEquipos();
        return ResponseEntity.ok(equipos);
    }

    @PostMapping("/equipos")
    public ResponseEntity<Equipo> postEquipo(@RequestBody @Valid EquipoRequest request) throws URISyntaxException {
        Equipo alumno = equipoService.createEquipo(request);
        return ResponseEntity
            .created(new URI("/equipos/" + alumno.getId()))
            .body(alumno);
    }

    @GetMapping("/equipos/{id}")
    public ResponseEntity<Equipo> getEquipo(@PathVariable Integer id){
        return ResponseEntity.ok().body(equipoService.getEquipo(id));
    }

    @PutMapping("/equipos/{id}")
    public ResponseEntity<Equipo> updateEquipo(@PathVariable Integer id, @Valid @RequestBody EquipoRequest request) {
        return ResponseEntity.ok().body(equipoService.updateEquipo(id, request));
    }

    @DeleteMapping("/equipos/{id}")
    public ResponseEntity<Equipo> deleteEquipo(@PathVariable Integer id){
        return ResponseEntity.ok().body(equipoService.deleteEquipo(id));
    }
    
}
