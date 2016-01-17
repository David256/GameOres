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
	private int vidaSinOxigeno = 50;
	private boolean encima = true;
	private boolean vivo = true;



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
	* Retorna el valor de la posicion de el objeto de la clase Box
	*
	* @return Retorna un entero con el valor de la posición de derecha a izquierda
	*/
	public int getPosicion(){ return this.posicion; }



	/**
	* Retorna el valor de la altitud de el objeto de la clase Box
	*
	* @return Retorna un entero con el valor de la altitud de abajo a arriba
	*/
	public int getAltitud(){ return this.altitud; }



	/**
	* Retorna el valor de la variable color de la clase Color.
	*
	* @return color 	Este es un objeto de la clase Color
	*
	* @see java.awt.Color
	*/
	public Color getColor(){ return this.color; }



	/**
	* Retorna el valor de la variable vivo, la cual denota si esta sobre la plataforma
	* y de ser negativo, maneja el tiempo que el objeto Box puede permanecer vivo.
	*
	* @return vivo es la variable booleana para conocer el estado de vida
	*/
	public boolean getVivo(){ return this.vivo; }



	/**
	* Este método retorno un valor unico para cada objeto de la clase Box
	*
	* @return String que contiene un valor unico por cubo, 
	*		  al menos que dos de estos tengan la misma posición en el espacio
	*/
	public String getNick(){
		return "x" + this.posicion + "y" + this.altitud;
	}



	/**
	* Cambia el valor boolean de la variable vivo, importante para poder eliminar el objecto.
	* Este método permite a cada objeto Box eliminar a sus cercanos
	*
	* @param deUna 		es true si se trata de un objeto que no es el jefe y su misión es ser eliminado
	* @param idBoss 	es el valor entero dentro del Vector de objetos Box para evitar
	* 				 	una pronta eliminación del cubo encargado de eliminar a los demás
	* @param selfie 	es un objeto de tipo Ventana útil para poder recorrer el Vector de Box's
	*/
	public void matar(boolean deUna, int idBoss, Ventana selfie)
	{
		if (deUna)
		{
			this.vivo = false;
			return;
		}
		// nos toca matar a todos los demás
		// TODO: continuar con esto
	}



	/**
	* Dibuja en un objeto de la clase Graphics el cubo, todo en su respectiva posición
	* en el espacio.
	*
	* @param g 		Es un objeto de la clase Graphics para poder dibujar en pantalla
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
	* un objeto de la clase Box.
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


	/**
	* Con este método comprobaremos si ya el cubo no puede vivir más para ser eliminado
	* del programa. Cada vez que el cubo es dibujado comprueba si esta encima de la
	* plataforma, de ser negativo comenzará a "morir".
	* Este proceso lo realiza restando una unidad entera a la variable entera vidaSinOxigeno
	* y cuando esta variable sea cero, se activará el estado de muerto: fijando la variable
	* booleana vivo a false.
	*
	*/
	public void estaVivo()
	{
		if (!this.encima)
		{
			this.vidaSinOxigeno--; // restamos una unidad
			if (this.vidaSinOxigeno <= 0)
			{
				this.vivo = false;
			}
		}
	}


	/**
	* Este método recibe una posición en pixele y devuelve true si esta dentro del area del cubo
	*
	* @param x 	Es la posición en el eje x en pixeles
	* @param y 	Es la posición en el eje y en pixeles
	*
	*
	* @return dentro es booleana y es true si los pixeles están dentro del area
	*
	*/
	public boolean estaDentro(int x, int y)
	{
		int x1 = 680 - (20 * this.posicion) - 20;
		int y1 = 400 - (20 * this.altitud) - 20;
		int x2 = x1 + 20;
		int y2 = y1 + 20;

		/* Comprobamos los valores ^_^

		System.out.println("El valor de x1:" + x1 + "\n"
			+ "El valor de x2:" + x2 + "\n"
			+ "El valor de y1:" + y1 + "\n"
			+ "El valor de y2:" + y2 + "\n");
		System.out.println("\n" + "Los valores entrantes son:" + "\n"
			+ "x:" + x + "\n"
			+ "y:" + y + "\n");

		if ((x > x1 && x < x2) && (y > y1 && y < y2))
		{
			System.out.println("Dentro");
		}else{
			System.out.println("Fuera!");
		}
		System.exit(0);
		*/
	
		// ahora a jugar con esto
		if ((x > x1 && x < x2) && (y > y1 && y < y2))
		{
			return true;
		}else{
			return false;
		}
	}
}