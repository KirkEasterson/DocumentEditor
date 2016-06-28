////////////////////
// Kirk Easterson //
// CST 242 Final ///
////////////////////

package model;

import java.util.ArrayList;

public class Link
{
	public String				sData;		// Data held by Link
	public Link					next;		// Next link
	public ArrayList<String>	wordsList;	// List of string after sData

	// Constructor
	public Link(String sData)
	{
		// Set sData
		this.sData = sData;
		// Next is null
		next = null;
		// Instantiate the wordsList
		wordsList = new ArrayList<String>();
	}

	// No argument constructor
	public Link()
	{
		// Set sData to empty string
		this.sData = "";
		// Next is null
		next = null;
		// Instantiate the wordsList
		wordsList = new ArrayList<String>();
	}

	// Add a word to the wordsList
	public void addWord(String string)
	{
		wordsList.add(string);
	}

}
