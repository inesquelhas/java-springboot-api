package pt.isban.cib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pt.isban.cib.dto.ClienteDTO;
import pt.isban.cib.dto.ClienteInsertDTO;
import pt.isban.cib.entity.Cliente;
import pt.isban.cib.repository.ClienteRepository;
import pt.isban.cib.service.ClienteService;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired //anotaçao do spring (procura obj no sistema e instancia o objeto)
    private ClienteService clienteService;

    //GET
    /*@GetMapping(path="/clientes") //transformar metodo num pedido REST
    public ResponseEntity<List<Cliente>> getClientesAtivos(){
        List<Cliente> list = clienteService.getClientes();
        return ResponseEntity.status(HttpStatus.OK)
                .body(clienteService
                .getClientes());
    }*/

    //GET
    /*@GetMapping(path="/clientes") //transformar metodo num pedido REST
    public ResponseEntity<List<ClienteDTO>> getClientesAtivos(){
        List<Cliente> list = clienteService.getClientes();
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        list.stream()
                                .map (cliente -> new ClienteDTO (cliente))
                                .collect (Collectors.toList()));
    }*/

    //GET
    /*@GetMapping(path="/clientes") //transformar metodo num pedido REST
    public ResponseEntity<List<ClienteDTO>> getClientesAtivos(
            @RequestParam(required = false, defaultValue = "") String nome,
            @RequestParam(required = false, defaultValue = "") String email
    ) {
        if (!"".equals(nome)) { //ou nome != null && !nome.equals("")
            List<Cliente> list = clienteService.getClientesByNome(nome);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(
                            list.stream()
                                    .map(cliente -> new ClienteDTO(cliente))
                                    .collect(Collectors.toList()));
        }else if (!"".equals(email)) {
            List<Cliente> list = clienteService.getClientesByEmail(email);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(
                            list.stream()
                                    .map(cliente -> new ClienteDTO(cliente))
                                    .collect(Collectors.toList()));

        } else {
            List<Cliente> list = clienteService.getClientes();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(
                            list.stream()
                                    .map (cliente -> new ClienteDTO (cliente))
                                    .collect (Collectors.toList()));
        }

    }*/

    //GET - Igual ao metodo da linha 46, mas numa forma mais simplificada (sem repetição de codigo)
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/clientes")
    public ResponseEntity<List<ClienteDTO>> getClientesAtivos(
            @RequestParam(required=false, defaultValue = "") String nome,
            @RequestParam(required=false, defaultValue = "") String email
    ){

        List<Cliente> list = new ArrayList<>();

        if (!"".equals(nome)) {
            list.addAll(clienteService.getClientesByNome(nome));
        } else if (!"".equals(email)) {
            list.addAll(clienteService.getClientesByEmail(email));
        } else {
            list.addAll(clienteService.getClientes());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list.stream()
                        .map( cliente -> new ClienteDTO(cliente))
                        .collect(Collectors.toList()));

    }

    //GET - Igual ao metodo GET de cima, mas o path muda - USADO PARA CONSULTA DE CLIENTE POR ID
    @PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
    @GetMapping(path = "/clientes/{id}")
    public ResponseEntity<ClienteDTO> getClientesById(@PathVariable Integer id) throws Throwable{

        Cliente cliente = clienteService.getClienteById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ClienteDTO(cliente));

    }

    //POST
    @PostMapping(path = "/clientes")
    public ResponseEntity<ClienteDTO> InserirCliente(@Valid @RequestBody ClienteInsertDTO dto) throws Throwable{

        ClienteDTO clienteDTO = clienteService.saveDTO(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clienteDTO.getClienteId())
                .toUri();

        return ResponseEntity.created(uri).body(clienteDTO);

    }

    //PUT
    @PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
    @PutMapping(path = "/clientes/{id}")
    public ResponseEntity<ClienteDTO> AtualizarCliente(@PathVariable Integer id,
                                                       @RequestBody ClienteInsertDTO dto) throws Throwable{

        ClienteDTO clienteDTO = clienteService.atualizaDTO(id,dto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteDTO);

    }

    //DELETE
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(path = "/clientes/{id}")
    public ResponseEntity<Void> removerClienteById(@PathVariable Integer id) throws Throwable{

        clienteService.removerClienteById(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();

    }

}
