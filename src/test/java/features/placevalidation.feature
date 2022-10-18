Feature: Validating place APIs

  @AddPlace @Regression
  Scenario Outline: : Verify if place is successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "Post" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceAPI"

    Examples: 
      | name    | language | address             |
      | AAHouse | English  | World cross center  |
      | BBHouse | French   | Indian cross center |

  @DeletePlace
  Scenario: Verify if Delete functionality is working
    Given Delete Payload
    When user calls "deletePlaceAPI" with "delete" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
