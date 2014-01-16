package com.homeinc.akka.first.model;

/**
 * Created by lit3hawk on 15/01/2014.
 */
public enum Msg {
    HELLO("Hello!"),
    RECEIVED("Received"),
    GOODBYE("Good Bye!");

    private String nameAsString;

    private Msg(String nameAsString){
        this.nameAsString = nameAsString;
    }

    @Override
    public String toString() {
        return this.nameAsString;
    }
}
