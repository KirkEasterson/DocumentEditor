////////////////////
// Kirk Easterson //
// CST 242 Final ///
////////////////////

package model;

public class LinkedList
{
	public Link	first;	// First link
	public int	nElems;	// Number of elements

	// Constructor
	public LinkedList()
	{
		// First link is null
		first = null;
		// Instantiate nElems to 0
		nElems = 0;
	}

	// Insert method with two arguments
	public void insert(String sData, String nextSData)
	{
		// Increment nElems
		nElems++;
		// If LinkedList is empty
		if (first == null)
		{
			// Create first link
			first = new Link(sData);
			// Set next for first link
			first.next = new Link(nextSData);
			return;
		}

		// Set current link to the first link
		Link current = first;

		// Check if link already exists
		while (current.next != null)
		{
			// If link string matches sData string
			if (current.sData.equalsIgnoreCase(sData))
			{
				// Add nextSData to current's wordsList
				current.addWord(nextSData);
				return;
			}
			// Set current to next link
			current = current.next;
		}

		// Link doesn't exist, create new one
		Link newLink = new Link(sData);
		// Add to LinkedList
		current.next = newLink;
		// Set newLink's next link
		newLink.next = new Link(nextSData);
	}

	// Find method
	public Link find(String key)
	{
		// Set current link to first
		Link current = first;
		// Find link with sData matching the key
		while (!current.sData.equalsIgnoreCase(key))
		{
			// If at end of list
			if (current.next == null)
			{
				return null;
			} else
			{
				// Keep going through list
				current = current.next;
			}
		}
		return current;
	}

}
