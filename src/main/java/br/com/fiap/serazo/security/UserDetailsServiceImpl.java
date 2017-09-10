package br.com.fiap.serazo.security;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.serazo.model.Empresa;
import br.com.fiap.serazo.repository.EmpresaRepository;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private EmpresaRepository repo;

    public UserDetailsServiceImpl(EmpresaRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empresa empresa = repo.findByLogin(username);
        if (empresa == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(empresa.getLogin(), empresa.getSenha(), emptyList());
    }
}