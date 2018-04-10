package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.bean.Jogador;
import br.ufc.npi.bean.Time;
import br.ufc.npi.repositorio.JogadorRepositorio;
import br.ufc.npi.repositorio.TimeRepositorio;

@Service
public class JogadorService {
	
	@Autowired
	private JogadorRepositorio jogadorRepositorio;
	
	@Autowired
	private TimeRepositorio timeRepositorio;
	
	
	
	public Jogador salvarJogador(String nome, Integer idade){
		Jogador jogador = new Jogador(nome, idade);
		jogadorRepositorio.save(jogador);
		
		return jogador;		
	}
	
	public List<Jogador> getTodosJogadores(){
		return jogadorRepositorio.findAll();
	}
	
	public List<Jogador> getJogadoresSemTime(){
		return jogadorRepositorio.buscarJogadoresSemTime();
	}

	public void removerJogador(Integer idJogador) {
		
		Jogador jogador = jogadorRepositorio.getOne(idJogador);
		if(jogador.getTime() != null){
			Time time = jogador.getTime();
			time.getJogadores().remove(jogador);
			jogador.setTime(null);
			
			timeRepositorio.save(time);
		}		
		jogadorRepositorio.delete(jogador);
		
	}

}
