package com.expedux.controllers;

import com.expedux.dtos.Player;
import com.expedux.models.Players;
import com.expedux.services.EmailServices;
import com.expedux.services.PlayerService;
import com.expedux.utils.CommonFileUtils;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PlayersContoller {

	@Autowired
	PlayerService service;

	@Autowired
	EmailServices mailservice;

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
		ModelAndView mv = new ModelAndView();
		if (result.hasErrors()) {
			mv.setViewName("playerlist");
		} else {
			System.out.println(pl);
			service.savePlayer(pl);

			mv.setViewName("redirect:/index");
		}
		return mv;
	}

	@ResponseBody
	@GetMapping("testmail")
	public String simpleMail() {
		String result = "simple mail failure";

		String from = CommonFileUtils.fromMail;
		String to = CommonFileUtils.toMail;
		String sub = "SMTP Test Mail";
		String body = "vanakkam da mapla eclipse la erunthu";

		if (mailservice.simpleMailSender(from, to, sub, body)) {
			result = "simple mail sucess";
		}

		return result;
	}

	@ResponseBody
	@GetMapping("testvelocitymail")
	public String simpleVelocityMail() {
		String result = "velocity - failure";

		String from = CommonFileUtils.fromMail;
		String to = CommonFileUtils.ccMail;
		String sub = "Velocity Test Mail - Sample";
		Map<String, Object> model = new HashMap<>();
		model.put("name", "vinothkumar D");
		model.put("company", "Rockfort Tech");
		model.put("source", "Naukri");

		if (mailservice.sampleVelocitySender(from, to, sub, model)) {
			result = "velocity - sucess";
		}

		return result;
	}

	@GetMapping("resume")
	public ModelAndView resumepage() {

		ModelAndView mv = new ModelAndView();
		System.out.println("resume sending page----loader");
		mv.setViewName("resumesender");
		return mv;
	}

	@PostMapping("/mailresume")
	public ModelAndView resumeSender(@RequestParam("company") String company, @RequestParam("source") String source,
			@RequestParam("name") String name, @RequestParam("toAddress") String toAddress,
			@RequestParam("file") List<MultipartFile> files) {

		ModelAndView mv =new ModelAndView();
		
		String from = CommonFileUtils.fromMail;
		String to = toAddress;
		String sub = "Application for Developer Position at " + company;
		Map<String, Object> model = new HashMap<>();
		model.put("name", name);
		model.put("company", company);
		model.put("source", source);

		if (mailservice.sampleVelocitySender(from, to, sub, model, files)) {
			mv.setViewName("success");
		}
		else {
			mv.setViewName("error");
		}

		return mv;
	}

}
