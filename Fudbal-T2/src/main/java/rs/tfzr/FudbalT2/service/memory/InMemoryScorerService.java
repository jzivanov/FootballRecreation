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

import rs.tfzr.FudbalT2.model.Scorers;
import rs.tfzr.FudbalT2.service.ScorerService;

@Service
public class InMemoryScorerService extends AbstractInMemoryService<Scorers>
		implements ScorerService {
	
	public InMemoryScorerService()
	{
		/*Exhibition exhibition = new Exhibition();
		exhibition.setId(1L);
		User user = new User();
		user.setId(1L);
		user.setEmail("zjovan.ost@gmail.com");
		user.setFirstName("jovan");
		user.setLastName("zivanov");
		Player player = new Player();
		player.setId(1L);
		player.setUser(user);
		player.setExhibition(exhibition);

		Scorers scorer = new Scorers();
		scorer.setId(1L);
		scorer.setExhibition(exhibition);
		scorer.setPlayer(player);

		// 2
		Exhibition exhibition2 = new Exhibition();
		exhibition2.setId(2L);
		User user2 = new User();
		user2.setId(2L);
		user2.setEmail("zjovan2.ost@gmail.com");
		user2.setFirstName("jovan2");
		user2.setLastName("zivanov2");
		Player player2 = new Player();
		player2.setId(2L);
		player2.setUser(user2);
		player2.setExhibition(exhibition2);

		Scorers scorer2 = new Scorers();
		scorer2.setId(2L);
		scorer2.setExhibition(exhibition2);
		scorer2.setPlayer(player2);

		// 3
		Exhibition exhibition3 = new Exhibition();
		exhibition3.setId(3L);
		Player player3 = new Player();
		player3.setId(3L);
		player3.setUser(user2);
		player3.setExhibition(exhibition3);

		Scorers scorer3 = new Scorers();
		scorer3.setId(3L);
		scorer3.setExhibition(exhibition3);
		scorer3.setPlayer(player3);

		Player player4 = new Player();
		player4.setId(4L);
		player4.setUser(user2);
		player4.setExhibition(exhibition3);
		Scorers scorer4 = new Scorers();
		scorer4.setId(4L);
		scorer4.setExhibition(exhibition3);
		scorer4.setPlayer(player4);
		
		this.save(scorer4);
		this.save(scorer3);
		this.save(scorer2);
		this.save(scorer);*/
	}
	
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
