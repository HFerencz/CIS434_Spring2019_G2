import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Circle extends Shape{
	private int radius;
	private Point origin;
	
	public Circle(Point origin, int radius, Color color) {
		
		this.origin = origin;
		this.radius = radius;
		changeColor(color);
	}
	public Point getOrigin() {
		return origin;
	}
	
	public int getRadius() {
		return radius;
	}

	@Override
	public void paint(Graphics2D shape) {
		shape.setColor(getColor());
		shape.drawOval(getOrigin().x-getRadius(), getOrigin().y-getRadius(), getRadius()*2,getRadius()*2);	
	}
}
