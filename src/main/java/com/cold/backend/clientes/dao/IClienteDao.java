package com.cold.backend.clientes.dao;

import com.cold.backend.clientes.models.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente, Long> {


}
