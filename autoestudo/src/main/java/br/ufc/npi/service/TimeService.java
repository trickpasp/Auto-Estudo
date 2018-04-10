package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.bean.Jogador;
import br.ufc.npi.bean.Time;
import br.ufc.npi.repositorio.JogadorRepositorio;
import br.ufc.npi.repositorio.TimeRepositorio;

@Service
public class TimeService {
	
	@Autowired
	private TimeRepositorio timeRepositorio;
	
	@Autowired 
	private JogadorRepositorio jogadorRepositorio;
	
	public Time salvarTime(String nome){
		Time time = new Time();
		time.setNome(nome);
		timeRepositorio.save(time);
		
		return time;
	}
	
	public List<Time> getTodosTimes(){
		return timeRepositorio.findAll();
	}
	
	public Time getTime(Integer id){
		return timeRepositorio.getOne(id);
	}
	
	public void removerTime(Integer idTime){
		Time time = timeRepositorio.getOne(idTime);
		
		for(Jogador jogador: time.getJogadores()){
			time.getJogadores().remove(jogador);
			jogador.setTime(null);
			jogadorRepositorio.save(jogador);
		}
		
		timeRepositorio.delete(time);
		
	}
	
	public void addJogadorAoTime(Integer idTime, Integer idJogador){
		Jogador jogador = jogadorRepositorio.getOne(idJogador);
		Time time = timeRepositorio.getOne(idTime);
		
		time.getJogadores().add(jogador);
		jogador.setTime(time);
		timeRepositorio.save(time);
		jogadorRepositorio.save(jogador);	
		
	}
	
	public void delJogadorDoTime(Integer idTime, Integer idJogador){
		Jogador jogador = jogadorRepositorio.getOne(idJogador);
		Time time = timeRepositorio.getOne(idTime);
		
		time.getJogadores().remove(jogador);
		jogador.setTime(null);
		timeRepositorio.save(time);
		jogadorRepositorio.save(jogador);	
		
	}
}
