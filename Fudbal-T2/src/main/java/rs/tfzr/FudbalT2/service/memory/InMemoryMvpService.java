package rs.tfzr.FudbalT2.service.memory;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.MVP;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.ExhibitionService;
import rs.tfzr.FudbalT2.service.MvpService;
import rs.tfzr.FudbalT2.service.ScorerService;

@Service
public class InMemoryMvpService extends AbstractInMemoryService<MVP> implements
		MvpService {
	
	@Autowired
	private ExhibitionService exhibitionService;
	
	@Autowired
	private ScorerService scorerService;
	
	@Override
	public Player getMvpForExhibition(Long exhibitionId) {
		MVP res = new MVP();
		int votesNum = 0;
		int max = -1;
		boolean equal = false;
		
		List<MVP> mvps = findAll();
		for (MVP mvp : mvps) 
		{
			votesNum = 0;
			if(mvp.getExhibition().getId() == exhibitionId)
			{
				for(MVP mvp2: mvps)
				{
					if(mvp2.getExhibition().getId() == exhibitionId && 
							mvp.getPlayer().getId() == mvp2.getPlayer().getId())
					{
						if(res != null && mvp.getPlayer().getId() != res.getPlayer().getId())
							votesNum++;
						else break;
					}
				}
				if(votesNum > max)
				{
					equal = false;
					max = votesNum;
					res = mvp;
				}
				else if(votesNum == max)
				{
					short resScores = 
						scorerService.getPlayerScoreCount(res.getPlayer().getId(), exhibitionId);
					short mvpScores =
						scorerService.getPlayerScoreCount(mvp.getPlayer().getId(), exhibitionId);
					equal = false;
					if(mvpScores > resScores)
						res = mvp;
					else if(resScores == mvpScores)
					{
						resScores = 
							scorerService.getUserScoreCount(res.getPlayer().getUser().getId());
						mvpScores = 
							scorerService.getUserScoreCount(mvp.getPlayer().getUser().getId());
						if(mvpScores > resScores)
							res = mvp;
						else if(resScores == mvpScores)
							equal = true;
					}
				}
			}
		}
		if (res.getId() == null || equal)
			return null;
		else
			return res.getPlayer();
	}

	@Override
	public Player userVoted(Long userId, Long exhibitionId) 
	{
		Player retVal = null;
		for(MVP mvp: findAll())
		{
			if(mvp.getPlayerVote().getUser().getId() == userId && mvp.getExhibition().getId() == exhibitionId)
			{
				retVal = mvp.getPlayer();
				break;
			}
		}
		
		return retVal;
	}

	@Override
	public Exhibition getLastAvailable() 
	{
		List<Exhibition> exs = exhibitionService.findAll();
		Exhibition lastOver = null;
		Exhibition lastNotOver = null;
		for(Exhibition ex: exs)
		{
			if(ex.getEnded())
			{
				if(lastOver == null
						|| ex.getExhibitionStart().after(lastOver.getExhibitionStart()))
					lastOver = ex;
			}
			else
			{
				if(lastNotOver == null
						|| ex.getExhibitionStart().after(lastNotOver.getExhibitionStart()))
					lastNotOver = ex;
			}
		}
		if(lastOver == null) return null;
		if(lastNotOver == null
				|| lastNotOver.getExhibitionStart().before(lastOver.getExhibitionStart()))
			return lastOver;
		else return null;
	}

	@Override
	public List<Player> getMvpHistory() 
	{
		List<Exhibition> exs = exhibitionService.findAll();
		List<Player> his = new ArrayList<Player>();
		for(Exhibition ex: exs)
		{
			if(ex.getEnded())
			{
				Player player = getMvpForExhibition(ex.getId());
				if(player != null)
					his.add(player);
			}
		}
		return his;
	}

}
