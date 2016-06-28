////////////////////
// Kirk Easterson //
// CST 242 Final ///
////////////////////

package view;

import java.util.EventObject;

import javafx.scene.control.TextArea;
import model.TextEditorModel;

public class TextEditorEvent extends EventObject
{
	private String text; // Text to be modified

	// One argument constructor
	public TextEditorEvent(Object source)
	{
		// Inherit source objects
		super(source);
	}

	// Two argument constructor
	public TextEditorEvent(Object source, String text)
	{
		// Inherit source objects
		super(source);
		// Set text to argument text
		this.text = text;
	}

	// Get text method
	public String getText()
	{
		// Return the text
		return text;
	}
}
