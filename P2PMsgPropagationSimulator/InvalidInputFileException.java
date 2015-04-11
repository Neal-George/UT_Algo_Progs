package assignment2;

public class InvalidInputFileException extends Exception 
{
	private static final long serialVersionUID = 1L; 
	
	public InvalidInputFileException()
	{
		super("Invalid Input File\n"); 
	}	
}
