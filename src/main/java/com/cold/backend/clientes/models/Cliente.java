package com.cold.backend.clientes.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="clientes")
public class Cliente implements Serializable {

    private static final long serialVersionUID = -8542267531548636225L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /*@PrePersist
    public void prePersist(){
        createAt = new Date();
    }*/

    @NotEmpty(message = "Tienes que completar el campo")
    @Size(min = 4, max = 12 , message = "No puedes escapar al intervalo 4> y >8")
    @Column(name = "nombre",nullable = false)
    private String nombre;

    @NotEmpty
    @Column(nullable = false)
    private String apellido;

    @Email
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "El campo fecha no puede estar vacio")
    @Column(name="create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
