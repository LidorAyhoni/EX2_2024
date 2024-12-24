package gym.management;

import gym.Bank;
import gym.customers.Client;
import gym.customers.Person;
import gym.management.Sessions.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Gym {
    private static Gym Instance;
    protected String name;
    protected Secretary secretary;
    protected Bank bankAccount=new Bank(0);
    protected final HashMap<Integer,Client> clients = new HashMap<>();
    protected final ArrayList<Instructor> instructors = new ArrayList<>();
    protected final ArrayList<Session> Sessions = new ArrayList<>();
    protected final ArrayList<String> gymLogs = new ArrayList<>();
    private Gym(){}
    public static Gym getInstance(){
        if(Instance == null){
            Instance = new Gym();
        }
        return Instance;
    }
    public ArrayList<Session> getSessions(){return Sessions;}
    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}
    public Secretary getSecretary(){return this.secretary;}
    public void addClient(Client client){
        clients.put(client.getID(),client);
    }
    public void addInstructor(Instructor instructor){
        getInstructorsList().add(instructor);
    }
    public void addSession(Session session){
        Sessions.add(session);
    }
    public HashMap<Integer, Client> getClientsList(){
        return clients;
    }
    public ArrayList<Instructor> getInstructorsList(){
        return this.instructors;
    }
    public void addLog(String log){
        gymLogs.add(log);
    }
    public ArrayList<String> getGymLogs(){
        return gymLogs;
    }

    public void setSecretary(Person person,int salary){
        this.secretary = new Secretary(person,salary);
        addLog("A new secretary has started working at the gym: "+person.getName());
    }
    public Bank getBankAccount(){return this.bankAccount;}
    public int getBalance(){
        return bankAccount.getBalance();
    }
    @Override
    public String toString(){
        String str="Gym Name: "+this.name;
        str+="\nGym Secretary: "+this.secretary;
        str+="\nGym Balance: "+getBalance();
        str+="\n \n";
        str+="Clients Data:";
        str+="\n";
        for (Map.Entry<Integer,Client> entry : clients.entrySet()){
            str+=entry.getValue().toString()+"\n";
        }
        str+="\n";
        str+="Employees Data:";
        str+="\n";
        for (Instructor instructor : getInstructorsList()){
            str+=instructor.toString()+"\n";
        }
        str+=this.secretary.toString()+"\n";
        str+="\n";
        str+="Sessions Data:";
        str+="\n";
        for (Session session : Sessions){
            str += session.toString()+"\n";
        }
        return str;
    }
}
