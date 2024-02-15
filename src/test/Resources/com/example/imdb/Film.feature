Feature: Film
  The credentials of a film can be stored and accessed

  Scenario: Manager wants to check film
    Given a film exists
    When manager sets film id to 201
    Then film id will be 201