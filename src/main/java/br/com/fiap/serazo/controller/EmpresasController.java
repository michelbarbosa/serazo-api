package br.com.fiap.serazo.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.serazo.model.Empresa;
import br.com.fiap.serazo.repository.EmpresaRepository;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresasController {
	
	@Autowired
	private EmpresaRepository repository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping
	public String cadastrar(@RequestBody Empresa empresa, HttpServletResponse response) {
		try {
			empresa.setSenha(bCryptPasswordEncoder.encode(empresa.getSenha()));
			repository.save(empresa);
			response.setStatus(HttpServletResponse.SC_CREATED);
			return "";
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return e.getMessage();
		}
	}
	
}
