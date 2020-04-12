@SmokeTest
Feature: Tests for JavaGuru website. Common elements

  Scenario: 000. Check redirection by registration button
	When I navigate to JAVAGURU.LV website
	And I see 'MENU_JAVAGURU_LOGO' element on the 'P100' page
	And I click on 'P100_REGISTER_BUTTON' element on the 'P100' page
	Then I see following elements on the 'P101' page
	  | ELEMENT                | LABEL                  |
	  | MENU_JAVAGURU_LOGO     |                        |
	  | P101_SCREEN_TITLE      | @/REGISTER/RU/TITLE    |
	  | P101_SCREEN_SUBTITLE_1 | @/REGISTER/RU/SUBTITLE |
	  | P101_JG_BONUSES_CARD_1 | @/REGISTER/RU/PROS/0/1 |
	  | P101_JG_BONUSES_CARD_2 | @/REGISTER/RU/PROS/1/2 |
	  | P101_JG_BONUSES_CARD_3 | @/REGISTER/RU/PROS/2/3 |
	  | P101_JG_BONUSES_CARD_4 | @/REGISTER/RU/PROS/3/4 |
	And I see text '@/REGISTER/RU/TITLE' for 'P101_SCREEN_TITLE' element on the 'P101' page

  Scenario: 001. Check RU portal version - header, menu, footer elements
	When I navigate to JAVAGURU.LV website
	Then I see following elements on the 'P100' page
	  | ELEMENT                | LABEL                  |
	  | MENU_JAVAGURU_LOGO     |                        |
	  | MENU_COURSES_OPTION    | @/MENU/RU/COURSES      |
	  | MENU_TIMETABLE_OPTION  | @/MENU/RU/TIMETABLE    |
	  | MENU_REGISTER_OPTION   | @/MENU/RU/REGISTRATION |
	  | MENU_FAQ_OPTION        | @/MENU/RU/FAQ          |
	  | MENU_NEWS_OPTION       | @/MENU/RU/NEWS         |
	  | MENU_ABOUT_US_OPTION   | @/MENU/RU/ABOUT_US     |
	  | HEADER_EMAIL_LABEL     |                        |
	  | HEADER_PHONE_LABEL     |                        |
	  | HEADER_MOODLE_BUTTON   | @/HEADER/RU/MOODLE     |
	  | HEADER_LANGUAGE_BUTTON | @/HEADER/RU/LANGUAGE   |
#	  | HEADER_QUESTION_BUTTON | @/HEADER/RU/question   |
	  | HEADER_ICON_FACEBOOK   |                        |
	  | HEADER_ICON_LINKEDIN   |                        |
	  | FOOTER_ADDRESS_ICON    |                        |
	  | FOOTER_ADDRESS_LABEL   | @/FOOTER/RU/ADDRESS    |
	  | FOOTER_EMAIL_ICON      |                        |
	  | FOOTER_EMAIL_LABEL     | @/FOOTER/RU/EMAIL      |
	  | FOOTER_PHONE_ICON      |                        |
	  | FOOTER_PHONE_LABEL     | @/FOOTER/RU/PHONE      |
	  | FOOTER_JAVAGURU_LOGO   |                        |
	  | FOOTER_PULS_LV_COUNTER |                        |
	  | FOOTER_TOP_LV_COUNTER  |                        |
	  | FOOTER_ICON_FACEBOOK   |                        |
	  | FOOTER_ICON_LINKEDIN   |                        |

  Scenario: 002. Check RU portal version - menu EXPAND
	When I navigate to JAVAGURU.LV website
	Then I see following elements on the 'P100' page
	  | ELEMENT               | LABEL                  |
	  | MENU_JAVAGURU_LOGO    |                        |
	  | MENU_COURSES_OPTION   | @/MENU/RU/COURSES      |
	  | MENU_TIMETABLE_OPTION | @/MENU/RU/TIMETABLE    |
	  | MENU_REGISTER_OPTION  | @/MENU/RU/REGISTRATION |
	  | MENU_FAQ_OPTION       | @/MENU/RU/FAQ          |
	  | MENU_NEWS_OPTION      | @/MENU/RU/NEWS         |
	  | MENU_ABOUT_US_OPTION  | @/MENU/RU/ABOUT_US     |
	And I click on 'MENU_COURSES_OPTION' element on the 'P100' page
	And I see 'MENU_COURSES_SUBMENU' element on the 'P100' page
	And I click on 'MENU_ABOUT_US_OPTION' element on the 'P100' page
	And I see 'MENU_ABOUT_US_SUBMENU' element on the 'P100' page