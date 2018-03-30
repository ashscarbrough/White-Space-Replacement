import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;

public class WhiteSpaceReplace {
	
	public static void main(String[] args)
	{
		String fileName;
		String tempNameBase = "TempX", tempName;
		File tempFile, replaceFile;
		
		// Ask user for the file they want to adjust
		System.out.println("What is the name of the file you would like to edit?");
		Scanner file = new Scanner(System.in);
		fileName = file.nextLine();
		file.close();
		
		Scanner inputStream = null;
		PrintWriter outputStream = null;
		
		// Temporary Name Base given .txt ending and configured as File
		tempName = tempNameBase + ".txt";
		tempFile = new File(tempName);
		
		// Try-catch exception for proper inputStream, if error
		// exception message and exit
		try
		{
			inputStream = new Scanner (new FileInputStream(fileName));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File " + fileName + " was not found");
			System.out.println("or could not be opened.");
			System.exit(0);
		}
		
		// Loop searches for file name "TempX", if present, add "X" to 
		// TempX file name until no file is found
		while (tempFile.exists())
		{
			tempNameBase = tempNameBase + "X";
			tempName = tempNameBase + ".txt";
			tempFile = new File(tempName);
		}
	
		// Try-catch exception for proper outputStream, if error
		// exception message and exit
		try
		{
			outputStream = new PrintWriter(new FileOutputStream(tempName));//
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file " + fileName + ".");
			System.exit(0);
		}
		
		System.out.println("Writing to File");
		String line;
		
		// Loop replaces multiple whitespaces from input
		// with single white space upon output
		while (inputStream.hasNextLine()) 
		{
			line = inputStream.nextLine();
			StringTokenizer st = new StringTokenizer(line, " ");
			
			while (st.hasMoreTokens()) 
			{
			String word = st.nextToken();
			
			if (st.hasMoreTokens())
				outputStream.print(word + " ");
			else
				outputStream.println(word);
			}
		}
		System.out.println("File has been altered for reduced whitespaces.");
		
		// Close input and output steams
		inputStream.close();
		outputStream.close();
		
		// Delete original file chosen by user
		replaceFile = new File(fileName);
		replaceFile.delete();
		
		// Create new file with user selected file name
		// rename TempX.txt file with original name
		replaceFile = new File(fileName);
		tempFile.renameTo(replaceFile);

	}
}