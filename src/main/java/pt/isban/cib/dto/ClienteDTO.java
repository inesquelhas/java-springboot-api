package pt.isban.cib.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import pt.isban.cib.entity.Cliente;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

//DTO da classe Cliente
public class ClienteDTO {

    private Integer clienteId;
    private String email;
//    private String password;
    private String nome;

    @JsonProperty(value = "dtNasc")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @JsonProperty(value = "dtCri")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataCriacao;

    @JsonProperty(value = "dtAct")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataAtualizacao;

    private Boolean ativo;

    public ClienteDTO (){};

    public ClienteDTO (Cliente cliente){
        this.clienteId = cliente.getClienteId();
        this.email = cliente.getEmail();
        this.nome = cliente.getNome();
        this.dataNascimento = cliente.getDataNascimento();
        this.dataCriacao = cliente.getDataCriacao();
        this.dataAtualizacao = cliente.getDataAtualizacao();
        this.ativo = cliente.getAtivo();
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public String getEmail() {
        return email;
    }

   /* public String getPassword() {
        return password;
    }*/

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*public void setPassword(String password) {
        this.password = password;
    }*/

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteDTO cliente = (ClienteDTO) o;
        return Objects.equals(clienteId, cliente.clienteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId);
    }
}
