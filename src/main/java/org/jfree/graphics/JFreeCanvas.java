package org.jfree.graphics;

import java.awt.Shape;

public interface JFreeCanvas {

	Shape getClip();

	void clip(Shape s);

}
