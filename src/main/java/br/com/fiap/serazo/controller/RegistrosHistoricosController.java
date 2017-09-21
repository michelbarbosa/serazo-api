package br.com.fiap.serazo.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.serazo.model.ConsultaScoreDto;
import br.com.fiap.serazo.model.Empresa;
import br.com.fiap.serazo.model.RegistroHistorico;
import br.com.fiap.serazo.model.RegistroHistoricoDto;
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
	public RegistroHistoricoDto consultarScore(@RequestBody ConsultaScoreDto consulta, HttpServletResponse response) {
		try {
			
			String login =  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			
			Empresa empresa = empresaRepo.findByLogin(login);
			
			if (empresa == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return null;
			}
			RegistroHistorico registro = new RegistroHistorico(empresa, consulta.getCpf(), new Date());
			registroRepo.save(registro);
			return new RegistroHistoricoDto(registro);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}
	
	@GetMapping(path = "empresas/historico")
	public List<RegistroHistoricoDto> listarHistorico(HttpServletResponse response) {
		try {
			
			String login =  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			
			Empresa empresa = empresaRepo.findByLogin(login);
			if (empresa == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return null;
			}
			return registroRepo.findAllByEmpresa(empresa)
					.stream()
					.sorted((left, right) -> right.getData().compareTo(left.getData()))
					.map(RegistroHistoricoDto::new)
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}
}
