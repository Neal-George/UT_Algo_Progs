package assignment2;

import java.util.ArrayList;

public class Device 
{
	public int id; 
	public int message; 
	public boolean visited = false; 
	ArrayList<Integer> messages = new ArrayList<Integer>();
	
	public Device()
	{
		super(); 
	}
	
	public Device(int id) 
	{
		this.id = this.message = id;
	}	
}
