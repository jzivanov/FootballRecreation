package rs.tfzr.FudbalT2.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.Player.Team;
import rs.tfzr.FudbalT2.service.PlayerService;

/**
 * 
 * @author jovan
 *
 */
@Controller
@RequestMapping("/players")
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@RequestMapping(value = "/exhibition/{id}", method = RequestMethod.GET)
	public String listAllPlayersOfExhibition(@PathVariable Long id, Model model)
	{
		model.addAttribute("players", playerService.listAllPlayersOfExhibition(id));
		return "players";
	}
	
	@RequestMapping(value = "/exhibition/{ide}/player/{idp}/team/{team:[\\d]+}", method = RequestMethod.GET)
	public String setPlayerTeam(@PathVariable Long ide, @PathVariable Long idp, @PathVariable Team team,
			Model model)
	{
		playerService.addToTeam(idp, team);
		return "redirect:/players/exhibition/" + ide;
	}
}
