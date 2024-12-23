package gym.management.Sessions;

import gym.customers.ForumType;
import gym.Employees.Instructor;
import gym.customers.Person;

import java.util.ArrayList;

public abstract class Session {
    protected String sessionDate;
    protected ForumType forumType;
    protected Instructor instructor;
    protected final int price;
    protected final int MAX_PARTICIPANTS;
    protected final ArrayList<Person> participants = new ArrayList<>();
    public Session(String sessionDate, ForumType forumType, Instructor instructor, int price, int maxParticipants) {
        this.sessionDate = sessionDate;
        this.forumType = forumType;
        this.instructor = instructor;
        this.price = price;
        MAX_PARTICIPANTS = maxParticipants;
    }
    public String getDate() {
        return sessionDate;
    }
    public ForumType getForumType() {
        return forumType;
    }
    public Instructor getInstructor() {
        return instructor;
    }
    public int getPrice() {
        return price;
    }
    public void addParticipant(Person person) {
        participants.add(person);
    }
    public void removeParticipant(Person person) {
        participants.remove(person);
    }
    public boolean isLeftPlace(){
        return participants.size() <= MAX_PARTICIPANTS;
    }
    public String getName() {
        return Session.class.getSimpleName();
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
