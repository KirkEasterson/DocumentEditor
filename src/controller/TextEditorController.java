
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.TextEditorModel;
import view.TextEditorEvent;
import view.TextEditorListener;
import view.TextEditorView;

/**
 * This class implements the controller for the text editor
 * 
 * @author Kirk Easterson
 * @version 1.0
 */

public class TextEditorController
{
	
	private String			text;	// Text to be modified
	private TextEditorModel	model;	// Model of text editor
	
	/**
	 * This constructs the controller of the text editor
	 * 
	 * @param window
	 *        The window of the text editor
	 * @param model
	 *        The model of the text editor
	 */
	public TextEditorController(TextEditorView window, TextEditorModel model)
	{
		// Set the model
		this.model = model;
		
		// Instantiate the listener
		window.setTextEditorListener(new TextEditorListener()
		{
			// FileOpen method
			@Override
			public void onFileOpen(TextEditorEvent ev)
			{
				// Retrieve text
				text = model.onFileOpen();
				// Set the text
				window.setText(text);
			}
			
			// FileSave method
			@Override
			public void onFileSave(TextEditorEvent ev)
			{
				// Get the text
				text = ev.getText();
				// Save the text
				model.onFileSave(text);
			}
			
			// EditGenerateDocument method
			@Override
			public void onEditGenerateDocument(TextEditorEvent ev)
			{
				// Get the text
				text = ev.getText();
				// analyze the text
				model.onEditGenerateDocument(text);
			}
		});
	}
	
}
