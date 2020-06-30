package pt.isban.cib.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pt.isban.cib.entity.Cliente;
import pt.isban.cib.repository.ClienteRepository;

import java.util.List;

@Service
//@Component
public class ClienteDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        List<Cliente> list = clienteRepository.findByEmail(email);

        if (list.isEmpty() || list.size()>1){
            throw new UsernameNotFoundException("Cliente com email " + email + " não encontrado");
        }

        Cliente cliente = list.get(0);

        return new MyUserDetails(cliente.getClienteId(),cliente.getEmail(),cliente.getPassword(),cliente.getPrivilegioList());

    }
}
