package interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.*;


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

	public Ventana()
	{
		setTitle("Ores y que?");
		setSize(lasX, lasY);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

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
					System.out.println("Ciclo off");
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

	public void dibujarPlataforma(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(limite, nivelPiso, 340, 50);
		g.setColor(Color.BLACK);
		g.drawRect(limite, nivelPiso, 340, 50);
	}

	public void dibujarLimite(Graphics g)
	{
		g.setColor(Color.DARK_GRAY);
		for (int i=0; i<10; i++)
			g.drawLine (this.limite, (400 - (i*23)), this.limite, (400 - (i*24)));
	}

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

	public void dibujarBoxes(Graphics g)
	{
		for (int i=0; i<boxes.size(); i++)
		{
			((Box) boxes.get(i)).dibujar(g);
			((Box) boxes.get(i)).setLimite(this.limite);
			((Box) boxes.get(i)).comprobarCaida();
		}
	}

	public void agregarFila()
	{
		for (int i=0; i<10; i++)
		{
			boxes.add(new Box(0, i));
		}
	}
}