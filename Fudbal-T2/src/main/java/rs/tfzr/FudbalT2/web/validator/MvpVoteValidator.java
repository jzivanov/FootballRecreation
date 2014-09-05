package rs.tfzr.FudbalT2.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.ExhibitionService;
import rs.tfzr.FudbalT2.service.MvpService;
import rs.tfzr.FudbalT2.service.PlayerService;
import rs.tfzr.FudbalT2.web.dto.MvpVoteDTO;

@Component
public class MvpVoteValidator implements Validator
{
	@Autowired
	private ExhibitionService exhibitionService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private MvpService mvpService;
	
	private final String EXHIBITION_MUST_BE_OVER = "page.mvp.validation.exhibitionMustBeOver";
	private final String VOTING_IS_NOT_LONGER_AVAILABLE = "page.mvp.validation.vottingNotAvailable";
	private final String USER_MUST_BE_PRESENT = "page.mvp.validation.userMustBePresent";
	private final String USER_ALREADY_VOTED = "page.mvp.validation.userAlreadyVoted";
	private final String EXHIBITION_DOES_NOT_EXIST = "page.exhibition.validation.exhibitionDoesNotExist";
	private final String PLAYER_DOES_NOT_EXISTS = "page.player.validation.playerDoesNotExists";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return MvpVoteDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) 
	{
		if(target != null && supports(target.getClass()))
		{
			MvpVoteDTO mvp = (MvpVoteDTO)target;
			if(mvp.getExhibition() == null)
				errors.reject(EXHIBITION_DOES_NOT_EXIST);
			else
			{
				if(!mvp.getExhibition().getEnded())
				{
					errors.reject(EXHIBITION_MUST_BE_OVER);
				}
				else
				{
					if(!mvp.getExhibition().equals(mvpService.getLastAvailable()))
						errors.reject(VOTING_IS_NOT_LONGER_AVAILABLE);
					
					if(mvp.getPlayer() == null)
						errors.reject(PLAYER_DOES_NOT_EXISTS);

					User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					Player player = playerService.findOne(user.getId(), mvp.getExhibition().getId());
					if(player == null)
					{
						errors.reject(USER_MUST_BE_PRESENT);
					}
					else
					{
						if(mvpService.userVoted(user.getId(), mvp.getExhibition().getId()) != null)
							errors.reject(USER_ALREADY_VOTED);
					}
				}
			}
		}
	}

}
