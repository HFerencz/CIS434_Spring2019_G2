import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

@SuppressWarnings("serial")
public final class View extends JFrame{ 
	
	//Used for determining the size of the display
	 int maxX, maxY;
	 
	 CardLayout cardLayout = new CardLayout();
	 JPanel cardPanel = new JPanel(cardLayout);
	 JPanel gamePanel = new displayPanel();
	 JPanel gameOverPanel = new displayPanel();
	 JPanel startPanel = new displayPanel();
	 JPanel bestOfPanel = new displayPanel();
	
	 JButton versusPlayerButton, versusCompButton, bestOf3Button, bestOf5Button, customRangeButton, customSelectButton;
	 JButton nextGameButton = new JButton("Next Game");
	 JButton gameOverButton = new JButton("Game Over");
	 JButton mainMenuButton = new JButton("MAIN MENU");
	 JButton playAgainButton = new JButton("PLAY AGAIN");
	 JButton compFirstButton = new JButton("Computer Plays First");
	 JButton compSecondButton = new JButton("Computer Plays Second");
	 
	 JLabel player1Wins = new JLabel("Wins: ");
	 JLabel player2Wins = new JLabel("Wins: ");
	 JLabel winnerLabel = new JLabel();
	 JLabel gameOverLabel = new JLabel("GAME OVER");
	 JLabel gameWinnerLabel = new JLabel();
	 
	 JFormattedTextField gameInputField;
	 
	 private final int BOARD_LINES = 4, MAX_TOKENS = 9;
	 
	 private ArrayList<Shape> board = new ArrayList<Shape>(BOARD_LINES);
	 private ArrayList<Shape> tokens = new ArrayList<Shape>(MAX_TOKENS);


	 
	 	//default constructor
		public View(int maxX, int maxY) {
			
			//Set name for the java application
			super("Tic Tac Toe");
			
			this.maxX = maxX;
			this.maxY = maxY;
			
			createStartScreen();
			createBestOfScreen();
			createGameScreen();
			createGameOverScreen();
			
			
	        
	        cardPanel.add(startPanel, "1");
	        cardPanel.add(bestOfPanel, "2");
			cardPanel.add(gamePanel,"3");
			cardPanel.add(gameOverPanel, "4");
			cardLayout.show(cardPanel, "1");
			
			
	        this.add(cardPanel, BorderLayout.CENTER);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setSize(this.maxX, this.maxY);
	        this.setResizable(false);
	        this.setLocationRelativeTo(null);
	       
	        this.setVisible(true);	
		}
	
		
		//Create panel for the start screen
		public void createStartScreen() {
			
			//Assign dimensions to the panel
			startPanel.setPreferredSize(new Dimension(this.maxX, this.maxY));
			startPanel.setMaximumSize(new Dimension(this.maxX, this.maxY));
			startPanel.setMinimumSize(new Dimension(this.maxX, this.maxY));
			
			
			startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
			
			//Inner panels for displaying the title and New Game buttons
			JPanel startTextPanel = new JPanel();
		    JPanel startButtonPanel = new JPanel(new GridBagLayout());
			
			//Label displaying the title of the game
			JLabel startTextLabel = new JLabel("Tic Tac Toe Game!");
			startTextLabel.setFont(new Font("Courier New", Font.BOLD, 72));
			startTextLabel.setForeground(Color.RED);
			startTextPanel.add(startTextLabel);
			
			
			
			/*Add buttons to the display:
			 * 	versusPlayerButton: Button for if the opponent is a human player
			 *  versusComputerButton: Button for if the opponent is the computer
			 *  compFirstButton: If pressed, computer makes the first move
			 *  compSecondButton: If pressed, computer makes the second move
			 */
			versusPlayerButton = new JButton("Play versus player");
	        versusPlayerButton.setEnabled(true);
	        versusCompButton = new JButton("Play versus computer");
	        versusCompButton.setEnabled(true);
	        
	        compFirstButton.setVisible(false);
	        compFirstButton.setEnabled(false);
	        compSecondButton.setVisible(false);
	        compSecondButton.setEnabled(false);
	        
			
			
			//Set up the button layout
			GridBagConstraints c = new GridBagConstraints();
			c.anchor = GridBagConstraints.CENTER;
			c.gridx = 2;
			c.ipady = 80;
			c.ipadx = 80;
			startButtonPanel.add(versusPlayerButton, c);
			startButtonPanel.add(compFirstButton,c);
			c.insets = new Insets(50,0,0,0);
			c.ipadx = 60;
			startButtonPanel.add(versusCompButton,c);
			startButtonPanel.add(compSecondButton,c);
			
			//Add the title and buttons to the panel
			startPanel.add(startTextPanel);
			startPanel.add(startButtonPanel);
		}
		
		
		//Create panel for the 'best of' selection screen
		public void createBestOfScreen() {
					
			//Assign dimensions to the panel
			bestOfPanel.setPreferredSize(new Dimension(this.maxX, this.maxY));
			bestOfPanel.setMaximumSize(new Dimension(this.maxX, this.maxY));
			bestOfPanel.setMinimumSize(new Dimension(this.maxX, this.maxY));
					
					
			bestOfPanel.setLayout(new BoxLayout(bestOfPanel, BoxLayout.Y_AXIS));
					
			//Inner panels for displaying the title and New Game buttons
			JPanel startTextPanel = new JPanel();
			JPanel startButtonPanel = new JPanel(new GridBagLayout());
					
			//Label displaying the title of the game
			JLabel startTextLabel = new JLabel("Tic Tac Toe Game!");
			startTextLabel.setFont(new Font("Courier New", Font.BOLD, 72));
			startTextLabel.setForeground(Color.RED);
			startTextPanel.add(startTextLabel);
					
					
					
			/*Add buttons to the display:
			 * 	bestOf3Button: Players compete in a best of 3 match
			 *  bestOf5Button: Players compete in a best of 5 match
			 *  customRangeButton: Allow user to decide the number of matches
			 *  customSelectButton: Verify the custom number of matches the user has provided
			 */
			bestOf3Button = new JButton("Best Of 3");
		    bestOf3Button.setEnabled(true);
		    bestOf5Button = new JButton("Best Of 5");
			bestOf5Button.setEnabled(true);	
			customRangeButton = new JButton("Custom Range");
			customRangeButton.setEnabled(true);
			customSelectButton = new JButton("ENTER");
			customSelectButton.setVisible(false);
			customSelectButton.setEnabled(false);
			
			/*
			 * Formatted text field for if the user decides to choose the number of games to play
			 */
		    MaskFormatter formatter = null;
		    
			try {
				formatter = new MaskFormatter("*****");
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			formatter.setValidCharacters("123456789");
			formatter.setPlaceholderCharacter(' ');
		
			gameInputField = new JFormattedTextField(formatter);
			gameInputField.setPreferredSize(new Dimension(150,100));
			gameInputField.setFont(new Font("Times New Roman", Font.BOLD, 30));
			gameInputField.setHorizontalAlignment(JTextField.CENTER);
			gameInputField.setVisible(false);
			gameInputField.setEditable(false);
			gameInputField.setFocusLostBehavior(JFormattedTextField.PERSIST);
			

			//Set up the button layout
			GridBagConstraints c = new GridBagConstraints();
			c.anchor = GridBagConstraints.NORTH;
			c.gridx = 2;
			c.ipady = 80;
			c.ipadx = 80;
			c.insets = new Insets(100,0,0,0);
			startButtonPanel.add(bestOf3Button, c);
			c.insets = new Insets(80,0,0,0);
			c.ipadx = 80;
			startButtonPanel.add(bestOf5Button,c);
			c.insets = new Insets(80,0,0,0);
			c.ipadx = 50;
			startButtonPanel.add(customRangeButton,c);
			
			//Set up input field layout
			c.anchor = GridBagConstraints.CENTER;
			c.ipadx = 20;
			c.ipady = 20;
			c.insets = new Insets(0,-200,0,0);
			startButtonPanel.add(gameInputField,c);
			c.ipadx = 80;
			c.ipady = 93;
			c.insets = new Insets(-120,200,0,0);
			startButtonPanel.add(customSelectButton,c);
			
					
			//Add the title and buttons to the panel
			bestOfPanel.add(startTextPanel);
			bestOfPanel.add(startButtonPanel);
		}
		
		
		
		//Create panel for the actual game
		public void createGameScreen() {
			//Assign dimensions to the panel
			gamePanel.setPreferredSize(new Dimension(this.maxX, this.maxY));
			gamePanel.setMaximumSize(new Dimension(this.maxX, this.maxY));
			gamePanel.setMinimumSize(new Dimension(this.maxX, this.maxY));	
			gamePanel.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			
			c.anchor = GridBagConstraints.NORTHWEST;
			c.gridx = 0;
		    c.gridy = 0;
		    c.insets = new Insets(0,10,0,0);
		    c.weightx = 1.0;
		    c.weighty = 1.0;
		    JLabel player1 = new JLabel("Player 1");
			player1.setFont(new Font("Courier New", Font.BOLD, 48));
			gamePanel.add(player1,c);
			
			c.insets = new Insets(50,50,0,0);
			player1Wins.setFont(new Font("Courier New", Font.BOLD, 32));
			gamePanel.add(player1Wins,c);
			
			c.anchor = GridBagConstraints.NORTHEAST;
			c.insets = new Insets(0,0,0,10);
			JLabel player2 = new JLabel("Player 2");
			player2.setFont(new Font("Courier New", Font.BOLD, 48));
			gamePanel.add(player2,c);
			
			c.insets = new Insets(50,0,0,90);
			player2Wins.setFont(new Font("Courier New", Font.BOLD, 32));
			gamePanel.add(player2Wins,c);
			
	
			c.anchor = GridBagConstraints.NORTH;
			c.insets = new Insets(70,25,0,0);
		    winnerLabel.setFont(new Font("Courier New", Font.BOLD, 40));
		    winnerLabel.setForeground(Color.MAGENTA);
		    winnerLabel.setVisible(false);
		    gamePanel.add(winnerLabel,c);
			
			c.anchor = GridBagConstraints.SOUTH;
			c.ipady = 50;
			c.ipadx = 50;
			c.insets = new Insets(0,0,20,0);
			nextGameButton.setEnabled(false);
			nextGameButton.setVisible(false);
			gamePanel.add(nextGameButton,c);
			gameOverButton.setEnabled(false);
			gameOverButton.setVisible(false);
			gamePanel.add(gameOverButton,c);
		}
		
		
		//Create panel for the game over  game
		public void createGameOverScreen() {
			
			//Inner panels for displaying the text and buttons
			JPanel gameOverTextPanel = new JPanel(new GridBagLayout());
		    JPanel gameOverButtonPanel = new JPanel(new GridBagLayout());
			
			//Assign dimensions to the panel
			gameOverPanel.setPreferredSize(new Dimension(this.maxX, this.maxY));
			gameOverPanel.setMaximumSize(new Dimension(this.maxX, this.maxY));
			gameOverPanel.setMinimumSize(new Dimension(this.maxX, this.maxY));	
			gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.Y_AXIS));
			GridBagConstraints c = new GridBagConstraints();
			
			c.anchor = GridBagConstraints.NORTH;
			c.gridx = 0;
		    c.gridy = 0;
		    c.insets = new Insets(0,10,0,0);
		    c.weightx = 1.0;
		    c.weighty = 1.0;
		  
			gameOverLabel.setFont(new Font("Courier New", Font.BOLD, 72));
			gameOverTextPanel.add(gameOverLabel,c);
			
			c.insets = new Insets(100, 0, 0, 0);
			gameWinnerLabel.setFont(new Font("Courier New", Font.BOLD, 60));
			gameOverTextPanel.add(gameWinnerLabel,c);
			
			playAgainButton.setVisible(true);
			playAgainButton.setEnabled(true);
			mainMenuButton.setVisible(true);
			mainMenuButton.setEnabled(true);
			
			//Set up the button layout
			c.gridx = 2;
			c.ipady = 80;
			c.ipadx = 100;
			c.insets = new Insets(50,0,0,0);
			gameOverButtonPanel.add(playAgainButton, c);
			c.insets = new Insets(200,0,0,0);
			c.ipadx = 100;
			gameOverButtonPanel.add(mainMenuButton,c);
			
			//Add the title and buttons to the panel
			gameOverPanel.add(gameOverTextPanel);
			gameOverPanel.add(gameOverButtonPanel);
			
					
		}
		
		//Function that returns the ArrayList holding the shapes that form the board
		public ArrayList<Shape> getBoardArray() {
			return board;
		}
		
		//Function that returns the ArrayList holding the X's and O's that go into the board
		public ArrayList<Shape> getTokenArray() {
			return tokens;
		}

		
		//Repaint the display
		public void repaintScreen() {
			gamePanel.repaint();
		}
		
		//Class for drawing the board onto the screen
		class displayPanel extends JPanel {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);	
			
				int thickness = 5;
				Graphics2D shape = (Graphics2D)g.create();
				shape.setStroke(new BasicStroke(thickness));
				for(int i = 0; i < board.size(); i++)
					board.get(i).paint(shape);
				for(int i = 0; i < tokens.size(); i++)
					tokens.get(i).paint(shape);
		
			}
		}
}
