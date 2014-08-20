package rs.tfzr.FudbalT2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import rs.tfzr.FudbalT2.model.MVP;
import rs.tfzr.FudbalT2.repository.MvpRepository;
import rs.tfzr.FudbalT2.service.MvpService;

public class MvpServiceImpl implements MvpService {
	@Autowired
	MvpRepository mvpRepository;

	@Override
	public MVP findOne(Long id) {
		return mvpRepository.findOne(id);
	}

	@Override
	public List<MVP> findAll() {
		return mvpRepository.findAll();
	}

	@Override
	public MVP save(MVP mvp) {
		return mvpRepository.save(mvp);
	}

	@Override
	public void remove(Long id) throws IllegalArgumentException {
		MVP mvp = mvpRepository.findOne(id);
		if (mvp == null) {
			throw new IllegalArgumentException("MVP with id " + id
					+ " does not exist.");
		}
		mvpRepository.delete(id);
	}

	@Override
	public MVP getMvpForExhibition(Long ExhibitionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
