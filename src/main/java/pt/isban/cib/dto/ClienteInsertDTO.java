package pt.isban.cib.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import pt.isban.cib.annotations.ValidaClienteInput;
import pt.isban.cib.entity.Cliente;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    private MoradaDTO morada;

    private List<DocumentoDTO> documentos = new ArrayList<>();


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

    public MoradaDTO getMorada() {
        return morada;
    }

    public void setMorada(MoradaDTO morada) {
        this.morada = morada;
    }

    public List<DocumentoDTO> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoDTO> documentos) {
        this.documentos = documentos;
    }
}
