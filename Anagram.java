/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		String word1 = preProcess(str1);
		String word2 = preProcess(str2);
		char letter1, letter2;
		// אם הם לא באותו האורך הם לא יכולות להיות אנגרמות
		if (word1.length() != word2.length()) {
			return false;
		}
			while (word1.length() >0) {
				
				letter1 = word1.charAt(0);
				boolean isEqual = false;
				
				if (letter1 == ' ') {
					word1 = word1.substring(1); // להסיר את הרווח מההתחלה
					continue;                   // ולהמשיך לסיבוב הבא
				}	

				for(int j = 0; j < word2.length();j++){
				letter2 = word2.charAt(j);
				if (letter2 == ' ') continue;
					if (letter1 == letter2) {
						word2 = word2.substring(0, j) + word2.substring(j + 1);
						word1 = word1.substring(1);
						isEqual = true;
						break;
					}
				}
				// במידה ולא נמצאה בכלל האות האשונה אין סיבה להמשיך כי זה לא אנגרמה
				if (!isEqual) {
					return false;
				}
			}	
		return true;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		String abcCheck = " abcdefghijklmnopqrstuvwxyz";
		String newWord = "";
		String lowerCase = str.toLowerCase();
		for(int i = 0;i < lowerCase.length(); i++ ){
			char letter = lowerCase.charAt(i);
			if (abcCheck.indexOf(letter) != -1) {
				newWord = newWord + letter;
			}
		}
		return newWord;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		String word = preProcess(str);
		String randWord = "";

		while (word.length() > 0) {
			int place = (int)(Math.random() * word.length());
        	char s = word.charAt(place);
        	randWord += s; // לביצועים עדיף StringBuilder, אבל זה עובר לתרגיל
        	word = word.substring(0, place) + word.substring(place + 1);
		}
		return randWord;
	
	}
}
