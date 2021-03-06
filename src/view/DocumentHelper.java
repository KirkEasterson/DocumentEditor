
package view;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class implements the methods to help analyze the text to be learned
 * 
 * @author Kirk Easterson
 * @version 1.0
 */
public class DocumentHelper
{
	private String text; // Text to be modified
	
	/**
	 * This constructs a document with specific text
	 * 
	 * @param text
	 *        The text to be analyzed
	 */
	public DocumentHelper(String text)
	{
		// Set text to argument text
		this.text = text;
	}
	
	/**
	 * This method counts the number of tokens for a specified pattern in the text
	 * 
	 * @param pattern
	 *        The pattern for analyzing
	 * @return A list of each token as a string
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
	 * This method counts the syllables in a specified word
	 * 
	 * @param word
	 *        The string to count the syllables in
	 * @return The number of syllables in the word
	 */
	public int countSyllables(String word)
	{
		// Instantiate integer for number of syllables
		int num = 0;
		// // Instantiate pattern as string
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
		// Return number of syllables
		return num;
	}
	
	/**
	 * This method counts the number of syllables in the text
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
	 * This method counts the number of words in the text
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
	 * This method counts the number of sentences in the text
	 * 
	 * @return The number of sentences in the text
	 */
	public int getSentenceCount()
	{
		// Instantiate and get tokens
		List<String> tokens = getTokens("[^.!?]+");
		// Return number of sentences
		return tokens.size();
	}
	
	/**
	 * This method calculates the Flesch score for the text
	 * 
	 * @return The Flesch score
	 */
	public double getFleschScore()
	{
		// Instantiate score
		double score = 0;
		// Calculate the Flesch score
		score = 206.835 - (1.015 * (getWordCount() / getSentenceCount()))
				- ((84.6 * getSyllableCount() / getWordCount()));
		// Return the Flesch score
		return score;
	}
	
}
