package gym.Exception;

public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String message) {super(message);}
    @Override
    public String toString() {
        return "Client must be at least 18 years old to register";
    }
}
