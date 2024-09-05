package com.example.prueba.mapper;

import com.example.prueba.dto.ClienteDto;
import com.example.prueba.dto.ClienteDtoResponse;
import com.example.prueba.dto.ClienteDtoResponseData;
import com.example.prueba.entity.ClienteEntity;

public class ClienteMapper {

    public static ClienteEntity clienteDtoToEntity(ClienteDto clienteDto){
        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setApellido(clienteDto.getApellido());
        clienteEntity.setEmail(clienteDto.getEmail());
        clienteEntity.setFechaNacimiento(clienteDto.getFechaNacimiento());
        clienteEntity.setNumeroIdentificacion(clienteDto.getNumeroIdentificacion());
        clienteEntity.setTipoIdentificacion(clienteDto.getTipoIdentificacion());
        clienteEntity.setNombre(clienteDto.getNombre());

        return  clienteEntity;
    }



    public static ClienteDtoResponse clienteEntityToClienClienteDtoResponse(String message, ClienteEntity cliente){
        return ClienteDtoResponse.
                builder()
                .code("200")
                .message(message)
                .data(
                        ClienteDtoResponseData
                                .builder()
                                .nombre(cliente.getNombre())
                                .apellido(cliente.getApellido())
                                .numeroIdentificacion(cliente.getNumeroIdentificacion())
                                .email(cliente.getEmail())
                                .build()
                )
                .build();
    }






}
