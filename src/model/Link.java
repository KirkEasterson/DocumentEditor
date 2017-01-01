
package model;

import java.util.ArrayList;

/**
 * This generates the link for the linked list in which data is collected from the document
 * 
 * @author Kirk Easterson
 * @version 1.0
 */

public class Link
{
	public String				sData;		// Data held by Link
	public Link					next;		// Next link
	public ArrayList<String>	wordsList;	// List of string after sData
	
	/**
	 * This constructs the link of the linked list with a specified string
	 * 
	 * @param sData
	 *        The string data to be stored
	 */
	public Link(String sData)
	{
		// Set sData
		this.sData = sData;
		// Next is null
		next = null;
		// Instantiate the wordsList
		wordsList = new ArrayList<String>();
	}
	
	/** This constructs the link of the linked list, but with no argument */
	public Link()
	{
		// Set sData to empty string
		this.sData = "";
		// Next is null
		next = null;
		// Instantiate the wordsList
		wordsList = new ArrayList<String>();
	}
	
	/**
	 * This adds a word to the linked list
	 * 
	 * @param string
	 */
	public void addWord(String string)
	{
		wordsList.add(string);
	}
	
}
