package pt.isban.cib.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
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
    @Max(value = 100)
    @NotNull
    private String endereco;


    @Column(name="street_complement")
    @Max(value = 100)
    @NotNull
    private String enderecoComplement;

    @Column(name="creation_at")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date dtCriacao;

    @Column(name="updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacao;


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
}
