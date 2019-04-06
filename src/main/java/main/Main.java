package main;

import model.Receiver;
import org.eclipse.jetty.websocket.api.Session;

import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Session> connectedUsers = new ArrayList<>();

    public static void main(String[] args) throws JMSException {
        String queue = "mensajes";
        Receiver receiver = new Receiver(queue);
        receiver.connect();
    }
}
