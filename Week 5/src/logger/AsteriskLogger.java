package logger;


public class AsteriskLogger implements Logger {

	//adds asterisks on both sides of the print out
	public void log(String printOut) {
	   System.out.println("***" + printOut + "***");
	}


	public void error(String printOut) {
	   String border= "";
	   //for the loop, +13 extra asterisks fits pretty nicely
	   for (int i = 0; i < printOut.length() +13; i++) { 
		   border += "*";
	   }
	   //constructs the error log with a box
	   System.out.println(border);
	   System.out.println("***ERROR: " + printOut + "***");
	   System.out.println(border);
	}
}