package ProgAssignment3;

import javax.swing.JOptionPane;

import ProgAssignment3.Board.Pebble;

public class HumanPlayer extends Player {

	public HumanPlayer(Pebble color, Board b) {
		super(color, b);

	}

	@Override
	public int[] nextMove() {
		// TODO Auto-generated method stub
		int x, y;
		while (true) {
			try {
				String firstNumber = JOptionPane.showInputDialog(null, "enter column number: ",getColor().getColor(), JOptionPane.PLAIN_MESSAGE);
				if(firstNumber.equals("quit"))
					System.exit(0);
				x = Integer.parseInt(firstNumber);
				String secondNumber =  JOptionPane.showInputDialog(null, "enter row number: ",getColor().getColor(), JOptionPane.PLAIN_MESSAGE);
				if(secondNumber.equals("quit"))
					System.exit(0);
				y = Integer.parseInt(secondNumber);
				if (!getBoard().isValidInput(x, y)) {
					JOptionPane.showMessageDialog(null,
							"Enter a number between 1 and " + getBoard().getBoardSize() + "!", "Really?",
							JOptionPane.ERROR_MESSAGE);
					continue;
				}
				if (getBoard().isEmpty(x, y))
					break;
				else
					JOptionPane.showMessageDialog(null, "Cell is already filled!", "Oops!", 
							JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Enter a number between 1 and " + 
						getBoard().getBoardSize() + "!",
						"Really?", JOptionPane.ERROR_MESSAGE);
			}
		}
		int[] res = { x, y };
		return res;
	}

}
