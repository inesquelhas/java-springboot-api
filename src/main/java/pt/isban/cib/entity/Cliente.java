package pt.isban.cib.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;
import pt.isban.cib.dto.ClienteInsertDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

//Classe de representação de objectos do banco de dados
@Entity
@Table(name="clients")
@SequenceGenerator(sequenceName="seq_clients",name="seq_clients")
public class Cliente {

    //mapear ojectos
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_clients")
    @Column(name="client_id")
    private Integer clienteId;

    //não é preciso mapear porque o nome no Java é igual ao da tabela sql
    @NotNull
    @Size(max=100)
    private String email;

    //@JsonIgnore
    @NotNull
    @Size(max=100)
    private String password;

    @Column(name="name")
    @NotNull
    @Size(max=100)
    private String nome;

    //@JsonProperty(value = "dtNasc")
    //@JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name="birth_date")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dataNascimento;

    //@JsonProperty(value = "dtCri")
    //@JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name="creation_at")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date dataCriacao;

    //@JsonProperty(value = "dtAct")
    //@JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name="updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    /*@Column(name="active", columnDefinition="char(1)")
    @Type(type="true_false")
    private Boolean ativo;*/

    @Column(name="active")
    private String ativo;

    //TO DO Criar endereco

    public Cliente(){
    }

    public Cliente(ClienteInsertDTO dto){
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.nome = dto.getNome();
        this.dataNascimento = dto.getDataNascimento();
    }

    public Integer getClienteId() {
        return clienteId;
    }

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
        return dataNascimento;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public Boolean getAtivo() {
        if(!"".equals(ativo) && ativo.equals("1")){
            return true;
        } else {
            return false;
        }
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
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
        this.dataNascimento = dataNascimento;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setAtivo(Boolean ativo) {
        if (ativo == true) {
            this.ativo = "1";
        } else {
            this.ativo = "0";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(clienteId, cliente.clienteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId);
    }
}
