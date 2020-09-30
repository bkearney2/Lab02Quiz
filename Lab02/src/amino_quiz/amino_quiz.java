package amino_quiz;

import java.util.Scanner;
import java.lang.Math; 
import java.util.Random;

public class amino_quiz 
{
	public static String[] SHORT_NAMES = {"A","R","N","D","C","Q","E","G","H","I","L","K","M","F","P","S","T","W","Y","V"};
	public static String[] FULL_NAMES = {"Alanine","Arginine","Asparagine","Aspartic acid","Cysteine",
		"Glutamine","Glutamic acid","Glycine","Histidine","Isoleucine",
		"Leucine","Lysine","Methionine","Phenylalanine","Proline",
		"Serine","Threonine","Tryptophan","Tyrosine","Valine"};
	
	// Check if user's response is able to be converted to double (if it's a valid time)
	public static boolean isNumeric(String str) 
	{ 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	
	public static void main(String[] args) 
	{
		int score = 0;
		int incorrects = 0;
		double current_time = 0;
		double user_time = 0;
		
		// Ask user for game time. If number is invalid, use default time instead
		System.out.println("Choose time limit (#seconds): ");
		Scanner myScanner = new Scanner(System.in);
		String user_time_str = myScanner.nextLine();
		if (isNumeric(user_time_str)==true) {
			user_time = Double.parseDouble(user_time_str);
			System.out.println("Time limit: "+user_time+" seconds");
		} else {
			user_time = 30;
			System.out.println("Invalid time entered. Using default of 30 seconds");
		}
		
		System.out.println("Amino acids quiz. Answer with a single letter to score a point.");
		System.out.println("Timer starts now! Enter 'quit' any time to end game");
		
		while (current_time < user_time) {
			long start_time = System.currentTimeMillis();
			Random random1 = new Random();
			int randInt = random1.nextInt(FULL_NAMES.length);
			System.out.println(FULL_NAMES[randInt]);

			String answer = myScanner.nextLine();  // Read user's answer
			String correct = SHORT_NAMES[randInt]; // Get correct answer from array
		
			if (answer.toUpperCase().equals("QUIT")) {
				break;
			}
			
			if (answer.equals(correct) || answer.equals(correct.toLowerCase())) {
				score++;
				System.out.println("Answer is correct");
				System.out.println("Score: " + score);
			} else {
				System.out.println("Answer is incorrect!");
				System.out.println("Correct answer: " + correct);
				System.out.println("Score: " + score);
				incorrects++;
			}
			
			long new_time = System.currentTimeMillis();
			double answer_time = (double)(new_time-start_time)/1000;
			current_time = current_time + answer_time;
			double time_left = (double)Math.round((user_time-current_time)*1000d)/1000d;
//			System.out.println("Time left: "+(user_time - current_time));
			System.out.println("Time left: "+time_left);
			System.out.println();
		}
		myScanner.close();
		System.out.println("Game over!");
		System.out.println("Final Score: " + score);
		System.out.println("Number of incorrect answers: "+incorrects);
		double accuracy = Math.round(100*(double)score/(double)(score+incorrects));
		System.out.println("Your accuracy rate: "+accuracy+"%");
	}

}
