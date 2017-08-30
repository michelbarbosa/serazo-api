package br.com.fiap.serazo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "historico")
public class RegistroHistorico {
	@Id
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id_empresa", nullable = false)
	private Empresa empresa;
	
	@Column(nullable = false, length = 11)
	private String cpf;
	
	@Column(nullable = false)
	private int score;
	
	public RegistroHistorico(Empresa empresa, String cpf, int score) {
		this.empresa = empresa;
		this.cpf = cpf;
		this.score = score;
	}
	
	public RegistroHistorico(int id, Empresa empresa, String cpf, int score) {
		this.id = id;
		this.empresa = empresa;
		this.cpf = cpf;
		this.score = score;
	}
	
	public int getId() {
		return id;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public String getCpf() {
		return cpf;
	}
	public int getScore() {
		return score;
	}
}
