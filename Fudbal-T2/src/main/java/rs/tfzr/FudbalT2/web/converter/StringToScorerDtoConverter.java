package rs.tfzr.FudbalT2.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.service.PlayerService;
import rs.tfzr.FudbalT2.web.dto.PlayerDTO;
import rs.tfzr.FudbalT2.web.dto.ScorerDTO;

public class StringToScorerDtoConverter  implements Converter<String, ScorerDTO>
{
	@Autowired
	private PlayerService playerService;
	
	@Override
	public ScorerDTO convert(String playerId) 
	{
		Player player = playerService.findOne(Long.parseLong(playerId));
		PlayerDTO playerDto = new PlayerDTO();
		playerDto.setExhibitionId(player.getExhibition().getId());
		playerDto.setExhibitionStart(player.getExhibition().getExhibitionStart());
		playerDto.setFirstName(player.getUser().getFirstName());
		playerDto.setId(player.getId());
		playerDto.setLastName(player.getUser().getLastName());
		playerDto.setTeam(player.getTeam());
		playerDto.setUserId(player.getUser().getId());
		ScorerDTO sc = new ScorerDTO();
		//sc.setPlayer(playerDto);
		return null;
	}

}
