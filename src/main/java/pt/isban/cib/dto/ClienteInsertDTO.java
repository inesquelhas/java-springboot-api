package pt.isban.cib.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import pt.isban.cib.entity.Cliente;

import java.util.Date;
import java.util.Objects;

//DTO da classe Cliente - Para ser usado quando queremos inserir cliente
public class ClienteInsertDTO {

    private String email;
    private String password;

    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dtNasc;


    public ClienteInsertDTO(){};


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dtNasc;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dtNasc = dataNascimento;
    }

}
