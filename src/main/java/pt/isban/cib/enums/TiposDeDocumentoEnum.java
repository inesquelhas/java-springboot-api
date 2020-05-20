package pt.isban.cib.enums;

import java.util.stream.Stream;

public enum TiposDeDocumentoEnum {

    BI(1, "Citizenship Card"),
    WORKING_VISA(2, "Working VISA"),
    RESIDENCE_PERMIT (3, "Residence Permit"),
    PASSPORT (4, "Passport");

    private Integer codigo;
    private String descricao;

    private TiposDeDocumentoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TiposDeDocumentoEnum toEnum(Integer codigo) {
        return Stream.of(TiposDeDocumentoEnum.values())
                .filter( tipo -> tipo.codigo.equals(codigo) )
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException("Invalid id: " + codigo));
    }
}
