package pt.isban.cib.enums;

import java.util.stream.Stream;

public enum PrivilegioEnum {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENT(3, "ROLE_CLIENT");


    private Integer codigo;
    private String descricao;

    private PrivilegioEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao () {
        return descricao;
    }

    public static PrivilegioEnum toEnum(Integer codigo) {
        return Stream.of(PrivilegioEnum.values())
                .filter( privilegio -> privilegio.codigo.equals(codigo) )
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException("Invalid id: " + codigo));
    }

}
