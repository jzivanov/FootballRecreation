package rs.tfzr.FudbalT2.service.memory;

import java.util.ArrayList;
import java.util.List;

import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.service.PlayerService;

public class InMemoryPlayerService extends AbstractInMemoryService<Player>
		implements PlayerService {
	@Override
	public List<Player> listAllPlayersOfExhibition(Long exhibitionId) {
		List<Player> list = new ArrayList<>();
		for (Player p : findAll()) {
			if (p.getExhibition().getId() == exhibitionId)
				list.add(p);
		}
		return list;
	}

}
