package com.ecoservicios.rest_eco_servicios.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @Column(name = "k01_key")
    private String id;
    @Column(name = "k01_usuario")
    private String user;
    @Column(name = "k01_clave")
    private String password;
    @Column(name = "k01_descripcion")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = Access.WRITE_ONLY)
    @JoinColumn(name = "k01_sede")
    private Campus campus;
    @Column(name = "k01_estado")
    private String status = "A";
    @Column(name = "k01_fechareg")
    private String dateRegion = getCurrentDate();
    @Column(name = "k01_tipo")
    private String type = "G";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateRegion() {
        return dateRegion;
    }

    public void setDateRegion(String dateRegion) {
        this.dateRegion = dateRegion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}