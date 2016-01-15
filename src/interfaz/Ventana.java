package interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.*;


public class Ventana extends JFrame implements Runnable
{

	int x = 0;
	int y = 0;
	Image img;
	Graphics gBuffer = null;
	int lasX = 680;
	int lasY = 480;
	Vector<Box> boxes = new Vector<Box>();
	int nivelPiso = 400;
	int limite = lasX/2;

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
			while(true)
			{
				calculo();
				repaint();
				Thread.sleep(100);
			}

		}
		catch(InterruptedException e)
		{
			System.out.println("Interruncción del juego");
		}

	}

	public void calculo()
	{
		this.x += 1;
		this.y += 1;
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

		gBuffer.fillOval(this.x, this.y, 50, 50);

		this.dibujarPlataforma(gBuffer);
		this.dibujarBoxes(gBuffer);

		
		g.drawImage(img, 0, 0, null);
		//g.setColor(Color.RED);
		//g.drawLine(0,0,100,100);
		//Box box = new Box(g, Color.RED);
		//g = box.getGraphicsObject();
	}

	public void dibujarPlataforma(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(limite, nivelPiso, 280, 50);
	}

	public void dibujarBoxes(Graphics g)
	{
		for (int i=0; i<7; i++)
		{
			for (int j=0; j<10; j++)
			{
				boxes.add(new Box(g, i*20, j*20));
			}
		}
	}
}