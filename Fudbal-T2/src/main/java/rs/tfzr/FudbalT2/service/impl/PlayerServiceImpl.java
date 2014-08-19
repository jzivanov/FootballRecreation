package rs.tfzr.FudbalT2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.repository.PlayerRepository;
import rs.tfzr.FudbalT2.service.PlayerService;

public class PlayerServiceImpl implements PlayerService
{
	@Autowired
	PlayerRepository playerRepository;
	
	@Override
	public Player findOne(Long id) {
		return playerRepository.findOne(id);
	}

	@Override
	public List<Player> findAll() {
		return playerRepository.findAll();
	}

	@Override
	public Player save(Player player) {
		return playerRepository.save(player);
	}

	@Override
	public void remove(Long id) throws IllegalArgumentException {
		Player player = playerRepository.findOne(id);
		if(player == null)
		{
            throw new IllegalArgumentException("Player with id " + id + " does not exist.");
		}
		playerRepository.delete(id);
	}

	@Override
	public List<Player> listAllPlayersOfExhibition(Long exhibitionId) {
		return playerRepository.listAllPlayersOfExhibition(exhibitionId);
	}

}