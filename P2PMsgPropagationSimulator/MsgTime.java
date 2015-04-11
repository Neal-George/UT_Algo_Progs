package assignment2;

public class MsgTime 
{
	public int messageID; 
	public int timeToExchange; 
	public boolean hasBeenTraversed = false; 
	
	public MsgTime(int id, int commTime) 
	{
		messageID = id; 
		timeToExchange = commTime; 
	}	
}
