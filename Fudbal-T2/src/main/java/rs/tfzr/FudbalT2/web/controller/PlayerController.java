package rs.tfzr.FudbalT2.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.Player.Team;
import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.ExhibitionService;
import rs.tfzr.FudbalT2.service.PlayerService;
import rs.tfzr.FudbalT2.service.UserService;
import rs.tfzr.FudbalT2.web.dto.PlayerDTO;
import rs.tfzr.FudbalT2.web.dto.UserDTO;
import rs.tfzr.FudbalT2.web.validator.ExhibitionAvailableValidator;
import rs.tfzr.FudbalT2.web.validator.PlayerValidator;

/**
 * 
 * @author jovan
 *
 */
@Controller
@RequestMapping("/players")
public class PlayerController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private ExhibitionService exhibitionService;
	
	@Autowired
	private ExhibitionAvailableValidator exhibitionAvailValid;
	
	@Autowired
	private PlayerValidator playerValidator;
	
	private BindingResult exhibitionAvailable(Exhibition exhibition)
	{
		DataBinder binder = new DataBinder(exhibition);
		binder.addValidators(exhibitionAvailValid);
		binder.validate();
		return binder.getBindingResult();
	}
	
	@RequestMapping(value = "/exhibition/{id}", method = RequestMethod.GET)
	public String listAllPlayersOfExhibition(@PathVariable Long id, Model model)
	{
		Exhibition exhibition = exhibitionService.findOne(id);
		if(exhibition != null)
		{
			model.addAttribute("exhibitionId", id);
			model.addAttribute("exhibitionStart", exhibition.getExhibitionStart());
			List<PlayerDTO> players = new ArrayList<PlayerDTO>();
			for(Player player: playerService.findAll(id))
			{
				PlayerDTO dto = new PlayerDTO();
				dto.setExhibitionId(id);
				dto.setExhibitionStart(exhibition.getExhibitionStart());
				dto.setFirstName(player.getUser().getFirstName());
				dto.setId(player.getId());
				dto.setLastName(player.getUser().getLastName());
				dto.setTeam(player.getTeam());
				dto.setUserId(player.getUser().getId());
				players.add(dto);
			}
			model.addAttribute("players", players);
		}
		return "players";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/exhibition/{ide}/player/{idp}/team/{team:[\\d]+}", method = RequestMethod.GET)
	public String setPlayerTeam(@PathVariable Long ide, @PathVariable Long idp, @PathVariable Team team,
			Model model)
	{
		Exhibition exhibition = exhibitionService.findOne(ide);
		BindingResult bindingResult = exhibitionAvailable(exhibition);
		if(!bindingResult.hasErrors())
		{
			DataBinder binder = new DataBinder(playerService.findOne(idp));
			binder.setValidator(playerValidator);
			binder.validate();
			BindingResult results = binder.getBindingResult();
			if(!results.hasErrors())
			{
				playerService.addToTeam(idp, team);
			}
			else
			{
				model.addAttribute("errors", results.getAllErrors());
			}
		}
		else
		{
			model.addAttribute("errors", bindingResult.getAllErrors());
		}
		return "redirect:/players/exhibition/" + ide;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/exhibition/{exhibitionId}/add", method = RequestMethod.GET)
	public String addPlayers(@PathVariable Long exhibitionId, Model model)
	{
		Exhibition exhibition = exhibitionService.findOne(exhibitionId);
		BindingResult bindingResult = exhibitionAvailable(exhibition);
		if(!bindingResult.hasErrors())
		{
			model.addAttribute("exhibitionId", exhibitionId);
			model.addAttribute("exhibitionStart", exhibition.getExhibitionStart());
			List<PlayerDTO> players = new ArrayList<PlayerDTO>();
			for(Player player: playerService.findAll(exhibitionId))
			{
				PlayerDTO dto = new PlayerDTO();
				dto.setExhibitionId(exhibitionId);
				dto.setExhibitionStart(exhibition.getExhibitionStart());
				dto.setFirstName(player.getUser().getFirstName());
				dto.setId(player.getId());
				dto.setLastName(player.getUser().getLastName());
				dto.setTeam(player.getTeam());
				dto.setUserId(player.getUser().getId());
				players.add(dto);
			}
			model.addAttribute("players", players);
			
			List<UserDTO> users = new ArrayList<UserDTO>();
			for(User user: userService.findAll())
			{
				UserDTO dto = new UserDTO();
				dto.setFirstName(user.getFirstName());
				dto.setId(user.getId());
				dto.setLastName(user.getLastName());
				dto.setUsername(user.getUsername());
				users.add(dto);
			}
			
			List<UserDTO> userList = new ArrayList<UserDTO>();
			for(UserDTO user: users)
			{
				boolean find = false;
				for(PlayerDTO player: players)
				{
					if(user.getId() == player.getUserId())
					{
						find = true;
						break;
					}
				}
				if(!find)
					userList.add(user);
			}
			model.addAttribute("users", users);
		}
		else
		{
			model.addAttribute("errors", bindingResult.getAllErrors());
		}
		return "players";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/exhibition/{exhibitionId}/add/{userId}", method = RequestMethod.GET)
	public String addPlayers(@PathVariable Long exhibitionId, @PathVariable Long userId, Model model)
	{
		Exhibition exhibition = exhibitionService.findOne(exhibitionId);
		BindingResult bindingResult = exhibitionAvailable(exhibition);
		if(!bindingResult.hasErrors())
		{
			Player player = new Player(userService.findOne(userId), exhibitionService.findOne(exhibitionId));
			playerService.save(player);
			return "redirect:/players/exhibition/" + exhibitionId + "/add";
		}
		else
		{
			model.addAttribute("errors", bindingResult.getAllErrors());
			return "players";
		}
	}
}
