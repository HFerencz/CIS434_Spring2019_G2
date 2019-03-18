import java.awt.Color;
import java.awt.Graphics2D;

/*************************
 * Class for the X token *
 *************************/
public class Cross extends Shape{
	private final int POINTS = 6;
	private int[] xCoords = new int[POINTS];
	private int[] yCoords= new int[POINTS];
	
public Cross(int[] xCoords, int[] yCoords, Color color) {
		for(int i = 0; i < POINTS; i++) {
			this.xCoords[i] = xCoords[i];
			this.yCoords[i] = yCoords[i];
		}
		changeColor(color);
	}

	public int[] getXCoords() {
		return xCoords;
	}
	
	public int[] getYCoords() {
		return yCoords;
	}
	
	
	@Override
	public void paint(Graphics2D shape) {
		shape.setColor(getColor());
		shape.drawPolygon(getXCoords(), getYCoords(), POINTS);
	}
}
