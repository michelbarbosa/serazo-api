package br.com.fiap.serazo.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RegistroHistoricoDto {
	private final RegistroHistorico registro;
	
	public RegistroHistoricoDto(RegistroHistorico registro) {
		this.registro = registro;
	}
	
	public String getCpf() {
		return registro.getCpf();
	}
	
	public int getScore() {
		return registro.getScore();
	}
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	public Date getData() {
		return registro.getData();
	}
}
