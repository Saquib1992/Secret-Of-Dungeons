package com.saquib.sod.generator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.saquib.sod.config.GameConfiguration;
import com.saquib.sod.entity.Room;
/**
 * Utility class to generate rooms with monsters
 * @author Saquib Sayyad
 *
 */
public class RoomGenerator {
	
	private final static Random random = new Random();
    private final static Set<Integer> roomsSeen = new HashSet<Integer>();
	
	private MonsterGenerator monsterGenerator;
	
	public RoomGenerator() {
		monsterGenerator = new MonsterGenerator();
	}
	
	
	public Room newRegularInstance() {
        if (roomsSeen.size() == GameConfiguration.ROOM_DESCRIPTION.length) {
            roomsSeen.clear();
        }
        int i;
        do {
            i = random.nextInt(GameConfiguration.ROOM_DESCRIPTION.length);
        } while (roomsSeen.contains(i));
        roomsSeen.add(i);

        String roomDescription = GameConfiguration.ROOM_DESCRIPTION[i];
        
        return new Room(roomDescription, this.monsterGenerator.generateRandomMonster(), false);
    }
	
	public Room newLeaderInstance() {
		int i = random.nextInt(GameConfiguration.ROOM_DESCRIPTION.length);
		String roomDescription = GameConfiguration.ROOM_DESCRIPTION[i];
		
		return new Room(roomDescription, this.monsterGenerator.generateMonsterLeader(), true);
	}

}
