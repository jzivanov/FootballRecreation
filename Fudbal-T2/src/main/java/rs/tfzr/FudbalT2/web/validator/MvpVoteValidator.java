package rs.tfzr.FudbalT2.web.validator;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.service.ExhibitionService;
import rs.tfzr.FudbalT2.service.PlayerService;
import rs.tfzr.FudbalT2.web.dto.MvpVoteDTO;

@Component
public class MvpVoteValidator implements Validator
{
	@Autowired
	private ExhibitionService exhibitionService;
	
	@Autowired
	private PlayerService playerService;
	
	private final String EXHIBITION_MUST_BE_OVER = "page.mvp.validation.exhibitionMustBeOver";
	private final String EXHIBITION_DOES_NOT_EXIST = "page.exhibition.validation.exhibitionDoesNotExist";
	private final String VOTING_IS_NOT_LONGER_AVAILABLE = "page.mvp.validation.vottingNotAvailable";
	private final String PLAYER_DID_NOT_PLAY = "page.mvp.validation.playerDidNotPlay";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return MvpVoteDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) 
	{
		if(target != null && supports(target.getClass()))
		{
			MvpVoteDTO dto = (MvpVoteDTO)target;
			if(dto.getExhibition() == null)
			{
				errors.reject(EXHIBITION_DOES_NOT_EXIST);
			}
			else
			{
				if(!dto.getExhibition().getEnded())
				{
					errors.reject(EXHIBITION_MUST_BE_OVER);
				}
				else
				{
					List<Exhibition> list = exhibitionService.findAll();
					for(Exhibition exb: list)
					{
						if(exb.getExhibitionStart().before(new Date()))
						{
							errors.reject(VOTING_IS_NOT_LONGER_AVAILABLE);
							break;
						}
					}
					
					List<Player> players = playerService.findAll(dto.getExhibition().getId());
					boolean played = false;
					for(Player player: players)
					{
						if(player.getId() == dto.getPlayer().getId())
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
	}

}
