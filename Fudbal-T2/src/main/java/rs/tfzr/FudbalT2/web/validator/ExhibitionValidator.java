/**
 * 
 */
package rs.tfzr.FudbalT2.web.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.ExhibitionService;
import rs.tfzr.FudbalT2.service.MvpService;
import rs.tfzr.FudbalT2.service.PlayerService;

/**
 * @author Miroslav
 *
 */
@Component
public class ExhibitionValidator implements Validator {
	
	@Autowired
	private ExhibitionService exhibitionService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private MvpService mvpService;
	
	private final String EXHIBITION_DOES_NOT_EXIST = "page.exhibition.validation.exhibitionDoesNotExist";
	private final String EXHIBITION_OVER = "page.exhibition.validation.exhibitionOver";
	private final String PLAYER_ALREADY_APPLIED = "page.exhibition.validation.playerAlreadyApplied";

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0) {
		return Exhibition.class.equals(arg0);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		//proverava se da li mec postoji
		if(target == null)
			errors.reject(EXHIBITION_DOES_NOT_EXIST);
		
		if(target != null && supports(target.getClass())){
			Exhibition exhibition = (Exhibition) target;
			List<Player> players = playerService.findSignedPlayers(exhibition.getId());
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			for (Player player : players) {
				if(player.getUser().getId() == user.getId())
					errors.reject(PLAYER_ALREADY_APPLIED);
				
			}
			if(exhibition.getEnded())
				errors.reject(EXHIBITION_OVER);
			
			
		}
	}

}
