package com.saquib.sod.io;

import com.saquib.sod.engine.Game;
import com.saquib.sod.entity.Being;
import com.saquib.sod.entity.Dungeon;
import com.saquib.sod.entity.Monster;
import com.saquib.sod.entity.Player;
import com.saquib.sod.entity.SavedGame;
import com.saquib.sod.entity.SuperPower;
import com.saquib.sod.entity.actions.Direction;
import com.saquib.sod.entity.actions.GameAction;
import com.saquib.sod.entity.actions.PlayerAction;

/**
 * Defines the external interface to be used in the game for input and output operations.
 * 
 * @author Saquib Sayyad
 *
 */
public interface IO {

	/**
	 * Outputs the game introduction
	 */
	public void intro();

	public GameAction showMenu();

	public void explainSuperPowers();

	/**
	 * Accepts the userInformation and returns a player created based on the input.
	 * 
	 * @return Player instance
	 */
	public Player acceptPlayerDetails();

	public void showPlayerSummary(Player player, Boolean showSuperPowerInfo);

	public void useBonusPoints(Player player);

	public void printCurrentLocation(Player player);

	public void battleWon();

	public void gameOver();

	public Direction acceptDirection(Boolean northPossible, Boolean southPossible, Boolean eastPossible,
			Boolean westPossible, Player player, Dungeon dungeon);

	public void saveGame(Player player, Dungeon dungeon);

	public SavedGame loadGame(Game game);

	public void foundMonsterInRoom(Player player, Monster monster);

	public void battleStart(Player player, Monster monster);

	public void battleStatus(Player player, Monster monster);

	public PlayerAction acceptBattleAction(Player player);

	public SuperPower acceptSuperPowerForAttack(Player player);

	public void monsterDefeated(Monster monster);

	public void granteBonusPoint(Player player, Integer bonusPoints);

	public void monsterUsesSuperPower(Monster monster, Integer damage);

	public void playerSuperPowerAttack(Player player, Being defender, SuperPower superPower);

	public void monsterAttack(Monster monster, Integer damage);

	public void playerAttack(Being monster, Integer damage);

	public void playerLevel(Player player);

	public void startAdventure(Player player);

}
