package pt.isban.cib.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="addresses")
@SequenceGenerator(sequenceName="seq_addresses",name="seq_addresses")
public class Morada {

    @Id
    @Column(name="address_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_addresses")
    private Integer moradaId;

    @Column(name="street_name")
    @Size(max = 100)
    @NotNull
    private String endereco;


    @Column(name="street_complement")
    @Size(max = 100)
    @NotNull
    private String enderecoComplement;

    @Column(name="creation_at")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date dtCriacao;

    @Column(name="updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacao;

    @JsonIgnore
    @OneToOne(mappedBy = "morada")
    private Cliente cliente;

    public Integer getMoradaId() {
        return moradaId;
    }

    public void setMoradaId(Integer moradaId) {
        this.moradaId = moradaId;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEnderecoComplement() {
        return enderecoComplement;
    }

    public void setEnderecoComplement(String enderecoComplement) {
        this.enderecoComplement = enderecoComplement;
    }

    public Date getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(Date dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
