/**
 * 
 */
package com.saquib.sod.entity;

/**
 * Represents a saved game instance of player and dungeon
 * @author Saquib Sayyad
 *
 */
public class SavedGame {

	private Player player;
	private Dungeon dungeon;

	public SavedGame(Player player, Dungeon dungeon) {
		super();
		this.player = player;
		this.dungeon = dungeon;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the dungeon
	 */
	public Dungeon getDungeon() {
		return dungeon;
	}

	/**
	 * @param dungeon the dungeon to set
	 */
	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
}
