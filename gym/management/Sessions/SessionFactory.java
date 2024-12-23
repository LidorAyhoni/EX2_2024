package gym.management.Sessions;

import gym.customers.ForumType;
import gym.Employees.Instructor;
public class SessionFactory {
    public static Session createSession(SessionType sessionType, String sessionDate, ForumType forumType, Instructor instructor) {
        switch (sessionType) {
            case Pilates -> {return new PilatesSession(sessionDate,forumType,instructor);}
            case MachinePilates -> {return new MachinePilatesSession(sessionDate,forumType,instructor);}
            case ThaiBoxing -> {return new ThaiBoxingSession(sessionDate,forumType,instructor);}
            case Ninja -> {return new NinjaSession(sessionDate,forumType,instructor);}
            default -> throw new IllegalArgumentException("Invalid session type");
        }
    }
}
