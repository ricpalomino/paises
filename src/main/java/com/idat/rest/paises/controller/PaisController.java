package com.idat.rest.paises.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.rest.paises.model.Pais;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api/paises")
public class PaisController {

    private final List<Pais> paises = new ArrayList<>();
    private final AtomicLong contador = new AtomicLong();

    public PaisController() {
        initData();
    }

    private void initData() {
        Pais peru = new Pais(contador.incrementAndGet(), "Peru", 33000);
        Pais mexico = new Pais(contador.incrementAndGet(), "Mexico", 133000);
        Pais chile = new Pais(contador.incrementAndGet(), "Chile", 10000);
        paises.add(peru);
        paises.add(mexico);
        paises.add(chile);
    }
    
    @GetMapping()
    public ResponseEntity<List<Pais>> listar() {
        return new ResponseEntity<>(paises, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Pais> obtener(@PathVariable long id) {
        Pais pais = paises.stream().filter(p -> p.getId().equals(id)).findAny().orElse(null);
        if(pais != null){
            return new ResponseEntity<>(pais, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Pais> registrar(@RequestBody Pais pais) {
        Pais paisNuevo = new Pais(contador.incrementAndGet(), pais.getNombre(), pais.getPoblacion());
        paises.add(paisNuevo);
        return new ResponseEntity<>(paisNuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pais> actualizar(@PathVariable long id, @RequestBody Pais p) {
        Pais paisActualizado = null;
        for(Pais pais: paises) {
            if(pais.getId() == id){
                pais.setNombre(p.getNombre());
                pais.setPoblacion(p.getPoblacion());
                paisActualizado = pais;
                break;
            }
        }
        return new ResponseEntity<>(paisActualizado, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    private ResponseEntity<Pais> eliminar(@PathVariable Long id) {
        Pais pais = paises.stream().filter(p -> p.getId().equals(id)).findAny().orElse(null);
        if(pais != null) {
            paises.remove(pais);
        }
        return new ResponseEntity<Pais>(HttpStatus.NO_CONTENT);
    }
    
    





}
