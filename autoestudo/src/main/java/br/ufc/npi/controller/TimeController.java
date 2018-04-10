package br.ufc.npi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.bean.Jogador;
import br.ufc.npi.bean.Time;
import br.ufc.npi.service.JogadorService;
import br.ufc.npi.service.TimeService;

@Controller
@RequestMapping("/times/")
public class TimeController {
	
	@Autowired
	private TimeService timeService;

	@Autowired
	private JogadorService jogadorService;
	
	@RequestMapping("/")
	public ModelAndView index(){
		ModelAndView model = new ModelAndView("times");
		
		List<Time> times = timeService.getTodosTimes();
		model.addObject("times", times);		
		
		return model;
	}
	
	@RequestMapping("/{id}")
	public ModelAndView detalhesTime(@PathVariable("id") Integer id){
		
		ModelAndView model = new ModelAndView("detalhes-time");
		Time time = timeService.getTime(id);
		List<Jogador> jogadoresSemTime = jogadorService.getJogadoresSemTime();
		
		model.addObject("time", time);
		model.addObject("jogadoresSemTime", jogadoresSemTime);
				
		return model;
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvarTime(@RequestParam String nomeTime){
		timeService.salvarTime(nomeTime);		
		return "redirect:/times/";
	}
	
	@RequestMapping("/excluir/{idTime}")
	public String excluirTime(@PathVariable Integer idTime){
		timeService.removerTime(idTime);
		
		return "redirect:/times/";
	}
	
	@RequestMapping(value = "/{idTime}/adicionarjogador", method = RequestMethod.POST)
	public String adicionarJogadorAoTime(@PathVariable("idTime") Integer idTime, @RequestParam Integer jogadorSemTimeID ){
		timeService.addJogadorAoTime(idTime, jogadorSemTimeID);	
		return "redirect:/times/"+idTime;
	}
	
	@RequestMapping(value = "/{idTime}/removerjogador/{idJogador}")
	public String removerJogadorDoTime(@PathVariable("idTime") Integer idTime, @PathVariable("idJogador") Integer idJogador ){
		timeService.delJogadorDoTime(idTime, idJogador);
		return "redirect:/times/"+idTime;
	}
	
}
