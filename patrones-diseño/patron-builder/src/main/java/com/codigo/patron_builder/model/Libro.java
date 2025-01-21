package com.codigo.patron_builder.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Libro {

    private String titulo;
    private String autor;
    private String fechaPublicacion;
    private String estado;

    //Constructor privado que utiliza a la clase Builder.
    private Libro(Builder builder){
        this.titulo = builder.titulo;
        this.autor = builder.autor;
        this.fechaPublicacion = builder.fechaPublicacion;
        this.estado = builder.estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getEstado() {
        return estado;
    }

    //Subclase Builder -> Clase que implementa los paso especificos para construir tu objeto.

    public static class Builder{
        private String titulo;
        private String autor;
        private String fechaPublicacion;
        private String estado;

        //Metodos para construir PARTES del objeto
        public Builder titulo(String titulo){
            this.titulo = titulo;
            return this;
        }

        public Builder autor(String autor){
            this.autor = autor;
            return this;
        }
        public Builder fechaPublicacion(String fechaPublicacion){
            this.fechaPublicacion = fechaPublicacion;
            return this;
        }
        public Builder estado(String estado){
            this.estado = estado;
            return this;
        }

        //Meotodo que costruye el objeto y lo retorna
        public Libro build(){
            return new Libro(this);
        }
    }
}
