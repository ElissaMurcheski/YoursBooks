
package com.mycompany.yoursbooks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class YoursBooksMain {

    public static void main(String[] args) throws ParseException {
 
        // ---- MEMBROS ----
        String nomeMembro;
        Date dataNasc;
        long cpf;
        MembrosDao objMembrosDao = new MembrosDao();
        CategoriasDao objCategoriasDao = new CategoriasDao();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        nomeMembro = JOptionPane.showInputDialog("Seu Nome Completo: ");
        dataNasc = sdf.parse(JOptionPane.showInputDialog("Data de Nascimento: ")); 
        cpf = Long.parseLong(JOptionPane.showInputDialog("CPF: "));
                
        Membros objMembro;
        objMembro = new Membros();
        objMembro.setNome_completo(nomeMembro);
        objMembro.setDataNasc(dataNasc);
        objMembro.setCpf(cpf);
        objMembrosDao.inserirMembros(objMembro);
        
        // ---- AUTORES ----
        int idAutor;        
        String nomeAutor;
        AutoresDao objAutoresDao = new AutoresDao();
        idAutor = Integer.parseInt(JOptionPane.showInputDialog("Escolha um Autor: \n" + objAutoresDao.pegaTodosAutores().stream().map(autor -> 
        { 
            return autor.getId() + " - " + autor.getNome_completo();
        }).collect(Collectors.toList()) + "\nCadastro de Autor -> Digite um Código:"));
        Autores autor = objAutoresDao.pegaAutoresPorId(idAutor);
        if (autor.getNome_completo() == null) {
            nomeAutor = JOptionPane.showInputDialog("Nome do Autor");
        } else {
            nomeAutor = autor.getNome_completo();
        }
        
        Autores objAutor;
        objAutor = new Autores();
        objAutor.setId(idAutor);
        objAutor.setNome_completo(nomeAutor);
        objAutoresDao.inserirAutores(objAutor);
        
        // ---- CATEGORIAS ----
        int idCategoria;
        String tipoCategoria; 
        idCategoria = Integer.parseInt(JOptionPane.showInputDialog("Escolha a Categoria do livro: \n" + objCategoriasDao.pegaTodasCategorias().stream().map(categoria -> 
        { 
            return categoria.getId() + " - " + categoria.getTipo();
        }).collect(Collectors.toList()) + "\nCadastro de Categoria -> Digite um Código:"));
        Categorias categoria = objCategoriasDao.pegaCategoriasPorId(idCategoria);
        if (categoria.getTipo()== null) {
            tipoCategoria = JOptionPane.showInputDialog("Categoria");
        } else {
            tipoCategoria = categoria.getTipo();
        }
        
        Categorias objCategoria;
        objCategoria = new Categorias();
        objCategoria.setId(idCategoria);
        objCategoria.setTipo(tipoCategoria);
        objCategoriasDao.inserirCategorias(objCategoria);
        
        // ---- EDITORA ----
        int idEditora;
        String nomeEditora;
        EditorasDao objEditorasDao = new EditorasDao();
        idEditora = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma Editora: \n" + objEditorasDao.pegaTodosEditoras().stream().map(editora -> 
        { 
            return editora.getId() + " - " + editora.getEditora();
        }).collect(Collectors.toList()) + "\nCadastro de Editora-> Digite um Código: "));
        Editoras editora = objEditorasDao.pegaEditorasPorId(idEditora);
        if (editora.getEditora()
                == null) {
            nomeEditora = JOptionPane.showInputDialog("Nome da Editora");
        } else {
            nomeEditora = editora.getEditora();
        }
        
        Editoras objEditora;
        objEditora = new Editoras();
        objEditora.setId(idEditora);
        objEditora.setEditora(nomeEditora);
        objEditorasDao.inserirEditoras(objEditora);
        
          // ---- LIVROS ----
        String tituloLivro;
        long paginas;
        LivrosDao objLivrosDao = new LivrosDao();
        tituloLivro = JOptionPane.showInputDialog("Título do Livro: ");
        paginas = Long.parseLong(JOptionPane.showInputDialog("Páginas: "));
        
        Livros objLivro;
        objLivro = new Livros();
        objLivro.setTitulo(tituloLivro);
        objLivro.setPaginas(paginas);
        objLivro.setAutor(objAutor);
        objLivro.setEditora(objEditora);
        objLivro.setCategoria(objCategoria);
        objLivrosDao.inserirLivros(objLivro);
        
        
        JOptionPane.showMessageDialog(null, objMembro.detalharMembro());
        JOptionPane.showMessageDialog(null, objLivro.detalharLivro());
        
    }
}
