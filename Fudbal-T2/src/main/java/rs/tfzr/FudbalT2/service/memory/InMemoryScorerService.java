package rs.tfzr.FudbalT2.service.memory;

import java.util.ArrayList;
import java.util.List;

import rs.tfzr.FudbalT2.model.Scorers;
import rs.tfzr.FudbalT2.service.ScorerService;

public class InMemoryScorerService extends AbstractInMemoryService<Scorers> implements ScorerService
{

	@Override
	public List<Scorers> listAllScorers(Long exhibitionId) 
	{
		List<Scorers> list = new ArrayList<>();
		for(Scorers scorer: findAll())
		{
			if(scorer.getExhibition().getId() == exhibitionId)
				list.add(scorer);
		}
		return list;
	}

	@Override
	public List<Scorers> getRankList() 
	{
		return null;
	}

}
