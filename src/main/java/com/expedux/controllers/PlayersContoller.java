package com.expedux.controllers;

import com.expedux.dtos.Player;
import com.expedux.models.Players;
import com.expedux.services.PlayerService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlayersContoller {
	
	@Autowired
	PlayerService service;

	@GetMapping("index")
	public ModelAndView playerList() {
		Players pl = new Players();
		ModelAndView mv = new ModelAndView();
		List<Player> pls = service.playersList();
		System.out.println(pls);
		mv.addObject("playerlist", pls);
		mv.addObject("playerinfo", pl);
		mv.setViewName("playerlist");
		return mv;
	}

	@PostMapping("saveplayer")
	public ModelAndView savePlayer(@Valid @ModelAttribute("playerinfo") Player pl, BindingResult result) {
		ModelAndView mv =new ModelAndView();
		if (result.hasErrors()) {
			mv.setViewName("playerlist");
		}
		else {
		System.out.println(pl);
		service.savePlayer(pl);

		mv.setViewName("redirect:/index");
		}
		return mv;
	}
	
}
