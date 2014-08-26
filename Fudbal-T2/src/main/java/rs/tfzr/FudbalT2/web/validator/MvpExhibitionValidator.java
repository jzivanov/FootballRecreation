package rs.tfzr.FudbalT2.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import rs.tfzr.FudbalT2.model.Exhibition;

public class MvpExhibitionValidator implements Validator
{
	private final String EXHIBITION_MUST_BE_OVER = "exhibition.ended.mustBeTrue";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Exhibition.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) 
	{
		if(target != null && supports(target.getClass()))
		{
			Exhibition ex = (Exhibition)target;
			if(!ex.getEnded())
			{
				errors.rejectValue("ended", EXHIBITION_MUST_BE_OVER);
			}
			else
			{
				//Potrebno je proveriti da li se mec zavrsio. Da li je dozvoljeno glasanje.
				//Da li je poceo sledeci termin jer samo do tad traje glasanje.
				
				//Uzmi id korisnika, proveri da li je taj korisnik jedan od igraca na datom mecu.
				//Jer samo igraci koji su igrali mogu da glasaju.
				//Ako je taj korisnik jedan od igraca, proveri da li je vec glasao.
				//Jer samo moze jednom da se glasa.
			}
		}
	}

}
