package br.ufc.npi.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufc.npi.bean.Jogador;

public interface JogadorRepositorio extends JpaRepository<Jogador, Integer> {
	
	@Query("from Jogador j where j.time is null")
	public List<Jogador> buscarJogadoresSemTime();

}
