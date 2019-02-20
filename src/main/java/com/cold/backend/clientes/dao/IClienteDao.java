package com.cold.backend.clientes.dao;

import com.cold.backend.clientes.models.Cliente;
import com.cold.backend.clientes.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IClienteDao extends JpaRepository<Cliente, Long> {

    @Query("from Region")
    public List<Region> findAllRegiones();

}
