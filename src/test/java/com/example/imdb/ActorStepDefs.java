package com.example.imdb;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class ActorStepDefs {
    Actor actor;

    //1st test
    @Given("an actor")
    public void anActor(){
        actor=new Actor();
    }

    @When("I set the actor's id to {int}")
    public void setActorID(int actorID) {
        actor.setActorID(actorID);
    }

    @Then("Actor's id is {int}")
    public void checkActorID(int actorID){
        assertEquals(actorID, actor.getActorID());
    }

    @When("I set the actor's first name to {string}")
    public void setActorFirstName(String name){
        actor.setFirstName(name);
    }

    @Then("Actor's first name is {string}")
    public void checkActorFirstName(String name){
        assertEquals(name,actor.getFirstName());
    }

}
