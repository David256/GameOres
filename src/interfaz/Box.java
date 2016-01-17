package interfaz;

import java.awt.*;
import java.util.*;


public class Box
{
	private final Color color;
	private int posicion;
	private int altitud;

	public Box(int posicion, int altitud)
	{
		this.posicion = posicion;
		this.altitud = altitud;
		/*
		  podemos escojer entre los siguientes colores:
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
	}

	public void setPosicion(int neoPos)
	{
		this.posicion = neoPos;
	}
	public void setAltitud(int neoAlt)
	{
		this.altitud = neoAlt;
	}
	public int getPosicion(){ return this.posicion; }
	public int getAltitud(){ return this.altitud; }

	public String getNick(){
		return "x" + this.posicion + "y" + this.altitud;
	}

	public void dibujar(Graphics g)
	{
		int x = 680 - (20 * this.posicion) - 20;
		int y = 400 - (20 * this.altitud) - 20;
		g.setColor(this.color);
		g.fillRect(x, y, 20, 20);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 20, 20);
	}
	public boolean esTuColor(Color koloro)
	{
		if (this.color.equals(koloro))
			return true;
		else
			return false;
	}

	public void avanzar()
	{
		this.posicion++;
	}
}