
package view;

import java.util.EventListener;

import javafx.stage.WindowEvent;

/**
 * This interface is for action methods for the listener
 * 
 * @author Kirk Easterson
 */

public interface TextEditorListener extends EventListener
{
	// FileOpen method
	public void onFileOpen(TextEditorEvent ev);
	
	// FileSave method
	public void onFileSave(TextEditorEvent ev);
	
	// EditGenerateDocument method
	public void onEditGenerateDocument(TextEditorEvent ev);
}
