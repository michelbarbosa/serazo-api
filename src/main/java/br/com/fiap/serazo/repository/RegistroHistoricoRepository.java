package br.com.fiap.serazo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.serazo.model.Empresa;
import br.com.fiap.serazo.model.RegistroHistorico;

@Repository
public interface RegistroHistoricoRepository extends JpaRepository<RegistroHistorico, Integer> {
	List<RegistroHistorico> findAllByEmpresa(Empresa empresa);
}
