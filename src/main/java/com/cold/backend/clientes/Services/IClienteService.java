package com.cold.backend.clientes.Services;

import com.cold.backend.clientes.models.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> findAll();

    Cliente findById(Long id);

    Cliente save(Cliente cliente);

    void delete(Long id);


}
