package com.example.prueba.service;

import com.example.prueba.dto.ClienteDto;
import com.example.prueba.dto.ClienteDtoResponse;
import com.example.prueba.entity.ClienteEntity;
import com.example.prueba.entity.ProductoEntity;
import com.example.prueba.exception.ClienteNotFoundException;
import com.example.prueba.mapper.ClienteMapper;
import com.example.prueba.repository.ClienteRepository;
import com.example.prueba.repository.ProductoRepository;
import org.springframework.beans.factory.CannotLoadBeanClassException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import java.time.Period;


@Service
@Validated
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteEntity> findAll() {
        return clienteRepository.findAll();
    }


    @Override
    public Object save(ClienteDto clienteDto) {

        if (esMayorDeEdad(clienteDto.getFechaNacimiento())) {
            ClienteEntity objcetMapperEntity = ClienteMapper.clienteDtoToEntity(clienteDto);
            objcetMapperEntity.setFechaCreacion(LocalDateTime.now());
            objcetMapperEntity.setFechaModificacion(null);
            return clienteRepository.save(objcetMapperEntity);
        }

        return "No es mayor de edad";

    }

    private boolean esMayorDeEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
        }

        LocalDate hoy = LocalDate.now();
        Period edad = Period.between(fechaNacimiento, hoy);

        return edad.getYears() >= 18;
    }


    @Override
    public ClienteEntity update(Long id, ClienteEntity clienteDetails) throws ClienteNotFoundException {
        ClienteEntity existingCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente not found with id " + id));

        existingCliente.setTipoIdentificacion(clienteDetails.getTipoIdentificacion());
        existingCliente.setNumeroIdentificacion(clienteDetails.getNumeroIdentificacion());
        existingCliente.setNombre(clienteDetails.getNombre());
        existingCliente.setApellido(clienteDetails.getApellido());
        existingCliente.setEmail(clienteDetails.getEmail());
        existingCliente.setFechaNacimiento(clienteDetails.getFechaNacimiento());
        existingCliente.setFechaModificacion(LocalDateTime.now()); // Actualizar fecha de modificación


        return clienteRepository.save(existingCliente);
    }


    @Override
    public void delete(Long id) throws ClienteNotFoundException {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNotFoundException("Cliente not found with id: " + id);
        }
        clienteRepository.deleteById(id);
    }

    @Override
    public ClienteDtoResponse findByClienteNombre(String nombre) throws ClienteNotFoundException {
        Optional<ClienteEntity> cliente = clienteRepository.findByClienteNombre(nombre);

        if (!cliente.isPresent()) {
            throw new ClienteNotFoundException("Cliente is not yupi");
        }
        return ClienteMapper.clienteEntityToClienClienteDtoResponse("Exist client con el nombre ", cliente.get());

    }

    @Override
    public ClienteDtoResponse findClienteById(Long id) throws CannotLoadBeanClassException, ClienteNotFoundException {
        Optional<ClienteEntity> cliente = clienteRepository.findById(id);

        if (!cliente.isPresent()) {
            throw new ClienteNotFoundException("Cliente is not available");

        }
        return ClienteMapper.clienteEntityToClienClienteDtoResponse("Exist client yupi", cliente.get());
    }


}





