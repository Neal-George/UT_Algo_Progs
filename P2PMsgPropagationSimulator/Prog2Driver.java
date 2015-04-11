package assignment2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Prog2Driver 
{	
	public static void main(String[] args)
	{
		try
		{
			String filename; 
			if (args.length > 0)
			{
				filename = args[0]; 
			}
			else
			{
//				 System.out.println("Error: Call syntax: RUN.jar <inputFilename>\nExiting...");
//				 return; 
				filename = "breaker.txt"; 
			}
			findExistingPathInP2PFile(filename); 
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
		catch(InvalidTraceError e)
		{
			System.err.println ("Error: Invalid Trace Created. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
		catch(InvalidInputFileException e)
		{
			System.err.println ("Error: Invalid Trace Created. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	private static int findExistingPathInP2PFile(String filepath) 
	throws FileNotFoundException, IOException, InvalidTraceError, InvalidInputFileException
	{
		StringTokenizer st; 
		String line;
		FileReader inFile = new FileReader(filepath);
		BufferedReader bif = new BufferedReader(inFile);
		
		String outputBlock = null; 
		P2PNetwork p2pNetwork = null; 
		ArrayList<Trace> traceList = new ArrayList<Trace>(); 
		Query query = null; 
		int numTraces = -1;
		int numDevices = -1; 
		
		
		if ((line = bif.readLine()) != null)
		{
			
			// get the number of devices and traces in input file
			st = new StringTokenizer(line);
			if (st.hasMoreTokens())
				numDevices = Integer.parseInt(st.nextToken()); 
			// else throw InvalidInputFileException
			
			if (st.hasMoreTokens())
			 	numTraces = Integer.parseInt(st.nextToken()); 
			// else throw InvalidInputFileException
			
			if (numTraces == -1)
			{
				System.out.println("Could not find number of traces in input file"); 
				bif.close(); 
				return -1; 
			}
			if (numDevices == -1)
			{
				System.out.println("Could not find number of devices in input file"); 
				bif.close(); 
				return -1; 
			}
			// if has more tokens throw InvalidInputFileException
			
			
			// populate traceList with new traces
			for (int i = 0; i < numTraces; i += 1)
			{
				if ((line = bif.readLine()) != null)
				{
					traceList.add(stringToTrace(line)); 
				}
			}
			
						
			// get query from input file
			if ((line = bif.readLine()) != null)
			{
				query = stringToQuery(line); 
			}
			
			// create new P2PNetwork and calculate query
			p2pNetwork = new P2PNetwork(traceList, numDevices); 
			outputBlock = p2pNetwork.calculateQuery(query); 
		}
		if (outputBlock != null)
			System.out.println(outputBlock);
		
		bif.close();
		return 0; 
	}
	
	private static Trace stringToTrace(String trace)
	throws InvalidTraceError, InvalidInputFileException
	{	
		int id1 = -1; 
		int id2 = -1; 
		int time = -1; 
		
		StringTokenizer st = new StringTokenizer(trace); 
		if (st.hasMoreTokens())
			id1 = Integer.parseInt(st.nextToken());
		else
			throw new InvalidInputFileException(); 
		
		if (st.hasMoreTokens())
			id2 = Integer.parseInt(st.nextToken());
		else
			throw new InvalidInputFileException();
		
		if (st.hasMoreTokens())
			time = Integer.parseInt(st.nextToken());
		else
			throw new InvalidInputFileException();
		
		if (id1 == -1 ||
			id2 == -1 || 
			time == -1)
			throw new InvalidTraceError(new Trace (id1, id2, time)); 
		
		if (st.hasMoreTokens())
			throw new InvalidInputFileException();
		
		return (new Trace(id1, id2, time)); 
	}
	
	private static Query stringToQuery(String query)
	{
		StringTokenizer st; 
		int id1 = -1; 
		int id2 = -1; 
		int msgSendTime = -1;
		int msgRecTime = -1;
		
		st = new StringTokenizer(query); 
		if (st.hasMoreTokens())
			id1 = Integer.parseInt(st.nextToken()); 
		
		if (st.hasMoreTokens())
			id2 = Integer.parseInt(st.nextToken()); 
		
		if (st.hasMoreTokens())
			msgSendTime = Integer.parseInt(st.nextToken()); 
	
		if (st.hasMoreTokens())
			msgRecTime = Integer.parseInt(st.nextToken()); 
		
		return new Query(id1, id2, msgSendTime, msgRecTime); 
	}
	
}
