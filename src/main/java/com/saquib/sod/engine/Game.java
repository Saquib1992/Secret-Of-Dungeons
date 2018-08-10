/**
 * 
 */
package com.saquib.sod.engine;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.saquib.sod.entity.Dungeon;
import com.saquib.sod.entity.Monster;
import com.saquib.sod.entity.Player;
import com.saquib.sod.entity.SavedGame;
import com.saquib.sod.entity.actions.Direction;
import com.saquib.sod.entity.actions.GameAction;
import com.saquib.sod.generator.DungeonGenerator;
import com.saquib.sod.io.ConsoleIO;
import com.saquib.sod.io.IO;

/**
 * Main class for running the game
 * 
 * @author Saquib Sayyad
 *
 */
public class Game {

	private IO io;
	private DungeonGenerator dungeonGenerator;

	public Game() {
		this.io = new ConsoleIO();
		this.dungeonGenerator = new DungeonGenerator();
	}
	
	
	/**
	 * Starts the game engine and loads up the menu.
	 */
	public void init() {

		io.intro();

		GameAction action = io.showMenu();

		switch (action) {
		case START:
			this.startNewGame();
			break;
		case RESUME:
			SavedGame savedGame = io.loadGame(this);
			if (savedGame != null) {
				this.resume(savedGame.getPlayer(), savedGame.getDungeon());
			}
			break;
		case EXIT:
			System.exit(0);
			break;
		}
	}

	/**
	 * Starts a new game
	 */
	public void startNewGame() {

		// Create Dungeon with monsters
		Dungeon dungeon = dungeonGenerator.generateDungeon();

		// Create player with details
		Player player = io.acceptPlayerDetails();

		// Spawn player in the first room of the dungeon
		player.setCurrentRoom(dungeon.getRoom(0, 0));
		player.setLocationX(0);
		player.setLocationY(0);

		this.playGame(player, dungeon);

	}

	/**
	 * Starts a game with defined player and dungeon
	 */
	private void playGame(Player player, Dungeon dungeon) {
		
		io.startAdventure(player);
		
		while (player.isAlive() && !dungeon.isComplete(player.getCurrentRoom())) {
			this.movePlayer(player, dungeon);
			Monster monster = player.getCurrentRoom().getMonster();
			if (monster.isAlive()) {
	        	io.foundMonsterInRoom(player, monster);
	            new Battle().startBattle(player, monster);;
	        }
			
		}
		if (player.isAlive()) {
			io.battleWon();
		} else {
			io.gameOver();
		}
	}

	/**
	 * Move player in the dungeon
	 */
	private void movePlayer(Player player, Dungeon dungeon) {
		boolean northPossible = dungeon.roomExists(player.getLocationX(), player.getLocationY() + 1);
		boolean southPossible = dungeon.roomExists(player.getLocationX(), player.getLocationY() - 1);
		boolean eastPossible = dungeon.roomExists(player.getLocationX() + 1, player.getLocationY());
		boolean westPossible = dungeon.roomExists(player.getLocationX() - 1, player.getLocationY());

		Direction direction = io.acceptDirection(northPossible, southPossible, eastPossible, westPossible, player,
				dungeon);

		switch (direction) {
		case NORTH:
			if (northPossible)
				player.addLocationY(1);
			break;
		case SOUTH:
			if (southPossible)
				player.addLocationY(-1);
			break;
		case EAST:
			if (eastPossible)
				player.addLocationX(1);
			break;
		case WEST:
			if (westPossible)
				player.addLocationX(-1);
			break;

		default:
			break;
		}

		player.setCurrentRoom(dungeon.getRoom(player.getLocationX(), player.getLocationY()));
		io.printCurrentLocation(player);
	}

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		Game game = new Game();
		game.init();
	}

	/**
	 * Resumes the game from player and dungeon objects
	 * 
	 * @param player
	 * @param dungeon
	 */
	public void resume(Player player, Dungeon dungeon) {
		this.playGame(player, dungeon);
	}
}
