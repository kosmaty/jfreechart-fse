/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2014, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.]
 *
 * -----------
 * Colors.java
 * -----------
 * (C) Copyright 2013, 2014, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 */

package org.jfree.chart;

import javafx.scene.paint.Color;

/**
 * A utility class that creates and returns color swatches that can be used in
 * charts. The "i want hue" utility has been used to generate a number of these
 * color sets. See <a href="http://tools.medialab.sciences-po.fr/iwanthue/">
 * http://tools.medialab.sciences-po.fr/iwanthue/</a>. <br>
 * <br>
 * This class has been copied (with permission as well as slight modification)
 * from the Orson Charts project.
 */
public class Colors {

	private Colors() {
		// no need to instantiate this class
	}

	/**
	 * Returns the default colors.
	 * 
	 * @return The default colors.
	 */
	public static Color[] getDefaultColors() {
		return createFancyLightColors();
	}

	/**
	 * Returns a palette of ten colors created using the "i want hue" utility,
	 * using the preset "Fancy (light background)" settings. A new array
	 * instance is created for each call to this method. A link to the
	 * "i want hue" utility is given in the class description.
	 * 
	 * @return An array of ten colors (never <code>null</code>).
	 */
	public static Color[] createFancyLightColors() {
		Color[] result = new Color[10];
		result[0] = Color.rgb(239, 164, 127);
		result[1] = Color.rgb(140, 228, 139);
		result[2] = Color.rgb(155, 208, 227);
		result[3] = Color.rgb(221, 228, 95);
		result[4] = Color.rgb(118, 223, 194);
		result[5] = Color.rgb(240, 166, 184);
		result[6] = Color.rgb(231, 185, 98);
		result[7] = Color.rgb(186, 214, 150);
		result[8] = Color.rgb(217, 184, 226);
		result[9] = Color.rgb(201, 212, 116);
		return result;
	}

	/**
	 * Returns a palette of ten colors created using the "i want hue" utility,
	 * using the preset "Fancy (dark background)" settings. A new array instance
	 * is created for each call to this method. A link to the "i want hue"
	 * utility is given in the class description.
	 * 
	 * @return An array of ten colors (never <code>null</code>).
	 */
	public static Color[] createFancyDarkColors() {
		Color[] result = new Color[10];
		result[0] = Color.rgb(78, 81, 97);
		result[1] = Color.rgb(91, 104, 51);
		result[2] = Color.rgb(138, 75, 65);
		result[3] = Color.rgb(72, 62, 34);
		result[4] = Color.rgb(58, 100, 75);
		result[5] = Color.rgb(39, 63, 59);
		result[6] = Color.rgb(105, 68, 75);
		result[7] = Color.rgb(120, 90, 120);
		result[8] = Color.rgb(119, 90, 50);
		result[9] = Color.rgb(59, 103, 111);
		return result;
	}

	/**
	 * Returns a palette of ten colors created using the "i want hue" utility,
	 * using the preset "Shades" settings. A new array instance is created for
	 * each call to this method. A link to the "i want hue" utility is given in
	 * the class description.
	 * 
	 * @return An array of ten colors (never <code>null</code>).
	 */
	public static Color[] createShadesColors() {
		Color[] result = new Color[10];
		result[0] = Color.rgb(137, 132, 104);
		result[1] = Color.rgb(217, 232, 208);
		result[2] = Color.rgb(53, 48, 40);
		result[3] = Color.rgb(240, 225, 172);
		result[4] = Color.rgb(196, 160, 128);
		result[5] = Color.rgb(92, 96, 87);
		result[6] = Color.rgb(136, 141, 136);
		result[7] = Color.rgb(106, 93, 66);
		result[8] = Color.rgb(205, 199, 168);
		result[9] = Color.rgb(158, 168, 143);
		return result;
	}

	/**
	 * Returns a palette of ten colors created using the "i want hue" utility,
	 * using the preset "Tarnish" settings. A new array instance is created for
	 * each call to this method. A link to the "i want hue" utility is given in
	 * the class description.
	 * 
	 * @return An array of ten colors (never <code>null</code>).
	 */
	public static Color[] createTarnishColors() {
		Color[] result = new Color[10];
		result[0] = Color.rgb(148, 129, 121);
		result[1] = Color.rgb(179, 181, 136);
		result[2] = Color.rgb(204, 163, 140);
		result[3] = Color.rgb(102, 93, 80);
		result[4] = Color.rgb(164, 178, 159);
		result[5] = Color.rgb(156, 130, 100);
		result[6] = Color.rgb(129, 142, 124);
		result[7] = Color.rgb(186, 168, 159);
		result[8] = Color.rgb(144, 148, 108);
		result[9] = Color.rgb(189, 169, 131);
		return result;
	}

	/**
	 * Returns a palette of ten colors created using the "i want hue" utility,
	 * using the preset "Pastel" settings. A new array instance is created for
	 * each call to this method. A link to the "i want hue" utility is given in
	 * the class description.
	 * 
	 * @return An array of ten colors (never <code>null</code>).
	 */
	public static Color[] createPastelColors() {
		Color[] result = new Color[10];
		result[0] = Color.rgb(232, 177, 165);
		result[1] = Color.rgb(207, 235, 142);
		result[2] = Color.rgb(142, 220, 220);
		result[3] = Color.rgb(228, 186, 115);
		result[4] = Color.rgb(187, 200, 230);
		result[5] = Color.rgb(157, 222, 177);
		result[6] = Color.rgb(234, 183, 210);
		result[7] = Color.rgb(213, 206, 169);
		result[8] = Color.rgb(202, 214, 205);
		result[9] = Color.rgb(195, 204, 133);
		return result;
	}

	/**
	 * Returns a palette of ten colors created using the "i want hue" utility,
	 * using the preset "Pimp" settings. A new array instance is created for
	 * each call to this method. A link to the "i want hue" utility is given in
	 * the class description.
	 * 
	 * @return An array of ten colors (never <code>null</code>).
	 */
	public static Color[] createPimpColors() {
		Color[] result = new Color[10];
		result[0] = Color.rgb(197, 75, 103);
		result[1] = Color.rgb(85, 154, 48);
		result[2] = Color.rgb(122, 110, 206);
		result[3] = Color.rgb(190, 100, 50);
		result[4] = Color.rgb(201, 79, 209);
		result[5] = Color.rgb(95, 127, 170);
		result[6] = Color.rgb(147, 129, 39);
		result[7] = Color.rgb(63, 142, 96);
		result[8] = Color.rgb(186, 84, 150);
		result[9] = Color.rgb(219, 66, 52);
		return result;
	}

	/**
	 * Returns a palette of ten colors created using the "i want hue" utility,
	 * using the preset "Intense" settings. A new array instance is created for
	 * each call to this method. A link to the "i want hue" utility is given in
	 * the class description.
	 * 
	 * @return An array of ten colors (never <code>null</code>).
	 */
	public static Color[] createIntenseColors() {
		Color[] result = new Color[10];
		result[0] = Color.rgb(107, 122, 160);
		result[1] = Color.rgb(99, 176, 67);
		result[2] = Color.rgb(214, 85, 52);
		result[3] = Color.rgb(202, 79, 200);
		result[4] = Color.rgb(184, 149, 57);
		result[5] = Color.rgb(82, 168, 146);
		result[6] = Color.rgb(194, 84, 128);
		result[7] = Color.rgb(77, 102, 50);
		result[8] = Color.rgb(132, 108, 197);
		result[9] = Color.rgb(144, 74, 61);
		return result;
	}

	/**
	 * Returns a palette of ten colors created using the "i want hue" utility,
	 * using the preset "Fluo" settings. A new array instance is created for
	 * each call to this method. A link to the "i want hue" utility is given in
	 * the class description.
	 * 
	 * @return An array of ten colors (never <code>null</code>).
	 */
	public static Color[] createFluoColors() {
		Color[] result = new Color[10];
		result[0] = Color.rgb(108, 236, 137);
		result[1] = Color.rgb(253, 187, 46);
		result[2] = Color.rgb(56, 236, 216);
		result[3] = Color.rgb(171, 231, 51);
		result[4] = Color.rgb(221, 214, 74);
		result[5] = Color.rgb(106, 238, 70);
		result[6] = Color.rgb(172, 230, 100);
		result[7] = Color.rgb(242, 191, 82);
		result[8] = Color.rgb(221, 233, 56);
		result[9] = Color.rgb(242, 206, 47);
		return result;
	}

	/**
	 * Returns a palette of ten colors created using the "i want hue" utility,
	 * using the preset "Red Roses" settings. A new array instance is created
	 * for each call to this method. A link to the "i want hue" utility is given
	 * in the class description.
	 * 
	 * @return An array of ten colors (never <code>null</code>).
	 */
	public static Color[] createRedRosesColors() {
		Color[] result = new Color[10];
		result[0] = Color.rgb(230, 129, 128);
		result[1] = Color.rgb(233, 56, 39);
		result[2] = Color.rgb(225, 45, 102);
		result[3] = Color.rgb(172, 79, 55);
		result[4] = Color.rgb(214, 154, 128);
		result[5] = Color.rgb(156, 96, 81);
		result[6] = Color.rgb(190, 77, 91);
		result[7] = Color.rgb(228, 121, 91);
		result[8] = Color.rgb(216, 63, 80);
		result[9] = Color.rgb(209, 75, 46);
		return result;
	}

	/**
	 * Returns a palette of ten colors created using the "i want hue" utility,
	 * using the preset "Ochre Sand" settings. A new array instance is created
	 * for each call to this method. A link to the "i want hue" utility is given
	 * in the class description.
	 * 
	 * @return An array of ten colors (never <code>null</code>).
	 */
	public static Color[] createOchreSandColors() {
		Color[] result = new Color[10];
		result[0] = Color.rgb(218, 180, 125);
		result[1] = Color.rgb(245, 184, 36);
		result[2] = Color.rgb(159, 103, 28);
		result[3] = Color.rgb(124, 96, 55);
		result[4] = Color.rgb(224, 132, 56);
		result[5] = Color.rgb(185, 143, 48);
		result[6] = Color.rgb(229, 171, 97);
		result[7] = Color.rgb(232, 165, 54);
		result[8] = Color.rgb(171, 102, 53);
		result[9] = Color.rgb(160, 122, 71);
		return result;
	}

	/**
	 * Returns a palette of ten colors created using the "i want hue" utility,
	 * using the preset "Yellow Lime" settings. A new array instance is created
	 * for each call to this method. A link to the "i want hue" utility is given
	 * in the class description.
	 * 
	 * @return An array of ten colors (never <code>null</code>).
	 */
	public static Color[] createYellowLimeColors() {
		Color[] result = new Color[10];
		result[0] = Color.rgb(235, 203, 59);
		result[1] = Color.rgb(113, 108, 56);
		result[2] = Color.rgb(222, 206, 134);
		result[3] = Color.rgb(169, 166, 62);
		result[4] = Color.rgb(214, 230, 54);
		result[5] = Color.rgb(225, 221, 105);
		result[6] = Color.rgb(128, 104, 23);
		result[7] = Color.rgb(162, 151, 86);
		result[8] = Color.rgb(117, 121, 25);
		result[9] = Color.rgb(183, 179, 40);
		return result;
	}

	/**
	 * Returns a palette of ten colors created using the "i want hue" utility,
	 * using the preset "Green Mint" settings. A new array instance is created
	 * for each call to this method. A link to the "i want hue" utility is given
	 * in the class description.
	 * 
	 * @return An array of ten colors (never <code>null</code>).
	 */
	public static Color[] createGreenMintColors() {
		Color[] result = new Color[10];
		result[0] = Color.rgb(99, 224, 113);
		result[1] = Color.rgb(98, 132, 83);
		result[2] = Color.rgb(145, 234, 49);
		result[3] = Color.rgb(181, 215, 158);
		result[4] = Color.rgb(95, 171, 43);
		result[5] = Color.rgb(100, 208, 142);
		result[6] = Color.rgb(172, 222, 84);
		result[7] = Color.rgb(75, 139, 53);
		result[8] = Color.rgb(177, 216, 123);
		result[9] = Color.rgb(83, 223, 60);
		return result;
	}

	/**
	 * Returns a palette of ten colors created using the "i want hue" utility,
	 * using the preset "Ice Cube" settings. A new array instance is created for
	 * each call to this method. A link to the "i want hue" utility is given in
	 * the class description.
	 * 
	 * @return An array of ten colors (never <code>null</code>).
	 */
	public static Color[] createIceCubeColors() {
		Color[] result = new Color[10];
		result[0] = Color.rgb(112, 235, 233);
		result[1] = Color.rgb(54, 110, 100);
		result[2] = Color.rgb(211, 232, 208);
		result[3] = Color.rgb(94, 230, 191);
		result[4] = Color.rgb(76, 154, 155);
		result[5] = Color.rgb(156, 181, 157);
		result[6] = Color.rgb(67, 152, 126);
		result[7] = Color.rgb(112, 135, 119);
		result[8] = Color.rgb(155, 213, 192);
		result[9] = Color.rgb(80, 195, 190);
		return result;
	}

	/**
	 * Returns a palette of ten colors created using the "i want hue" utility,
	 * using the preset "Blue Ocean" settings. A new array instance is created
	 * for each call to this method. A link to the "i want hue" utility is given
	 * in the class description.
	 * 
	 * @return An array of ten colors (never <code>null</code>).
	 */
	public static Color[] createBlueOceanColors() {
		Color[] result = new Color[10];
		result[0] = Color.rgb(53, 84, 154);
		result[1] = Color.rgb(41, 46, 57);
		result[2] = Color.rgb(115, 124, 151);
		result[3] = Color.rgb(38, 52, 91);
		result[4] = Color.rgb(84, 117, 211);
		result[5] = Color.rgb(76, 125, 181);
		result[6] = Color.rgb(109, 108, 112);
		result[7] = Color.rgb(48, 105, 134);
		result[8] = Color.rgb(72, 82, 107);
		result[9] = Color.rgb(91, 99, 144);
		return result;
	}

	/**
	 * Returns a palette of ten colors created using the "i want hue" utility,
	 * using the preset "Indigo Night" settings. A new array instance is created
	 * for each call to this method. A link to the "i want hue" utility is given
	 * in the class description.
	 * 
	 * @return An array of ten colors (never <code>null</code>).
	 */
	public static Color[] createIndigoNightColors() {
		Color[] result = new Color[10];
		result[0] = Color.rgb(127, 88, 147);
		result[1] = Color.rgb(201, 67, 217);
		result[2] = Color.rgb(112, 97, 218);
		result[3] = Color.rgb(219, 134, 222);
		result[4] = Color.rgb(154, 80, 172);
		result[5] = Color.rgb(170, 106, 231);
		result[6] = Color.rgb(142, 111, 210);
		result[7] = Color.rgb(194, 149, 235);
		result[8] = Color.rgb(152, 118, 188);
		result[9] = Color.rgb(214, 101, 237);
		return result;
	}

	/**
	 * Returns a palette of ten colors created using the "i want hue" utility,
	 * using the preset "Purple Wine" settings. A new array instance is created
	 * for each call to this method. A link to the "i want hue" utility is given
	 * in the class description.
	 * 
	 * @return An array of ten colors (never <code>null</code>).
	 */
	public static Color[] createPurpleWineColors() {
		Color[] result = new Color[10];
		result[0] = Color.rgb(116, 28, 93);
		result[1] = Color.rgb(112, 79, 75);
		result[2] = Color.rgb(178, 37, 101);
		result[3] = Color.rgb(109, 24, 56);
		result[4] = Color.rgb(167, 42, 140);
		result[5] = Color.rgb(66, 30, 40);
		result[6] = Color.rgb(128, 70, 95);
		result[7] = Color.rgb(78, 20, 56);
		result[8] = Color.rgb(155, 62, 111);
		result[9] = Color.rgb(139, 61, 75);
		return result;
	}

	/**
	 * Returns a palette of 7 colors with earth tones.
	 * 
	 * @return An array of 7 colors (never <code>null</code>).
	 */
	public static Color[] getEarthColors() {
		Color[] result = new Color[7];
		result[0] = Color.rgb(98, 98, 98);
		result[1] = Color.rgb(159, 87, 43);
		result[2] = Color.rgb(194, 176, 46);
		result[3] = Color.rgb(134, 155, 64);
		result[4] = Color.rgb(57, 118, 40);
		result[5] = Color.rgb(40, 114, 110);
		result[6] = Color.rgb(78, 79, 62);
		return result;
	}

	/**
	 * Returns a newly created array containing 9 colors from the the
	 * ColorBrewer tool. This is a high-contrast set of colors, good for pie
	 * charts.
	 * 
	 * http://colorbrewer2.org/?type=qualitative&amp;scheme=Set1&amp;n=9
	 * 
	 * @return A color array.
	 */
	public static Color[] getBrewerQualitativeSet1N9Colors() {
		Color[] result = new Color[9];
		result[0] = Color.rgb(228, 26, 28);
		result[1] = Color.rgb(55, 126, 184);
		result[2] = Color.rgb(77, 175, 74);
		result[3] = Color.rgb(152, 78, 163);
		result[4] = Color.rgb(255, 127, 0);
		result[5] = Color.rgb(255, 255, 51);
		result[6] = Color.rgb(166, 86, 40);
		result[7] = Color.rgb(247, 129, 191);
		result[8] = Color.rgb(153, 153, 153);
		return result;
	}

	/**
	 * Returns a newly created array containing 12 colors from the the
	 * ColorBrewer tool.
	 * 
	 * http://colorbrewer2.org/?type=qualitative&amp;scheme=Paired&amp;n=12
	 * 
	 * @return A color array.
	 */
	public static Color[] getBrewerQualitativePairedN12Colors() {
		Color[] result = new Color[12];
		result[0] = Color.rgb(166, 206, 227);
		result[1] = Color.rgb(31, 120, 180);
		result[2] = Color.rgb(178, 223, 138);
		result[3] = Color.rgb(51, 160, 44);
		result[4] = Color.rgb(251, 154, 153);
		result[5] = Color.rgb(227, 26, 28);
		result[6] = Color.rgb(253, 191, 111);
		result[7] = Color.rgb(255, 127, 0);
		result[8] = Color.rgb(202, 178, 214);
		result[9] = Color.rgb(106, 61, 154);
		result[10] = Color.rgb(255, 255, 153);
		result[11] = Color.rgb(177, 89, 40);
		return result;
	}

	/**
	 * Returns a newly created array containing 11 colors from the the
	 * ColorBrewer tool. Good for pie charts and bar charts, not so good for
	 * scatter plots.
	 * 
	 * http://colorbrewer2.org/?type=qualitative&amp;scheme=Set3&amp;n=12
	 * 
	 * @return A color array.
	 */
	public static Color[] getBrewerQualitativeSet3N12Colors() {
		Color[] result = new Color[12];
		result[0] = Color.rgb(141, 211, 199);
		result[1] = Color.rgb(255, 255, 179);
		result[2] = Color.rgb(190, 186, 218);
		result[3] = Color.rgb(251, 128, 114);
		result[4] = Color.rgb(128, 177, 211);
		result[5] = Color.rgb(253, 180, 98);
		result[6] = Color.rgb(179, 222, 105);
		result[7] = Color.rgb(252, 205, 229);
		result[8] = Color.rgb(217, 217, 217);
		result[9] = Color.rgb(188, 128, 189);
		result[10] = Color.rgb(204, 235, 197);
		result[11] = Color.rgb(255, 237, 111);
		return result;
	}

	/**
	 * Returns a set of colors sourced from
	 * http://www.sapdesignguild.org/goodies/diagram_guidelines/index.html.
	 * 
	 * @return A color array.
	 */
	public static Color[] getSAPMultiColor() {
		return new Color[] {
				Color.rgb(255, 248, 163),
				Color.rgb(169, 204, 143),
				Color.rgb(178, 200, 217),
				Color.rgb(190, 163, 122),
				Color.rgb(243, 170, 121),
				Color.rgb(181, 181, 169),
				Color.rgb(230, 165, 164),
				Color.rgb(248, 215, 83),
				Color.rgb(92, 151, 70),
				Color.rgb(62, 117, 167),
				Color.rgb(122, 101, 62),
				Color.rgb(225, 102, 42),
				Color.rgb(116, 121, 111),
				Color.rgb(196, 56, 79)
		};
	}

}
