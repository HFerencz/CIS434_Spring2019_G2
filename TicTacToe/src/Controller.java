import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller implements ActionListener, MouseListener{
	
	//Model and view objects
	private Model model;
	private View view;
	
	//Constructor for the controller
	public Controller(View view, Model model) {
			
		this.view = view;
		this.model = model;		
		}
	
	//Add event listeners for all buttons as well as the game board when player clicks
	public void setActionListeners() {
		view.gamePanel.addMouseListener(this);
		view.versusPlayerButton.addActionListener(this);
		view.versusCompButton.addActionListener(this);
		view.compFirstButton.addActionListener(this);
		view.compSecondButton.addActionListener(this);
		view.bestOf3Button.addActionListener(this);
		view.bestOf5Button.addActionListener(this);
		view.customRangeButton.addActionListener(this);
		view.customSelectButton.addActionListener(this);
		view.nextGameButton.addActionListener(this);
		view.gameOverButton.addActionListener(this);
		view.mainMenuButton.addActionListener(this);
		view.playAgainButton.addActionListener(this);	
	
	}
	
	/*
	 * New game event: Executed once game settings are established. If playing against computer
	 * 				   and computer is going first, handle computer's first move once board is
	 * 				   created
	 */
	public void processNewGameEvent(int bestOfChoice) {
		model.setBestOf(bestOfChoice);
		model.newGameEvent(view.getBoardArray());
		processCompGoesEvent();
		this.view.repaint();
	}
	
	/*
	 * Selection event: Order of events for each player making a move:
	 * 		1. Determine if there has been a winner yet
	 * 		2. Handle human player's selection event
	 * 		3. Determine if there has been a winner yet
	 * 		4. If no winner, change to the other player
	 * 		5. If other player is computer, handle computer player's selection event
	 * 		6. After computer makes its turn, determine if there has been a winner
	 * 		7. At end of computer event, change current player back to the user
	 */
	public void processSelectionEvent(int xCoord, int yCoord) {
		if(!model.checkIfWinner()) {
			if (model.selectionEvent(xCoord, yCoord, view.getTokenArray())) {
				this.view.repaint();
				if(!processWinnerEvent()) {
					processChangePlayerEvent();
					if(model.getComputer()) {
						model.computerSelectionEvent(view.getTokenArray());
						this.view.repaint();
						processWinnerEvent();
						processChangePlayerEvent();
					}
				}	
			}
		}
	}
	
	/*
	 * Change player event: Event for switching players
	 */
	public void processChangePlayerEvent() {
		model.changePlayerEvent();
	}
	
	
	/*
	 * Winner event: Determine if there has been a winner. If so, determine if the last win
	 * 	             determines the outcome of the series. If so, enable the Game Over button.
	 * 				 Otherwise, enable the Next Game button.
	 */
	public boolean processWinnerEvent() {
		
		if(model.winnerEvent(view.winnerLabel, view.player1Wins, view.player2Wins)) {
			if(model.gameOverEvent(view.gameWinnerLabel)) {
				view.gameOverButton.setEnabled(true);
				view.gameOverButton.setVisible(true);
			} else {
				view.nextGameButton.setEnabled(true);
				view.nextGameButton.setVisible(true);
			}
			
			return true;
		}
		return false;
	}
	
	/*
	 * Game reset event: Event for resetting the board for a fresh new game
	 */
	public void processGameResetEvent() {
		model.gameReset(view.getTokenArray(), view.getBoardArray(), view.nextGameButton, view.gameOverButton, view.winnerLabel,
				view.gameWinnerLabel, view.player1Wins, view.player2Wins);
		this.view.repaint();
	}
	
	/*
	 * Play again event: Event for setting up a new match with previous settings. 
	 */
	public void processPlayAgainEvent() {
		processNewGameEvent(model.getBestOf());
		this.view.repaint();
	}
	
	/*
	 * Computer turn order event: Event for deciding if the computer goes first or second
	 */
	public void processCompTurnOrderEvent(int flag) {
		if(flag == 1)
			model.setComputerFirst();
		else
			model.setComputerSecond();
	}
	
	
	/*
	 * Computer moves event: Event for letting the computer make its move
	 */
	public void processCompGoesEvent() {
		if(model.getComputer() && model.isComputerFirst()) {
			processChangePlayerEvent();
			model.computerSelectionEvent(view.getTokenArray());
			processChangePlayerEvent();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		view.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		Object source = event.getSource();
		
		/*
		 * BUTTON EVENT DESCRIPTIONS:
		 * 
		 * versusPlayerButton: both players are human users; change to number of matches screen
		 * versusCompButton: other player is the computer; disable versus buttons, enable buttons for deciding comp's turn
		 * compFirstButton: sets up computer for making the first move; change to number of matches screen
		 * compSecondButton: sets up computer for going after the player; change to number of matches screen
		 * bestof3Button: sets up match to be a best of 3 games; change to game screen
		 * bestof5Button: sets up match to be a best of 5 games; change to game screen
		 * customRangeButton: hide best of buttons, enable text field so the user can decide the number of games
		 * customSelectButton: if valid user input, store in a variable for determining length of match; change to game screen
		 * nextGameButton: reset the board for the next game, keeping track of each player's wins in-between matches
		 * gameOverButton: reset game board but leaves best of and computer info untouched; change to game over screen
		 * playAgainButton: set up a new match with previous settings intact; change to game screen
		 * mainMenuButton: disable computer and clear best of variable; change to main menu screen
		 */
		if(source == view.versusPlayerButton) {
			view.cardLayout.next(view.cardPanel);
			view.repaintScreen();	
		}
		else if(source == view.versusCompButton) {
			model.setComputer();
			view.versusPlayerButton.setEnabled(false);
			view.versusPlayerButton.setVisible(false);
			view.versusCompButton.setEnabled(false);
			view.versusCompButton.setVisible(false);
			
			view.compFirstButton.setEnabled(true);
			view.compFirstButton.setVisible(true);
			view.compSecondButton.setEnabled(true);
			view.compSecondButton.setVisible(true);
			view.repaintScreen();
		}
		else if(source == view.compFirstButton) {
			this.processCompTurnOrderEvent(1);
			view.versusPlayerButton.setEnabled(true);
			view.versusPlayerButton.setVisible(true);
			view.versusCompButton.setEnabled(true);
			view.versusCompButton.setVisible(true);
			
			view.compFirstButton.setEnabled(false);
			view.compFirstButton.setVisible(false);
			view.compSecondButton.setEnabled(false);
			view.compSecondButton.setVisible(false);
			view.cardLayout.next(view.cardPanel);
			view.repaintScreen();
		}
		else if(source == view.compSecondButton) {
			this.processCompTurnOrderEvent(0);
			view.versusPlayerButton.setEnabled(true);
			view.versusPlayerButton.setVisible(true);
			view.versusCompButton.setEnabled(true);
			view.versusCompButton.setVisible(true);
			
			view.compFirstButton.setEnabled(false);
			view.compFirstButton.setVisible(false);
			view.compSecondButton.setEnabled(false);
			view.compSecondButton.setVisible(false);
			view.cardLayout.next(view.cardPanel);
			view.repaintScreen();
		}
		else if(source == view.bestOf3Button) {
			this.processNewGameEvent(3);
			view.cardLayout.next(view.cardPanel);
			view.repaintScreen();
		}
		else if(source == view.bestOf5Button) {
			this.processNewGameEvent(5);
			view.cardLayout.next(view.cardPanel);
			view.repaintScreen();
		}
		else if(source == view.customRangeButton) {
			view.bestOf3Button.setVisible(false);
			view.bestOf3Button.setEnabled(false);
			view.bestOf5Button.setVisible(false);
			view.bestOf5Button.setEnabled(false);
			view.customRangeButton.setVisible(false);
			view.customRangeButton.setEnabled(false);
			
			view.gameInputField.setVisible(true);
			view.gameInputField.setEditable(true);
			view.customSelectButton.setVisible(true);
			view.customSelectButton.setEnabled(true);
			view.selectionLabel.setVisible(true);
		}
		else if(source == view.customSelectButton) {
			if(view.gameInputField.getText().trim().length() != 0) {
				this.processNewGameEvent(Integer.parseInt(view.gameInputField.getText().trim()));
				view.bestOf3Button.setVisible(true);
				view.bestOf3Button.setEnabled(true);
				view.bestOf5Button.setVisible(true);
				view.bestOf5Button.setEnabled(true);
				view.customRangeButton.setVisible(true);
				view.customRangeButton.setEnabled(true);
				
				view.gameInputField.setVisible(false);
				view.gameInputField.setEditable(false);
				view.gameInputField.setText(null);
				view.customSelectButton.setVisible(false);
				view.customSelectButton.setEnabled(false);
				view.selectionLabel.setVisible(false);
				view.cardLayout.next(view.cardPanel);
				view.repaintScreen();
			}	
		} 
		
		else if(source == view.nextGameButton) {
			this.processGameResetEvent();
			this.processCompGoesEvent();
			view.repaintScreen();
		} 
		else if(source == view.gameOverButton) {
			this.processGameResetEvent();
			view.cardLayout.next(view.cardPanel);
			view.repaintScreen();
		}
		else if(source == view.playAgainButton) {
			this.processPlayAgainEvent();
			view.cardLayout.previous(view.cardPanel);
			view.repaintScreen();
		}
		else if(source == view.mainMenuButton) {
			model.disableComputer();
			model.resetBestOf();
			view.cardLayout.next(view.cardPanel);
			view.repaintScreen();
		}
		
		view.setCursor(Cursor.getDefaultCursor());
	}
	
	
	/*
	 * MOUSE EVENT DESCRIPTIONS:
	 * 
	 * processSelectionEvent: Determine if the player made a valid selection on the game board
	 */
	
	@Override
	public void mouseClicked(MouseEvent event) {
		view.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		this.processSelectionEvent(event.getX(), event.getY());
		view.setCursor(Cursor.getDefaultCursor());
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
