// POSSIBLE DELETION

package model;

public class WordLink
{
	public String	sData;
	public WordLink	next;

	public WordLink(String sData)
	{
		this.sData = sData;
		next = null;
	}

	public void displayLink()
	{
		System.out.println("{ " + sData + " }");
	}
}
