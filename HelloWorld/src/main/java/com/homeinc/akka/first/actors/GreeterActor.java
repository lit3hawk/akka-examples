package com.homeinc.akka.first.actors;

import akka.actor.Props;
import akka.actor.UntypedActor;
import com.homeinc.akka.first.model.Msg;

/**
 * Created by lit3hawk on 16/01/2014.
 */
public class GreeterActor extends UntypedActor{

    private final String actorName;

    public GreeterActor(String actorName){
        this.actorName = actorName;
    }

    public static Props mkProps(String name){
        return Props.create(GreeterActor.class, name);
    }

    @Override
    public void onReceive(Object msg) throws Exception {

        if (msg == Msg.HELLO) {
            System.out.println("Inside Greeter");

            getSender().tell(Msg.RECEIVED, getSelf());
        }
        else
            unhandled(msg);
    }
}
