package rs.tfzr.FudbalT2.web.dto;

public class ScorerDTO 
{
	private Long playerId;
	private short goalCount;
	
	public short getGoalCount() {
		return goalCount;
	}
	public void setGoalCount(short goalCount) {
		this.goalCount = goalCount;
	}
	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
}
