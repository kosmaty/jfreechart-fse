package org.jfree.graphics;

import java.awt.Graphics2D;
import java.awt.Shape;

public class Graphics2DCanvas implements JFreeCanvas {

	private Graphics2D graphics;

	public Graphics2DCanvas(Graphics2D graphics) {
		this.graphics = graphics;
	}

	public Shape getClip() {
		return graphics.getClip();
	}

	public void clip(Shape s) {
		graphics.clip(s);
	}

}
