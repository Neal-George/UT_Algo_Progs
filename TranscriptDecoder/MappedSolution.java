package assignment3;

import java.util.ArrayList;

public class MappedSolution 
{
	public ArrayList<String> solutionSubstrings; 
	public boolean visited; 
	
	public MappedSolution()
	{
		solutionSubstrings = new ArrayList<String>(); 
		visited = false; 
	}
	
	public MappedSolution(String str)
	{
		solutionSubstrings = new ArrayList<String>(); 
		solutionSubstrings.add(str); 
		visited = false; 
	}
	
	public MappedSolution(ArrayList<String> alstr)
	{
		solutionSubstrings = alstr; 
		visited = false; 
	}
}
