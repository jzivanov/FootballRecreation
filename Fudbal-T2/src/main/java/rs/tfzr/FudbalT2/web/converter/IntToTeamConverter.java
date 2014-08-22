package rs.tfzr.FudbalT2.web.converter;

import org.springframework.core.convert.converter.Converter;

import rs.tfzr.FudbalT2.model.Player.Team;

public class IntToTeamConverter implements Converter<Integer, Team>
{

	@Override
	public Team convert(Integer flag) {
		switch(flag)
		{
			case 1: return Team.Home;
			case 2: return Team.Away;
			case 0:
			default: return Team.None;
		}
	}

}
