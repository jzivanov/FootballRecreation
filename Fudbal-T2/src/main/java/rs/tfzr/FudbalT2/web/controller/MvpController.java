package rs.tfzr.FudbalT2.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.MVP;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.service.ExhibitionService;
import rs.tfzr.FudbalT2.service.MvpService;
import rs.tfzr.FudbalT2.service.PlayerService;
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
		DataBinder binder = new DataBinder(ex);
		binder.setValidator(new MvpExhibitionValidator());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		if(!results.hasErrors())
		{
			//Iz liste igraca koji su prisustvovali mecu, izbaci korisnika
			//Jer ne moze da glasa sam za sebe.
			model.addAttribute("players", playerService.findAll(id));
			return "";
		}
		else
		{
			return "";
		}
	}
	
	@RequestMapping(value = "/exhibition/{id}/vote/player/{idp}/user/{idu}", method = RequestMethod.GET)
	public String vote(@PathVariable Long id, @PathVariable Long idp, @PathVariable Long idu)
	{
		Exhibition exh = exhibitionService.findOne(id);
		Player player = playerService.findOne(idp);
		Player playerVote = playerService.findOne(idu, id);
		MVP mvp = new MVP(player, exh, playerVote);
		mvpService.save(mvp);
		return "redirect:mvp";
	}
}
