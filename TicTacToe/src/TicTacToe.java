
public class TicTacToe {
	public static void main(String[] args) {
		View view = new View(900,900);
		Model model = new Model();
		Controller controller = new Controller(view, model);
		controller.setActionListeners();
	}
}
