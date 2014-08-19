package rs.tfzr.FudbalT2.model;

public class MVP extends AbstractBaseEntity
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private Player player;
	private Exhibition exhibition;
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Exhibition getExhibition() {
		return exhibition;
	}
	public void setExhibition(Exhibition exhibition) {
		this.exhibition = exhibition;
	}
	
	public MVP()
	{
		
	}
	
	public MVP(Player player)
	{
		this.player = player;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        return true;
    }
	
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + player.hashCode();
        result = 31 * result + exhibition.hashCode();
        return result;
    }
}
