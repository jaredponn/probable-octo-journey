package Components;

import poj.Component.Component;
import poj.Render.*;
import poj.linear.Vector2f;

public class Render implements Component
{
	private ImageRenderObject graphic;
	private Vector2f position_translation;
	private int render_layer;

	// constructor assumes the width and the height of the image is the same
	// as model shown. Will not work for animations which need their own
	// specified width and height
	public Render(ImageRenderObject a)
	{
		this.graphic = a;
		this.position_translation = new Vector2f(0, 0);
	}

	public ImageRenderObject getGraphic()
	{
		return this.graphic;
	}
	// render layer
	// load things in, give render component

	public void setImageWindow(ImageWindow iw)
	{
		this.graphic.setImageWindow(iw);
	}

	public ImageWindow getImageWindow()
	{
		return this.graphic.getImageWindow();
	}

	public void print()
	{
		System.out.println("Render Component");
		System.out.println();
		System.out.println("END Render Component");
	}
}
