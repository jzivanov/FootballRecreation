package rs.tfzr.FudbalT2.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.MVP;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.ExhibitionService;
import rs.tfzr.FudbalT2.service.MvpService;
import rs.tfzr.FudbalT2.service.PlayerService;
import rs.tfzr.FudbalT2.web.dto.MvpDTO;
import rs.tfzr.FudbalT2.web.dto.MvpVoteDTO;
import rs.tfzr.FudbalT2.web.validator.ExhibitionAvailableValidator;
import rs.tfzr.FudbalT2.web.validator.MvpVoteValidator;
import rs.tfzr.FudbalT2.web.validator.MvpVoteValidator;

@Controller
@RequestMapping("/mvp")
public class MvpController {
	
	@Autowired
	private MvpService mvpService;
	
	@Autowired
	private ExhibitionService exhibitionService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private MvpVoteValidator mvpVoteValidator;
	
	@Autowired
	private ExhibitionAvailableValidator exAvailValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	public void get(Model model)
	{
		List<Player> ret = mvpService.getMvpHistory();
		model.addAttribute("mvps", ret);
		
		Exhibition ae = AvailableExhibition();
		if(ae != null)
			model.addAttribute("avilex", ae);
	}
	
	private Exhibition AvailableExhibition()
	{
		Exhibition availableExhibition = mvpService.getLastAvailable();
		if(availableExhibition != null)
		{
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Player player = playerService.findOne(user.getId(), availableExhibition.getId());
			if(player != null)
				return availableExhibition;
		}
		return null;
	}
	
	@RequestMapping(value="/exhibition/{id}", method = RequestMethod.GET)
	public String getExhibitionMvp(@PathVariable Long id, Model model)
	{
		DataBinder binder = new DataBinder(exhibitionService.findOne(id));
		binder.addValidators(exAvailValidator);
		binder.validate();
		BindingResult results = binder.getBindingResult();
		if(!results.hasErrors())
		{
			List<Player> list = new ArrayList<Player>();
			Player mvp = mvpService.getMvpForExhibition(id);
			if(mvp != null)
				list.add(mvpService.getMvpForExhibition(id));
			model.addAttribute("mvps", list);

			Exhibition ae = AvailableExhibition();
			if(ae != null)
				model.addAttribute("avilex", ae);
		}
		else
		{
			model.addAttribute("errors", results.getAllErrors());
		}
		return "mvp";
	}
	
	@RequestMapping(value = "/exhibition/{exhibitionId}/vote", method = RequestMethod.GET)
	public String vote(@PathVariable Long exhibitionId, Model model)
	{		
		DataBinder binder = new DataBinder(exhibitionService.findOne(exhibitionId));
		binder.addValidators(exAvailValidator);
		binder.validate();
		BindingResult results = binder.getBindingResult();
		if(!results.hasErrors())
		{
			model.addAttribute("exhibitionId", exhibitionId);
			List<Player> players = playerService.findSignedPlayers(exhibitionId);
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			for(Player player: players)
			{
				if(player.getUser().getId() == user.getId())
				{
					players.remove(player);
					break;
				}
			}
			model.addAttribute("players", players);

			Exhibition ae = AvailableExhibition();
			if(ae != null)
				model.addAttribute("avilex", ae);
			
			Player votedPlayer = mvpService.userVoted(user.getId(), exhibitionId);
			if(votedPlayer != null)
			{
				model.addAttribute("votedPlayer", votedPlayer);
			}
		}
		else
		{
			model.addAttribute("errors", results.getAllErrors());
		}
		
		return "mvp";
	}
	
	@RequestMapping(value = "/exhibition/{exhibitionId}/vote/player/{idp}", method = RequestMethod.GET)
	public String vote(@PathVariable Long exhibitionId, @PathVariable Long idp, Model model)
	{
		Exhibition exh = exhibitionService.findOne(exhibitionId);
		Player player = playerService.findOne(idp);
		MvpVoteDTO dto = new MvpVoteDTO();
		dto.setExhibition(exh);
		dto.setPlayer(player);

		DataBinder binder = new DataBinder(dto);
		binder.setValidator(mvpVoteValidator);
		binder.validate();
		BindingResult results = binder.getBindingResult();
		
		if(!results.hasErrors())
		{
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Player playerVote = playerService.findOne(user.getId(), exhibitionId);
			MVP mvp = new MVP(player, exh, playerVote);
			mvpService.save(mvp);
			return "redirect:/mvp/exhibition/" + exhibitionId + "/vote";
		}
		else
		{
			model.addAttribute("errors", results.getAllErrors());
			return "mvp";
		}
		
	}
}
