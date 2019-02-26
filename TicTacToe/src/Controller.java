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
		view.bestOf3Button.addActionListener(this);
		view.bestOf5Button.addActionListener(this);
		view.nextGameButton.addActionListener(this);
		view.mainMenuButton.addActionListener(this);
		
	}
	
	public void processNewGameEvent(int bestOfChoice) {
		model.setBestOf(bestOfChoice);
		model.newGameEvent(view.getBoardArray());
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
			if(model.gameOverEvent(view.mainMenuButton)) {
				view.mainMenuButton.setEnabled(true);
				view.mainMenuButton.setVisible(true);
			} else {
				view.nextGameButton.setEnabled(true);
				view.nextGameButton.setVisible(true);
			}
			
			return true;
		}
		return false;
	}
	
	public void processGameResetEvent() {
		model.gameReset(view.getTokenArray(), view.nextGameButton, view.mainMenuButton, view.winnerLabel, view.player1Wins, view.player2Wins);
		this.view.repaint();
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
			view.cardLayout.next(view.cardPanel);
			view.repaintScreen();
		}
		else if(source == view.bestOf3Button) {
			this.processNewGameEvent(0);
			view.cardLayout.next(view.cardPanel);
			view.repaintScreen();
		}
		else if(source == view.bestOf5Button) {
			this.processNewGameEvent(1);
			view.cardLayout.next(view.cardPanel);
			view.repaintScreen();
		}
		else if(source == view.nextGameButton) {
			this.processGameResetEvent();
			view.repaintScreen();
		} 
		else if(source == view.mainMenuButton) {
			this.processGameResetEvent();
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
