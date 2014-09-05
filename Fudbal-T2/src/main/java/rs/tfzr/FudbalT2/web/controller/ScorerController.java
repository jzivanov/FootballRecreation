package rs.tfzr.FudbalT2.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.Scorers;
import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.ExhibitionService;
import rs.tfzr.FudbalT2.service.PlayerService;
import rs.tfzr.FudbalT2.service.ScorerService;
import rs.tfzr.FudbalT2.service.UserService;
import rs.tfzr.FudbalT2.web.dto.PlayerDTO;
import rs.tfzr.FudbalT2.web.dto.ScorerDTO;

/**
 * 
 * @author jovan
 *
 */
@Controller
@RequestMapping("/scorers")
public class ScorerController 
{
	@Autowired
	private ScorerService scorerService;
	
	@Autowired
	private ExhibitionService exhibitionService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public void get(Model model)
	{
		model.addAttribute("scorers", scorerService.getRankList());
		List<Exhibition> exs = new ArrayList<Exhibition>();
		for(Exhibition ex: exhibitionService.findAll())
		{
			if(ex.getEnded())
				exs.add(ex);
		}
		model.addAttribute("exhibitions", exs);
	}
	
	@RequestMapping(value = "/exhibition/{exhibitionId}", method = RequestMethod.GET)
	public String scorersAtExhibition(@PathVariable Long exhibitionId, Model model)
	{
		model.addAttribute("exhibitionId", exhibitionId);
		
		List<Player> players = playerService.findSignedPlayers(exhibitionId);
		List<PlayerDTO> dtoPlayers = new ArrayList<PlayerDTO>();
		for(Player player: players)
		{
			PlayerDTO dtop = new PlayerDTO();
			dtop.setFirstName(player.getUser().getFirstName());
			dtop.setId(player.getId());
			dtop.setLastName(player.getUser().getLastName());
			dtoPlayers.add(dtop);
		}
		model.addAttribute("players", dtoPlayers);
		
		ScorerDTO dto = new ScorerDTO();
		model.addAttribute("scorerform", dto);
		model.addAttribute("scorers", scorerService.getRankList(exhibitionId));
		
		List<Exhibition> exs = new ArrayList<Exhibition>();
		for(Exhibition ex: exhibitionService.findAll())
		{
			if(ex.getEnded())
				exs.add(ex);
		}
		model.addAttribute("exhibitions", exs);
		return "scorers";
	}
	
	@RequestMapping(value = "/exhibition/{exhibitionId}/add", method = RequestMethod.POST)
	public String addScorers(ScorerDTO scorer, @PathVariable Long exhibitionId, Model model)
	{
		Exhibition exhibition = exhibitionService.findOne(exhibitionId);
		Player player = playerService.findOne(scorer.getPlayerId());
		
		//Proveri servis
		for(int i = 0; i < scorer.getGoalCount(); i++)
		{		
			Scorers newScorer = new Scorers();
			newScorer.setExhibition(exhibition);
			newScorer.setPlayer(player);
			scorerService.save(newScorer);
		}
		
		return "redirect:/scorers/exhibition/" + exhibitionId;
	}
}
