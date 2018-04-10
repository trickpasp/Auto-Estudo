package br.ufc.npi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.npi.bean.Time;

@Repository
public interface TimeRepositorio extends JpaRepository<Time, Integer>{
	
	public Time findByNome(String nome);
	
}
