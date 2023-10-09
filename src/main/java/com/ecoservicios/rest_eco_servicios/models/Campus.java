package com.ecoservicios.rest_eco_servicios.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sedes")
public class Campus {

    @Id
    @Column(name = "k03_key")
    private String id;
    @Column(name = "k03_descripcion")
    private String description;
    @Column(name = "k03_direccion")
    private String address;
    @Column(name = "k03_estado")
    private String status = "A";

    @OneToMany(mappedBy = "campus", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<User> getUsers() {
        return users;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
        for (User user : users) {
            user.setCampus(this);
        }
    }
}
