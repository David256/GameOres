package interfaz;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MouseHandler extends MouseAdapter
{

	private int ratonX;
	private int ratonY;

	public MouseHandler()
	{
		this.ratonX = 0;
		this.ratonY = 0;
	}

	public void mouseClicked(MouseEvent e)
	{
		this.ratonX = e.getX();
		this.ratonY = e.getY();
		System.out.println(this.ratonX + ":" + this.ratonY);
	}

	public int getRatonX()
	{
		int temporal = this.ratonX;
		this.ratonX = 0;
		return temporal;
	}
	public int getRatonY()
	{
		int temporal = this.ratonY;
		this.ratonY = 0;
		return temporal;
	}
}