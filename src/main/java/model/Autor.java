package model;

import java.sql.Date;

public class Autor {
    private int id;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private String nacionalidad;

    public Autor(int id, String nombre, String apellidos, String nacionalidad, Date fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }


    @Override
    public String toString() {
        return "id=" + id +
                "   |   Nombre='" + nombre + '\'' +
                "   |   Apellidos='" + apellidos + '\'' +
                "   |   Fecha de Nacimiento=" + fechaNacimiento +
                "   |   Nacionalidad='" + nacionalidad + '\'';
    }
}
