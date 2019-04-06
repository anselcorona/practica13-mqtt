package main;

import model.Sender;
import org.apache.activemq.broker.BrokerService;

import javax.jms.JMSException;

public class MessageGenerator {

    public static void main(String[] args) throws JMSException {
        String queue = "mensajes";
        BrokerService brokerService = new BrokerService();

        try {
            brokerService.addConnector("tcp://localhost:34343");
            brokerService.start();
        }catch (Exception e){
            e.printStackTrace();
        }

        new Sender().sendMessage(queue);
    }
}
