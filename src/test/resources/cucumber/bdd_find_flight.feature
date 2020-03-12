@cucumber
  Feature: Book flight tickets

    Users would like to book flight tickets

    Scenario: Find flights from Jakarta to Surabaya
      Given that User open the home page
      Then he should see the home page with list of icon buttons and one of them is 'flight icon'
      When he search flight from Jakarta to Surabaya
      Then he should find list of flights scheduled related

    Scenario: Fill in information related for booking flight ticket
      Given that the user chooses one of the flight schedules available
      Then he should be directed to the booking summary
      When he confirm the flight
      Then he should fill the information related details in the fill in details page


# Note: this BDD has not been applied to this script program


