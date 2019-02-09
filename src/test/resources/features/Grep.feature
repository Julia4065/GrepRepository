Feature: As a user
  I want to extract from file all mentioned words
  So that new file is generated with extracted words te

  Scenario: 1-File contains words from grep line if they exist in the initial file
    Given I have an initial file "C:\D\IdeaProjectForGitHub\Grep\readFromThisFile.txt" with the following content
      | hello my dear friend |
      | test disk napkin     |
      | hello Max            |
      | dear Alex            |
    When I execute the following command line "java -jar C:\D\IdeaProjectForGitHub\Grep\out\artifacts\Grep_jar\Grep.jar -f "C:\D\IdeaProjectForGitHub\Grep\readFromThisFile.txt" -w "dear|mother|hello" -o "C:\D\IdeaProjectForGitHub\Grep\writeInThisFile.txt""
    Then I receive a new file "C:\D\IdeaProjectForGitHub\Grep\writeInThisFile.txt" with extracted words
      | hello my dear friend |
      | hello Max            |
      | dear Alex            |

  Scenario: 2-If initial file does not contain words from grep line then result file is empty
    Given I have an initial file "C:\D\IdeaProjectForGitHub\Grep\readFromThisFile.txt" with the following content
      | hello my dear friend |
      | test disk napkin     |
    When I execute the following command line "java -jar C:\D\IdeaProjectForGitHub\Grep\out\artifacts\Grep_jar\Grep.jar -f "C:\D\IdeaProjectForGitHub\Grep\readFromThisFile.txt" -w "mother|hey" -o "C:\D\IdeaProjectForGitHub\Grep\writeInThisFile.txt""
    Then I receive an empty file "C:\D\IdeaProjectForGitHub\Grep\writeInThisFile.txt"


  Scenario: 3-If words that needs to be grepped are not pointed then result file is empty
    Given I have an initial file "C:\D\IdeaProjectForGitHub\Grep\readFromThisFile.txt" with the following content
      | hello my dear friend |
      | test disk napkin     |
    Then I receive exception when I execute the following command line "java -jar C:\D\IdeaProjectForGitHub\Grep\out\artifacts\Grep_jar\Grep.jar -f "C:\D\IdeaProjectForGitHub\Grep\readFromThisFile.txt" -w " " -o "C:\D\IdeaProjectForGitHub\Grep\writeInThisFile.txt""


