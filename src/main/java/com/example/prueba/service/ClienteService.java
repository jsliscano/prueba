package com.example.prueba.service;


import com.example.prueba.dto.ClienteDto;
import com.example.prueba.dto.ClienteDtoResponse;
import com.example.prueba.entity.ClienteEntity;
import com.example.prueba.exception.ClienteNotFoundException;

import java.util.List;

public interface ClienteService {


    List<ClienteEntity> findAll();

    Object save(ClienteDto clienteDto);

    ClienteEntity update(Long id, ClienteEntity clienteEntity) throws ClienteNotFoundException;


    void delete(Long id) throws ClienteNotFoundException;

    ClienteDtoResponse findByClienteNombre(String nombre) throws ClienteNotFoundException;

    ClienteDtoResponse findClienteById (Long id) throws ClienteNotFoundException;

}
