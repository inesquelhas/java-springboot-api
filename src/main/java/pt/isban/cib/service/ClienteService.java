package pt.isban.cib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pt.isban.cib.dto.ClienteDTO;
import pt.isban.cib.dto.ClienteInsertDTO;
import pt.isban.cib.entity.Cliente;
import pt.isban.cib.entity.Morada;
import pt.isban.cib.enums.PrivilegioEnum;
import pt.isban.cib.exception.NotFoundException;
import pt.isban.cib.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//Classe que contém o lógica de negócio
@Service
public class ClienteService {

    @Autowired //anotaçao do spring (procura obj no sistema e instancia o objeto)
    private ClienteRepository clienteDAO;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<Cliente> getClientes(){

        List<Cliente> list = new ArrayList<>();

        clienteDAO.findAll().forEach(cliente -> {

            //Incluir somente cliente ativos
            if(cliente.getAtivo()){
                list.add(cliente);
            }

        });

        return list;
    }

    public List<Cliente> getClientesByNome(String nome){
        return clienteDAO.findByNomeContaining(nome);
    }

    public List<Cliente> getClientesByEmail(String email){
        return clienteDAO.findByEmail(email.toLowerCase());
    }

    public Cliente getClienteById(Integer id) throws Throwable{
        return clienteDAO.findById(id)
                .orElseThrow( () -> {
                    throw new NotFoundException(
                            String.format("O Cliente de ID {%d} não foi encontrado.",id));
                });
    }

    public ClienteDTO saveDTO (ClienteInsertDTO dto){

        Cliente cliente = new Cliente(dto);

        cliente.setAtivo(true);

        final Date currentDate = new Date();

        cliente.setDataCriacao(currentDate);

        //Definir as datas de criação para Morada e Documento de Identificação
        Morada morada = cliente.getMorada();
        morada.setDtCriacao(currentDate);
        morada.setCliente(cliente);

        cliente.getDocList().forEach(doc -> {
            doc.setDtCriacao(currentDate);
            doc.setCliente(cliente);
        });

        //Definir os privilegios do cliente - Privilegio 'CLIENT' por default

        List<PrivilegioEnum> list = new ArrayList<>();
        list.add(PrivilegioEnum.CLIENT);
        cliente.setPrivilegioList(list);

        //Codificar a Password
        cliente.setPassword(encoder.encode(cliente.getPassword()));

        Cliente ClienteNew = clienteDAO.save(cliente);

        return new ClienteDTO(ClienteNew);

    }

    @Transactional(isolation = Isolation.SERIALIZABLE, timeout = 5)  //timeout em segundos
    public void removerClienteById (Integer id) throws Throwable{

        clienteDAO.findById(id).map(cliente -> {
            clienteDAO.deleteById(id);
            return cliente;

        }).orElseThrow( () -> {
            throw new NotFoundException(
                    String.format("O Cliente de ID {%d} não foi encontrado.",id));
        });


    }

    @Transactional(isolation = Isolation.SERIALIZABLE, timeout = 5)  //timeout em segundos
    public ClienteDTO atualizaDTO(Integer id, ClienteInsertDTO dto) throws Throwable{
        return clienteDAO.findById(id).map(cliente -> {

            // Filtering
            if (dto.getNome() != null) {
                cliente.setNome(dto.getNome());
            }

            if (dto.getEmail() != null) {
                cliente.setEmail(dto.getEmail());
            }

            if (dto.getPassword() != null) {
                cliente.setPassword(dto.getPassword());
            }

            if (dto.getDtNasc() != null) {
                cliente.setDataNascimento(dto.getDtNasc());
            }

            cliente.setDataAtualizacao(new Date());

            return new ClienteDTO(clienteDAO.save(cliente));

        }).orElseThrow( () -> {
                    throw new NotFoundException(
                            String.format("O Cliente de ID {%d} não foi encontrado.",id));
        });
    }
}
