package assignment3;

import java.util.ArrayList;
import java.util.Collections;

public class TranscriptDecoder 
{
	private ArrayList<String> DecodedTranscript;
	private ArrayList<String> Dictionary; 
	private MemoizeMapping mMap; 
	
	public TranscriptDecoder (ArrayList<String> dictList)
	{
		if (dictList.isEmpty() == true)
			System.out.println("Internal Error: Dictionary is empty");
		
		Dictionary = dictList; 
		return; 
	}
	
	public ArrayList<String> Decode (String transcript)
	{
		DecodedTranscript = new ArrayList<String>();
		if (transcript.isEmpty() == true)
		{
			System.out.println("Transcript string is empty.");
			return null; 
		}
		mMap = new MemoizeMapping();
		

		rDPDecoderBuilder(transcript); 
		decoderSolutionBuilder(transcript); 
		Collections.sort(DecodedTranscript);
		return DecodedTranscript; 
	}
	

	private boolean rDPDecoderBuilder (String transcript)
	{				
		// if transcript is empty we have found a solution
		if (transcript.isEmpty() == true)
		{
			return true; 
		}
		// if we already have a set of solutions for this transcript return true, because we found all of them already
		if (mMap.getSolution(transcript) != null)
		{
			if (mMap.getSolution(transcript).visited == false) 
				return false;
			else 
				return true; 
		}
		
		Boolean foundSolution = false;
	
		for (String entry : Dictionary)
		{
			if (transcript.indexOf(entry) == 0)
			{
				String decompTranscript = transcript.substring(entry.length());
				
				Boolean result= rDPDecoderBuilder(decompTranscript);
				if (result == true)
				{
					mMap.addSolution(transcript, entry);
					foundSolution = true; 
				}					
			}
		}
		if (foundSolution == false)
			mMap.addSolution(transcript,  null);
		return foundSolution; 		
	}	
	
	private void decoderSolutionBuilder(String ltranscript)
	{
		ArrayList<String> prefixes = new ArrayList<String>(); 
		ArrayList<String> transcripts = new ArrayList<String>(); 
		ArrayList<ArrayList<String>> solutions = new ArrayList<ArrayList<String>>();
		ArrayList<String> tempTranscripts = new ArrayList<String>(); 
		ArrayList<ArrayList<String>> tempSolutions = new ArrayList<ArrayList<String>>(); 
		
		transcripts.add(ltranscript); 
		ArrayList<String> temp = new ArrayList<String>(); 
		temp.add(""); 
		solutions.add(temp); 
		
		while (transcripts.isEmpty() == false)
		{
			for (int i = 0; i < transcripts.size(); i += 1)
			{
				// for each of the transcripts we want to: 
				// - decompose the string using the prefix  
				// - add each decomposing prefix to its solution list
				
				String transcript = transcripts.get(i);
				ArrayList<String> solution = solutions.get(i); 
				
				// System.out.println(transcript);
				prefixes = mMap.getSolution(transcript).solutionSubstrings; 
				Collections.sort(prefixes); 
				for (String prefix : prefixes)
				{
					String decomposedTranscript = transcript.substring(prefix.length());
					if (decomposedTranscript.isEmpty() == true)
					{
						for (String str : solution)
							if (str.isEmpty() == true)
								DecodedTranscript.add(prefix); 
							else 
								DecodedTranscript.add(str + " " + prefix); 
					}
					else
					{
						tempTranscripts.add(decomposedTranscript); 
						tempSolutions.add(new ArrayList<String>()); 
						
						// for each solution to the current transcript, add the prefix
						for (String str : solution)
							if (str.isEmpty() == true)
								tempSolutions.get(tempSolutions.size() - 1).add(prefix); 
							else 
								tempSolutions.get(tempSolutions.size() - 1).add(str + " " + prefix);
					}		
				}
			}
			transcripts.clear(); 
			solutions.clear(); 
			transcripts.addAll(tempTranscripts); 
			solutions.addAll(tempSolutions); 
			tempTranscripts.clear(); 
			tempSolutions.clear(); 
		}
	}
}
