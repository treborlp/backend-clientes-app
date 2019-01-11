package com.cold.backend.clientes.Services;

import com.cold.backend.clientes.dao.IClienteDao;
import com.cold.backend.clientes.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteDao clienteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
      return (List<Cliente>) clienteDao.findAll();
    }
}
