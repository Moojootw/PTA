package logger;


// this class puts spaces in-between letters of the print out
public class SpacedLogger implements Logger {
	
	//uses join with a space and split to interlace space characters in the string
	private String spaced(String printOut) {  
	   return String.join(" ", printOut.split(""));
	}

	public void log(String printOut) {
	   System.out.println(spaced(printOut));
	}


	public void error(String printOut) {
	   System.out.println("ERROR: " + spaced(printOut));
	}
	

}
