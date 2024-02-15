Feature: Actors
  Actor entities can be be initialised and store details

  Scenario: An actor has an ID
    Given an actor
    When I set the actor's id to 201
    Then Actor's id is 201

  Scenario: An actor has a first name
    Given an actor
    When I set the actor's first name to "John"
    Then Actor's first name is "John"