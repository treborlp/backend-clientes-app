package com.cold.backend.clientes.dao;

import com.cold.backend.clientes.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteDao extends JpaRepository<Cliente, Long> {


}
