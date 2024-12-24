package gym.management.Sessions;

import gym.customers.Client;
import gym.customers.ForumType;
import gym.management.Instructor;

import java.util.ArrayList;

public abstract class Session {
    protected String sessionDate;
    protected ForumType forumType;
    protected Instructor instructor;
    protected final int price;
    protected final int MAX_PARTICIPANTS;
    protected final ArrayList<Client> participants = new ArrayList<>();
    protected Session(String sessionDate, ForumType forumType, Instructor instructor, int price, int maxParticipants) {
        this.sessionDate = sessionDate;
        this.forumType = forumType;
        this.instructor = instructor;
        this.price = price;
        MAX_PARTICIPANTS = maxParticipants;
    }
    public abstract String getSessionType();
    public String getDate() {
        return sessionDate;
    }
    public ForumType getForumType() {
        return forumType;
    }
    public int getPrice() {
        return price;
    }
    public void addParticipant(Client client) {
        participants.add(client);
    }

    public ArrayList<Client> getParticipants() {
        return participants;
    }
    public boolean isLeftPlace(){
        return participants.size() < MAX_PARTICIPANTS;
    }
    @Override
    public String toString() {
        return " | Date: "+getDate()+
                " | Forum: "+getForumType()+
                " | Instructor: "+this.instructor.getName()+
                " | Participants: "+participants.size()+
                "/"+MAX_PARTICIPANTS;
    }
}
