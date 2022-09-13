package com.mycompany.yoursbooks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AutoresDao {

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

    public boolean inserirAutores(Autores autores) {
        PreparedStatement ps = null;
        con = conectarBanco();
        String sql = "INSERT INTO autores (idAUTORES, NOME_COMPLETO) VALUES(?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, autores.getId());
            ps.setString(2, autores.getNome_completo());
            ps.execute();
            desconectarBanco();
            return true;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return false;
        }
    }

    public List<Autores> pegaTodosAutores() {
        List<Autores> autores = new ArrayList<>();
        Statement s = null;
        con = conectarBanco();
        String sql = "SELECT * FROM autores;";
        try {
            s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Autores autor = new Autores();
                autor.setId(rs.getInt("idAUTORES"));
                autor.setNome_completo(rs.getString("NOME_COMPLETO"));
                autores.add(autor);
            }
            desconectarBanco();
            return autores;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return autores;
        }
    }

    public Autores pegaAutoresPorId(int id) {
        Autores autor = new Autores();
        Statement s = null;
        con = conectarBanco();
        String sql = "SELECT * FROM autores WHERE idAUTORES = " + id;
        try {
            s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                autor.setId(rs.getInt("idAUTORES"));
                autor.setNome_completo(rs.getString("NOME_COMPLETO"));
            }
            desconectarBanco();
            return autor;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return autor;
        }
    }
}
