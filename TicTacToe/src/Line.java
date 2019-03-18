import java.awt.Color;
import java.awt.Graphics2D;

/***************************************************
 * Class for the lines that make up the game board *
 ***************************************************/
public class Line extends Shape {
	private int x1,y1,x2,y2;
	
	public Line(int x1, int y1, int x2, int y2, Color color) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		changeColor(color);
	}
	
	public int getX1() {
		return x1;
	}
	
	public int getY1() {
		return y1;
	}
	
	public int getX2() {
		return x2;
	}
	
	public int getY2() {
		return y2;
	}
	
	@Override
	public void paint(Graphics2D shape) {
		shape.setColor(getColor());
		shape.drawLine(getX1(), getY1(), getX2(), getY2());
		
	}

}
