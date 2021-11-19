package org.danicv.hibernateapp.services;

import org.danicv.hibernateapp.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteServices {
    List<Cliente> listar();

    Optional<Cliente> porId(Long id);

    void guardar(Cliente cliente);

    void eliminar(Long id);
}
