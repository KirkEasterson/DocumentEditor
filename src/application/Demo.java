
package application;

import controller.TextEditorController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.TextEditorModel;
import view.TextEditorView;

/**
 * The application object. It creates an instance of the document editor program.
 * 
 * @author Kirk Easterson
 * @version 1.0.3 26 December 2016
 */

public class Demo extends Application
{
	/**
	 * The main method where the text editor is launched.
	 * 
	 * @param args
	 *        The string array of arguments
	 */
	public static void main(String[] args)
	{
		// Launch the program
		launch(args);
	}
	
	/**
	 * The starting method. It is where the model, view, and controller are created.
	 */
	@Override
	public void start(Stage stage) throws Exception
	{
		// Instance of the view
		TextEditorView window = new TextEditorView(stage);
		
		// Instance of the model
		TextEditorModel model = new TextEditorModel();
		
		// Instance of the controller
		TextEditorController controller = new TextEditorController(window, model);
	}
}
