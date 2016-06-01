package org.jfree.chart.text;

import static org.junit.Assert.assertEquals;

import java.text.AttributedString;

import org.junit.Test;

public class TextUtilitiesTest {

	@Test
	public void toPlainStringShouldReturnEmptyStringForEmptyAttributedString() {
		AttributedString attributedString = new AttributedString("");

		String result = TextUtilities.toPlainText(attributedString);

		assertEquals("Shoud return empty string", "", result);
	}

	@Test
	public void toPlainStringShouldReturnStringPassedToAttributedStringCounstructor() {
		AttributedString attributedString = new AttributedString("Test");

		String result = TextUtilities.toPlainText(attributedString);

		assertEquals("Shoud return string passed to constructor", "Test", result);
	}
}
