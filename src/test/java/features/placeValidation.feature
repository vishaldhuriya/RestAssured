Feature: Validating Place API

@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
		Given Add Place Payload with "<name>" "<language>" "<address>"
		When user call "AddPlaceAPI" with "POST" http request
		Then the API call got Success with status code 200
		And "status" in responce body is "OK"
		And "scope" in responce body is "APP"
		And place_id created maps to "<name>" using "getPlaceAPI"
		
Examples:
		|name | language | address  |
		|AAA  | English  | Kandali  |
#		|BBB  | French   | Amravati |

@DeletePlace
Scenario: Verify if Delete Place Functionality is working

		Given DeletePlace Payload
		When user call "deletePlaceAPI" with "POST" http request
		Then the API call got Success with status code 200
		And "status" in responce body is "OK"