////////////////////
// Kirk Easterson //
// CST 242 Final ///
////////////////////

package view;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.TextEditorModel;

public class TextEditorHelper
{
	private TextEditorModel	model;	// Model
	private String			text;	// String to be modified

	// constructor
	public TextEditorHelper(String text)
	{
		// Inherit methods
		super();
		// Set text to argument text
		this.text = text;
	}

	// Set text method
	public void setText(String text)
	{
		// Set text to argument text
		this.text = text;
	}

	// Get tokens method
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

	// Count syllables method
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

		// If the 'e' is islent
		if ((num > 1) && (word.charAt(word.length() - 1) == 'e') && lastToken.equals("e"))
		{
			// Decrease the number of syllables by 1
			num--;
		}
		// Return the number of syllables
		return num;
	}

	// Get syllable count method
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

	// Get word count method
	public int getWordCount()
	{
		// Instantiate and get tokens
		List<String> tokens = getTokens("[a-zA-Z]+");
		// Return number of tokens
		return tokens.size();
	}

	// Get sentence count method
	public int getSentenceCount()
	{
		// Instantiate and get tokens
		List<String> tokens = getTokens("[^.!?]+");
		// Return number of sentences
		return tokens.size();
	}

	// Get Flesch score method
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
