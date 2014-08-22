package rs.tfzr.FudbalT2.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.tfzr.FudbalT2.model.Scorers;
import rs.tfzr.FudbalT2.service.ScorerService;

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
	ScorerService scorerService;

	/**
	 * Retrieves all scorers and returns them as model attribute <code>scorers</code>.
	 * 
	 * @return list of all scorers, as model attribute <code>scorers</code>
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ModelAttribute("scorers")
	public List<Scorers> get()
	{
		return scorerService.findAll();
	}
	
	@RequestMapping(value = "/ranklist", method = RequestMethod.GET)
	public String getRankList(Model model)
	{
		model.addAttribute("scorers", scorerService.getRankList());
		return "ranklist";
	}
	
	@RequestMapping(value = "/exhibition/{id}", method = RequestMethod.GET)
	public String scorersAtExhibition(@PathVariable Long id, Model model)
	{
		model.addAttribute("scorers", scorerService.listAllScorers(id));
		return "scorers";
	}
}
