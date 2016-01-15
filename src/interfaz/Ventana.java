package interfaz;

import javax.swing.*;


public class Ventana extends JFrame
{

	Papel panel;

	public Ventana()
	{

		panel = new Papel();
		setTitle("Ores y que?");
		setSize(680, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel);

	}

}