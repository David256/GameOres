package interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
* Es la clase encargada de preparar la interfaz gráfica de usuario
* y manejar los eventos que en ella sucedan
*
* @author Yavi OS
*/
public class Ventana extends JFrame implements Runnable
{
	Image img;
	Graphics gBuffer = null;
	int lasX = 680;
	int lasY = 480;
	Vector<Box> boxes = new Vector<Box>();
	int nivelPiso = 400;
	int limite = lasX/2;
	boolean cubosDentro = false;
	MouseHandler mhd = new MouseHandler();

	/**
	* Contructor de la clase
	*/
	public Ventana()
	{
		setTitle("Ores y que?");
		setSize(lasX, lasY);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(mhd);
	}

	/**
	* Método sobrecargado de la interfaz Runnable, que permite ejecutar
	* algunas funciones en un Thread aparte. Dentro de este método esta
	* un ciclo While para realizar los calculos de los gráficos del juego
	* y luego llamar al método repaint()
	*
	* @see java.lang.Runnable
	*/
	public void run()
	{
		try
		{
			int ciclo = 0;
			while(true)
			{
				calculo();
				repaint();
				Thread.sleep(100);
				ciclo++;
				if (ciclo > 30) // el común es 230 ciclos
				{
					ciclo = 0;
					for (int i=0; i<boxes.size(); i++)
						((Box) boxes.get(i)).avanzar();
					this.agregarFila();
					System.err.println("Ciclo off");
				}
			}

		}
		catch(InterruptedException e)
		{
			System.out.println("Interruncción del juego");
		}

	}

	public void calculo()
	{
		int x = mhd.getRatonX();
		int y = mhd.getRatonY();

		for (int i=0; i<boxes.size(); i++)
		{
			// comprovamos valores de verdad
			if (x != 0 && y != 0)
			{
				// si son cero, no se ha dado click en añosssss
				if ( ((Box) boxes.get(i)).estaDentro(x, y) )
				{
					((Box) boxes.get(i)).matar(false, i, this);
				}
			}
		}
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);

		if (gBuffer == null)
		{
			img = createImage(lasX, lasY);
			gBuffer = img.getGraphics();
		}
		gBuffer.setColor(getBackground());
		gBuffer.fillRect(0,0, lasX,lasY);
		
		// mejoramos graficos
		Graphics2D g2d = (Graphics2D) gBuffer;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(Color.RED);

		this.dibujarPlataforma(gBuffer);
		this.dibujarLimite(gBuffer);
		if (!this.cubosDentro){
			System.out.println("Iniciamos primera vez Box()");
			this.prepararBoxes();
			this.cubosDentro = true;
		}
		this.dibujarBoxes(gBuffer);

		
		g.drawImage(img, 0, 0, null);
	}

	/**
	* Con este método dibujamos una plataforma en pantalla.
	*
	* @param g    Es un objeto de la clase Graphics para poder dibujar en pantalla
	*/
	public void dibujarPlataforma(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(limite, nivelPiso, 340, 50);
		g.setColor(Color.BLACK);
		g.drawRect(limite, nivelPiso, 340, 50);
	}

	/**
	* Este método dibuja una linea que servirá de limite para saber donde
	* comenzarán a caer los cubitos
	*
	* @param g    Es un objeto de la clase Graphics para poder dibujar en pantalla
	*/
	public void dibujarLimite(Graphics g)
	{
		g.setColor(Color.DARK_GRAY);
		for (int i=0; i<10; i++)
			g.drawLine (this.limite, (400 - (i*23)), this.limite, (400 - (i*24)));
	}

	/**
	* Este método crea un vector de objetos de la clase Box.
	*
	* @see Box
	*/
	public void prepararBoxes()
	{
		for (int j=0; j<10; j++)
		{
			for (int i=0; i<7; i++)
			{
				boxes.add(new Box(i,j));
			}
		}
	}

	/**
	* Con este método dibujamos en pantalla los cubos (cajas), la posición
	* está fijada por los métodos internos de la clase Box.
	*
	* @param g    Es un objeto de la clase Graphics para poder dibujar en pantalla
	*/
	public void dibujarBoxes(Graphics g)
	{
		for (int i=0; i<boxes.size(); i++)
		{
			((Box) boxes.get(i)).estaVivo();
			((Box) boxes.get(i)).dibujar(g);
			((Box) boxes.get(i)).setLimite(this.limite);
			((Box) boxes.get(i)).comprobarCaida();
			if (!((Box) boxes.get(i)).getVivo())
			{
				boxes.remove(i);
			}
			//System.out.println("Hay box: " + boxes.size());
		}
	}

	/**
	* Este método se encarga de agregar una nueva fila de cubos cada
	* vez que todos los cubos avanzan un lugar hacia la izquierda.
	*
	*/
	public void agregarFila()
	{
		for (int i=0; i<10; i++)
		{
			boxes.add(new Box(0, i));
		}
	}
}