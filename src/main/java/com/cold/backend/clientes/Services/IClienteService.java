package com.cold.backend.clientes.Services;

import com.cold.backend.clientes.models.Cliente;

import java.util.List;

public interface IClienteService {

    public List<Cliente> findAll();
}
