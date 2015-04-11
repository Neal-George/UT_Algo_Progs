package assignment2;

public class Query 
{
	public int id1; 
	public int id2; 
	public int msgSendTime; 
	public int msgReceiveDeadline;
	
	public Query(int id1, int id2, int msgSendTime, int msgReceiveDeadline) {
		super();
		this.id1 = id1;
		this.id2 = id2;
		this.msgSendTime = msgSendTime;
		this.msgReceiveDeadline = msgReceiveDeadline;
	}	
}
