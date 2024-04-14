package logger;


public class App {
public static void main(String[] args) {
   Logger asteriskLogger = new AsteriskLogger();
   Logger spacedLogger = new SpacedLogger();

   asteriskLogger.log("Hello");
   asteriskLogger.error("Hello");

   spacedLogger.log("Hello");
   spacedLogger.error("Hello");

   asteriskLogger.error("Dont mind me, I'm just a really long error message!");

	}
}