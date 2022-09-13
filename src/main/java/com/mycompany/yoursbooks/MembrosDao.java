package com.mycompany.yoursbooks;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MembrosDao {

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

    public boolean inserirMembros(Membros membros) {
        PreparedStatement ps = null;
        con = conectarBanco();
        String sql = "INSERT INTO MEMBROS (NOME_COMPLETO, DATA_NASC, CPF) VALUES(?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, membros.getNome_completo());
            ps.setDate(2, new Date(membros.getDataNasc().getTime()));
            ps.setLong(3, membros.getCpf());
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
