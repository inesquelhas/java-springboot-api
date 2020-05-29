package pt.isban.cib.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import pt.isban.cib.entity.Cliente;
import pt.isban.cib.enums.PrivilegioEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    private MoradaDTO morada;

    private List<DocumentoDTO> documentos = new ArrayList<>();

    private List<PrivilegioEnum> privilegios = new ArrayList<>();

    public ClienteDTO (){};

    public ClienteDTO (Cliente cliente){
        this.clienteId = cliente.getClienteId();
        this.email = cliente.getEmail();
        this.nome = cliente.getNome();
        this.dataNascimento = cliente.getDataNascimento();
        this.dataCriacao = cliente.getDataCriacao();
        this.dataAtualizacao = cliente.getDataAtualizacao();
        this.ativo = cliente.getAtivo();

        this.morada = new MoradaDTO();
        this.morada.setEndereco(cliente.getMorada().getEndereco());
        this.morada.setComplemento(cliente.getMorada().getEnderecoComplement());

        this.documentos.addAll(cliente.getDocList()
               .stream()
                .map(docIdent -> new DocumentoDTO(docIdent))
                .collect(Collectors.toList())
        );

        this.privilegios.addAll(cliente.getPrivilegioList());
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public String getEmail() {
        return email;
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
        return ativo;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public void setEmail(String email) {
        this.email = email;
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
        this.ativo = ativo;
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

    public List<PrivilegioEnum> getPrivilegios() {
        return privilegios;
    }

    public void setPrivilegios(List<PrivilegioEnum> privilegios) {
        this.privilegios = privilegios;
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
