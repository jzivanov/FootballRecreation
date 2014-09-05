package rs.tfzr.FudbalT2.service.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.model.Scorers;
import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.ScorerService;

@Service
public class InMemoryScorerService extends AbstractInMemoryService<Scorers>
		implements ScorerService {
	
	@Override
	public Map<Scorers, Short> getRankList(Long exhibitionId) {
		Map<Scorers, Short> map = new HashMap<>();
		for (Scorers scorer : findAll()) {
			if (scorer.getExhibition().getId() == exhibitionId
				&& !containsScorer(map, scorer))
					map.put(scorer, (short) getPlayerScoreCount(scorer.getPlayer().getId(), exhibitionId));
		}
		return sortByComparator(map);
	}
	
	private boolean containsScorer(Map<Scorers, Short> map, Scorers scorer)
	{
		for(Scorers s: map.keySet())
		{
			if(s.getExhibition().getId() == scorer.getExhibition().getId()
					&& s.getPlayer().getId() == scorer.getPlayer().getId())
			{
				return true;
			}
		}
		return false;
	}
	
	private boolean containsUser(Map<Scorers, Short> map, Scorers scorer)
	{
		for(Scorers s: map.keySet())
		{
			if(s.getPlayer().getUser().getId() == scorer.getPlayer().getUser().getId())
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public Map<Scorers, Short> getRankList() {
		Map<Scorers, Short> map = new HashMap<>();
		for (Scorers scorer : findAll()) {
			if (!containsUser(map, scorer))
			{
				short num = getUserScoreCount(scorer.getPlayer().getUser().getId());
				map.put(scorer, num);
			}
		}
		return sortByComparator(map);
	}

	private static Map<Scorers, Short> sortByComparator(
			Map<Scorers, Short> unsortMap) {
		List<Map.Entry<Scorers, Short>> list = new LinkedList<Map.Entry<Scorers, Short>>(
				unsortMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<Scorers, Short>>() {
			public int compare(Map.Entry<Scorers, Short> o1,
					Map.Entry<Scorers, Short> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		Map<Scorers, Short> sortedMap = new LinkedHashMap<Scorers, Short>();
		for (Iterator<Map.Entry<Scorers, Short>> it = list.iterator(); it
				.hasNext();) {
			Map.Entry<Scorers, Short> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	@Override
	public short getUserScoreCount(Long userId) 
	{
		short scores = 0;
		List<Scorers> list = findAll();
		for(Scorers scorer: list)
		{
			if(scorer.getPlayer().getUser().getId() == userId)
				scores++;
		}
		return scores;
	}

	@Override
	public byte getPlayerScoreCount(Long playerId, Long exhibitionId) {
		byte scores = 0;
		List<Scorers> list = findAll();
		for(Scorers scorer: list)
		{
			if(scorer.getExhibition().getId() == exhibitionId
					&& scorer.getPlayer().getId() == playerId)
				scores++;
		}
		return scores;
	}
}
