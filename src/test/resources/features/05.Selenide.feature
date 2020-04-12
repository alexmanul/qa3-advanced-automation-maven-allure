@SELENIDE
Feature: QA3 - Advanced Automation. Selenide

  Scenario Outline: SLN01. User can set browser properties and navigate to URL
	When I set following properties and navigate to '<URL>' url
	  | KEY        | VALUE      |
	  | PROPERTY   | <PROPERTY> |
	  | VALUE      | <VALUE>    |
	  | RESOLUTION | <SCREEN>   |

	Examples:
	  | PROPERTY                      | VALUE              | SCREEN  | URL               |
	  | selenide.browser              | Chrome             | 360x720 | https://www.ss.lv |
	  | chromeoptions.mobileEmulation | deviceName=Nexus 5 | 600x800 | https://www.1a.lv |