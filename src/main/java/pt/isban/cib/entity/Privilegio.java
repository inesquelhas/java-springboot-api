package pt.isban.cib.entity;

import pt.isban.cib.enums.PrivilegioEnum;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="roles")
public class Privilegio {

    @Id
    @Column(name="role_id")
    private Integer privilegioId;


    @NotNull
    @Column(name="description")
    @Max(value=200)
    private String privilegioDesc;

    @ManyToMany(mappedBy = "privilegioList" , fetch = FetchType.LAZY) //fetchtype = lazy porque não quer ver dados de cliente aquando consulta de privilegios
    private List<Cliente> clienteList = new ArrayList<>();

    public Privilegio(){}

    public Privilegio(PrivilegioEnum privilegio) {
        this.privilegioId = privilegio.getCodigo();
        this.privilegioDesc = privilegio.getDescricao();
    }

    public Integer getPrivilegioId() {
        return privilegioId;
    }

    public void setPrivilegioId(Integer privilegioId) {
        this.privilegioId = privilegioId;
    }

    public String getPrivilegioDesc() {
        return privilegioDesc;
    }

    public void setPrivilegioDesc(String privilegioDesc) {
        this.privilegioDesc = privilegioDesc;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }
}
