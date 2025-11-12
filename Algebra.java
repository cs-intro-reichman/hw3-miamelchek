public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  
	// פעולה שבודקת אם לשניהם יש את אותו הערך
	public static boolean sameSign(int x1, int x2){
		if (x1 > 0 && x2 > 0 || x1 < 0 && x2 < 0) {
			return true ;
		}
		else{
			return false;
		}

	}
	public static int abs(int x1){
		int a = x1;
		// נבדוק אם המשתנים שלילים ואם כן נהפוך אותם לחיובים לעבודה נוחה יותר
		if(x1 < 0){
			a = minus(0, a);
		}
		return a;
	}

	public static int plus(int x1, int x2) {

		if (x2 > 0) {
			while (x2 > 0) {
				x1++;
				x2--;
			}
		} else {
			while (x2 < 0) {
				x1--;
				x2++;
			}
		}
			return x1;
	}

	public static int minus(int x1, int x2) {

		if (x2 > 0) {
			while (x2 > 0) {
				x1--;
				x2--;
			}
		} else {
			while (x2 < 0) {
				x1++;
				x2++;
			}
		}
			return x1;		
	}

	public static int times(int x1, int x2) {
		int result = 0;
		if(x1 == 0 || x2 == 0){
			return 0;
		}
    	int a = abs(x1);
		int b = abs(x2);

		// נחבר את a לעצמו b פעמים
		while (b > 0) {
			result = plus(result, a);
			b--;
		}

		// אם צריך – נהפוך את התוצאה לשלילית
		if (!sameSign(x1, x2)) {
			result = minus(0, result);
		}

		return result;
	}


	public static int pow(int x, int n) {
		//every number in the power of 0 is 1
		int result = 1;
		
		while (n > 0) {
			result = times(result, x); 
			n--; 
		}

		return result;
	}


	public static int div(int x1, int x2) {
		
		if (x2 == 0) {
			return -1;
		}
		int count = 0;
		int a = abs(x1);
		int b = abs(x2);

		while (a >= b ) {
			a = minus(a, b);
			count++;
		}
		if (!sameSign(x1, x2)) {
			count = minus(0, count);
		}
		return count;
	}

	public static int mod(int x1, int x2){

		if (x2 == 0) {
			return -1;
			
		}
		int a = abs(x1);
		int b= abs(x2);

		while (a >= b) {
			a = minus(a, b);
		}
		// במידה והמספר הראשון שלילי התוצאה היא שלילית אז נמיר
		if (x1 < 0 && a != 0 ) {
			a = minus(0, a);
		}
		return a;
	}

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		if (x < 0) {
			return -1;
		}

		int a = 0;
		while (times(a, a) <= x) {
			a = plus(a, 1);
		}
		return minus(a, 1);
	}	  	  
}