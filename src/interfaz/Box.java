package interfaz;

import java.awt.*;
import java.util.*;


public class Box
{
	Graphics g;
	Color color;
	int x;
	int y;

	public Box(Graphics g, int x, int y)
	{
		this.g = g;
		this.color = color;
		this.x = x;
		this.y = y;
		/* podemos escojer entre los siguientes colores:
		- BLUE
		- DARK_GRAY
		- GREEN
		- RED
		- YELLOW
		*/
		Random m = new Random();
		int i = (int) (m.nextDouble()*5);
		Color elColor[] = {Color.BLUE, Color.DARK_GRAY, Color.GREEN, Color.RED, Color.YELLOW};
		this.color = elColor[i];
		preparar();
	}

	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void setx(int x)
	{
		this.x = x;
	}

	public void sety(int y)
	{
		this.y = y;
	}

	public int getx(){ return this.x; }
	public int gety(){ return this.y; }

	public String getNick(){
		return "x" + this.x + "y" + this.y;
	}

	public void preparar()
	{
		g.setColor(this.color);
		g.fillRect(this.x,this.y,20,20);
	}

	public Graphics getGraphicsObject()
	{
		return this.g;
	}

}