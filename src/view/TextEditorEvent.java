
package view;

import java.util.EventObject;

import javafx.scene.control.TextArea;
import model.TextEditorModel;

/**
 * This class implements an event for the text editor
 * 
 * @author Kirk Easterson
 * @version 1.0
 *
 */

public class TextEditorEvent extends EventObject
{
	private String text; // Text to be modified
	
	/**
	 * This constructs an event for the text editor with one argument
	 * 
	 * @param source
	 *        The object whose methods are to be inherited
	 */
	public TextEditorEvent(Object source)
	{
		// Inherit source objects
		super(source);
	}
	
	/**
	 * This constructs an event for the text editor with two arguments
	 * 
	 * @param source
	 *        The object whose methods are to be inherited
	 * @param text
	 *        The text to be used in the event
	 */
	public TextEditorEvent(Object source, String text)
	{
		// Inherit source objects
		super(source);
		// Set text to argument text
		this.text = text;
	}
	
	/**
	 * This method returns the text from the event
	 * 
	 * @return A string with the text
	 */
	public String getText()
	{
		// Return the text
		return text;
	}
}
