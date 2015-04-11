package assignment2;

import java.util.ArrayList;
import java.util.LinkedList;

public class SolutionTraceList 
{
	public LinkedList<MsgTime> solutionList = new LinkedList<MsgTime>();
	
	
	public SolutionTraceList()
	{
		solutionList.clear(); 
	}
	
	
	public void addToBeginning(MsgTime m)
	{
		solutionList.addFirst(m);
	}
	
	
	
	public String toString() 
	{
		if (solutionList.isEmpty())
			return "0"; 
		
		else
		{
			int traceCount = -1; 
			
			String retStr = ""; 
			ArrayList<MsgTime> tempTimes = new ArrayList<MsgTime>(); 
			
			for (MsgTime m : solutionList)
			{
				tempTimes.add(m); 
				traceCount += 1; 
			}
			retStr += traceCount + "\n"; 
			
			int arraySize = tempTimes.size(); 
			
			for (int i = 0; i < arraySize- 1; i += 1)
				retStr += orderedTraceToPrint(tempTimes.get(i).messageID, tempTimes.get(i + 1).messageID, tempTimes.get(i).timeToExchange); 

			return retStr; 
		}
	}
	
	private String orderedTraceToPrint(int id1, int id2, int time)
	{
		int t1 = id1; 
		int t2 = id2; 
		String retStr = ""; 
		
		if (id1 > id2)
		{
			t1 = id2; 
			t2 = id1; 
		}
		retStr += (t1 + " " + t2 + " " + time + "\n");  
		return retStr; 
	}
	
}
