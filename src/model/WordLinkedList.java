
package model;

/**
 * This class implements a linked list of words
 * 
 * @author Kirk Easterson
 *
 */

public class WordLinkedList
{
	public WordLink first;
	
	/**
	 * This constructs the linked list with no argument
	 */
	public WordLinkedList()
	{
		first = null;
	}
	
	/**
	 * This method inserts a string into the list with a specified string
	 * 
	 * @param sData
	 *        The string to be inserted
	 */
	public void insert(String sData)
	{
		WordLink newLink = new WordLink(sData);
		WordLink current = first;
		WordLink previous = null;
		while (current != null)
		{
			previous = current;
			current = current.next;
		}
		if (previous == null)
		{
			first = newLink;
		} else
		{
			previous.next = newLink;
		}
		newLink.next = current;
	}
	
	/**
	 * This method inserts a string as the first link
	 * 
	 * @param sData
	 *        The string to be added
	 */
	public void insertFirst(String sData)
	{
		WordLink newLink = new WordLink(sData);
		newLink.next = first;
		first = newLink;
	}
	
	/**
	 * This method deletes the first link
	 * 
	 * @return The word link that was deleted
	 */
	public WordLink deleteFirst()
	{
		WordLink temp = first;
		first = first.next;
		return temp; // To show which link was deleted
	}
	
	/**
	 * This method displays each link in the list
	 */
	public void displayList()
	{
		System.out.println("List (First --> Last):");
		WordLink current = first;
		
		while (current != null)
		{
			current.displayLink();
			current = current.next;
		}
		System.out.println();
	}
	
	/**
	 * This method finds the link with a specified string.
	 * If no link exists, it returns null.
	 * 
	 * @param key
	 *        The string to be searched for
	 * @return The link containing the word
	 */
	public WordLink find(String key)
	{
		WordLink current = first;
		while (!current.sData.equalsIgnoreCase(key))
		{
			if (current.next == null)
			{
				
			} else
			{
				current = current.next;
			}
		}
		return current;
	}
	
	/**
	 * This method deletes a link containing a specified word.
	 * If no link exists, it returns null.
	 * 
	 * @param key
	 *        The string to be searched for
	 * @return The link that was deleted
	 */
	public WordLink delete(String key)
	{
		WordLink current = first;
		WordLink previous = first;
		while (!current.sData.equalsIgnoreCase(key))
		{
			if (current.next == null)
			{
				return null;
			} else
			{
				previous = current;
				current = current.next;
			}
		}
		if (current == first)
		{
			first = first.next;
		} else
		{
			previous.next = current.next;
		}
		return current;
	}
	
}
