package ProgAssignment3;

import ProgAssignment3.Board.Pebble;

public class Sammy extends Player {

	public Sammy(Pebble color, Board b) {
		super(color, b);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[] nextMove() {
		// TODO Auto-generated method stub
		return getBoard().randomEmpty();
	}

}
