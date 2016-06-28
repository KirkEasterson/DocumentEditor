////////////////////
// Kirk Easterson //
// CST 242 Final ///
////////////////////

package view;

import java.util.Optional;
import java.util.Timer;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TextEditorView
{
	private MenuBar				menuBar;					// MenuBar for top
								
	private Menu				menuFile;					// File menu
	private MenuItem			menuFileNew;				// File -> New
	private MenuItem			menuFileOpen;				// File -> Open
	private MenuItem			menuFileSave;				// File -> Save
	private MenuItem			menuFileExit;				// File -> Exit
								
	private Menu				menuEdit;					// Edit menu
	private MenuItem			menuEditWordCount;			// Edit -> Word Count
	private MenuItem			menuEditSentenceCount;		// Edit -> Sentence Count
	private MenuItem			menuEditFleschScore;		// Edit -> Flesch Score
	private MenuItem			menuEditGenerateDocument;	// Edit -> Generate Document
								
	private TextArea			textArea;					// Text Area for text
								
	private Timer				timer;						// Timer
	private TextEditorHelper	textEditorHelper;			// Text editor helper
								
	private HBox				wordCountHBox;				// Word count HBox
	private Label				wordCountLabel;				// Word count label
	private Label				wordCountResultLabel;		// Word count result label
	private HBox				sentenceCountHBox;			// Sentence count HBox
	private Label				sentenceCountLabel;			// Sentence count label
	private Label				sentenceCountResultLabel;	// Sentence count result label
	private HBox				fleschScoreHBox;			// Flesch score HBox
	private Label				fleschScoreLabel;			// Flesch score label
	private Label				fleschScoreResultLabel;		// Flesch score result label
								
	private AnchorPane			textInfoAnchorPane;			// Text info anchor pane
								
	private BorderPane			border;						// Border pane
	private TextEditorListener	textEditorListener;			// Text editor listener
	private Stage				stage;						// Stage
	private Alert				alert;						// Alert for generated document
								
	private String				text;						// String for document text
								
	// Constructor
	public TextEditorView(Stage stage)
	{
		// Set the stage
		this.stage = stage;
		// Set stage title
		stage.setTitle("Text Editor v1.1");
		
		// Instantiate menu bar
		menuBar = new MenuBar();
		
		// Instantiate file menu
		menuFile = new Menu("File");
		
		// Instantiate fileNew menu item
		menuFileNew = new MenuItem("New");
		// Set event for fileNew
		menuFileNew.setOnAction(event ->
		{
			// Clear the text area
			textArea.clear();
		});
		
		// Instantiate fileOpen menu item
		menuFileOpen = new MenuItem("Open");
		// Set event for fileNew
		menuFileOpen.setOnAction(event ->
		{
			// Set text to text from text area
			text = textArea.getText();
			// Create new text editor event
			TextEditorEvent ev = new TextEditorEvent(this, text);
			// If listener has not been instantiated
			if (textEditorListener != null)
			{
				// File open method from controller/listener
				textEditorListener.onFileOpen(ev);
			}
			// Set text for helper
			textEditorHelper.setText(text);
			
			// Create new runnable to get statistics on document right as it opens
			Platform.runLater(new Runnable()
			{
				// Override the run method
				@Override
				public void run()
				{
					// To not divide by zero
					if ((textEditorHelper.getSentenceCount() != 0) && (textEditorHelper.getWordCount() != 0))
					{
						// Get and set word count
						wordCountResultLabel.setText(Integer.toString(textEditorHelper.getWordCount()));
						// Get and set sentence count
						sentenceCountResultLabel.setText(Integer.toString(textEditorHelper.getSentenceCount()));
						// Get and set Flesch score
						fleschScoreResultLabel
								.setText(Double.toString(textEditorHelper.getFleschScore()).substring(0, 5));
					}
				}
			});
		});
		
		// Instantiate fileSave menu item
		menuFileSave = new MenuItem("Save");
		// Set event for fileSave
		menuFileSave.setOnAction(event ->
		{
			// Set text to text from text area
			text = textArea.getText();
			// Create new text editor event
			TextEditorEvent ev = new TextEditorEvent(this, text);
			// If listener has not been instantiated
			if (textEditorListener != null)
			{
				// File save method from controller/listener
				textEditorListener.onFileSave(ev);
			}
		});
		
		// Instantiate fileExit menu item
		menuFileExit = new MenuItem("Exit");
		// Set event for fileExit
		menuFileExit.setOnAction(event ->
		{
			// Close the window
			stage.close();
		});
		
		// Add menu items to file menu
		menuFile.getItems().addAll(menuFileNew, menuFileOpen, menuFileSave, menuFileExit);
		
		// Instantiate edit menu
		menuEdit = new Menu("Edit");
		
		// Instantiate editWordCount
		menuEditWordCount = new MenuItem("Word Count");
		// Set event for editWordCount
		menuEditWordCount.setOnAction(event ->
		{
			// Set title of word count alert
			alert.setTitle("Word Count");
			// No header text for word count alert
			alert.setHeaderText(null);
			// Set content text of word count alert
			alert.setContentText("Total Words: " + Integer.toString(textEditorHelper.getWordCount()));
			// Show word count alert
			alert.showAndWait();
		});
		
		// Instantiate editSentenceCount
		menuEditSentenceCount = new MenuItem("Sentence Count");
		// Set event for editSentenceCount
		menuEditSentenceCount.setOnAction(event ->
		{
			// Set title of sentence count alert
			alert.setTitle("Sentence Count");
			// No header text for sentence count alert
			alert.setHeaderText(null);
			// Set content text for sentence count alert
			alert.setContentText("Total Sentences: " + Integer.toString(textEditorHelper.getSentenceCount()));
			// Show sentence count alert
			alert.showAndWait();
		});
		
		// Instantiate editFleschScore
		menuEditFleschScore = new MenuItem("Flesch Score");
		// Set event for editFleschScore
		menuEditFleschScore.setOnAction(event ->
		{
			// Set title of Flesch score alert
			alert.setTitle("Flesch Score");
			// No header text for Flesch score alert
			alert.setHeaderText(null);
			// Set content text for Flesch score alert
			alert.setContentText("Flesch Score: " + Double.toString(textEditorHelper.getFleschScore()).substring(0, 5));
			// Show Flesch score alert
			alert.showAndWait();
		});
		
		// Instantiate editGenerateDocument
		menuEditGenerateDocument = new MenuItem("Generate Document");
		// Set event for editGenerateDocument
		menuEditGenerateDocument.setOnAction(event ->
		{
			// Set text to text from text area
			text = textArea.getText();
			// Create new text editor event
			TextEditorEvent ev = new TextEditorEvent(this, text);
			// If listener has not been instantiated
			if (textEditorListener != null)
			{
				// Edit generate text method from controller/listener
				textEditorListener.onEditGenerateDocument(ev);
			}
		});
		
		// Add menu items to edit menu
		menuEdit.getItems().addAll(menuEditWordCount, menuEditSentenceCount, menuEditFleschScore,
				menuEditGenerateDocument);
				
		// Add menus to menu bar
		menuBar.getMenus().addAll(menuFile, menuEdit);
		
		// Instantiate text area
		textArea = new TextArea();
		// Wrap text
		textArea.setWrapText(true);
		
		// Instantiate text editor helper
		textEditorHelper = new TextEditorHelper("");
		// Generate statistics with each key press
		textArea.setOnKeyPressed(e ->
		{
			// Set text for editor helper with text from text area
			textEditorHelper.setText(textArea.getText());
			
			// Instantiate new runnable
			Platform.runLater(new Runnable()
			{
				// Override the run method
				@Override
				public void run()
				{
					// To not divide by zero
					if ((textEditorHelper.getSentenceCount() != 0) || (textEditorHelper.getWordCount() != 0))
					{
						// Get and set word count
						wordCountResultLabel.setText(Integer.toString(textEditorHelper.getWordCount()));
						// Get and set sentence count
						sentenceCountResultLabel.setText(Integer.toString(textEditorHelper.getSentenceCount()));
						// Get and set Flesch score
						fleschScoreResultLabel
								.setText(Double.toString(textEditorHelper.getFleschScore()).substring(0, 5));
					}
				}
			});
		});
		
		// Instantiate word count label
		wordCountLabel = new Label("Word Count: ");
		// Set font to bold
		wordCountLabel.setStyle("-fx-font-weight: bold");
		// Instantiate word count result label
		wordCountResultLabel = new Label();
		// Instantiate word count HBox
		wordCountHBox = new HBox();
		// Set padding for word count HBox
		wordCountHBox.setPadding(new Insets(0, 10, 0, 10));
		// Add labels to word count HBox
		wordCountHBox.getChildren().addAll(wordCountLabel, wordCountResultLabel);
		
		// Instantiate sentence count label
		sentenceCountLabel = new Label("Sentence Count: ");
		// Set font to bold
		sentenceCountLabel.setStyle("-fx-font-weight: bold");
		// Instantiate sentence count label
		sentenceCountResultLabel = new Label();
		// Instantiate sentence count HBox
		sentenceCountHBox = new HBox();
		// Set padding for sentence count HBox
		sentenceCountHBox.setPadding(new Insets(0, 10, 0, 10));
		// Add labels to sentence count HBox
		sentenceCountHBox.getChildren().addAll(sentenceCountLabel, sentenceCountResultLabel);
		
		// Instantiate Flesch score label
		fleschScoreLabel = new Label("Flesch Score: ");
		// Set font to bold
		fleschScoreLabel.setStyle("-fx-font-weight: bold");
		// Instantiate Flesch score result label
		fleschScoreResultLabel = new Label();
		// Instantiate Flesch score HBox
		fleschScoreHBox = new HBox();
		// Set padding for Flesch score HBox
		fleschScoreHBox.setPadding(new Insets(0, 10, 0, 10));
		// Add labels to Flesch score HBox
		fleschScoreHBox.getChildren().addAll(fleschScoreLabel, fleschScoreResultLabel);
		
		// Instantiate anchor pane for text information
		textInfoAnchorPane = new AnchorPane();
		// Add HBoxes to anchor pane
		textInfoAnchorPane.getChildren().addAll(wordCountHBox, sentenceCountHBox, fleschScoreHBox);
		// Anchor sentence count to left
		AnchorPane.setLeftAnchor(sentenceCountHBox, 125.0);
		// Anchor Flesch score to left
		AnchorPane.setLeftAnchor(fleschScoreHBox, 275.0);
		
		// Instantiate alert
		alert = new Alert(AlertType.INFORMATION);
		
		// Instantiate border
		border = new BorderPane();
		// Set menu bar on top
		border.setTop(menuBar);
		// Set text area on center
		border.setCenter(textArea);
		// Set anchor pane on the bottom
		border.setBottom(textInfoAnchorPane);
		
		// Set scene
		stage.setScene(new Scene(border, 800, 800));
		// Show stage
		stage.show();
	}
	
	// Set text editor listener method
	public void setTextEditorListener(TextEditorListener windowListener)
	{
		// Set listener to argument listener
		this.textEditorListener = windowListener;
	}
	
	// Set text method
	public void setText(String newText)
	{
		// Set text area text to newText argument
		textArea.setText(newText);
	}
	
}
