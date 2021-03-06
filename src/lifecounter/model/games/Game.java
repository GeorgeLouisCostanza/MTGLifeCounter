package lifecounter.model.games;

import java.io.Serializable;
import java.util.Random;
import java.util.Set;

import lifecounter.model.Counter;
import lifecounter.model.Player;
import lifecounter.model.Team;
import lifecounter.model.exceptions.NoBackgroundAvailableException;

/**
 * Abstract class for implementing different game types.
 * @author Mirko
 *
 */
public abstract class Game implements Serializable{

	private static final long serialVersionUID = 4633630495547334989L;
	
	/**
	 * The standard background if no background should be used.
	 */
	public static final GameBackground NO_BACKGROUND = new GameBackground(PlaneType.none,0 , 0);
	
	/**
	 * Returns all players in the game.
	 * @return An array of all players of the game.
	 */
	public Player[] getPlayers() {
		
		Counter[] counters = getCounters();
		
		Player[] result = new Player[counters.length];
		
		for(int i = 0; i < counters.length; i++)
			result[i] = counters[i].getPlayer();
		
		return result;
	}
	
	/**
	 * Returns an array with all teams.
	 * @return An array of all teams or null if there are no teams.
	 */
	public abstract Team[] getTeams();
	
	/**
	 * Resets the game to its standard value.
	 */
	public abstract void reset();
	
	/**
	 * Returns whether the game is a 1 vs 1 game.
	 * @return True if it is a 1 vs 1 game.
	 */	
	public abstract boolean is1vs1();
	
	/**
	 * Sets the next background for this game.
	 */
	public abstract void nextGameBackground() throws NoBackgroundAvailableException;	
	
	/**
	 * Sets the previous background for this game.
	 */
	public abstract void lastGameBackground() throws NoBackgroundAvailableException;	
	
	/**
	 * Returns the current game background.
	 */
	public abstract GameBackground getGameBackground();
	
	public abstract Set<GameRequirement> getRequirements();	
		
	/**
	 * Returns a random player from among all players with a new {@link Random} object.
	 * @return A random player from among the players in this game.
	 */	
	public Player randomPlayer() {
		return randomPlayer(new Random());		
	}
	
	public Player randomPlayer(Random randSrc) {
		Player[] players = getPlayers();
		return players[randSrc.nextInt(players.length)];
	}
	
	public abstract Counter[] getCounters();
		
	
	
	/**
	 * Class for specifying game backgrounds in the model.
	 * @author Mirko
	 *
	 */
	public static class GameBackground {
		private final int resourceNumber;
		private final PlaneType type;
		private final int edition;
		
		public GameBackground(PlaneType type, int edition, int resourceNumber) {
			this.type = type;
			this.resourceNumber = resourceNumber;
			this.edition = edition;
		}

		public int getResourceNumber() {
			return resourceNumber;
		}

		public PlaneType getType() {
			return type;
		}		
		
		public int getEdition() {
			return edition;
		}
	}
	
	public static enum GameRequirement {
		PictureSlideshow
	}
	
	/**
	 * The type of a background. Used to match the right resource IDs.
	 * @author Mirko
	 *
	 */
	public static enum PlaneType{
		none, planechase, archenemy
	}
}
