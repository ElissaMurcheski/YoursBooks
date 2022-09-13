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

public class EditorasDao {

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

    public boolean inserirEditoras(Editoras editoras) {
        PreparedStatement ps = null;
        con = conectarBanco();
        String sql = "INSERT INTO EDITORAS (idEDITORA, EDITORA) VALUES(?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, editoras.getId());
            ps.setString(2, editoras.getEditora());
            ps.execute();
            desconectarBanco();
            return true;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return false;
        }
    }

    public List<Editoras> pegaTodosEditoras() {
        List<Editoras> editoras = new ArrayList<>();
        Statement s = null;
        con = conectarBanco();
        String sql = "SELECT * FROM editoras;";
        try {
            s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Editoras editora = new Editoras();
                editora.setId(rs.getInt("idEDITORA"));
                editora.setEditora(rs.getString("EDITORA"));
                editoras.add(editora);
            }
            desconectarBanco();
            return editoras;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return editoras;
        }
    }

    public Editoras pegaEditorasPorId(int id) {
        Editoras editora = new Editoras();
        Statement s = null;
        con = conectarBanco();
        String sql = "SELECT * FROM editoras WHERE idEDITORA = " + id;
        try {
            s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                editora.setId(rs.getInt("idEDITORA"));
                editora.setEditora(rs.getString("EDITORA"));
            }
            desconectarBanco();
            return editora;
        } catch (SQLException e) {
            desconectarBanco();
            System.err.println(e);
            return editora;
        }
    }

}
