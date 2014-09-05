package rs.tfzr.FudbalT2.web.converter;

import org.springframework.core.convert.converter.Converter;

import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.service.PlayerService;
import rs.tfzr.FudbalT2.web.dto.PlayerDTO;

public class StringToPlayerConverter implements Converter<String, PlayerDTO>
{
	private PlayerService playerService;
	@Override
	public PlayerDTO convert(String playerId) 
	{
		Player player = playerService.findOne(Long.parseLong(playerId));
		PlayerDTO dto = new PlayerDTO();
		dto.setExhibitionId(player.getExhibition().getId());
		dto.setExhibitionStart(player.getExhibition().getExhibitionStart());
		dto.setFirstName(player.getUser().getFirstName());
		dto.setId(player.getId());
		dto.setLastName(player.getUser().getLastName());
		dto.setTeam(player.getTeam());
		dto.setUserId(player.getUser().getId());
		return dto;
	}

}
