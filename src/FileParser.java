// -----------------------------------------------------
// Assignment 3
// Written by: Reuven Ostrofsky - 40188881
// -----------------------------------------------------
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 


/**
 * 
 * @author reuvenostrofsky
 *FileParser class allows user tp input any file to be read and create/output a new file with the given methods
 */
public class FileParser{

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Scanner s = null;
		PrintWriter ona = null;
		PrintWriter triple = null;
		PrintWriter nonAlphanumeric = null;
		System.out.println("Enter file you would like to be parsed:");
		String file = input.nextLine();
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> c1 = new ArrayList<String>();
		ArrayList<String> c2 = new ArrayList<String>();
		ArrayList<String> c3 = new ArrayList<String>();

		try {
			ona = new PrintWriter(new FileOutputStream("src/Parsed Files/Obsessive_non_ona.txt"));
			triple = new PrintWriter(new FileOutputStream("src/Parsed Files/Popular_peeps.txt"));
			nonAlphanumeric = new PrintWriter(new FileOutputStream("src/Parsed Files/Non_Alphanumeric.txt"));
			s = new Scanner(new FileInputStream("src/Given Files/"+file));
		}catch(FileNotFoundException f) {
			System.out.println(f.getMessage());
			System.out.println("Exiting program...");
			System.exit(0);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			}

			while(s.hasNextLine()){
				data.add(s.next().toLowerCase());
			}

			
			for(String indData : data){
				if(!(indData.contains("o") || indData.contains("n") || indData.contains("a"))){
					c1.add(indData);
				}
			}
			c1 = deleteDuplicates(c1);
			Collections.sort(c1);
			
			ona.println("Word count: " + c1.size());
			for(String d : c1){
				ona.println(d);
				
			}

			c2 = getDuplicates(data);
			Collections.sort(c2);
			triple.println("Word count: " + c2.size());
			for(String d : c2){
				triple.println(d);
			}

			c3 = checkAlphanumeric(data);
			c3 = deleteDuplicates(c3);
			Collections.sort(c3);
			nonAlphanumeric.println("Word count: " + c3.size());
			for(String d : c3){
				nonAlphanumeric.println(d);
			}
		
		
		input.close();
		s.close();
		ona.close();
		triple.close();
		nonAlphanumeric.close();
		
	}
	/**
	 * This method deletes all duplicate String in the inputed array list 
	 * @param arrList - An array list with String as the parameter 
	 * @return - New array list with unique strings
	 */
	public static ArrayList<String> deleteDuplicates(ArrayList<String> arrList) {
		
		ArrayList<String> newList = new ArrayList<String>();
		for(String s : arrList) {
			if(!newList.contains(s)){
				newList.add(s);
			}
		}
		return newList;
	}
	/**
	 * This method acquires all duplicate String(more than 3) in the inputed array list 
	 * @param arrList - An array list with String as the parameter 
	 * @return - New array list with duplicate strings
	 */
	public static ArrayList<String> getDuplicates(ArrayList<String> arrList){
		int wordCount;
		ArrayList<String> newList = new ArrayList<String>();
		ArrayList<String> WordsUsed = new ArrayList<String>();
		for(String s: arrList) {
			if(!WordsUsed.contains(s)){
				wordCount =0;
			for(String s2: arrList) {
				
				if (s.equals(s2) || s == s2){
					wordCount++;
					
				}
				
			}
			if(wordCount >3){
				newList.add(s);
			}
			}
			
			WordsUsed.add(s);
			
		}
		return newList;
	}
	/**
	 * This method returns an array list with only the alphanumerics strings of the inputed array list
	 * @param arrList - An array list with String as the parameter 
	 * @return - New array list with only alphanumerics
	 */
	public static ArrayList<String> checkAlphanumeric(ArrayList<String> arrList) {
		ArrayList<String> newList = new ArrayList<String>();
		for(String s: arrList) {
		 char[] characters = s.toCharArray();
			for(int i = 0; i < characters.length; i++) {
				if (!Character.isLetterOrDigit(characters[i])) {
					
					newList.add(String.valueOf(characters[i]));
				}
			
			}
		}
		return newList;
	}

	

}
