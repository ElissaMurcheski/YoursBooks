
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

public class CategoriasDao {

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

    public boolean inserirCategorias(Categorias categorias) {
        PreparedStatement ps = null;
        con = conectarBanco();
        String sql = "INSERT INTO categorias (idCATEGORIA, TIPO) VALUES(?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, categorias.getId());
            ps.setString(2, categorias.getTipo());
            ps.execute();
            desconectarBanco();
            return true;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return false;
        }
    }

    public List<Categorias> pegaTodasCategorias() {
        List<Categorias> categorias = new ArrayList<>();
        Statement s = null;
        con = conectarBanco();
        String sql = "SELECT * FROM categorias;";
        try {
            s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Categorias categoria = new Categorias();
                categoria.setId(rs.getInt("idCATEGORIA"));
                categoria.setTipo(rs.getString("TIPO"));
                categorias.add(categoria);
            }
            desconectarBanco();
            return categorias;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return categorias;
        }
    }

    public Categorias pegaCategoriasPorId(int id) {
        Categorias categoria = new Categorias();
        Statement s = null;
        con = conectarBanco();
        String sql = "SELECT * FROM categorias WHERE idCATEGORIA = " + id;
        try {
            s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                categoria.setId(rs.getInt("idCATEGORIA"));
                categoria.setTipo(rs.getString("TIPO"));
            }
            desconectarBanco();
            return categoria;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return categoria;
        }
    }

}
