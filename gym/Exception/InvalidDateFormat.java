package gym.Exception;

public class InvalidDateFormat extends RuntimeException  {
    public InvalidDateFormat(String message) {super(message);}
    @Override
    public String toString() {
        return "Invalid Date Format! Please try the following format: dd-MM-yyyy.";
    }
}
