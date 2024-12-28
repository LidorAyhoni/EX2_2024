package gym.management;

import gym.customers.Client;

import java.util.ArrayList;

public class Publisher extends Notification {
    private ArrayList<Client> clients = new ArrayList<>();

    public void addClient(Client client) {
        clients.add(client);
    }

    @Override
    public void send(String message) {
        for (Client client : clients) {
            client.update(message);
        }
    }
}
