package assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.StringTokenizer;

public class Prog3Driver 
{
	private static ArrayList<String> ldictionary = new ArrayList<String>(); 
	private static String ltranscript;
	
	
	public static void main(String[] args)
	{
		try 
		{
			String filename; 
			
			if (args.length > 0)
				filename = args[0]; 
			
			else
			{
				System.out.println("Error: Call Syntax: RUN.jar <File Name>\nExiting...");
				return; 
//				filename = "a"; 
			}
			
			// begin test timing after here
//			long startTime = System.nanoTime();
			parseInputBlock(filename); 
			// the hefty stuff
			TranscriptDecoder decoder = new TranscriptDecoder(ldictionary); 
			ArrayList<String> output = decoder.Decode(ltranscript); 			
//			long endTime = System.nanoTime(); 
			// end timing before here
			
			if (output == null)
				System.out.println("0");
			else
				printOutputBlock(output); 
//		System.out.println(output.size());
			
//			System.out.println("Run time was: " + (double) (endTime - startTime)/1000000000);
//			if (output != null)
//				System.out.println("Number of solutions found: " + output.size());
			return; 
		}
		
		catch (FileNotFoundException e)
		{
			System.err.println ("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
			return; 
		}
		catch(IOException e)
		{
			System.err.println ("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private static void parseInputBlock(String filepath) 
	throws FileNotFoundException, IOException
	{
		StringTokenizer st; 
				
		FileReader inFile = new FileReader(filepath); 
		BufferedReader bif = new BufferedReader(inFile); 
		String line; 
		
		int numDictionaryEntries = -1; 
		
		if ((line = bif.readLine()) != null)
		{
			st = new StringTokenizer(line); 
			
			// get number of dictionary entries
			if (st.hasMoreTokens())
				numDictionaryEntries = Integer.parseInt(st.nextToken()); 
			
			// populate local dictionary list
			for (int i = 0; i < numDictionaryEntries; i += 1)
			{
				if ((line = bif.readLine()) != null)
				{
					st = new StringTokenizer(line); 
					String str = st.nextToken(); 
					if (ldictionary.contains(str) == false)
						ldictionary.add(str); 
				}
			}
			
			// get transcript file to decode 
			if ((line = bif.readLine()) != null)
				ltranscript = line; 
			
			bif.close(); 
		}
	}
	
	private static void printOutputBlock(ArrayList<String> output)
	{
		System.out.println(output.size());
		for (String line : output)
			System.out.println(line);
	}
	
//	private static String toOutputBlock(ArrayList<String> output)
//	{
//		String possibleTranscripts = ""; 
//		
//		// top line of output is number of possible transcripts
//		possibleTranscripts += String.valueOf(output.size());
//		possibleTranscripts += "\n"; 		
//	
//		// populate each line with a possible transcript
//		for (String line : output)
//		{
//			possibleTranscripts += line; 
//			possibleTranscripts += "\n"; 
//		}
//		
//		return possibleTranscripts; 
//	}
}
