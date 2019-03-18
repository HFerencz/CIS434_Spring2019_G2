import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Model {
	
	private final int MAX_X = 750, MAX_Y = 750, MIN_X = 150, MIN_Y = 150, xSection = (MAX_X-MIN_X) / 3, ySection = (MAX_Y-MIN_Y) / 3;
	private final int HALF_SECTION = xSection / 2;
	private final int ROWS = 3, COLS = 3;
	private final int MAX_SHAPES = 9;
	private final int RADIUS = xSection/3;
	private final int BOX1 = 0, BOX2 = 1, BOX3 = 2, BOX4 = 3, BOX5 = 4, BOX6 = 5, BOX7 = 6, BOX8 = 7, BOX9 = 8;
	
	private int CURRENT_PLAYER = 1;
	private int TURNS = 0;
	private int BEST_OF = 0, GAMES = 0;
	private int PLAYER_1_WINS = 0, PLAYER_2_WINS = 0;
	
	private boolean isComputer = false, isComputerFirst = false, winner = false;
	private boolean isContainedArray[] = new boolean[MAX_SHAPES];
	private Character tokenArray[][] = new Character[ROWS][COLS];
	
	/*
	 * newGameEvent: Creates the tic-tac-toe board that will be displayed on the screen
	 */
	public void newGameEvent(ArrayList<Shape> board) {
		 
		 board.add(new Line(MIN_X+xSection, MIN_Y, MIN_X+xSection, MAX_Y, new Color(156,93,82)));
		 board.add(new Line(MIN_X+(xSection*2), MIN_Y, MIN_X+(xSection*2), MAX_Y, new Color(156,93,82)));
		 board.add(new Line(MIN_X, MIN_Y+ySection, MAX_X, MIN_Y+ySection, new Color(156,93,82)));
		 board.add(new Line(MIN_X, MIN_Y+(ySection*2), MAX_X, MIN_Y+(ySection*2), new Color(156,93,82))); 
	}
	
	
	/*
	 * selectionEvent: Event that handles the user's turn. The coordinates where the user clicked on the game screen are
	 * 				   passed to the event. An event is considered valid if the user clicked within the confines of the
	 * 				   game board and if the tile within which they clicked does not already contain an X or O. During a
	 * 				   valid event, the player's token is displayed on the screen in the location they clicked, the
	 * 				   TURNS counter is incremented by 1 and the location of the new token is marked in an array so that
	 * 				   during future calls to this event, that tile cannot be used again.
	 */
	public boolean selectionEvent(int xCoord, int yCoord, ArrayList<Shape> tokens) {
		int posWhereStored = -1;
		boolean successfulTurn = false;
		
		if((xCoord >= MIN_X && xCoord < (MIN_X+xSection)) && (yCoord >= MIN_Y && yCoord < (MIN_Y+ySection))) {
			if(!isContainedArray[BOX1]) {
				
				if(CURRENT_PLAYER == 1) {
					int[] xCoords = {MIN_X+HALF_SECTION-RADIUS, MIN_X+HALF_SECTION, MIN_X+HALF_SECTION-RADIUS, MIN_X+HALF_SECTION+RADIUS, MIN_X+HALF_SECTION, MIN_X+HALF_SECTION+RADIUS};
					int[] yCoords = {MIN_Y+HALF_SECTION-RADIUS, MIN_Y+HALF_SECTION, (MIN_Y+HALF_SECTION)+RADIUS, MIN_Y+HALF_SECTION-RADIUS, MIN_Y+HALF_SECTION, (MIN_Y+HALF_SECTION)+RADIUS};
					tokens.add(new Cross(xCoords, yCoords, Color.BLACK));
				} else 
					tokens.add(new Circle(new Point(MIN_X+HALF_SECTION,MIN_Y+HALF_SECTION), RADIUS, Color.RED));
				
					
				isContainedArray[BOX1] = true;
				posWhereStored = BOX1;
			}	
		}
		else if((xCoord > MIN_X+xSection && xCoord < (MIN_X+(xSection*2)) && (yCoord >= MIN_Y && yCoord < (MIN_Y+ySection)))) {
			if(!isContainedArray[BOX2]) {
				
				if(CURRENT_PLAYER == 1) {
					int[] xCoords = {(MIN_X+HALF_SECTION-RADIUS)+xSection, (MIN_X+HALF_SECTION)+xSection, (MIN_X+HALF_SECTION-RADIUS)+xSection, 
							(MIN_X+HALF_SECTION+RADIUS)+xSection, (MIN_X+HALF_SECTION)+xSection, (MIN_X+HALF_SECTION+RADIUS)+xSection};
					int[] yCoords = {MIN_Y+HALF_SECTION-RADIUS, MIN_Y+HALF_SECTION, (MIN_Y+HALF_SECTION)+RADIUS, MIN_Y+HALF_SECTION-RADIUS, MIN_Y+HALF_SECTION, (MIN_Y+HALF_SECTION)+RADIUS};
					tokens.add(new Cross(xCoords, yCoords, Color.BLACK));
				} else 
					tokens.add(new Circle(new Point(MIN_X+(HALF_SECTION*3),MIN_Y+HALF_SECTION), RADIUS, Color.RED));
				
				
				isContainedArray[BOX2] = true;
				posWhereStored = BOX2;
			}
		}
		else if((xCoord > MIN_X+(xSection*2) && xCoord < MAX_X) && (yCoord >= MIN_Y && yCoord < (MIN_Y+ySection))) {
			if(!isContainedArray[BOX3]) {
				
				if(CURRENT_PLAYER == 1) {
					int[] xCoords = {(MIN_X+HALF_SECTION-RADIUS)+(xSection*2), (MIN_X+HALF_SECTION)+(xSection*2), (MIN_X+HALF_SECTION-RADIUS)+(xSection*2), 
							(MIN_X+HALF_SECTION+RADIUS)+(xSection*2), (MIN_X+HALF_SECTION)+(xSection*2), (MIN_X+HALF_SECTION+RADIUS)+(xSection*2)};
					int[] yCoords = {MIN_Y+HALF_SECTION-RADIUS, MIN_Y+HALF_SECTION, (MIN_Y+HALF_SECTION)+RADIUS, MIN_Y+HALF_SECTION-RADIUS, MIN_Y+HALF_SECTION, (MIN_Y+HALF_SECTION)+RADIUS};
					tokens.add(new Cross(xCoords, yCoords, Color.BLACK));
				} else
					tokens.add(new Circle(new Point(MIN_X+(HALF_SECTION*5),MIN_Y+HALF_SECTION), RADIUS, Color.RED));
				
				
				isContainedArray[BOX3] = true;
				posWhereStored = BOX3;
			}
		}
		else if((xCoord > MIN_X && xCoord < (MIN_X+xSection)) && (yCoord >= MIN_Y+ySection && yCoord < (MIN_Y+(ySection*2)))) {
			if(!isContainedArray[BOX4]) {
				
				if(CURRENT_PLAYER == 1) {
					int[] xCoords = {MIN_X+HALF_SECTION-RADIUS, MIN_X+HALF_SECTION, MIN_X+HALF_SECTION-RADIUS, MIN_X+HALF_SECTION+RADIUS, MIN_X+HALF_SECTION, MIN_X+HALF_SECTION+RADIUS};
					int[] yCoords = {MIN_Y+HALF_SECTION-RADIUS+ySection, MIN_Y+HALF_SECTION+ySection, (MIN_Y+HALF_SECTION)+RADIUS+ySection,
							MIN_Y+HALF_SECTION-RADIUS+ySection, MIN_Y+HALF_SECTION+ySection, (MIN_Y+HALF_SECTION)+RADIUS+ySection};
					tokens.add(new Cross(xCoords, yCoords, Color.BLACK));
				} else
					tokens.add(new Circle(new Point(MIN_X+HALF_SECTION, MIN_Y+(HALF_SECTION*3)), RADIUS, Color.RED));
				
				
				isContainedArray[BOX4] = true;
				posWhereStored = BOX4;
			}
		}
		else if((xCoord > MIN_X+xSection && xCoord < (MIN_X+(xSection*2))) && (yCoord > MIN_Y+ySection && yCoord < (MIN_Y+(ySection*2)))) {
			if(!isContainedArray[BOX5]) {
				
				if(CURRENT_PLAYER == 1) {
					int[] xCoords = {(MIN_X+HALF_SECTION-RADIUS)+xSection, (MIN_X+HALF_SECTION)+xSection, (MIN_X+HALF_SECTION-RADIUS)+xSection, 
							(MIN_X+HALF_SECTION+RADIUS)+xSection, (MIN_X+HALF_SECTION)+xSection, (MIN_X+HALF_SECTION+RADIUS)+xSection};
					int[] yCoords = {MIN_Y+HALF_SECTION-RADIUS+ySection, MIN_Y+HALF_SECTION+ySection, (MIN_Y+HALF_SECTION)+RADIUS+ySection,
							MIN_Y+HALF_SECTION-RADIUS+ySection, MIN_Y+HALF_SECTION+ySection, (MIN_Y+HALF_SECTION)+RADIUS+ySection};
					tokens.add(new Cross(xCoords, yCoords, Color.BLACK));
				} else
					tokens.add(new Circle(new Point(MIN_X+(HALF_SECTION*3), MIN_Y+(HALF_SECTION*3)), RADIUS, Color.RED));
				
				
				isContainedArray[BOX5] = true;
				posWhereStored = BOX5;
			}
		}
		else if((xCoord > MIN_X+(xSection*2) && xCoord < MAX_X) && (yCoord > MIN_Y+ySection && yCoord < (MIN_Y+(ySection*2)))) {
			if(!isContainedArray[BOX6]) {
				
				if(CURRENT_PLAYER == 1) {
					int[] xCoords = {(MIN_X+HALF_SECTION-RADIUS)+(xSection*2), (MIN_X+HALF_SECTION)+(xSection*2), (MIN_X+HALF_SECTION-RADIUS)+(xSection*2), 
							(MIN_X+HALF_SECTION+RADIUS)+(xSection*2), (MIN_X+HALF_SECTION)+(xSection*2), (MIN_X+HALF_SECTION+RADIUS)+(xSection*2)};
					int[] yCoords = {MIN_Y+HALF_SECTION-RADIUS+ySection, MIN_Y+HALF_SECTION+ySection, (MIN_Y+HALF_SECTION)+RADIUS+ySection,
							MIN_Y+HALF_SECTION-RADIUS+ySection, MIN_Y+HALF_SECTION+ySection, (MIN_Y+HALF_SECTION)+RADIUS+ySection};
					tokens.add(new Cross(xCoords, yCoords, Color.BLACK));
				} else 
					tokens.add(new Circle(new Point(MIN_X+(HALF_SECTION*5), MIN_Y+(HALF_SECTION*3)), RADIUS, Color.RED));
				
				
				isContainedArray[BOX6] = true;
				posWhereStored = BOX6;
			}
		}
		else if((xCoord > MIN_X && xCoord < (MIN_X+xSection)) && (yCoord > MIN_Y+(ySection*2) && yCoord < MAX_Y)) {
			if(!isContainedArray[BOX7]) {
				
				if(CURRENT_PLAYER == 1) {
					int[] xCoords = {MIN_X+HALF_SECTION-RADIUS, MIN_X+HALF_SECTION, MIN_X+HALF_SECTION-RADIUS, MIN_X+HALF_SECTION+RADIUS, MIN_X+HALF_SECTION, MIN_X+HALF_SECTION+RADIUS};
					int[] yCoords = {MIN_Y+HALF_SECTION-RADIUS+(ySection*2), MIN_Y+HALF_SECTION+(ySection*2), (MIN_Y+HALF_SECTION)+RADIUS+(ySection*2),
							MIN_Y+HALF_SECTION-RADIUS+(ySection*2), MIN_Y+HALF_SECTION+(ySection*2), (MIN_Y+HALF_SECTION)+RADIUS+(ySection*2)};
					tokens.add(new Cross(xCoords, yCoords, Color.BLACK));
				} else
					tokens.add(new Circle(new Point(MIN_X+HALF_SECTION, MIN_Y+(HALF_SECTION*5)), RADIUS, Color.RED));
				
				
				isContainedArray[BOX7] = true;
				posWhereStored = BOX7;
			}
		}
		else if((xCoord > MIN_X+xSection && xCoord < (MIN_X+(xSection*2))) && (yCoord > MIN_Y+(ySection*2) && yCoord < MAX_Y)) {
			if(!isContainedArray[BOX8]) {
				
				if(CURRENT_PLAYER == 1) {
					int[] xCoords = {(MIN_X+HALF_SECTION-RADIUS)+xSection, (MIN_X+HALF_SECTION)+xSection, (MIN_X+HALF_SECTION-RADIUS)+xSection, 
							(MIN_X+HALF_SECTION+RADIUS)+xSection, (MIN_X+HALF_SECTION)+xSection, (MIN_X+HALF_SECTION+RADIUS)+xSection};
					int[] yCoords = {MIN_Y+HALF_SECTION-RADIUS+(ySection*2), MIN_Y+HALF_SECTION+(ySection*2), (MIN_Y+HALF_SECTION)+RADIUS+(ySection*2),
							MIN_Y+HALF_SECTION-RADIUS+(ySection*2), MIN_Y+HALF_SECTION+(ySection*2), (MIN_Y+HALF_SECTION)+RADIUS+(ySection*2)};
					tokens.add(new Cross(xCoords, yCoords, Color.BLACK));
				} else
					tokens.add(new Circle(new Point(MIN_X+(HALF_SECTION*3), MIN_Y+(HALF_SECTION*5)), RADIUS, Color.RED));
				
				
				isContainedArray[BOX8] = true;
				posWhereStored = BOX8;
			}
		}
		else if((xCoord > MIN_X+(xSection*2) && xCoord < MAX_X) && (yCoord > MIN_Y+(ySection*2) && yCoord < MAX_Y)) {
			if(!isContainedArray[BOX9]) {
				
				if(CURRENT_PLAYER == 1) {
					int[] xCoords = {(MIN_X+HALF_SECTION-RADIUS)+(xSection*2), (MIN_X+HALF_SECTION)+(xSection*2), (MIN_X+HALF_SECTION-RADIUS)+(xSection*2), 
							(MIN_X+HALF_SECTION+RADIUS)+(xSection*2), (MIN_X+HALF_SECTION)+(xSection*2), (MIN_X+HALF_SECTION+RADIUS)+(xSection*2)};
					int[] yCoords = {MIN_Y+HALF_SECTION-RADIUS+(ySection*2), MIN_Y+HALF_SECTION+(ySection*2), (MIN_Y+HALF_SECTION)+RADIUS+(ySection*2),
							MIN_Y+HALF_SECTION-RADIUS+(ySection*2), MIN_Y+HALF_SECTION+(ySection*2), (MIN_Y+HALF_SECTION)+RADIUS+(ySection*2)};
					tokens.add(new Cross(xCoords, yCoords, Color.BLACK));
				} else 
					tokens.add(new Circle(new Point(MIN_X+(HALF_SECTION*5), MIN_Y+(HALF_SECTION*5)), RADIUS, Color.RED));
				
				
				isContainedArray[BOX9] = true;
				posWhereStored = BOX9;
			}
		}
		
		if(posWhereStored >= 0) {
			storeTokenValue(posWhereStored);
			successfulTurn = true;
			TURNS++;
		}
		return successfulTurn;
	}
	
	
	
	
	
	
	
	
	/*
	 * computerSelectionEvent: Event for handing the computer's turn. For the current AI functionality, the computer rolls
	 * 					       a random number between 0-8, where 0 corresponds to the first element in the stored token array.
	 * 						   If there is no token in the tile corresponding to the random number generated, a new token is
	 * 						   placed there, the position is stored and the turns counter is incremented by 1. In the event that
	 * 						   the computer is blocked from placing a tile in the spot that they generated, it will continue to
	 * 						   generate new random numbers until it finds a vacant spot on the board.
	 */
	public void computerSelectionEvent(ArrayList<Shape> tokens) {
		
		
		int posWhereStored = -1;
		boolean selection = false;
		
		
		while(!selection) {
			Random r = new Random();
			int low = BOX1;
			int high = BOX9+1;
			int result = r.nextInt(high-low);
			
			switch(result) {
			case BOX1: 
				if(!isContainedArray[result]) {
					tokens.add(new Circle(new Point(MIN_X+HALF_SECTION,MIN_Y+HALF_SECTION), RADIUS, Color.RED));
					posWhereStored = result;
					isContainedArray[result] = true;
					selection = true;
					TURNS++;
					break;
				}
			case BOX2: 
				if(!isContainedArray[result]) {
					tokens.add(new Circle(new Point(MIN_X+(HALF_SECTION*3),MIN_Y+HALF_SECTION), RADIUS, Color.RED));
					posWhereStored = result;
					isContainedArray[result] = true;
					selection = true;
					TURNS++;
					break;
				}
			case BOX3: 
				if(!isContainedArray[result]) {
					tokens.add(new Circle(new Point(MIN_X+(HALF_SECTION*5),MIN_Y+HALF_SECTION), RADIUS, Color.RED));
					posWhereStored = result;
					isContainedArray[result] = true;
					selection = true;
					TURNS++;
					break;
				}
			case BOX4: 
				if(!isContainedArray[result]) {
					tokens.add(new Circle(new Point(MIN_X+HALF_SECTION, MIN_Y+(HALF_SECTION*3)), RADIUS, Color.RED));
					posWhereStored = result;
					isContainedArray[result] = true;
					selection = true;
					TURNS++;
					break;
				}
			case BOX5: 
				if(!isContainedArray[result]) {
					tokens.add(new Circle(new Point(MIN_X+(HALF_SECTION*3), MIN_Y+(HALF_SECTION*3)), RADIUS, Color.RED));
					posWhereStored = result;
					isContainedArray[result] = true;
					selection = true;
					TURNS++;
					break;
				}
			case BOX6: 
				if(!isContainedArray[result]) {
					tokens.add(new Circle(new Point(MIN_X+(HALF_SECTION*5), MIN_Y+(HALF_SECTION*3)), RADIUS, Color.RED));
					posWhereStored = result;
					isContainedArray[result] = true;
					selection = true;
					TURNS++;
					break;
				}
			case BOX7: 
				if(!isContainedArray[result]) {
					tokens.add(new Circle(new Point(MIN_X+HALF_SECTION, MIN_Y+(HALF_SECTION*5)), RADIUS, Color.RED));
					posWhereStored = result;
					isContainedArray[result] = true;
					selection = true;
					TURNS++;
					break;
				}
			case BOX8: 
				if(!isContainedArray[result]) {
					tokens.add(new Circle(new Point(MIN_X+(HALF_SECTION*3), MIN_Y+(HALF_SECTION*5)), RADIUS, Color.RED));
					posWhereStored = result;
					isContainedArray[result] = true;
					selection = true;
					TURNS++;
					break;
				}
			case BOX9: 
				if(!isContainedArray[result]) {
					tokens.add(new Circle(new Point(MIN_X+(HALF_SECTION*5), MIN_Y+(HALF_SECTION*5)), RADIUS, Color.RED));
					posWhereStored = result;
					isContainedArray[result] = true;
					selection = true;
					TURNS++;
					break;
				}
				
			}
		}
		if(posWhereStored >= 0) 
			storeTokenValue(posWhereStored);
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * changePlayerEvent: Changes the current player
	 */
	public void changePlayerEvent() {
		if(CURRENT_PLAYER % 2 != 0)
			CURRENT_PLAYER = 2;
		else
			CURRENT_PLAYER = 1;
		
	}
	
	/*
	 * storeTokenValue: Uses a 2-dimensional array set up to match the layout of the game board. When called,
	 * 				    either an X or O is placed in the corresponding element of the array.
	 */
	public void storeTokenValue(int posWhereStored) {
		if(CURRENT_PLAYER % 2 != 0)
			tokenArray[posWhereStored/ROWS][posWhereStored%ROWS] = 'X';
		else
			tokenArray[posWhereStored/ROWS][posWhereStored%ROWS] = 'O';
	}
	
	
	/*
	 * winnerEvent: Event for determining if there is a winner of the current match. Gets used after the turn counter
	 * 				reaches 5 turns since this is the minimum number of turns before a winner is determined. Checks
	 * 				the rows, columns and diagonals of the game board. If there are 3 of the same kind of token in
	 * 				a line, the player corresponding to the token is declared the winner. If no winner is determined after
	 * 				9 turns, the game is declared a tie.
	 */
	public boolean winnerEvent(JLabel winnerLabel, JLabel player1Wins, JLabel player2Wins) {
		if(TURNS >= 5) {
			
			if(checkRows() || checkCols() || checkDiags()) {
				winnerLabel.setText("Player " + CURRENT_PLAYER + " wins!");
				winnerLabel.setVisible(true);
				GAMES++;
				if(CURRENT_PLAYER % 2 != 0) {
					PLAYER_1_WINS++;
					player1Wins.setText("Wins: " + PLAYER_1_WINS);
				}
					
				else {
					PLAYER_2_WINS++;
					player2Wins.setText("Wins: " + PLAYER_2_WINS);
				}
				winner = true;		
				return true;
			}
			else if(TURNS == 9) {
				winnerLabel.setText("Tie game!");
				winnerLabel.setVisible(true);
				GAMES++;
				return true;
			}
		}	
		return false;
	}
	
	/*
	 * checkRows: Check the rows of the game board for a winner
	 */
	public boolean checkRows() {
		
		for(int i = 0; i < ROWS; i++) {
			if(tokenArray[i][0] == tokenArray[i][1] && tokenArray[i][1] == tokenArray[i][2] && tokenArray[i][0] != null) 
				return true;				
		}
		return false;
	}
	
	/*
	 * checkCols: Check the columns of the game board for a winner
	 */
	public boolean checkCols() {
		for(int i = 0; i < COLS; i++) {
			if(tokenArray[0][i] == tokenArray[1][i] && tokenArray[1][i] == tokenArray[2][i] && tokenArray[0][i] != null)
				return true;
		}
		return false;
	}
	
	/*
	 * checkDiags: Check the diagonals of the game board for a winner
	 */
	public boolean checkDiags() {
		if(tokenArray[1][1] == null)
			return false;
		if(tokenArray[0][0] == tokenArray[1][1] && tokenArray[1][1] == tokenArray[2][2])
			return true;
		else if(tokenArray[2][0] == tokenArray[1][1] && tokenArray[1][1] == tokenArray[0][2])
			return true;
		return false;
	}
	
	/*
	 * gameOverEvent: If a player's wins is greater than the total number of games in the series, they
	 *                are declared the winner. Otherwise, if the total games played is the same as the
	 *                total number of games in the series and neither player has majority wins, the series
	 *                is declared a tie. If neither circumstances occur, then more games need to be played.
	 *                Event is called after a match winner has been declared.
	 */
	public boolean gameOverEvent(JLabel gameWinnerLabel) {
			if(PLAYER_1_WINS > BEST_OF/2)
				gameWinnerLabel.setText("PLAYER 1 WINS");
			else if(PLAYER_2_WINS > BEST_OF/2)
				gameWinnerLabel.setText("PLAYER 2 WINS");
			else if(GAMES == BEST_OF)
				gameWinnerLabel.setText("TIE!");
			else
				return false;
			return true;	
	}
			
		
	/*
	 * gameReset: Resets the game board between matches. If the game is over, then the counters for the total number of games
	 *            played and each player's wins are reset.
	 */
	public void gameReset(ArrayList<Shape> tokens, ArrayList<Shape> board, JButton gameResetButton, JButton gameOverButton, 
			JLabel winnerLabel, JLabel gameWinnerLabel, JLabel player1Wins, JLabel player2Wins) {
		CURRENT_PLAYER = 1;
		TURNS = 0;
		tokens.removeAll(tokens);
		isContainedArray = new boolean[MAX_SHAPES];
	    tokenArray = new Character[ROWS][COLS];
	    winner = false;
	    if(gameOverEvent(gameWinnerLabel)) {
	    	gameOverButton.setEnabled(false);
	    	gameOverButton.setVisible(false);
	    	player1Wins.setText("Wins: ");
	 	    player2Wins.setText("Wins: ");
	    	GAMES = 0;
	    	PLAYER_1_WINS = 0;
	    	PLAYER_2_WINS = 0;
	    	board.removeAll(board);
	    } else {
	    	gameResetButton.setEnabled(false);
	    	gameResetButton.setVisible(false);
	    }
	    winnerLabel.setVisible(false);
	}
	
	/*
	 * setBestOf: Sets the number of games to be played in the series
	 */
	public void setBestOf(int games) {
		BEST_OF = games;
	}
	
	/*
	 * getBestOf: Gets the number of games to be played in the series
	 */
	public int getBestOf() {
		return BEST_OF;
	}
	
	/*
	 * resetBestOf: Reset the number of games to be played in the series
	 */
	public void resetBestOf() {
		BEST_OF = 0;
	}
	
	/*
	 * setComputer: Enables the computer as a player
	 */
	public void setComputer() {
		isComputer = true;
	}
	
	/*
	 * disableComputer: Disables the computer as a player
	 */
	public void disableComputer() {
		isComputer = false;
	}
	
	/*
	 * getComputer: Returns whether or not the computer is an active player
	 */
	public boolean getComputer() {
		return isComputer;
	}
	
	/*
	 * setComputerFirst: Makes it so the computer makes the first move
	 */
	public void setComputerFirst() {
		isComputerFirst = true;
	}
	
	/*
	 * setComputerSecond: Makes it so the computer players after the user makes a move
	 */
	public void setComputerSecond() {
		isComputerFirst = false;
	}
	
	/*
	 * isComputerFirst: Returns whether or not the computer is set to make the first move
	 */
	public boolean isComputerFirst() {
		return isComputerFirst;
	}
	
	/*
	 * checkIfWinner: Returns whether or not there is a winner for the current match
	 */
	public boolean checkIfWinner() {
		return winner;
	}
	
}
