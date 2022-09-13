
package com.mycompany.yoursbooks;

import java.util.Date;

public class Membros {
    private String nome_completo;
    private Date dataNasc;
    private long cpf;

    public String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
    
    public String detalharMembro(){
        return "Inofrmações do Membro: \nNome Completo: " + nome_completo + "\nData de Nascimeto: " +
                dataNasc + "\nCPF: " + cpf;
    }
    
}
