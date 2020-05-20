package pt.isban.cib.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="identifications")
@SequenceGenerator(sequenceName="seq_identifications",name="seq_identifications")
public class DocumentoIdentificacao {

    @Id
    @Column(name="identification_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_identifications")
    private Integer documentoIdentificacaoId;

    @Column(name="ident_type")
    @NotNull
    private Integer tipoDocumento;

    @Column(name="ident_code")
    @NotNull
    @Max(value=100)
    private String numeroDocumento;

    @Column(name="emission_date")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dtEmissao;

    @Column(name="valid_date")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dtValidade;

    @Column(name="creation_at")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date dtCriacao;

    @Column(name="updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacao;

    @JsonIgnore
    @NotNull
    @ManyToOne
    @JoinColumn(name="client_id",nullable = false)
    private Cliente cliente;

    public Integer getDocumentoIdentificacaoId() {
        return documentoIdentificacaoId;
    }

    public void setDocumentoIdentificacaoId(Integer documentoIdentificacaoId) {
        this.documentoIdentificacaoId = documentoIdentificacaoId;
    }

    public Integer getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getDtEmissao() {
        return dtEmissao;
    }

    public void setDtEmissao(Date dtEmissao) {
        this.dtEmissao = dtEmissao;
    }

    public Date getDtValidade() {
        return dtValidade;
    }

    public void setDtValidade(Date dtValidade) {
        this.dtValidade = dtValidade;
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
