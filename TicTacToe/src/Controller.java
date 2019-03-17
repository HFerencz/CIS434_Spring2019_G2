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
	
	public void processNewGameEvent(int bestOfChoice) {
		model.setBestOf(bestOfChoice);
		model.newGameEvent(view.getBoardArray());
		if(model.getComputer() && model.isComputerFirst()) {
			processChangePlayerEvent();
			model.computerSelectionEvent(view.getTokenArray());
			processChangePlayerEvent();
		}
		this.view.repaint();
	}
	
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
	
	public void processChangePlayerEvent() {
		model.changePlayerEvent();
	}
	
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
	
	public void processGameResetEvent() {
		model.gameReset(view.getTokenArray(), view.getBoardArray(), view.nextGameButton, view.gameOverButton, view.winnerLabel,
				view.gameWinnerLabel, view.player1Wins, view.player2Wins);
		this.view.repaint();
	}
	
	public void processPlayAgainEvent() {
		processNewGameEvent(model.getBestOf());
		this.view.repaint();
	}
	
	public void processCompTurnEvent(int flag) {
		if(flag == 1)
			model.setComputerFirst();
		else
			model.setComputerSecond();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		view.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		Object source = event.getSource();
		
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
			this.processCompTurnEvent(1);
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
			this.processCompTurnEvent(0);
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
				view.cardLayout.next(view.cardPanel);
				view.repaintScreen();
			}	
		} 
		
		else if(source == view.nextGameButton) {
			this.processGameResetEvent();
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
