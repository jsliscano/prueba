package com.example.prueba.controller;


import com.example.prueba.dto.ClienteDto;
import com.example.prueba.dto.ClienteDtoResponse;
import com.example.prueba.entity.ClienteEntity;
import com.example.prueba.exception.ClienteNotFoundException;
import com.example.prueba.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/clientes")

public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/findAll")
    public List<ClienteEntity> findAll() {
        return clienteService.findAll();
    }

    @GetMapping("/findById/{id}")
    ClienteDtoResponse findById(@PathVariable Long id) throws ClienteNotFoundException {
        return clienteService.findClienteById(id);
    }

    @GetMapping("/findNombre/{nombre}")
    ClienteDtoResponse findByClienteNombre(@PathVariable String nombre) throws ClienteNotFoundException {
    return clienteService.findByClienteNombre(nombre);

    }

    @PostMapping("/saveCliente")
    public Object save (@RequestBody ClienteDto clienteDto){
       return clienteService.save(clienteDto);
    }


    @PutMapping("/update/{id}")
    public ClienteEntity update(@PathVariable Long id, @RequestBody ClienteEntity clienteEntity) throws ClienteNotFoundException {
        return clienteService.update(id, clienteEntity);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            clienteService.delete(id);
            return ResponseEntity.ok("Successfully deleted");
        } catch (ClienteNotFoundException e) {
            // Return a 404 Not Found status with the error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // Handle other unexpected exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}









