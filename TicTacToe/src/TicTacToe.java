/*TicTacToe.java
 * 
 * GUI for playing a game of tic-tac-toe against either a human opponent or the computer. GUI
 * designed using the Model-View-Controller architectural framework
 * 
 * @author: Hayden Ferencz
 * April 14, 2019
 * 
 */
public class TicTacToe {
	public static void main(String[] args) {
		View view = new View(900,900);
		Model model = new Model();
		Controller controller = new Controller(view, model);
		controller.setActionListeners();
	}
}
