package br.ufc.npi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.service.JogadorService;

@Controller
@RequestMapping("/jogadores/")
public class JogadorController {
	
	@Autowired
	private JogadorService jogadorService;
	
	@RequestMapping("/")
	public ModelAndView index(){		
		ModelAndView model = new  ModelAndView("jogadores");			
		model.addObject("jogadores", jogadorService.getTodosJogadores());	
		return model;
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvarJogador(@RequestParam String nomeJogador, @RequestParam Integer idadeJogador){
		
		jogadorService.salvarJogador(nomeJogador, idadeJogador);		
		return "redirect:/jogadores/";	
	}
		
	@RequestMapping("/excluir/{idJogador}")
	public String excluirJogador(@PathVariable Integer idJogador){
		jogadorService.removerJogador(idJogador);
		
		return "redirect:/jogadores/";
	}
	
}
