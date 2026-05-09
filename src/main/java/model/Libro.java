package model;

public class Libro {
    private int id;
    private String isbn;
    private String titulo;
    private String genero;
    private int stock;
    private int id_autor;

    public Libro(int id, String isbn, String titulo, String genero, int stock, int id_autor) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.genero = genero;
        this.stock = stock;
        this.id_autor = id_autor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }
}
