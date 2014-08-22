package rs.tfzr.FudbalT2.service.memory;


import org.springframework.stereotype.Service;

import rs.tfzr.FudbalT2.model.MVP;
import rs.tfzr.FudbalT2.model.Player;
import rs.tfzr.FudbalT2.service.MvpService;

@Service
public class InMemoryMvpService extends AbstractInMemoryService<MVP> implements
		MvpService {
	
	public InMemoryMvpService()
	{
		/*Exhibition exhibition = new Exhibition();
		exhibition.setId(1L);
		exhibition.setExhibitionStart(new Date());
		exhibition.setEnded(true);
		User user = new User();
		user.setId(1L);
		user.setEmail("zjovan.ost@gmail.com");
		user.setFirstName("jovan");
		user.setLastName("zivanov");
		Player player = new Player();
		player.setId(1L);
		player.setUser(user);
		player.setExhibition(exhibition);
		
		MVP mvp = new MVP();
		mvp.setExhibition(exhibition);
		mvp.setPlayer(player);

		// 2
		Exhibition exhibition2 = new Exhibition();
		exhibition2.setId(2L);
		exhibition2.setExhibitionStart(new Date());
		exhibition2.setEnded(true);
		User user2 = new User();
		user2.setId(2L);
		user2.setEmail("zjovan2.ost@gmail.com");
		user2.setFirstName("petar");
		user2.setLastName("petrovic");
		Player player2 = new Player();
		player2.setId(2L);
		player2.setUser(user2);
		player2.setExhibition(exhibition2);

		MVP mvp2 = new MVP();
		mvp2.setExhibition(exhibition2);
		mvp2.setPlayer(player2);

		// 3
		Exhibition exhibition3 = new Exhibition();
		exhibition3.setId(3L);
		exhibition3.setExhibitionStart(new Date());
		exhibition3.setEnded(false);
		Player player3 = new Player();
		player3.setId(3L);
		player3.setUser(user2);
		player3.setExhibition(exhibition3);

		MVP mvp3 = new MVP();
		mvp3.setExhibition(exhibition3);
		mvp3.setPlayer(player3);

		Player player4 = new Player();
		player4.setId(4L);
		player4.setUser(user2);
		player4.setExhibition(exhibition3);

		MVP mvp4 = new MVP();
		mvp4.setExhibition(exhibition2);
		mvp4.setPlayer(player4);
		
		this.save(mvp4);
		this.save(mvp3);
		this.save(mvp2);
		this.save(mvp);*/
	}
	
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

}
