
package view;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.TextEditorModel;

/**
 * This implements a helper class for the text editor
 * 
 * @author Kirk Easterson
 *
 */

public class TextEditorHelper
{
	private TextEditorModel	model;	// Model
	private String			text;	// String to be modified
	
	/**
	 * This constructs the helper with a specified string of text to be analyzed
	 * 
	 * @param text
	 *        The text to be analyzed
	 */
	public TextEditorHelper(String text)
	{
		// Inherit methods
		super();
		// Set text to argument text
		this.text = text;
	}
	
	/**
	 * This class sets the text for the helper
	 * 
	 * @param text
	 *        The new string for the text
	 */
	public void setText(String text)
	{
		// Set text to argument text
		this.text = text;
	}
	
	/**
	 * This class counts the number of tokens for a specified pattern in the text
	 * 
	 * @param pattern
	 *        The pattern to be used in search of tokens
	 * @return A list of all the tokens
	 */
	public List<String> getTokens(String pattern)
	{
		// Instantiate tokens ArrayList
		ArrayList<String> tokens = new ArrayList<>();
		// Instantiate pattern
		Pattern tokenSplitter = Pattern.compile(pattern);
		// Instantiate matcher
		Matcher m = tokenSplitter.matcher(text);
		
		// Iterate through matcher
		while (m.find())
		{
			// Add tokens
			tokens.add(m.group());
		}
		// Return list of all tokens
		return tokens;
	}
	
	/**
	 * This method counts the number of syllables in a word
	 * 
	 * @param word
	 *        The word to have its syllables counted
	 * @return The number of syllables
	 */
	public int countSyllables(String word)
	{
		// Instantiate integer for number of syllables
		int num = 0;
		// String pattern = "[aeiouyAEIOUY]+";
		// Instantiate pattern
		Pattern tokenSplitter = Pattern.compile("[aeiouyAEIOUY]+");
		// Instantiate matcher
		Matcher m = tokenSplitter.matcher(word);
		// Set last token to empty string
		String lastToken = "";
		
		// Iterate through matcher
		while (m.find())
		{
			// Increase number of syllables
			num++;
			// Set last token
			lastToken = m.group();
		}
		
		// If the 'e' is silent
		if ((num > 1) && (word.charAt(word.length() - 1) == 'e') && lastToken.equals("e"))
		{
			// Decrease the number of syllables by 1
			num--;
		}
		// Return the number of syllables
		return num;
	}
	
	/**
	 * This method returns the number of syllables in the document
	 * 
	 * @return The number of syllables in the text
	 */
	public int getSyllableCount()
	{
		// Instantiate and get tokens
		List<String> tokens = getTokens("[a-zA-Z]+");
		// Instantiate number of syllables to 0
		int count = 0;
		// Iterate through all the tokens
		for (String token : tokens)
		{
			// Increase count with each number of syllables
			count += countSyllables(token);
		}
		// Return number of syllables
		return count;
	}
	
	/**
	 * This method returns the number of words in the document
	 * 
	 * @return The number of words in the text
	 */
	public int getWordCount()
	{
		// Instantiate and get tokens
		List<String> tokens = getTokens("[a-zA-Z]+");
		// Return number of tokens
		return tokens.size();
	}
	
	/**
	 * This method returns the number of sentences in the document
	 * 
	 * @return
	 */
	public int getSentenceCount()
	{
		// Instantiate and get tokens
		List<String> tokens = getTokens("[^.!?]+");
		// Return number of sentences
		return tokens.size();
	}
	
	/**
	 * This method calculates the Flesch score for the document
	 * 
	 * @return The Flesch score
	 */
	public double getFleschScore()
	{
		// Instantiate score
		double score;
		// Calcualte the Flesch score
		score = 206.835 - (1.015 * (getWordCount() / getSentenceCount()))
				- ((84.6 * getSyllableCount() / getWordCount()));
		// Return the Flesch score
		return score;
	}
	
}
