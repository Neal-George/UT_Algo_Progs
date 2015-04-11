package assignment2;

public class InvalidTraceError extends Exception
{
	private static final long serialVersionUID = 1L; 
	
	public InvalidTraceError(Trace trace)
	{
		super("Trace " + trace.toString() + " is invalid"); 
	}
}
