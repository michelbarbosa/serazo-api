package br.com.fiap.serazo.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.serazo.model.ConsultaScoreDto;
import br.com.fiap.serazo.model.Empresa;
import br.com.fiap.serazo.model.RegistroHistorico;
import br.com.fiap.serazo.repository.EmpresaRepository;
import br.com.fiap.serazo.repository.RegistroHistoricoRepository;

@RestController
@RequestMapping("/")
public class RegistrosHistoricosController {
	
	@Autowired
	private RegistroHistoricoRepository registroRepo;
	
	@Autowired
	private EmpresaRepository empresaRepo;
	
	@PostMapping(path = "/score")
	public RegistroHistorico consultarScore(@RequestBody ConsultaScoreDto consulta, HttpServletResponse response) {
		Empresa empresa = empresaRepo.findOne(consulta.getIdEmpresa());
		if (empresa == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		try {
			RegistroHistorico registro = new RegistroHistorico(empresa, consulta.getCpf());
			registroRepo.save(registro);
			return registro;
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}
}
