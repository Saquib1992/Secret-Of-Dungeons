package com.saquib.sod.config;

import java.util.ArrayList;
import java.util.List;

import com.saquib.sod.entity.Monster;
import com.saquib.sod.entity.SuperPower;

/**
 * Class contains the game configuration.
 * 
 * @author Saquib Sayyad
 *
 */
public class GameConfiguration {
	
	public static final String DEFAULT_ENCODING = "UTF-8";
	
	
	/*
	 * Basic Configuration
	 */
	public static final Integer INITIAL_BONUS_POINTS = 5;
	public static final Integer INITIAL_DAMAGE = 5;
	public static final Integer INITIAL_HEALTH = 15;
	public static final Integer INITIAL_MANA = 20;
	public static final Double MIN_ATTACK_EFFICIENY = 0.6; // 60%
	public static final Integer BONUS_POINT_BATTLE = 10;
	
	public static final Integer TOTAL_SUPER_POWER_CAN_HAVE = 2;
	
	/*
	 * Dungeon Room
	 */
	public final static String ROOM_DESCRIPTION[] = new String[] {
			"a fetid, dank room teeming with foul beasts",
			"an endless mountain range where eagles soar looking for prey",
			"a murky swamp with a foul smelling odour",
			"a volcano with rivers of lava at all sides",
			"a thick forest where strange voices call out from the trees high above",
			"an old abandoned sailing ship, littered with the remains of some unlucky sailors",
			"a cafe filled with hipster baristas who refuse to use encapsulation"
	};
	
	
	/*
	 * 	Super Powers
	 */

	public static final List<SuperPower> PLAYER_SUPERPOWERS = new ArrayList<SuperPower>();
	static {
		PLAYER_SUPERPOWERS.add(new SuperPower("Blessing", "Blessing increases your total healthpoint.", 0, 10, 10));
		PLAYER_SUPERPOWERS.add(new SuperPower("Wrath", "Wrath targets an enemy with mighty blow causing massive damage.", 10, 0, 10));
		PLAYER_SUPERPOWERS.add(new SuperPower("Life Steal", "Life steal attacks enemy and grants you additional health for the same damage.", 5, 5, 5));
		PLAYER_SUPERPOWERS.add(new SuperPower("Arrow of Steal", "Hits the enemy causing severe damage.", 7, 0, 5));
	}



	/*
	 * 	Monsters
	 */
	public static final List<Monster> MONSTERS = new ArrayList<Monster>();
	
	static{
		MONSTERS.add(new Monster("Boo", 20, 2, null, 0));
		MONSTERS.add(new Monster("Dwarf", 23, 5, new SuperPower("Super Punch", "", 8, 0, 0), 2));
		MONSTERS.add(new Monster("Drow", 25, 6, new SuperPower("Power Arrow", "", 10, 0, 0), 3));
		MONSTERS.add(new Monster("Bowser", 28, 7, new SuperPower("Fire Ball", "", 10, 0, 0), 3));
	};
	public static final Monster MONSTER_LEADER = new Monster("Dragon", 50, 8, new SuperPower("Breath Fire", "", 12, 0, 0), 5);

}
