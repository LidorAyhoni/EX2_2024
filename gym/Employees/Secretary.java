package gym.Employees;
import gym.Exception.*;
import gym.MyDateFunc;
import gym.customers.*;
import gym.management.Gym;
import gym.management.Sessions.*;

import java.util.ArrayList;

import static gym.management.Sessions.SessionFactory.createSession;

public class Secretary extends Employees {
    public Secretary(Person person,int salary) {
        super(person,"Secretary",salary);
    }
    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        if (isTheCurrentSecretary(this)) {
            if (person.getAge() < 18) {
                throw new InvalidAgeException("Error: Client must be at least 18 years old to register.");
            } else if (Gym.getInstance().getClientsList().containsKey(person.getID())) {
                throw new DuplicateClientException("Error: Client is already registered.");
            } else {
                Client client = new Client(person);
                Gym.getInstance().addClient(client);
                Gym.getInstance().addLog("Registered new client: " + client.getName());
                return client;
            }
        }
        return null;
    }

    public void unregisterClient(Client client) {
        if (Gym.getInstance().getClientsList().containsKey(client.getID())) {
            Gym.getInstance().getClientsList().remove(client.getID());
            Gym.getInstance().addLog("Unregistered client: " + client.getName());
            Person.decreaseNumberID();
            return;
        }
        throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
    }
    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> certifiedClasses ) {
        Instructor instructor = new Instructor(person,salary,certifiedClasses);
        Gym.getInstance().addInstructor(instructor);
        Gym.getInstance().addLog("Hired new instructor: " + instructor.getName()+" with salary per hour: "+salary);
        return instructor;
    }
    public boolean isTheCurrentSecretary(Secretary secretary){
        return Gym.getInstance().getSecretary()==secretary;
    }

    public Session addSession(SessionType type,String date,ForumType forum,Instructor instructor)throws InstructorNotQualifiedException {
        if (instructor.isCertified(type)){
            Session session =createSession(type,date,forum,instructor);
            Gym.getInstance().addSession(session);
            Gym.getInstance().addLog("Created new session: "+type+" on "+MyDateFunc.parseDateFormat(date)+" with instructor: "+instructor.getName());
            return session;
        }
        throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.");
    }
    //@ToDo
    /**throw the next exception "The client is already registered for this lesson"
    throw the next exception "Error: The client is not registered with the gym and cannot enroll in lessons"
     **/
    public void registerClientToLesson(Client client,Session session) {
        if (MyDateFunc.theSessionIsInTheFuture(session.getDate())){
            Gym.getInstance().addLog("Failed registration: Session is not in the future");
            return;//Brake
        }
        if (session.getForumType() == ForumType.Seniors && client.getForumType() != ForumType.Seniors){
            Gym.getInstance().addLog("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
            return;//Brake
        }
        if (session.getForumType() != client.getForumType() && session.getForumType() !=ForumType.All){
            Gym.getInstance().addLog("Failed registration: Client's gender doesn't match the session's gender requirements");
            return;//Brake
        }
        if (client.getBalance()<session.getPrice()){
            Gym.getInstance().addLog("Failed registration: Client doesn't have enough balance");
            return;//Brake
        }
        if (!session.isLeftPlace()){
            System.out.println("No available spots for session");
        }
        else {
            client.setBalance(client.getBalance()-session.getPrice());
            session.addParticipant(client);
            Gym.getInstance().addLog("Registered client: "+client.getName()+" to session: "+session.getName()+" on "+session.getDate()+" for price: "+session.getPrice());
        }
    }

    public void paySalaries(){
        for (Instructor instructor : Gym.getInstance().getInstructorsList()) {
            Gym.getInstance().setBalance(-instructor.salary);
        }
        Gym.getInstance().setBalance(-this.salary);
        Gym.getInstance().addLog("Salaries have been paid to all employees");
    }
    public void printActions() {
        for (String log:Gym.getInstance().getGymLogs()){
            System.out.println(log);
        }
    }
    //@ToDo
    public void notify(Session session,String MSG) {}
    //@ToDo
    public void notify(String MSG1,String MSG2){}
    //@ToDo
    public void notify(String MSG){}

    @Override
    public String toString() {
        return super.toString()+" | Salary per Month:"+this.salary;
    }
}
