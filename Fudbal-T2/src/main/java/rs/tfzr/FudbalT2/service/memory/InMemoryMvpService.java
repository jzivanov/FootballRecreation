package rs.tfzr.FudbalT2.service.memory;

import org.springframework.stereotype.Service;

import rs.tfzr.FudbalT2.model.MVP;
import rs.tfzr.FudbalT2.service.MvpService;

@Service
public class InMemoryMvpService extends AbstractInMemoryService<MVP> implements
		MvpService {
	@Override
	public MVP getMvpForExhibition(Long exhibitionId) {
		MVP res = new MVP();
		for (MVP mvp : findAll()) {
			if (mvp.getExhibition().getId() == exhibitionId) {
				res = mvp;
				break;
			}
		}
		if (res.getId() == null)
			return null;
		else
			return res;
	}

}
