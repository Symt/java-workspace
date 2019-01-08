package main.java.com.symt.sidescroller2;

import java.util.ArrayList;

public class Handler extends ArrayList<Block> {
	private static final long serialVersionUID = 1L;
	public Handler() {
	}

	public void tick() {
		for (Block i : this) {
			i.update(Player.xv, 0);
		}
	}
}
