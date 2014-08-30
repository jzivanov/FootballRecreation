package rs.tfzr.FudbalT2.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import rs.tfzr.FudbalT2.web.validator.MvpExhibitionValidator;

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
	private MvpExhibitionValidator mvpValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	@ModelAttribute("mvps")
	public List<Player> get()
	{
		List<Player> ret = new ArrayList<Player>();
		for(Exhibition ex: exhibitionService.findAll())
		{
			ret.add(mvpService.getMvpForExhibition(ex.getId()));
		}
		return ret;
	}
	
	@RequestMapping(value="/exhibition/{id}", method = RequestMethod.GET)
	public String getExhibitionMvp(@PathVariable Long id, Model model)
	{
		List<Player> list = new ArrayList<Player>();
		list.add(mvpService.getMvpForExhibition(id));
		model.addAttribute("mvps", list);
		return "mvp";
	}
	
	@RequestMapping(value = "/exhibition/{id}/vote", method = RequestMethod.GET)
	public String vote(@PathVariable Long id, Model model)
	{		
		Exhibition ex = exhibitionService.findOne(id);
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		System.out.println("From MvpController.vote(); user email: " + user.getEmail());
		System.out.println("exhibition end date: " + ex.getExhibitionEnd().toString());
		MvpDTO mvpdto = new MvpDTO();
		mvpdto.setExhibition(ex);
		
		DataBinder binder = new DataBinder(mvpdto);
		binder.setValidator(mvpValidator);
		binder.validate();
		BindingResult results = binder.getBindingResult();
		
		if(!results.hasErrors())
		{
			//Iz liste igraca koji su prisustvovali mecu, izbaci korisnika
			//Jer ne moze da glasa sam za sebe.
			List<Player> list = playerService.findAll(id);
			for(Player player: list)
			{
				if(player.getUser().getId() == user.getId())
					list.remove(player);
			}
		    mvpdto.setPlayerList(list);
			model.addAttribute("mvp", mvpdto);
		}
		else
		{
			model.addAttribute("errors", results.getAllErrors());
		}
		return "mvpVote";
	}
	
	@RequestMapping(value = "/exhibition/{id}/vote/player/{idp}", method = RequestMethod.GET)
	public String vote(@PathVariable Long id, @PathVariable Long idp)
	{
		Exhibition exh = exhibitionService.findOne(id);
		Player player = playerService.findOne(idp);
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Player playerVote = playerService.findOne(user.getId(), id);
		
		MVP mvp = new MVP(player, exh, playerVote);
		mvpService.save(mvp);
		return "redirect:mvp";
	}
}
