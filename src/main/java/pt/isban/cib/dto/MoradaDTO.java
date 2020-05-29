package pt.isban.cib.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MoradaDTO {

    @Size(max = 100)
    @NotNull(message = "O endereco não pode ser vazio")
    private String endereco;

    @Size(max = 100)
    @NotNull(message = "O complemento não pode ser vazio")
    private String complemento;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
