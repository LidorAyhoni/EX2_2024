package gym.Exception;

public class InstructorNotQualifiedException extends RuntimeException {
    public InstructorNotQualifiedException(String message) {super(message);}
    @Override
    public String toString() {
        return "Error: Instructor is not qualified to conduct this session type.";
    }
}
