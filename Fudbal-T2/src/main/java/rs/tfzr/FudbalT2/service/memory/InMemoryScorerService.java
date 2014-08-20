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

import rs.tfzr.FudbalT2.model.Scorers;
import rs.tfzr.FudbalT2.service.ScorerService;

public class InMemoryScorerService extends AbstractInMemoryService<Scorers>
		implements ScorerService {
	@Override
	public List<Scorers> listAllScorers(Long exhibitionId) {
		List<Scorers> list = new ArrayList<>();
		for (Scorers scorer : findAll()) {
			if (scorer.getExhibition().getId() == exhibitionId)
				list.add(scorer);
		}
		return list;
	}

	@Override
	public List<Scorers> getRankList() {
		List<Scorers> rankList = new ArrayList<>();
		Map<Scorers, Integer> map = new HashMap<>();
		for (Scorers scorer : findAll()) {
			int num = 0;
			for (Scorers scorer2 : findAll()) {
				if (scorer.getPlayer().getUser().getId() == scorer2.getPlayer()
						.getUser().getId())
					num++;
			}
			boolean exists = false;
			for (Scorers scorer2 : map.keySet()) {
				if (scorer.getId() != scorer2.getId()
						&& scorer.getPlayer().getUser().getId() == scorer2
								.getPlayer().getUser().getId()) {
					exists = true;
				}
			}
			if (!exists)
				map.put(scorer, num);
		}
		rankList.addAll(sortByComparator(map).keySet());
		return rankList;
	}

	private static Map<Scorers, Integer> sortByComparator(
			Map<Scorers, Integer> unsortMap) {
		List<Map.Entry<Scorers, Integer>> list = new LinkedList<Map.Entry<Scorers, Integer>>(
				unsortMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<Scorers, Integer>>() {
			public int compare(Map.Entry<Scorers, Integer> o1,
					Map.Entry<Scorers, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		Map<Scorers, Integer> sortedMap = new LinkedHashMap<Scorers, Integer>();
		for (Iterator<Map.Entry<Scorers, Integer>> it = list.iterator(); it
				.hasNext();) {
			Map.Entry<Scorers, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
}
