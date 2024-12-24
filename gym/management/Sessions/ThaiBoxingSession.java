package gym.management.Sessions;

import gym.customers.ForumType;
import gym.management.Instructor;

class ThaiBoxingSession extends Session {
    public ThaiBoxingSession(String sessionDate, ForumType forumType, Instructor instructor) {
        super(sessionDate, forumType, instructor,100,20);
    }
    public String getSessionType() {
        return "ThaiBoxing";
    }
    @Override
    public String toString() {
        return "Session Type: ThaiBoxing"+super.toString();
    }
}
