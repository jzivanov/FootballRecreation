/**
 * 
 */
package rs.tfzr.FudbalT2.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.ExhibitionService;
import rs.tfzr.FudbalT2.service.PlayerService;
import rs.tfzr.FudbalT2.service.UserService;
import rs.tfzr.FudbalT2.web.dto.ExhibitionDTO;
import rs.tfzr.FudbalT2.web.validator.ExhibitionValidator;

/**
 * @author Miroslav
 *
 */
@Controller
@RequestMapping("/exhibitions")
public class ExhibitionController {

	@Autowired
	private ExhibitionService exhibitionService;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private UserService userService;

	@Autowired
	private ExhibitionValidator exhibitionValidator;

	// Returns the list of all exhibitions
	@RequestMapping(method = RequestMethod.GET)
	@ModelAttribute("exhibitions")
	public List<ExhibitionDTO> get(Model model) {
		List<ExhibitionDTO> retVal = new ArrayList<>();
		List<Exhibition> exhibitions = exhibitionService.findAll();
		ExhibitionDTO exhibitionDTO = new ExhibitionDTO();
		model.addAttribute("exhibitionForm", exhibitionDTO);
		ExhibitionDTO dto = new ExhibitionDTO();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		for (Exhibition exhibition : exhibitions) {
			dto = new ExhibitionDTO();
			dto.setId(exhibition.getId());
			dto.setStartDate(exhibition.getExhibitionStart());
			dto.setEndDate(exhibition.getExhibitionEnd());
			dto.setLocation(exhibition.getLocation());
			dto.setUserId(user.getId());
			retVal.add(dto);
		}
		return retVal;
	}

	// Returns the view for adding exhibitions
	// @param model
	// @return the name of the view for adding or editing an exhibition
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNew(Model model) {
		ExhibitionDTO dto = new ExhibitionDTO();
		dto.setStartDate(new Date());
		model.addAttribute("exhibitionDTO", dto);
		return "addEditExhibition";
	}

	// Returns the view for editing an exhibition
	// @param exhibitionId - the id of the exhibition to edit
	// @param model
	// @return the name of the view for editing an exhibition
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getEdit(@PathVariable Long exhibitionid, Model model) {
		Exhibition exhibition = exhibitionService.findOne(exhibitionid);
		ExhibitionDTO dto = new ExhibitionDTO();
		dto = new ExhibitionDTO();
		dto.setId(exhibition.getId());
		dto.setStartDate(exhibition.getExhibitionStart());
		dto.setEndDate(exhibition.getExhibitionEnd());
		model.addAttribute("exhibitionDTO", dto);
		return "addEditExhibition";
	}

	// Removes the exhibition with the specified id
	// @param exhibitionId - the id of the exhibition to remove
	// @return the redirect view name, which redirects to exhibition list
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String remove(@PathVariable Long exhibitionId) {
		exhibitionService.remove(exhibitionId);
		return "redirect:..";
	}

	// Persists the passed exhibition object
	// @param dto for the exhibition to persist
	// @param bindingResult - the result of binding
	// @param model
	// @return redirects to view for exhibition list
	@RequestMapping(params = "save", method = RequestMethod.POST)
	public String post(@Valid ExhibitionDTO dto, BindingResult bindingResult,
			Model model) {
		String viewName;
		if (!bindingResult.hasErrors()) {
			Exhibition exhibition;
			if (dto.getId() != null) {
				exhibition = exhibitionService.findOne(dto.getId());
			} else {
				exhibition = new Exhibition();
			}
			exhibition.setExhibitionStart(new Date());
			exhibition.setLocation(dto.getLocation());
			exhibitionService.save(exhibition);
			viewName = "redirect:exhibitions";
		} else {
			
			model.addAttribute("exhibitionForm", dto);
			viewName = "redirect:exhibitions";
		}
		return viewName;
	}

	// Cancels the add/edit action
	// @return redirects to exhibition list
	@RequestMapping(params = "cancel", method = RequestMethod.POST)
	public String cancel() {
		return "redirect:exhibitions";
	}

	// Adds new player to the exhibition
	// @param exhibitionId - the id of the exhibition to which to add the player
	// @param userId - the id that connects the user to the player and which
	// player to add to the exhibition
	// @param model
	// @returns the name of the view for adding/removing a player to/from an
	// exhibition
	@RequestMapping(value = "/{iexhibitionID}", method = RequestMethod.GET)
	public String addPlayer(@PathVariable("iexhibitionID") Long exhibitionId, Model model) {
		String viewName;
		Exhibition exhibition = exhibitionService.findOne(exhibitionId);
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		DataBinder binder = new DataBinder(exhibition);
		binder.setValidator(exhibitionValidator);
		binder.validate();
		BindingResult results = binder.getBindingResult();
		if (!results.hasErrors()) {
			exhibitionService.addPlayer(exhibition, user.getId());
			model.addAttribute("exhibition", exhibition);
			viewName="applied";
		}
		else
		{
			model.addAttribute("errors", results.getAllErrors());
			viewName = "exhibitions";
		}

		
		return viewName;
	}

	// Removes a player from an exhibition
	// @param exhibitionId - the id of the exhibition from which to remove the
	// player
	// @param playerId - id of the player to remove from the exhibition
	// @param model
	// @returns the name of the view for adding/removing a player to/from an
	// exhibition
	@RequestMapping(value = "/{iexhibitionID}/player/{playerID}", method = RequestMethod.GET)
	public String removePlayer(@PathVariable Long exhibitionId, Long playerId,
			Model model) {
		Player player = playerService.findOne(playerId);
		Exhibition exhibition = exhibitionService.findOne(exhibitionId);
		if (player != null) {
			exhibitionService.removePlayer(playerId, exhibition);
			exhibitionService.save(exhibition);
		} else {
			model.addAttribute("removePlayerRequired",
					"exhibition.removePlayer.required");
		}
		model.addAttribute("exhibition", exhibitionId);
		return "addRemovePlayers";
	}
}
