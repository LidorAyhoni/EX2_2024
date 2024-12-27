package gym.customers;

import gym.management.Observer;

import java.util.ArrayList;

public class Client extends Person implements Observer {
    public ArrayList<String> messages= new ArrayList<String>();

    public Client(Person person) {
        super(person);
    }
    //@ToDo
    //[The instructor will be a few minutes late for the session, Heavy traffic reported around the gym today. Plan ahead to avoid missing your session!, Happy New Year to all our valued clients!]
    public String getNotifications(){
        String str="[";
        for (int i = 0; i < messages.size(); i++){
            str +=  messages.get(i)+", " ;
        }
        str=str.substring(0,str.length()-2)+"]";
        return str;}

    @Override
    public void update(String message) {
        messages.add(message);
    }
}
