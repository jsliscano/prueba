package com.example.prueba.service;

import com.example.prueba.dto.ClienteDto;
import com.example.prueba.entity.ClienteEntity;
import com.example.prueba.repository.ClienteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {

        // Arrange  MOquea o instacia objectos que vaya usar en el metodo
        ClienteDto clienteDto = ClienteDto
                .builder()
                .fechaNacimiento(LocalDate.parse("2008-05-18"))
                .build();

        ClienteEntity clienteEntity = ClienteEntity.builder().build();

        // Act realiza la llamada al método a probar con los parámetros preparados para tal fin.

        Mockito.when(clienteRepository.save(clienteEntity)).thenReturn(clienteEntity);

        Object response = clienteService.save(clienteDto);


        // Assert comprueba que el método de pruebas ejecutado se comporta tal y como teníamos previsto que lo hiciera.

        assertEquals("No es mayor de edad",response);


    }

    @Test
    void saveNotEsMayor() {

        // Arrange  MOquea o instacia objectos que vaya usar en el metodo
        ClienteDto clienteDto = ClienteDto
                .builder()
                .fechaNacimiento(LocalDate.parse("2008-05-18"))
                .build();

        // Act realiza la llamada al método a probar con los parámetros preparados para tal fin.

        Object response = clienteService.save(clienteDto);

        // Assert comprueba que el método de pruebas ejecutado se comporta tal y como teníamos previsto que lo hiciera.

        assertEquals("No es mayor de edad",response);

    }
}