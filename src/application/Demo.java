////////////////////
// Kirk Easterson //
// CST 242 Final ///
////////////////////

package application;

import controller.TextEditorController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.TextEditorModel;
import view.TextEditorView;

public class Demo extends Application
{
	public static void main(String[] args)
	{
		// Launch the program
		launch(args);
	}

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
