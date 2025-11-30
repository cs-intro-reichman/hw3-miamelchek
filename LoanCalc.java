// great

// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	
		double remain = loan;
		double epsilon = 0.01;
		for(int i = 0; i < n; i++){
			remain = (remain - payment) * (1 + rate / 100);
		}
		return remain;
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		iterationCounter = 0;
		// ניחוש התחלתי קטן שממנו נוכל להתחיל
		double payment = loan / n;
		//נבדוק שוב ושוב אם התשלום שלנו מספיק ואם לא נגדיל אותו במספר קטן וברגע נשלם יותר נקבל שזה יותר מאפס ונעצור עם סטייה קטנה של אפסילון
		while (endBalance(loan, rate, n, payment) > 0) {
				payment += epsilon;
			iterationCounter++;
		}
		return payment;
    }    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
		iterationCounter = 0;
		// ניחוש תשלום קטן מידי
		double lowPay = loan / n;
		// ניחוש תשלום גדול מאוד
		double hiPay = loan * (1 + rate / 100);
		double midPay = (hiPay + lowPay) / 2;
		// נבדוק אם הטווח בין התשלומים גדול אם הוא גדול נבדוק אם שילמנו יותר מידי או פחות מידי ובהתאם נשנה את הגבולות עד שהטווח יהיה קטן מאפסילון וככה נמצא את התשלום הנכון
		while (hiPay - lowPay > epsilon) {
			if (endBalance(loan, rate, n, midPay) < 0) {
				hiPay = midPay;
			}
			else{
				lowPay = midPay;
			}
			iterationCounter++;
			midPay = (hiPay + lowPay) / 2;
		}
		return midPay;
    }
}