package interfaz;

import javax.swing.*;
import java.awt.*;


public class Ventana extends JFrame implements Runnable
{

	int x = 0;
	int y = 0;
	Image img;
	Graphics gBuffer = null;
	int lasX = 680;
	int lasY = 480;

	public Ventana()
	{
		panel = new Papel();
		setTitle("Ores y que?");
		setSize(lasX, lasY);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void run()
	{
		// TODO: inciamos el juego
		try
		{
			while(true)
			{
				calculo();
				repaint();
				Thread.sleep(100);
			}

		}catch(InterruptedException e)
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
		// intentemos mejorar esto
		gBuffer.setColor(getBackground());
		gBuffer.fillRect(0,0, lasX,lasY);

		// dibujamos la cosa
		gBuffer.fillOval(this.x, this.y, 60,10);
		
		Graphics2D g2d = (Graphics2D) gBuffer;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(Color.RED);
		gBuffer.fillOval(this.x, this.y, 50, 50);

		
		g.drawImage(img, 0, 0, null);
		//g.setColor(Color.RED);
		//g.drawLine(0,0,100,100);
		//Box box = new Box(g, Color.RED);
		//g = box.getGraphicsObject();
	}

}