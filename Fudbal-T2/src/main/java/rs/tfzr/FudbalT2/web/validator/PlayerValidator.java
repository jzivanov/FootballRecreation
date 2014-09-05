package rs.tfzr.FudbalT2.web.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.service.PlayerService;

@Component
public class PlayerValidator implements Validator
{
	private final String PLAYER_DID_NOT_PLAY = "page.mvp.validation.playerDidNotPlay";
	
	@Autowired
	private PlayerService playerService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Player.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) 
	{
		if(target != null && supports(target.getClass()))
		{
			Player plr = (Player)target;
			List<Player> players = playerService.findSignedPlayers(plr.getExhibition().getId());

			boolean played = false;
			for(Player player: players)
			{
				if(player.getId() == plr.getId())
				{
					played = true;
					break;
				}
			}
			if(!played)
				errors.reject(PLAYER_DID_NOT_PLAY);
		}
	}

}
