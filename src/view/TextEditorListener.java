////////////////////
// Kirk Easterson //
// CST 242 Final ///
////////////////////

package view;

import java.util.EventListener;

import javafx.stage.WindowEvent;

public interface TextEditorListener extends EventListener
{
	// FileOpen method
	public void onFileOpen(TextEditorEvent ev);

	// FileSave method
	public void onFileSave(TextEditorEvent ev);

	// EditGenerateDocument method
	public void onEditGenerateDocument(TextEditorEvent ev);
}
