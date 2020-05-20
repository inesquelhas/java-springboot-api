package pt.isban.cib.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import pt.isban.cib.annotations.ValidaClienteInput;
import pt.isban.cib.entity.Cliente;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

//DTO da classe Cliente - Para ser usado quando queremos inserir cliente
@ValidaClienteInput
public class ClienteInsertDTO {

    @NotNull(message = "O email não pode ser vazio")
    @Email(message = "O email não é válido")
    private String email;

    @NotNull(message = "A password não pode ser vazia")
    private String password;

    @NotNull(message = "O nome não pode ser vazio")
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

    public Date getDtNasc() {
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

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }
}
