package io.yhh.mq.activemq.producer;

import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Destination;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.log4j.BasicConfigurator;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.command.ActiveMQQueue;


public class Producer {

	public static void main(String[] args) throws JMSException{

	     Destination topicDestination = new ActiveMQTopic("test.topic");

	     Destination queueDestination = new ActiveMQQueue("test.Queue");

	     testTopicOrQuque(topicDestination);
	     testTopicOrQuque(queueDestination);
	}
	
	public static void testTopicOrQuque(Destination dest) {
		
		try {
	        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
	        Connection connection = factory.createConnection("admin", "admin");
	        connection.start();
	        
	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        
	        //create consumer
            MessageConsumer consumer = session.createConsumer( dest );	        
            final AtomicInteger count = new AtomicInteger(0);
	        MessageListener listener = new MessageListener() {
	        	public void onMessage(Message message) {
	        		try {
	        			Thread.sleep(10);
	        			System.out.println(count.incrementAndGet() +" received from " + dest.toString() +": " + message);
	        		}catch(Exception e) {
	        			e.printStackTrace();
	        		}
	        	}
	        };
	        //bind to listner
	        consumer.setMessageListener(listener);
            //consumer.receive();
            
            //create producer,produce 1000 messages
	        MessageProducer producer = session.createProducer(dest);
	        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	        
	        int messages = 100;
	        int size = 256;
	        String DATA = "abcdefghijklmnopqrstuvwxyz";
	        String body = "";
	        for( int i=0; i < size; i ++) {
	            body += DATA.charAt(i%DATA.length());
	        }


	        for( int i=1; i <= messages; i ++) {
	            TextMessage msg = session.createTextMessage(body);
	            msg.setIntProperty("id", i);
	            producer.send(msg);
	            if( (i % 100) == 0) {
	                System.out.println(String.format("Sent %d messages", i));
	            }
	        }	        
	        
            Thread.sleep(2000);
            session.close();
            connection.close();	        
		}catch(Exception e) {
			e.printStackTrace();
		}
        
	}

}
