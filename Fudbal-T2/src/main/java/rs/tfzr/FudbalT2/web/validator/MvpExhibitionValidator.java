package rs.tfzr.FudbalT2.web.validator;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.service.ExhibitionService;
import rs.tfzr.FudbalT2.service.MvpService;
import rs.tfzr.FudbalT2.service.PlayerService;
import rs.tfzr.FudbalT2.web.dto.MvpDTO;

@Component
public class MvpExhibitionValidator implements Validator
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
	
	@Override
	public boolean supports(Class<?> clazz) {
		return MvpDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) 
	{
		//Potrebno je proveriti da li se mec zavrsio. Da li je dozvoljeno glasanje.
		//Da li je poceo sledeci termin jer samo do tad traje glasanje.
		
		//Uzmi id korisnika, proveri da li je taj korisnik jedan od igraca na datom mecu.
		//Jer samo igraci koji su igrali mogu da glasaju.
		//Ako je taj korisnik jedan od igraca, proveri da li je vec glasao.
		//Jer samo moze jednom da se glasa.
		if(target != null && supports(target.getClass()))
		{
			MvpDTO mvp = (MvpDTO)target;
			if(!mvp.getExhibition().getEnded())
			{
				errors.reject(EXHIBITION_MUST_BE_OVER);
			}
			else
			{
				if(exhibitionService == null)
					System.out.println("Exhibition service is null");
				List<Exhibition> list = exhibitionService.findAll();
				for(Exhibition exb: list)
				{
					if(exb.getExhibitionStart().before(new Date()))
					{
						errors.reject(VOTING_IS_NOT_LONGER_AVAILABLE);
						break;
					}
				}
				
				Player player = playerService.findOne(mvp.getUser().getId(), mvp.getExhibition().getId());
				if(player == null)
				{
					errors.reject(USER_MUST_BE_PRESENT);
				}
				else
				{
					if(mvpService.playerVoted(player.getId(), mvp.getExhibition().getId()))
						errors.reject(USER_ALREADY_VOTED);
				}
			}
		}
	}

}
