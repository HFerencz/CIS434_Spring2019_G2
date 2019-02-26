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
	private int BEST_OF = 0, BEST_OF_3 = 3, BEST_OF_5 = 5, GAMES = 0;
	private int PLAYER_1_WINS = 0, PLAYER_2_WINS = 0;
	
	private boolean isComputer = false, winner = false;
	private boolean isContainedArray[] = new boolean[MAX_SHAPES];
	private Character tokenArray[][] = new Character[ROWS][COLS];
	
	public void newGameEvent(ArrayList<Shape> board) {
		 
		 board.add(new Line(MIN_X+xSection, MIN_Y, MIN_X+xSection, MAX_Y, new Color(156,93,82)));
		 board.add(new Line(MIN_X+(xSection*2), MIN_Y, MIN_X+(xSection*2), MAX_Y, new Color(156,93,82)));
		 board.add(new Line(MIN_X, MIN_Y+ySection, MAX_X, MIN_Y+ySection, new Color(156,93,82)));
		 board.add(new Line(MIN_X, MIN_Y+(ySection*2), MAX_X, MIN_Y+(ySection*2), new Color(156,93,82))); 
	}
	
	
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void changePlayerEvent() {
		if(CURRENT_PLAYER % 2 != 0)
			CURRENT_PLAYER = 2;
		else
			CURRENT_PLAYER = 1;
		
	}
	
	public void storeTokenValue(int posWhereStored) {
		if(CURRENT_PLAYER % 2 != 0)
			tokenArray[posWhereStored/ROWS][posWhereStored%ROWS] = 'X';
		else
			tokenArray[posWhereStored/ROWS][posWhereStored%ROWS] = 'O';
	}
	
	
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
	
	public boolean checkRows() {
		
		for(int i = 0; i < ROWS; i++) {
			if(tokenArray[i][0] == tokenArray[i][1] && tokenArray[i][1] == tokenArray[i][2] && tokenArray[i][0] != null) 
				return true;
			
						
		}
		return false;
	}
	
	public boolean checkCols() {
		for(int i = 0; i < COLS; i++) {
			if(tokenArray[0][i] == tokenArray[1][i] && tokenArray[1][i] == tokenArray[2][i] && tokenArray[0][i] != null)
				return true;
		}
		return false;
	}
	
	public boolean checkDiags() {
		if(tokenArray[1][1] == null)
			return false;
		if(tokenArray[0][0] == tokenArray[1][1] && tokenArray[1][1] == tokenArray[2][2])
			return true;
		else if(tokenArray[2][0] == tokenArray[1][1] && tokenArray[1][1] == tokenArray[0][2])
			return true;
		return false;
	}
	
	public boolean gameOverEvent(JButton mainMenuButton) {
		if(GAMES == BEST_OF) {
			if(PLAYER_1_WINS > PLAYER_2_WINS)
				mainMenuButton.setText("PLAYER 1 WINS");
			else if(PLAYER_1_WINS < PLAYER_2_WINS)
				mainMenuButton.setText("PLAYER 2 WINS");
			else
				mainMenuButton.setText("TIE!");
			return true;
		}
			
		return false;
	}
	
	public void gameReset(ArrayList<Shape> tokens, JButton gameResetButton, JButton mainMenuButton, JLabel winnerLabel, 
			JLabel player1Wins, JLabel player2Wins) {
		CURRENT_PLAYER = 1;
		TURNS = 0;
		tokens.removeAll(tokens);
		isContainedArray = new boolean[MAX_SHAPES];
	    tokenArray = new Character[ROWS][COLS];
	    winner = false;
	    if(GAMES == BEST_OF) {
	    	mainMenuButton.setEnabled(false);
	    	mainMenuButton.setVisible(false);
	    	player1Wins.setText("Wins: ");
	 	    player2Wins.setText("Wins: ");
	    	GAMES = 0;
	    	BEST_OF = 0;
	    	PLAYER_1_WINS = 0;
	    	PLAYER_2_WINS = 0;
	    	if(isComputer)
	    		disableComputer();
	    } else {
	    	gameResetButton.setEnabled(false);
	    	gameResetButton.setVisible(false);
	    }
	    winnerLabel.setVisible(false);
	}
	
	public void setBestOf(int games) {
		if(games % 2 != 0)
			BEST_OF = BEST_OF_5;
		else
			BEST_OF = BEST_OF_3;
	}
	
	public void setComputer() {
		isComputer = true;
	}
	
	public void disableComputer() {
		isComputer = false;
	}
	
	public boolean getComputer() {
		return isComputer;
	}
	
	public boolean checkIfWinner() {
		return winner;
	}
}
