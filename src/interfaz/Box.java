package interfaz;

import java.awt.*;
import java.util.*;

/**
* Esta clase define los métodos y atributos para el manejo y dibujo de
* los cubos en el juego.
*
*/
public class Box
{
	private final Color color;
	private int posicion;
	private int altitud;
	private int limite;
	private boolean encima = true;



	/**
	* Contructor de la clase Box.
	*
	* @param posicion 	indica la posición del cubo en la distribución de cubos.
	*					Esta no es la posición en pixeles, sino la de los cubos.
	*
	* @param altitud 	indica la altitud del cubo en la distribución de cubos.
	*					Esta no es la altitud en pixeles, sino la de los cubos.
	*/
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



	/**
	* Fija el valor de la posición nueva ue tendrá el cubo.
	*
	* @param neoPos 	Es el valor de la nueva posición.
	*/
	public void setPosicion(int neoPos)
	{
		this.posicion = neoPos;
	}



	/**
	* Fija el valor de la altitud nueva ue tendrá el cubo.
	*
	* @param neoAlt	Es el valor de la nueva altitud.
	*/
	public void setAltitud(int neoAlt)
	{
		this.altitud = neoAlt;
	}


	/**
	* Fija el valor limite para desplazar los cubos.
	*
	* @param limite
	*/
	public void setLimite(int limite)
	{
		this.limite = limite;
	}



	/**
	* Retorna el valor de la posicion de el objecto de la clase Box
	*
	* @return Retorna un entero con el valor de la posición de derecha a izquierda
	*/
	public int getPosicion(){ return this.posicion; }



	/**
	* Retorna el valor de la altitud de el objecto de la clase Box
	*
	* @return Retorna un entero con el valor de la altitud de abajo a arriba
	*/
	public int getAltitud(){ return this.altitud; }



	/**
	* Este método retorno un valor unico para cada objecto de la clase Box
	*
	* @return String que contiene un valor unico por cubo, 
	*		  al menos que dos de estos tengan la misma posición en el espacio
	*/
	public String getNick(){
		return "x" + this.posicion + "y" + this.altitud;
	}



	/**
	* Dibuja en un objecto de la clase Graphics el cubo, todo en su respectiva posición
	* en el espacio.
	*
	* @param g 		Es un objecto de la clase Graphics para poder dibujar en pantalla
	*
	* @see java.awt.Graphics
	*/
	public void dibujar(Graphics g)
	{
		if (this.encima)
		{
			int x = 680 - (20 * this.posicion) - 20;
			int y = 400 - (20 * this.altitud) - 20;
			g.setColor(this.color);
			g.fillRect(x, y, 20, 20);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, 20, 20);
		}
		else
		{
			this.posicion += (int)((new Random()).nextDouble() * 4) - 1;
			this.altitud -= 5;
			int x = 680 - (20 * this.posicion) - 20;
			int y = 400 - (20 * this.altitud) - 20;
			g.setColor(this.color);
			g.fillRect(x, y, 20, 20);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, 20, 20);
		}
	}



	/**
	* Con este método podremos saber si un color es igual al que tiene
	* un objecto de la clase Box.
	*
	* @return boolean que define si es verdadero, siendo los colores iguales.
	*		  o falso si no lo son.
	*
	*/
	public boolean esTuColor(Color koloro)
	{
		if (this.color.equals(koloro))
			return true;
		else
			return false;
	}


	/**
	* Nos permite avanzar una posición hacia la izquierda, haciendo que los cubos
	* se desplacen
	*/
	public void avanzar()
	{
		this.posicion++;
	}


	/**
	* Este método gestiona la activación de la animación de caida solo si
	* el valor de la posición del cubo ha sobrepasado el limite permitido.
	* El no uso de este método evitará que los cubos caigan, lo que significa
	* que no tendrá gravedad.
	*
	* @see #setLimite(int limite)
	*/
	public void comprobarCaida()
	{
		if (this.limite == (680 - (this.posicion*20)))
			this.encima = false;
	}
}