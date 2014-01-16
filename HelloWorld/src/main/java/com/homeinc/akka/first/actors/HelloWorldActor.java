package com.homeinc.akka.first.actors;


import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.homeinc.akka.first.model.Msg;

import java.util.concurrent.TimeUnit;

/**
 * Created by lit3hawk on 15/01/2014.
 */
public class HelloWorldActor extends UntypedActor{

    LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    private String name;

    private final ActorRef greeterRef;

    public static Props mkProps(String name, ActorRef greeter){
        return Props.create(HelloWorldActor.class, name, greeter);
    }

    public HelloWorldActor(String name, ActorRef greeter){
        this.name = name;
        this.greeterRef = greeter;
    }

    @Override
    public void onReceive(Object msg) throws Exception {

        if (msg == Msg.HELLO) {

            logger.info("In HelloWorld Actor");

            greeterRef.tell(msg, getSelf());

            //waitForTimeInSeconds(5);

            logger.info("After call to greeter.tell");

            logger.info("Received message: {}", msg.toString());
        }
        else if (msg == Msg.RECEIVED) {

            logger.info("Response from greeter received!");

        }
        else if (msg == Msg.GOODBYE) {

            System.out.println("Done");

            getContext().stop(getSelf());
        }
        else
            unhandled(msg);
    }


    private void waitForTimeInSeconds(int seconds) {

        try {

            TimeUnit.SECONDS.sleep(seconds);
        }
        catch(InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
