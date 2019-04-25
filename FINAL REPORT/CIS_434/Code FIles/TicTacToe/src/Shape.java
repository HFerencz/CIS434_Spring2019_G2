import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Shape {
	private Color color;
	
	//default constructor
	public Shape() {
		
	}
	
	
	public Color getColor() {
		return color;
	}
	
	public void changeColor(Color color) {
		this.color = color;
	}
	
	public abstract void paint(Graphics2D shape);
}
