package assignment3;

import java.util.HashMap;
import java.util.Map;

public class MemoizeMapping 
{
	public Map<String, MappedSolution> solutionMap; 
	
	public MemoizeMapping()
	{
		solutionMap = new HashMap<String, MappedSolution>();
	}
	
	public void addSolution(String remainingTranscript, String string)
	{
		if (solutionMap.get(remainingTranscript) == null)
			solutionMap.put(remainingTranscript, new MappedSolution()); 
		if (solutionMap.get(remainingTranscript).solutionSubstrings.contains(string) == false)
		{
			if (string != null)
			{
				solutionMap.get(remainingTranscript).solutionSubstrings.add(string); 
				solutionMap.get(remainingTranscript).visited = true;
			}
			else 
				solutionMap.get(remainingTranscript).visited = false; 
		}
	}
	
	public MappedSolution getSolution(String remainingTranscript)
	{
		return solutionMap.get(remainingTranscript); 
	}
}
