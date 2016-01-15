package interfaz;

import java.awt.*;


public class Box
{
	Graphics g;
	Color color;
	int posX;
	int posY;

	public Box(Graphics g, Color color)
	{
		this.g = g;
		this.color = color;
	}

	public Box(Graphics g, Color color, int x, int y))
	{
		this.g = g;
		this.color = color;
		this.posX = x;
		this.posY = y;
	}

	public void setPosition(int x, int y)
	{
		this.posX = x;
		this.posY = y;
	}

	public void setPosX(int posx)
	{
		this.posX = posx;
	}

	public void setPosY(int posy)
	{
		this.posY = posy;
	}

	public int getPosX(){ return this.posX; }
	public int getPosY(){ return this.posY; }

	public void preparar()
	{
		g.setColor(color);
		g.drawLine(0,0,50,50);
	}

	public Graphics getGraphicsObject()
	{
		System.out.println("Retornamos objecto Graphics");
		return this.g;
	}

}