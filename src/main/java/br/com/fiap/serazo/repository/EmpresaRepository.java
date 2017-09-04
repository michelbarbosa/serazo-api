package br.com.fiap.serazo.repository;

import org.springframework.stereotype.Repository;

import br.com.fiap.serazo.model.Empresa;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
	Empresa findByLoginAndSenha(String login, String senha);
	Empresa findByLogin(String login);
}
