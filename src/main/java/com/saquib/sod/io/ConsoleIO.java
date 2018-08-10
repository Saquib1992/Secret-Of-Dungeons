package com.saquib.sod.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import com.saquib.sod.config.GameConfiguration;
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
 * Implements a console based input/output, to be used in the game.
 * @author Saquib Sayyad
 *
 */
public class ConsoleIO implements IO {

	BufferedReader reader;

	public ConsoleIO() {
		this.reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));
	}

	/**
	 * Writes the data to output stream
	 * 
	 * @param data     Data to be written.
	 * @param encoding Encoding schema.
	 */
	public void write(byte[] data, String encoding) {
		try {
			System.out.print(new String(data, encoding));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads a Character from input stream.
	 * 
	 * @return Character
	 */
	public char readChar() {
		try {
			return reader.readLine().charAt(0);
		} catch (IOException e) {
			write(String.format("Unable to read character. [%s]", e.getMessage()));
			System.exit(-1);
		}
		return 0;
	}

	/**
	 * Reads an Integer from input stream.
	 * 
	 * @return Integer
	 */
	public Integer readInteger() {
		while (true) {
			try {
				String string = this.reader.readLine();
				int number = Integer.parseInt(string);
				return number;
			} catch (Exception e) {
				write("Please enter valid number: ");
			}
		}

	}

	/**
	 * Reads a line of String from input stream.
	 * 
	 * @return String
	 */
	public String readStringLine() {
		while (true) {
			try {
				return this.reader.readLine();
			} catch (Exception e) {
				write(String.format("IO Error [%s]\n", e.getMessage()));
			}
		}
	}

	/**
	 * Outputs the game introduction
	 */
	public void intro() {
		write("***************************************************\n");
		write("Welcome to Secret of the Dungeons\n");
		write("***************************************************\n");
	}

	public void explainSuperPowers() {
		List<SuperPower> superPowers = GameConfiguration.PLAYER_SUPERPOWERS;

		for (SuperPower superPower : superPowers) {
			write(String.format("%d. %s \n", superPowers.indexOf(superPower) + 1, superPower.getName()));
			write(String.format("\tDescription:\t %s\n", superPower.getDescription()));
			write(String.format("\tDamage: %d\t Health Regeneration: %d\t Mana Cost: %d\n",
					superPower.getAttackDamage(), superPower.getHealPoints(), superPower.getManaCost()));
		}
	}

	/**
	 * Accepts the userInformation and returns a player created based on the input.
	 * 
	 * @return Player instance
	 */
	public Player acceptPlayerDetails() {
		int choice;

		write("Create your hero.\n");
		write("Enter Your Hero Name: ");
		String name = this.readStringLine();

		Player player = new Player(name, GameConfiguration.INITIAL_HEALTH, GameConfiguration.INITIAL_DAMAGE);
		player.setMana(GameConfiguration.INITIAL_MANA);
		player.setBonusPoints(GameConfiguration.INITIAL_BONUS_POINTS);

		write("Chose any two super powers listed below: \n");
		this.explainSuperPowers();

		for (int i = 0; i < GameConfiguration.TOTAL_SUPER_POWER_CAN_HAVE; ++i) {

			write("Enter selection: ");
			choice = readInteger();
			if (choice > GameConfiguration.PLAYER_SUPERPOWERS.size()) {
				write("Please select correct opetion.");
				--i;
				continue;
			}

			SuperPower sup = GameConfiguration.PLAYER_SUPERPOWERS.get(choice - 1);

			if (player.getSuperPower().containsValue(sup)) {
				write("Super Power already added, select another.\n");
				--i;
				continue;
			}
			player.getSuperPower().put(i, sup);

			write(String.format("%s power is added\n", sup.getName()));

		}
		this.useBonusPoints(player);
		write("Player created, below are the details. \n");
		this.showPlayerSummary(player, true);
		return player;
	}

	public void showPlayerSummary(Player player, Boolean showSuperPowerInfo) {
		write("\n--------------------------------------------------\n");
		write(String.format("Name: %s\tHealth: %d\nDamage: %d\tMana: %d\n", player.getName(), player.getHealthPoint(),
				player.getDamage(), player.getMana()));
		write(String.format("Level: %d\n", player.getLevel()));

		if (showSuperPowerInfo) {
			write("Super Powers: ");

			Set<Integer> keys = player.getSuperPower().keySet();
			String powerList = "";

			for (Integer key : keys) {
				powerList += player.getSuperPower().get(key).getName() + ", ";
			}
			powerList = powerList.substring(0, powerList.length() - 2) + "\n";
			write(powerList);
		}
		write("--------------------------------------------------\n");
	}

	public void useBonusPoints(Player player) {
		write(String.format(
				"You have %d bonus points, which can be used to increase your health point, attack damage or mana.\n",
				player.getBonusPoints()));
		Integer feature, points;
		while (player.getBonusPoints() > 0) {

			this.explainFeatures();
			write("Select feature to apply bonus points: ");
			feature = readInteger();
			while (true) {
				write(String.format("Enter bonus points to be used (1-%s): ", player.getBonusPoints()));
				points = readInteger();
				if (points < 0 || points > player.getBonusPoints()) {
					write("Please enter correct bonus points.\n");
					continue;
				}
				break;
			}

			switch (feature) {
			case 1:
				player.addBonusHealth(points);
				break;
			case 2:
				player.addBonusDamage(points);
				break;
			case 3:
				player.addBonusMana(points);
				break;
			default:
				write("Please select correct option.\n");
				continue;
			}

			write(String.format("Remaining bonus points %s \n", player.getBonusPoints()));
		}

	}

	private void explainFeatures() {
		write("1.\tHealth\n");
		write("2.\tDamage\n");
		write("3.\tMana\n");
	}

	public void printCurrentLocation(Player player) {
		write(String.format("You are in %s.\n", player.getCurrentRoom().getDescription()));

	}

	public void battleWon() {
		write("Good job, you killed the monster.");
	}

	public void gameOver() {
		write("You are dead! Game Over.");
	}

	public Direction acceptDirection(Boolean northPossible, Boolean southPossible, Boolean eastPossible,
			Boolean westPossible, Player player, Dungeon dungeon) {
	
		drawDirections(northPossible, southPossible, eastPossible, westPossible);
		write("Where would you like to go :");

		while (true) {
			if (northPossible) {
				write(" North (n)");
			}
			if (eastPossible) {
				write(" East (e)");
			}
			if (southPossible) {
				write(" South (s)");
			}
			if (westPossible) {
				write(" West (w)");
			}
			write(" Quit(q)?");

			Character direction = this.readChar();
			switch (direction) {
			case 'n':
				return Direction.NORTH;
			case 's':
				return Direction.SOUTH;
			case 'e':
				return Direction.EAST;
			case 'w':
				return Direction.WEST;
			case 'q':
				write("Would you like to save game(y/n)?");
				Character option = this.readChar();
				if (Character.toLowerCase(option) == 'y') {
					this.saveGame(player, dungeon);
				}
				this.exit();
			default:
				write("Please enter correct direction.\n");
			}
		}
	}

	private void drawDirections(Boolean northPossible, Boolean southPossible, Boolean eastPossible,
			Boolean westPossible) {

		write("Directions:\n");
		if(northPossible) {
			write("    N\n");
			write("    \u25B2\n");
		}
		if(eastPossible) {
			write("E \u25C4 \u25A0");
		}else {
			write("    \u25A0");
		}
		if(westPossible) {
			write(" \u25BA W");
		}
		write("\n");
		if(southPossible) {
			write("    \u25BC\n");
			write("    S\n");
		}
		
	}

	public void saveGame(Player player, Dungeon dungeon) {
		write("Enter file name to save progress in: ");
		String filename = this.readStringLine();

		String fullPath = Paths.get(".").toAbsolutePath().normalize().toString() + File.separator + filename;

		try {

			ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(fullPath));
			oout.writeObject(player);
			oout.writeObject(dungeon);
			oout.close();

			write(String.format("Game saved at \"%s\"\n", fullPath));
		} catch (IOException e) {
			write(String.format("Unable to save game. (%s) ", e.getMessage()));
		}
	}

	public SavedGame loadGame(Game game) {

		write("Enter file name to load game progress from: ");
		String filename = this.readStringLine();

		String fullPath = Paths.get(".").toAbsolutePath().normalize().toString() + File.separator + filename;

		ObjectInputStream oin;
		try {
			oin = new ObjectInputStream(new FileInputStream(fullPath));
			Player player = (Player) oin.readObject();
			Dungeon dungeon = (Dungeon) oin.readObject();
			oin.close();
			write(String.format("Loaded game from \"%s\"\n", fullPath));

			return new SavedGame(player, dungeon);

		} catch (Exception e) {
			write(String.format("Unable to load game. (%s) ", e.getMessage()));
		}
		return null;
	}

	private void exit() {
		write("Thankyou for playing.\n");
		System.exit(0);
	}

	public void foundMonsterInRoom(Player player, Monster monster) {
		write(String.format("You encounter %s\n", monster.getName()));
	}

	public void battleStart(Player player, Monster monster) {
		write(String.format("Battle with %s starts.\n", monster.getName()));
	}

	public void battleStatus(Player player, Monster monster) {
		
		write("-----------------------------------------------------------------------------------------------\n");
		write(String.format(
				"Status: (%s : Health %d, Mana %d, Attack Damage %d) / (%s : Health %d, Attack Damage %d)\n",
				player.getName(), player.getHealthPoint(), player.getMana(), player.getDamage(), monster.getName(),
				monster.getHealthPoint(), monster.getDamage()));
		write("-----------------------------------------------------------------------------------------------\n");
	}

	public PlayerAction acceptBattleAction(Player player) {

		while (true) {
			write("Select any of below option: \n");
			write("1.\tAttack\n2.\tUse Super Power\n");
			write("Enter Option: \n");
			Integer option = readInteger();
			switch (option) {
			case 1:
				return PlayerAction.ATTACK;
			case 2:
				if (player.isManaEnoughToSuperPower()) {
					return PlayerAction.SUPER_POWER;
				} else {
					write(String.format("Cannot use super power, insufficient mana. (Mana: %s)", player.getMana()));
				}
			default:
				write("Select correct option.\n");
				continue;
			}

		}
	}

	public SuperPower acceptSuperPowerForAttack(Player player) {
		write("Select any super power from below: \n");

		while (true) {
			Set<Integer> keys = player.getSuperPower().keySet();
			for (Integer key : keys) {
				SuperPower superPower = player.getSuperPower().get(key);
				write(String.format("%s.\t%s\n", (key + 1), superPower.getName()));
			}
			write("Enter option: ");
			SuperPower power = player.getSuperPower().get(readInteger() - 1);
			if (power == null) {
				write("Enter correct option.");
				continue;
			}
			return power;
		}
	}

	public void monsterDefeated(Monster monster) {
		write(String.format("\nGood Job! %s has been defeated.\n", monster.getName()));
	}

	public void granteBonusPoint(Player player, Integer bonusPoints) {
		write(String.format("Congratulations, You have been granted %d bonus points.\n", bonusPoints));
		this.useBonusPoints(player);
	}

	public void monsterUsesSuperPower(Monster monster, Integer damage) {
		write(String.format("\n%s hits you using %s, damage caused %d. \n", monster.getName(),
				monster.getSuperPower().getName(), damage));
	}

	public void playerSuperPowerAttack(Player player, Being defender, SuperPower superPower) {

		if (superPower.getAttackDamage() > 0) {
			write(String.format("\nYou used %s on %s, damage caused %d, Mana used %d. \n", superPower.getName(),
					defender.getName(), superPower.getAttackDamage(), superPower.getManaCost()));
		} else if (superPower.getAttackDamage() == 0 && superPower.getHealPoints() > 0) {
			write(String.format("\nYou healed yourself, added health is %d, Mana used %d.\n", superPower.getHealPoints(),
					superPower.getManaCost()));
		} else {
			write(String.format("\nYou used %s power.\n", superPower.getName()));
		}
	}

	public void monsterAttack(Monster monster, Integer damage) {
		write(String.format("\n%s hits you causing %d damage. \n", monster.getName(), damage));
	}

	public void playerAttack(Being monster, Integer damage) {
		write(String.format("You hit %s causing %d damage. \n", monster.getName(), damage));
	}

	private void write(String line) {
		try {
			this.write(line.getBytes(GameConfiguration.DEFAULT_ENCODING), GameConfiguration.DEFAULT_ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public GameAction showMenu() {
		while (true) {
			write("Please select any options below: \n");
			write("1. Start a new game.\n2. Resume saved game.\n3. Quit game\nOption: ");
			Integer option = readInteger();
			switch (option) {
			case 1:
				return GameAction.START;
			case 2:
				return GameAction.RESUME;
			case 3:
				return GameAction.EXIT;
			default:
				write("Please select correction.\n");
			}
		}
	}

	public void playerLevel(Player player) {
		write(String.format("Your level has been increase to %d\n", player.getLevel()));
	}

	public void startAdventure(Player player) {
		write("All set, lets start the adventure.\n");
		write("Press any key to continue.");
		readStringLine();
		write("***************************************************\n");
		
	}
}
