package com.cold.backend.clientes.Controllers;

import com.cold.backend.clientes.Services.IClienteService;
import com.cold.backend.clientes.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> index(){
        return clienteService.findAll();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){

        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>() ;

        try {
           cliente = clienteService.findById(id);
        }catch (DataAccessException e){
            response.put("mensaje","Error en la base de datos :(");
            response.put("error", " Error Especfico:".concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(cliente==null){
            response.put("mensaje","El cliente ID:".concat(id.toString().concat(" No existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

    return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @PostMapping("/clientes")
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result){
        //return clienteService.save(cliente);
        Cliente clienteNew = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()){

            List<String> errors = result.getFieldErrors()
                                    .stream()
                                    .map(err->"El campo " + err.getField()+ " "+ err.getDefaultMessage())
                                    .collect(Collectors.toList());

            response.put("errors",errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }

        try{
            clienteNew = clienteService.save(cliente);
        }catch(DataAccessException e){
            response.put("mensaje","Error al crear el usuario");
            response.put("error","Error Especifico:".concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","Cliente Creado");
        response.put("cliente",clienteNew);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id){

        Cliente clienteActual = clienteService.findById(id);
        Cliente clienteActualizado = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()){

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err->"El campo a editar " + err.getField()+ " "+ err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors",errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (clienteActual==null){
            response.put("mensaje","No se encuentra al cliente en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try{
            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setEmail(cliente.getEmail());

            clienteActualizado = clienteService.save(clienteActual);

        }catch(DataAccessException e){
            response.put("mensaje","Error al actualizar el usuario");
            response.put("error","Error Especifico:".concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","Cliente Actualizado");
        response.put("cliente",clienteActualizado);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id){

        Map<String, Object> response=new HashMap<>();

        try {
            clienteService.delete(id);
        }catch (DataAccessException e){
            response.put("mensaje","Error al eliminar el usuario");
            response.put("error","Error Especifico:".concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","Cliente Eliminado");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);


    }

}

