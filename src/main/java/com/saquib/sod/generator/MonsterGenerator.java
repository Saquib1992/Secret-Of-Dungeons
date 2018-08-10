package com.saquib.sod.generator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.saquib.sod.config.GameConfiguration;
import com.saquib.sod.entity.Monster;
/**
 * Utility class to generate monsters
 * 
 * @author Saquib Sayyad
 *
 */
public class MonsterGenerator {

	private final static Random random = new Random();
	private final static Set<Integer> monstersSeen = new HashSet<Integer>();

	public Monster generateRandomMonster() {
		int i;
		
		if (monstersSeen.size() == GameConfiguration.MONSTERS.size()) {
			monstersSeen.clear();
        }

		do {
			i = random.nextInt(GameConfiguration.MONSTERS.size());
		} while (monstersSeen.contains(i));
		monstersSeen.add(i);

		return GameConfiguration.MONSTERS.get(i);
	}

	public Monster generateMonsterLeader() {
		return GameConfiguration.MONSTER_LEADER;
	}

}
