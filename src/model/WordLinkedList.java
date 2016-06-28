// POSSIBLE DELETION

package model;

public class WordLinkedList
{
	public WordLink first;

	public WordLinkedList()
	{
		first = null;
	}

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

	public void insertFirst(String sData)
	{
		WordLink newLink = new WordLink(sData);
		newLink.next = first;
		first = newLink;
	}

	public WordLink deleteFirst()
	{
		WordLink temp = first;
		first = first.next;
		return temp; // To show which link was deleted
	}

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

	// public boolean ifExists(String key)
	// {
	// WordLink current = first;
	// while (!current.sData.equalsIgnoreCase(key))
	// {
	// if (current.next == null)
	// {
	// return false;
	// } else
	// {
	// current = current.next;
	// }
	// }
	// return true;
	// }

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
