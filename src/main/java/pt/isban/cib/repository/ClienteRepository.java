package pt.isban.cib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.isban.cib.entity.Cliente;

import java.util.List;

//Objecto de acesso ao banco de dados DAO (Data Access Object)
//extends é para herdar
@Repository
/*public interface ClienteRepository extends CrudRepository <Cliente,Integer> {

}*/

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    List<Cliente> findByNomeContaining(String nome);

    @Query(value = "select * from clients where lower(email) like %?1%",
            nativeQuery = true)
    List<Cliente> findByEmail(String nome);

}
