package com.homeinc.akka.first;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.homeinc.akka.first.actors.GreeterActor;
import com.homeinc.akka.first.actors.HelloWorldActor;
import com.homeinc.akka.first.model.Msg;

import java.util.concurrent.TimeUnit;

/**
 * Created by lit3hawk on 15/01/2014.
 */
public class MainApplication {

    public static void main(String[] args) {

        ActorSystem system = ActorSystem.create("MySystem");

        ActorRef greeterActor = system.actorOf(GreeterActor.mkProps("MyGreeterActor"), "greeter");

        ActorRef helloWorldActor = system.actorOf(HelloWorldActor.mkProps("MyHelloWorldActor", greeterActor), "HelloWorld");

        helloWorldActor.tell(Msg.HELLO, ActorRef.noSender());

        try {

            TimeUnit.SECONDS.sleep(5);
        }
        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }

        helloWorldActor.tell(Msg.GOODBYE, ActorRef.noSender());

    }
}
