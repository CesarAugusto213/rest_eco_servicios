package com.ecoservicios.rest_eco_servicios.controller;

import com.ecoservicios.rest_eco_servicios.models.Campus;
import com.ecoservicios.rest_eco_servicios.repository.CampusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/campus")
public class CampusController {

    @Autowired
    private CampusRepository campusRepository;

    @GetMapping
    ResponseEntity<Page<Campus>> listCampus(Pageable pageable){
        return ResponseEntity.ok(campusRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Campus> saveCampus(@Valid @RequestBody Campus campus) {
        Campus saveCampus = campusRepository.save(campus);
        URI path = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(campus.getId()).toUri();
        return ResponseEntity.created(path).body(saveCampus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campus> updateCampus(@PathVariable String id, @Valid @RequestBody Campus campus) {
        Optional<Campus> campusOptional= campusRepository.findById(id);

        if(!campusOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        campus.setId(campusOptional.get().getId());
        campusRepository.save(campus);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Campus> deleteCampusById(@PathVariable String id) {
        Optional<Campus> campusOptional = campusRepository.findById(id);

        if(!campusOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        campusRepository.delete(campusOptional.get());
        return ResponseEntity.ok(campusOptional.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campus> getCampusById(@PathVariable String id){
        Optional<Campus> campusOptional = campusRepository.findById(id);

        if(!campusOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(campusOptional.get());
    }

}
