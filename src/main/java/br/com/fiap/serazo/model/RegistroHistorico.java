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
	
	public RegistroHistorico(Empresa empresa, String cpf) {
		this.empresa = empresa;
		this.cpf = cpf;
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
		int score = 0;
		int multiplier = 1;
		for (int i = 0; i < cpf.length(); ++i) {
			char c = cpf.charAt(i);
			if (!Character.isDigit(c)) {
				continue;
			}
			int digit = Character.getNumericValue(c);
			score += Math.pow(digit, multiplier++);
		}
		return score % 1001;
	}
}
