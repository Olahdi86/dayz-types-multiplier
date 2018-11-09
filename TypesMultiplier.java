import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;


public class TypesMultiplier{
    public static void main(String[] args){
	try{
	    File inputFile = new File("types.xml");
	    Scanner scan = new Scanner(inputFile);
	    ArrayList<String> myList = new ArrayList<String>();

	    //output to doubled-dir directory
	    new File("doubled-dir").mkdirs();

	    //add all xml tags to array
	    while(scan.hasNextLine()){
		myList.add(scan.nextLine());
	    }

	    //loop through all xml tags
	    for(String line : myList){

		String fileName = "doubled-types.xml";
		File outputFile = new File("doubled-dir", fileName);
		FileOutputStream fos = new FileOutputStream(outputFile, true); //true for append to file, not overwrite
		
		if(line.contains("        <nominal>")){ //> is 16
		    String strVal = line.substring(17, 19); //returns ## or #<
		    if(isInt(strVal)){
			int numVal = Integer.parseInt(strVal);
			numVal *= 2;
			line = "        <nominal>" + numVal + "</nominal>";
		    } else{ //it's #<
			char singleDigit = strVal.charAt(0);
			int numVal = Integer.parseInt(singleDigit + "");
			numVal *= 2;
			line = "        <nominal>" + numVal + "</nominal>";
		    }
		}

		if(line.contains("        <min>")){ //> is 12
		    String strVal = line.substring(13, 15); //returns ## or #<
		    if(isInt(strVal)){
			int numVal = Integer.parseInt(strVal);
			numVal *= 2;
			line = "        <min>" + numVal + "</min>";
		    } else{ //it's #<
			char singleDigit = strVal.charAt(0);
			int numVal = Integer.parseInt(singleDigit + "");
			numVal *= 2;
			line = "        <min>" + numVal + "</min>";
		    }
		}

		//write to file@@@@@@@@2
		try{
		    System.out.println("Working with this line: " + line);
		    fos.write(line.getBytes());
		    fos.flush();
		    fos.close();
		} catch(IOException e){
		    System.out.println("IOException 1: fos method failed");
		} finally{
		    try{
			if(fos != null){
			    fos.close();
			}
		    } catch(IOException e){
			System.out.println("IOException 2: fos method failed");
		    }
		}
	    }
	} catch(IOException e){
	    System.out.println("wow");
	}
    }

    public static boolean isInt(String strNum) {
	try {
	    int x = Integer.parseInt(strNum);
	} catch (NumberFormatException | NullPointerException nfe) {
	    return false;
	}
	return true;
    }
}
