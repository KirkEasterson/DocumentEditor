
package model;

/**
 * 
 * This class implements a link of a word for a linked list
 * 
 * @author Kirk Easterson
 * @version 1.0
 *
 */

public class WordLink
{
	public String	sData;
	public WordLink	next;
	
	/**
	 * This constructs a word link with a string as data
	 * 
	 * @param sData
	 *        The string to be stored as data
	 */
	public WordLink(String sData)
	{
		this.sData = sData;
		next = null;
	}
	
	/**
	 * This method displays the data in the link
	 */
	public void displayLink()
	{
		System.out.println("{ " + sData + " }");
	}
}
