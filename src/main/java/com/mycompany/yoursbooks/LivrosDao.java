package com.mycompany.yoursbooks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LivrosDao {

    private String user = "root";
    private String password = "root";
    private String url = "jdbc:mysql://localhost:3306/murcheskielissa?allowPublicKeyRetrieval  = true & useSSL = false";
    //jdbc:mysql://localhost:3306/"+base;
    private Connection con = null;
    private String driver = "com.mysql.cj.jdbc.Driver";

    public Connection conectarBanco() {
        try {
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            System.err.print(e);
            JOptionPane.showMessageDialog(null, "error");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MembrosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void desconectarBanco() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean inserirLivros(Livros livros) {
        PreparedStatement ps = null;
        con = conectarBanco();
        String sql = "INSERT INTO LIVROS (TITULO, PAGINAS, editoras_idEDITORA, categorias_idCATEGORIA, autores_idAUTORES) VALUES(?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, livros.getTitulo());
            ps.setLong(2, livros.getPaginas());
            ps.setInt(3, livros.getEditora().getId());
            ps.setInt(4, livros.getCategoria().getId());
            ps.setInt(5, livros.getAutor().getId());
            ps.execute();
            desconectarBanco();
            return true;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return false;
        }
    }

}
