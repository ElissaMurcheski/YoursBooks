
package com.mycompany.yoursbooks;

public class Livros {
    private String titulo;
    private long paginas;
    private Editoras editora;
    private Categorias categoria;
    private Autores autor;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public long getPaginas() {
        return paginas;
    }

    public void setPaginas(long paginas) {
        this.paginas = paginas;
    }

    public Editoras getEditora() {
        return editora;
    }

    public void setEditora(Editoras editora) {
        this.editora = editora;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public Autores getAutor() {
        return autor;
    }

    public void setAutor(Autores autor) {
        this.autor = autor;
    }
    
    public String detalharLivro(){
        return "Informações do Livro: \nTítulo: " + titulo + "\nPáginas: " + paginas +
                "\nEditora: " + editora.getEditora() + "\nCategoria: " + categoria.getTipo() +
                "\nAutor: " + autor.getNome_completo(); 
    }
    
}
