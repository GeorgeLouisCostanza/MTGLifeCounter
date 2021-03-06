package lifecounter.model.games;

import java.util.HashSet;
import java.util.Set;

import lifecounter.model.Counter;
import lifecounter.model.CounterType;
import lifecounter.model.Player;
import lifecounter.model.Team;
import lifecounter.model.exceptions.NoBackgroundAvailableException;

public class TeamGame extends Game{

	private static final long serialVersionUID = -5822116547092744693L;
	
	private Team leftTeam;
	private Team rightTeam;
	
	public TeamGame(Team left, Team right) {
		leftTeam = left;
		rightTeam = right;
	}
	
	public TeamGame(Player left, Player right) {
		this(new Team(CounterType.DEFAULT, left),new Team(CounterType.DEFAULT, right));
	}

	public TeamGame(CounterType c, Player[] left, Player[] right) {
		this(new Team(c,left), new Team(c,right));
	}
	
	public Team getLeftTeam() {
		return leftTeam;
	}

	public Team getRightTeam() {
		return rightTeam;
	}

	@Override
	public Player[] getPlayers() {
		Player[] result = new Player[leftTeam.countPlayers() + rightTeam.countPlayers()];
		for(int i = 0; i < leftTeam.countPlayers(); i++)
			result[i] = leftTeam.getCounter(i).getPlayer();
		for(int i = 0; i < rightTeam.countPlayers(); i++)
			result[i + leftTeam.countPlayers()] = rightTeam.getCounter(i).getPlayer();
		return result;
	}

	@Override
	public void reset() {
		leftTeam.reset();
		rightTeam.reset();
	}

	@Override
	public boolean is1vs1() {
		if(leftTeam == null || rightTeam == null)
			return false;
		else
			return leftTeam.countPlayers() == 1 && rightTeam.countPlayers() == 1;
	}

	

	@Override
	public Team[] getTeams() {		
		return new Team[] {leftTeam, rightTeam};
	}

	@Override
	public Counter[] getCounters() {
		Counter[] result = new Counter[leftTeam.countPlayers() + rightTeam.countPlayers()];
		for(int i = 0; i < leftTeam.countPlayers(); i++)
			result[i] = leftTeam.getCounter(i);
		for(int i = 0; i < rightTeam.countPlayers(); i++)
			result[i + leftTeam.countPlayers()] = rightTeam.getCounter(i);
		return result;
	}

	@Override
	public GameBackground getGameBackground() {
		return Game.NO_BACKGROUND;
	}	
	
	@Override
	public void nextGameBackground() throws NoBackgroundAvailableException {		
		throw new NoBackgroundAvailableException();
	}
	
	@Override
	public void lastGameBackground() throws NoBackgroundAvailableException {
		throw new NoBackgroundAvailableException();		
	}

	@Override
	public Set<GameRequirement> getRequirements() {		
		return new HashSet<GameRequirement>();
	}
	
	
}
