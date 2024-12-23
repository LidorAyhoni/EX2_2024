package gym.Exception;

public class ClientNotRegisteredException extends RuntimeException {
    public ClientNotRegisteredException(String message) {super(message);}
    @Override
    public String toString() {
        return "Registration is required before attempting to unregister";
    }
}
