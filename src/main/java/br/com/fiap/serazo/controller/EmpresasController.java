package br.com.fiap.serazo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.serazo.model.Empresa;
import br.com.fiap.serazo.model.Login;
import br.com.fiap.serazo.repository.EmpresaRepository;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresasController {
	
	@Autowired
	private EmpresaRepository repository;
	
	@PostMapping(path = "/login")
	public Empresa login(@RequestBody Login login) {
		return repository.findByLoginAndSenha(login.getLogin(), login.getSenha());
	}
	
	@PostMapping
	public String cadastrar(@RequestBody Empresa empresa) {
		repository.save(empresa);
		return "OK";
	}
	
}
