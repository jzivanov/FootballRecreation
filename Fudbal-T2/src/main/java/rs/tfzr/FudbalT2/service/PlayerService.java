package rs.tfzr.FudbalT2.service;

import java.util.List;

import rs.tfzr.FudbalT2.model.Player;

public interface PlayerService extends CrudService<Player> {
	List<Player> listAllPlayersOfExhibition(Long exhibitionId);
}
