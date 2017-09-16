package br.com.fiap.serazo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "historico")
public class RegistroHistorico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id_empresa", nullable = false)
	private Empresa empresa;

	@Column(nullable = false, length = 11)
	private String cpf;
	

	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private Date data;
	
	public RegistroHistorico() {
	}
	
	public RegistroHistorico(Empresa empresa, String cpf, Date data) {
		this.empresa = empresa;
		this.cpf = cpf;
		this.data = data;
	}
	
	public Integer getId() {
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
