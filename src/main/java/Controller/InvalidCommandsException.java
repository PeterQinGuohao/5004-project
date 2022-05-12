package Controller;


public class InvalidCommandsException extends Exception {
    public InvalidCommandsException(String message) {
        super("There is a command line error: " + message + "\n");
    }
}