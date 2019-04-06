package model;

import main.Main;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Receiver {
    
    ActiveMQConnectionFactory activeMQConnectionFactory;
    Connection connection;
    Session session;
    Queue queue_;
    MessageConsumer consumer;
    String queue;

    public Receiver(String queue) {
        this.queue = queue;
    }
    
    public void connect() throws JMSException{
        activeMQConnectionFactory = new ActiveMQConnectionFactory("admin", "admin", "failover:tcp://localhost:34343");
        connection = activeMQConnectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        queue_ = session.createQueue(queue);
        consumer = session.createConsumer(queue_);
        consumer.setMessageListener(message -> {
            try {
                TextMessage textMessage = (TextMessage) message;
                System.out.println("mensaje recibido: " + textMessage.getText()+" - "+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }
}
