package gym.Exception;

public class DuplicateClientException extends RuntimeException {
    public DuplicateClientException(String message) {super(message);}
    @Override
    public String toString() {
        return "Error: The client is already registered";
    }
}
