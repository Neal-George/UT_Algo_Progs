package assignment2;


import java.util.ArrayList;

public class P2PNetwork
{
	private ArrayList<Device> devices = new ArrayList<Device>();
	private ArrayList<ArrayList<MsgTime>> deviceAdjacencyList = new ArrayList<ArrayList<MsgTime>>(); 
	private static final int SUCCESS = 0; 
	private static final int FAILURE = -1; 
	private SolutionTraceList solList; 
	
	public P2PNetwork(ArrayList<Trace> traces, int numDevices)
	{
		// add a device to list for each device in input file
		for (int i = 0; i < numDevices; i += 1)
		{
			devices.add(new Device()); 
			deviceAdjacencyList.add(new ArrayList<MsgTime>()); 
		}
		
		for (Trace t : traces)
		{
			deviceAdjacencyList.get(t.id1).add(new MsgTime(t.id2,t.commTime)); 
			deviceAdjacencyList.get(t.id2).add(new MsgTime(t.id1,t.commTime)); 			
		}
	} 
	
	public String calculateQuery(Query query)
	{
		// initialize solution list
		solList = new SolutionTraceList();
		
		// find the shortest path using the modified DFS algo
		devices.get(query.id1).visited = true; 
		findPath(query.id1, query.id2, query.msgSendTime, query.msgReceiveDeadline);
		
		// reformat solList into string to return as result
		return solList.toString();	
	}
		
	private int findPath(int startID, int endID, int currentTime, int endTime)
	{
		if (currentTime > endTime)
			return FAILURE; 
		
		if (startID == endID)
		{
			solList.addToBeginning(new MsgTime(startID,currentTime));
			return SUCCESS; 
		}
		
		int status = FAILURE; 
		
		ArrayList<MsgTime> mTLL = deviceAdjacencyList.get(startID); 
		int edgeCount = 0; 
		int ALSize = mTLL.size(); 
		
		while(edgeCount < ALSize)
		{	
			if (mTLL.get(edgeCount).timeToExchange >= currentTime)
			{
				
				if ((devices.get(mTLL.get(edgeCount).messageID).visited == false))
				{	
					devices.get(mTLL.get(edgeCount).messageID).visited = true; 
					MsgTime mTime = new MsgTime(mTLL.get(edgeCount).messageID, mTLL.get(edgeCount).timeToExchange); 
					mTLL.remove(edgeCount); 
					ALSize -=1 ; 
					status = findPath(mTime.messageID, endID, mTime.timeToExchange, endTime); 
					if (status == SUCCESS)
					{
						solList.addToBeginning(mTime);
						return SUCCESS; 
					}
				}
				else 
					edgeCount += 1;  
			}
			else 
				edgeCount += 1; 
		}
		// if no paths led to SUCCESS, return FAILURE
		devices.get(startID).visited = false; 
		return status; 
	}
	
}

