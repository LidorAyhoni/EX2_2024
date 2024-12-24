package gym.management;
import gym.Exception.*;
import gym.MyDateFunc;
import gym.customers.*;
import gym.management.Sessions.*;

import java.util.ArrayList;

import static gym.management.Sessions.SessionFactory.createSession;

public class Secretary extends Employees {
    protected Secretary(Person person,int salary) {
        super(person,"Secretary",salary);
    }
    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        isTheCurrentSecretary();
        if (person.getAge() < 18) {
            throw new InvalidAgeException("Error: Client must be at least 18 years old to register");
        } else if (Gym.getInstance().getClientsList().containsKey(person.getID())) {
            throw new DuplicateClientException("Error: The client is already registered");
        } else {
            Client client = new Client(person);
            Gym.getInstance().addClient(client);
            Gym.getInstance().addLog("Registered new client: " + client.getName());
            return client;
        }
    }
    public void unregisterClient(Client client) {
        isTheCurrentSecretary();
        if (Gym.getInstance().getClientsList().containsKey(client.getID())) {
            Gym.getInstance().getClientsList().remove(client.getID());
            Gym.getInstance().addLog("Unregistered client: " + client.getName());
            Person.decreaseNumberID();
            return;
        }
        throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
    }
    public Instructor hireInstructor(Person person, int salary, ArrayList<SessionType> certifiedClasses ) {
        isTheCurrentSecretary();
        Instructor instructor = new Instructor(person,salary,certifiedClasses);
        Gym.getInstance().addInstructor(instructor);
        Gym.getInstance().addLog("Hired new instructor: " + instructor.getName()+" with salary per hour: "+salary);
        return instructor;
    }
    public void isTheCurrentSecretary(){
        if(Gym.getInstance().getSecretary().ID!=this.ID) throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
    }
    public Session addSession(SessionType type,String date,ForumType forum,Instructor instructor)throws InstructorNotQualifiedException {
        isTheCurrentSecretary();
        if (instructor.isCertified(type)){
            Session session =createSession(type,date,forum,instructor);
            Gym.getInstance().addSession(session);
            Gym.getInstance().addLog("Created new session: "+type+" on "+MyDateFunc.parseDateFormat(date)+" with instructor: "+instructor.getName());
            instructor.setNumHoursOfTraining(1);
            return session;
        }
        throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.");
    }
    public void registerClientToLesson(Client client,Session session) throws DuplicateClientException,ClientNotRegisteredException {
        isTheCurrentSecretary();
        if (session.getParticipants().contains(client)) {
            throw new DuplicateClientException("Error: The client is already registered for this lesson");
        }
        if (!Gym.getInstance().getClientsList().containsKey(client.getID())) {
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        }
        if (MyDateFunc.theSessionIsInTheFuture(session.getDate())) {
            Gym.getInstance().addLog("Failed registration: Session is not in the future");
            return;
        }
        if (session.getForumType() == ForumType.Seniors && client.getForumType() != ForumType.Seniors) {
            Gym.getInstance().addLog("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
            return;
        }
        if (session.getForumType() != client.getForumType() && session.getForumType() != ForumType.All) {
            Gym.getInstance().addLog("Failed registration: Client's gender doesn't match the session's gender requirements");
            return;
        }
        if (client.getBankAccount().getBalance() < session.getPrice()) {
            Gym.getInstance().addLog("Failed registration: Client doesn't have enough balance");
            return;
        }
        if (!session.isLeftPlace()) {
            Gym.getInstance().addLog("Failed registration: No available spots for session");
        } else {
            client.getBankAccount().pay(session.getPrice());
            Gym.getInstance().getBankAccount().addMoneyToMyAcc(session.getPrice());
            session.addParticipant(client);
            Gym.getInstance().addLog("Registered client: " + client.getName() + " to session: " + session.getSessionType() + " on " + session.getDate() + " for price: " + session.getPrice());
        }
    }

    public void paySalaries(){
        for (Instructor instructor : Gym.getInstance().getInstructorsList()) {
            Gym.getInstance().getBankAccount().pay(instructor.salary*instructor.numHoursOfTraining);
            instructor.getBankAccount().addMoneyToMyAcc(instructor.salary*instructor.numHoursOfTraining);
        }
        Gym.getInstance().getBankAccount().pay(this.salary);
        Gym.getInstance().addLog("Salaries have been paid to all employees");
    }
    public void printActions() {
        for (String log:Gym.getInstance().getGymLogs()){
            System.out.println(log);
        }
    }
    //@ToDo
    public void notify(Session session,String MSG) {
        Publisher publisher = new Publisher();
        isTheCurrentSecretary();
        for (Client client : session.getParticipants()) {
            publisher.addClient(client);
        }
        publisher.send(MSG);
        Gym.getInstance().addLog("A message was sent to everyone registered for session "+session.getSessionType()+" on " +MyDateFunc.parseDateFormat(session.getDate())+" : "+ MSG);
    }

    public void notify(String date,String MSG2){
        isTheCurrentSecretary();
        Publisher publisher = new Publisher();
        for(Session session: Gym.getInstance().getSessions()){
            if (MyDateFunc.parseDateFormat(session.getDate()).equals(date)) {
                for(Client client : session.getParticipants()){
                    publisher.addClient(client);
                }
            }
        }
        publisher.send(MSG2);
        Gym.getInstance().addLog("A message was sent to everyone registered for a session on " + MyDateFunc.opDate(date) + " : " + MSG2);

    }


    public void notify(String MSG){
        Publisher publisher = new Publisher();
        isTheCurrentSecretary();
        for(Client client: Gym.getInstance().getClientsList().values()){
            publisher.addClient(client);
        }
        publisher.send(MSG);
        Gym.getInstance().addLog("A message was sent to all gym clients: " + MSG);
    }
    @Override
    public String toString() {
        return super.toString()+" | Salary per Month:"+this.salary;
    }
}
