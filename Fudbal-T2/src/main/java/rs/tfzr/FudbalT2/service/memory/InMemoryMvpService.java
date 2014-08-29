package rs.tfzr.FudbalT2.service.memory;


import org.springframework.stereotype.Service;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.MVP;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.MvpService;

@Service
public class InMemoryMvpService extends AbstractInMemoryService<MVP> implements
		MvpService {
	
	@Override
	public Player getMvpForExhibition(Long exhibitionId) {
		MVP res = new MVP();
		int votesNum = 0;
		int max = 0;
		
		for (MVP mvp : findAll()) 
		{
			votesNum = 0;
			if(mvp.getExhibition().getId() == exhibitionId)
			{
				for(MVP mvp2: findAll())
				{
					if(mvp2.getExhibition().getId() == exhibitionId && 
							mvp.getPlayer().getId() == mvp2.getPlayer().getId())
						votesNum++;
				}
				if(votesNum > max)
				{
					max = votesNum;
					res = mvp;
				}
			}
		}
		if (res.getId() == null)
			return null;
		else
			return res.getPlayer();
	}

	@Override
	public boolean playerVoted(Long playerId, Long exhibitionId) 
	{
		boolean retVal = false;
		for(MVP mvp: findAll())
		{
			if(mvp.getPlayerVote().getId() == playerId && mvp.getExhibition().getId() == exhibitionId)
			{
				retVal = true;
				break;
			}
		}
		
		return retVal;
	}

}
