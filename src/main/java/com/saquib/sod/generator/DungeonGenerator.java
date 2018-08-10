package com.saquib.sod.generator;

import com.saquib.sod.entity.Dungeon;
/**
 * Utility to create dungeon with room and monsters.
 * @author Saquib Sayyad
 *
 */
public class DungeonGenerator {

	private RoomGenerator roomGenerator;

	public DungeonGenerator() {
		this.roomGenerator = new RoomGenerator();
	}

	public Dungeon generateDungeon() {

		Dungeon dungeon = new Dungeon();
		dungeon.putRoom(0, 0, roomGenerator.newRegularInstance());
		dungeon.putRoom(-1, 1, roomGenerator.newRegularInstance());
		dungeon.putRoom(0, 1, roomGenerator.newRegularInstance());
		dungeon.putRoom(1, 1, roomGenerator.newRegularInstance());
		dungeon.putRoom(-1, 2, roomGenerator.newRegularInstance());
		dungeon.putRoom(1, 2, roomGenerator.newRegularInstance());
		dungeon.putRoom(-1, 3, roomGenerator.newRegularInstance());
		dungeon.putRoom(0, 3, roomGenerator.newRegularInstance());
		dungeon.putRoom(1, 3, roomGenerator.newRegularInstance());
		dungeon.putRoom(0, 4, roomGenerator.newLeaderInstance());
		return dungeon;
	}
}
