package lifecounter.model.games;

import java.util.HashSet;
import java.util.Set;

import lifecounter.model.Counter;
import lifecounter.model.CounterType;
import lifecounter.model.Player;
import lifecounter.model.Team;
import lifecounter.model.exceptions.NoBackgroundAvailableException;

public class FreeForAllGame extends Game{

	private static final long serialVersionUID = 6124612697552445988L;
	
	private Counter[] counters;
	
	public FreeForAllGame(Player... players) {
		counters = CounterType.DEFAULT.createCounters(players);
	}

	@Override
	public Counter[] getCounters() {
		return counters;
	}


	@Override
	public void reset() {
		for(Counter c : counters)
			c.reset();
	}

	@Override
	public boolean is1vs1() {		
		if(counters == null)
			return false;
		else		
			return counters.length == 2;
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
	public Team[] getTeams() {		
		return null;
	}

	@Override
	public GameBackground getGameBackground() {
		return Game.NO_BACKGROUND;
	}

	@Override
	public Set<GameRequirement> getRequirements() {		
		return new HashSet<GameRequirement>();
	}
	
	

}
