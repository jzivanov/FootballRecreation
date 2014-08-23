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
	
	public InMemoryScorerService()
	{
		/*User user = new User();
		user.setId(1L);
		user.setEmail("zjovan.ost@gmail.com");
		user.setFirstName("jovan");
		user.setLastName("zivanov");
		
		User user2 = new User();
		user2.setId(2L);
		user2.setEmail("pera@kojot.supergenije");
		user2.setFirstName("pera kojot");
		user2.setLastName("super genije");
		
		User user3 = new User();
		user3.setId(3L);
		user3.setEmail("bip@bip.bip");
		user3.setFirstName("bip");
		user3.setLastName("bip");
		
		Exhibition exhibition = new Exhibition();
		exhibition.setId(1L);
		
		Exhibition exhibition2 = new Exhibition();
		exhibition2.setId(2L);
		
		Player player1 = new Player();
		player1.setId(1L);
		player1.setUser(user);
		player1.setExhibition(exhibition);

		Player player2 = new Player();
		player2.setId(2L);
		player2.setUser(user2);
		player2.setExhibition(exhibition);
		
		Player player3 = new Player();
		player3.setId(3L);
		player3.setUser(user3);
		player3.setExhibition(exhibition);

		Player player4 = new Player();
		player4.setId(4L);
		player4.setUser(user2);
		player4.setExhibition(exhibition2);
		
		Player player5 = new Player();
		player5.setId(5L);
		player5.setUser(user3);
		player5.setExhibition(exhibition2);
		
		Scorers scorer1 = new Scorers();
		scorer1.setExhibition(exhibition);
		scorer1.setPlayer(player1);
		
		Scorers scorer2 = new Scorers();
		scorer2.setExhibition(exhibition);
		scorer2.setPlayer(player1);
		
		Scorers scorer3 = new Scorers();
		scorer3.setExhibition(exhibition2);
		scorer3.setPlayer(player3);
		
		this.save(scorer1);
		this.save(scorer2);
		this.save(scorer3);*/
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
