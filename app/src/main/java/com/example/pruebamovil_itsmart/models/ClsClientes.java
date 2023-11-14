package com.example.pruebamovil_itsmart.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class ClsClientes implements Parcelable {
    private int id_cliente;
            private String nombre;
            private String telefono;
            private String email;
            private String estado;
            private String municipio;
            private String colonia;
            private String calle;
            private String latitud;
            private String longitud;
            private Date createdat;
            private Date updatedat;
//    -----------------------------------------------------------------


    protected ClsClientes(Parcel in) {
        id_cliente = in.readInt();
        nombre = in.readString();
        telefono = in.readString();
        email = in.readString();
        estado = in.readString();
        municipio = in.readString();
        colonia = in.readString();
        calle = in.readString();
        latitud = in.readString();
        longitud =in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_cliente);
        dest.writeString(nombre);
        dest.writeString(telefono);
        dest.writeString(email);
        dest.writeString(estado);
        dest.writeString(municipio);
        dest.writeString(colonia);
        dest.writeString(calle);
        dest.writeString(latitud);
        dest.writeString(longitud);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ClsClientes> CREATOR = new Creator<ClsClientes>() {
        @Override
        public ClsClientes createFromParcel(Parcel in) {
            return new ClsClientes(in);
        }

        @Override
        public ClsClientes[] newArray(int size) {
            return new ClsClientes[size];
        }
    };
//    -----------------------------------------------------------------

    public ClsClientes(int id_cliente, String nombre, String telefono, String email, String estado, String municipio, String colonia, String calle, String latitud, String longitud, Date createdat, Date updatedat) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
        this.municipio = municipio;
        this.colonia = colonia;
        this.calle = calle;
        this.latitud = latitud;
        this.longitud = longitud;
        this.createdat = createdat;
        this.updatedat = updatedat;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public Date getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Date updatedat) {
        this.updatedat = updatedat;
    }
}
