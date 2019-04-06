package model;

import org.apache.activemq.ActiveMQConnectionFactory;
import utils.Json;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

public class Sender {

    public Sender() {
    }

    public void sendMessage(String queue) throws JMSException{
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:34343");
        Connection connection = activeMQConnectionFactory.createConnection("admin", "admin");
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue_ = session.createQueue(queue);

        MessageProducer producer = session.createProducer(queue_);

        while (true){
            try {
                TimeUnit.SECONDS.sleep(3);
                Mensaje mensaje = new Mensaje();
                String messageData = Json.toJson(mensaje);
                TextMessage message = session.createTextMessage(messageData);
                producer.send(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
