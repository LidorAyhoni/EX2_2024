package gym.customers;

public class Client extends Person{

    public Client(Person person) {
        super(person);
    }
    //@ToDo
    //[The instructor will be a few minutes late for the session, Heavy traffic reported around the gym today. Plan ahead to avoid missing your session!, Happy New Year to all our valued clients!]
    public String getNotifications(){return "";}
}
