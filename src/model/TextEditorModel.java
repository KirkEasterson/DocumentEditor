
package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * This class implements the model of the Text Editor
 * 
 * @author Kirk Easterson
 * @version 1.0
 */

public class TextEditorModel
{
	private String			text;		// Text to be modified
	private LinkedList		wordList;	// List of following words
	
	private File			file;		// File
	private FileChooser		fc;			// FileChooser
	private FileInputStream	fis;		// FileInputStream
	private StringBuilder	builder;	// StringBuilder
	private FileWriter		fw;			// FileWriter
	private Stage			stage;		// Stage for showing
	
	private TextInputDialog	numInput;	// InputDialog for number of words
	private TextInputDialog	textInput;	// InputDialog for first word
	
	/** This constructs the model of the text editor with no arguments */
	public TextEditorModel()
	{
		wordList = new LinkedList();
		fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("Text Files (*.txt)", "*.txt"),
				new ExtensionFilter("All Files (*.*)", "*.*"));
	}
	
	/**
	 * This method opens a file and returns the text, with no arguments
	 * 
	 * @return The text in the file
	 */
	public String onFileOpen()
	{
		// Set title of FileChooser
		fc.setTitle("Open File");
		// Choose the file
		file = fc.showOpenDialog(stage);
		
		// File validation
		try
		{
			// Instantiate FileInputStream
			fis = new FileInputStream(file);
			// Instantiate StringBuilder
			builder = new StringBuilder();
			
			// Integer for building string
			int ch;
			// While there is a character remaining
			while ((ch = fis.read()) != -1)
			{
				// Build the string
				builder.append((char) ch);
			}
		} catch (Exception e)
		{
			// Print error
			e.printStackTrace();
		}
		
		// Set text
		text = builder.toString();
		// Return the text from the file
		return text;
	}
	
	/**
	 * This method saves a file with a specified string
	 * 
	 * @param text
	 *        The text to be saved
	 */
	public void onFileSave(String text)
	{
		// Set text
		this.text = text;
		// Set title of FileChooser
		fc.setTitle("Save File");
		// Choose the file
		file = fc.showSaveDialog(stage);
		
		// Data validation
		try
		{
			// Instantiate FileWriter with the file
			fw = new FileWriter(file);
			// Write to the file
			fw.write(text);
			// Close the FileWriter
			fw.close();
		} catch (IOException e)
		{
			// Print error
			e.printStackTrace();
		}
	}
	
	/**
	 * This method generates a random document of a specified length with words taken from the document
	 * 
	 * @param text
	 *        The text from the document
	 */
	public void onEditGenerateDocument(String text)
	{
		// Instantiate int for number of words to be generated
		int numWords = 0;
		// Instantiate string with first word
		String result = null;
		
		// Loop for data validation
		while (true)
		{
			try
			{
				// Instantiate integer input
				numInput = new TextInputDialog("10");
				// Set title of integer input dialog
				numInput.setTitle("Generate Document");
				// Set header text of integer input dialog
				numInput.setHeaderText("Enter the number of words for\nthe document to be generated");
				// Set content text for integer input dialog
				numInput.setContentText("Number of words:");
				// Set input as string
				Optional<String> numResult = numInput.showAndWait();
				// Convert input string to integer
				numWords = Integer.parseInt(numResult.get());
				break;
			} catch (NumberFormatException e)
			{
				// Print error
				e.printStackTrace();
			}
		}
		
		// Instantiate first word input dialog
		textInput = new TextInputDialog("Word");
		// Set title of text input dialog
		textInput.setTitle("Generate Document");
		// Set header text for text input dialog
		textInput.setHeaderText("Enter the first word of the document. If the word\n"
				+ "is not in the document, this window will appear again.");
		// Set content text for text input dialog
		textInput.setContentText("First word:");
		// Instantiate result from dialog box
		Optional<String> textResult = textInput.showAndWait();
		// Get the result
		result = textResult.get();
		
		// Learn the text
		wordList = learnText(text);
		
		// Find link with first word input
		Link current = wordList.find(result);
		
		// If there is no link with the first word
		while (wordList.find(result) == null)
		{
			// Show input dialog again
			textResult = textInput.showAndWait();
			// Get the result
			result = textResult.get();
		}
		
		// Generate doc with valid result and a space
		String newDoc = (result + " ");
		
		// Instantiate new random
		Random r = new Random();
		// Instantiate int for random ints
		int randomInt = 0;
		
		//
		for (int i = 0; i < (numWords - 1); i++)
		{
			// If wordsList is 0 (last link in chain)
			if (current.wordsList.size() == 0)
			{
				// Set current to first link
				current = wordList.first;
				
				// Do while wordsList is not empty
				do
				{
					// Generate new random integer
					randomInt = r.nextInt(wordList.nElems);
					// Go to randomly generated link
					for (int j = 0; j < randomInt; j++)
					{
						// If last link is reached
						if (current.next == null)
						{
							// Set current to first
							current = wordList.first;
							// Set j back to 0
							j = 0;
							// Generate new random integer
							randomInt = r.nextInt(wordList.nElems);
						}
						// Go to next link
						current = current.next;
					}
					// While wordsList isn't empty
				} while (current.wordsList.size() == 0);
			}
			
			// Generate new random integer
			randomInt = r.nextInt(current.wordsList.size());
			// Set current to newly found link
			current = wordList.find(current.wordsList.get(randomInt));
			
			// Add current's sData to the generated document
			newDoc = newDoc.concat(current.sData);
			
			// Create new sentence after every 10 words
			if ((i > 0) && (i % 10 == 0))
			{
				// Concatenate a period and sentence
				newDoc = newDoc.concat(". ");
				// Generate a new random integer
				randomInt = r.nextInt(current.wordsList.size());
			}
			// Concatenate a space
			newDoc = newDoc.concat(" ");
		}
		
		// Instantiate alert with new document
		Alert generatedDocument = new Alert(AlertType.INFORMATION);
		// Set title of alert
		generatedDocument.setTitle("New Document");
		// No header text
		generatedDocument.setHeaderText(null);
		// Set content text to newly generated document
		generatedDocument.setContentText(newDoc);
		// Show the alert
		generatedDocument.showAndWait();
	}
	
	/**
	 * This method generates a linked list of each word in a specified string
	 * 
	 * @param text
	 *        The string to be analyzed
	 * @return The linked list with each word
	 */
	public LinkedList learnText(String text)
	{
		// Set the text to the argument text
		this.text = text;
		// Instantiate wordList
		wordList = new LinkedList();
		// Get all words in the text
		List<String> tokens = getTokens(text);
		
		// Iterate through tokens to add to linkedList
		for (int i = 0; i < tokens.size(); i++)
		{
			// If last iteration, (tokens.get(i + 1) will return error)
			if (i == (tokens.size() - 1))
			{
				// Add first word to last word's wordList
				wordList.find(tokens.get(i)).addWord(tokens.get(0));
			} else
			{
				// Add token and next token
				wordList.insert(tokens.get(i), tokens.get(i + 1));
			}
		}
		return wordList;
	}
	
	/**
	 * This searches a specified string for each word
	 * 
	 * @param text
	 *        The string to be searched
	 * @return The list with each word
	 */
	public List<String> getTokens(String text)
	{
		// Instantiate tokens ArrayList
		ArrayList<String> tokens = new ArrayList<>();
		// Instantiate pattern
		Pattern tokenSplitter = Pattern.compile("[a-zA-Z']+");
		// Instantiate matcher
		Matcher m = tokenSplitter.matcher(text);
		
		// Iterate through document
		while (m.find())
		{
			// Add tokens
			tokens.add(m.group());
		}
		return tokens;
	}
	
	// TEMP METHOD FOR CALCULATING TIME
	public void calculateTime()
	{
		long begin = System.nanoTime();
		// SOME TASK TO TAKE UP TIME
		long end = System.nanoTime();
		long result = (end - begin);
		System.out.println(result);
	}
	
}
