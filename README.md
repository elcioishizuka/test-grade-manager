# Grade REST API 
This is a REST API created in Java 15 with Spring Boot 2.4.2 to manage grades in a school.

This API uses Lombok version 1.18.16, Mapstruct version 1.4.1.Final and H2 database.



## Description
This API has most of CRUD implementation and provides the following features:

- To register students, answer keys and student's answer sheets;
- To check final grade (average) for each student;
- To list final grades and approved student per subject.



## How to use
It is recommended to use an IDE (like IntelliJ IDEA) and Postman to make the requests to the API through the end-points.

No additional setting is required to run the API, but H2 database settings may be edited in src -> main -> resources -> application-sample.properties file.

Following inputs are required to use this API:

- Student class must contain the following information:
  - studentId - Student ID. This input must be unique for each student;
  - name - Student name;
  - lastName - Student last name;
- Answer class must contain the following information:
  - question - Question identification. Must be unique
  - answer - Answer of the question. This is a string and accepts any kind of answer. E.g. "A", "B", "C"; "1", "2", "3"; etc
  - weight - This is the weight of the question over the total grade. 
- TestInfo class is the information of the test must contain the following information:
  - subject - This information is provided in a enum. This enum can be modified according to user preference at Subject enums class. Currently hardcoded options are : PORTUGUES("Português"), MATEMATICA("Matemática"), HISTORIA("História"), GEOGRAFIA("Geografia"), FISICA("Física"), QUIMICA("Química"), EDUCACAOFISICA("Educação Física") and INGLES("Inglês").
  - testNumber - This is the number of the test. This is a string and accepts any kind of identification. E.g. "A", "B", "C"; "1", "2", "3"; etc
- Answekey class is the answer key for each subject and test number. It must contain the following information:
  - TestInfo - Explained before;
  - List of Answer - Answer was explained before. List of Answer must be provided between []
- TestAnswer class is the test answer sheet information of each student. It must contain the following information:
  - Student - Explained before;
  - TestInfo - Explained before;
  - List of Answer - Answer was explained before. List of Answer must be provided between []



## Restrictions

This API has following restrictions:

- Grades will be always between 0 and 10
- Maximum distinct students quantity will be 100
- The weight for each question will be an integer bigger than 0
- Approved students will have final grades higher or equal to 7
- Inputs and output are provided in JSON format



## Exceptions

Following exceptions may happen:

- StudentAlreadyCreated - This exception happens when the post method is requested with an existing student ID.

- AnswerAlreadyRegisteredToThisStudent - This exception happens when the post method is requested with an existing test answer sheet with same student ID, subject and test number.
- AnswerkeyAlreadyRegistered - This exception happens when the post method is requested with an existing answer key with same subject and test number.
- StudentQuantityExceeded - This exception happens when more than 100 different student ID is being saved in DB.







## Enpoints and payloads
### Endpoints
**Action** | **Endpoint** | **Method**
---------- | ------------ | ----------
Insert all students | /api/v1/students                                       | POST
List student | /api/v1/students | GET
 Insert answer key                                            | /api/v1/answerkeys                                     | POST       
 List all answer keys                                         | /api/v1/answerkeys                                     | GET        
 List answer keys by subject                                  | /api/v1/answerkeys/{subject}                           | GET        
 List answer keys by subject and by test number               | /api/v1/answerkeys/{subject}/{testnumber}              | GET        
Delete answer key | /api/v1/answerkeys/{id} | DELETE 
Insert test answer | /api/v1/testanswers | POST 
List all test answers | /api/v1/testanswers | GET 
List test answers | /api/v1/testanswers/{subject}                          | GET 
List test answers by student ID and by subject | /api/v1/testanswers/{studentId}/{subject} | GET 
List test answers by student ID and by subject and by test number | /api/v1/testanswers/{studentId}/{subject}/{testNumber} | GET 
 Delete test answer                                           | /api/v1/testanswers/{id}                               | DELETE     
 List all grades                                              | /api/v1/grades                                         | GET        
 List grades by subject and by student ID                     | /api/v1/grades/{subject}/{studentId}                   | GET        
 List all final grades                                        | /api/v1/finalgrades                                    | GET        
List final grades by student ID | /api/v1/finalgrades/{studentId} | GET 
List final grades by subject and by status (* {status} options are "approved" or "reproved") | /api/v1/finalgrades/{studentId}/{status} | GET 



This API can be testes with Postman or similar apps, making requests to the following local host:
  http://localhost:8080/



### Payload Examples:
Following are some examples of payload and responses for some HTTP methods:

#### POST (student) - Request
    {
        "name" : "José",
        "lastName": "Silva",
        "studentId" : "125"
    }

#### POST (student) - Response
    {
        "id": 1,
        "studentId": "125",
        "name": "José",
        "lastName": "Silva"
    }

#### POST (answer key) - Request
    {
        "testInfo": {
            "subject" : "FISICA",
            "testNumber" : "1"
        },
        "answers": [
            {
            "question": "1",
            "answer": "A",
            "weight": 1
            },
            {
            "question": "2",
            "answer": "B",
            "weight": 1
            },
            {
            "question": "3",
            "answer": "A",
            "weight": 1
            },
            {
            "question": "4",
            "answer": "B",
            "weight": 1
            },
            {
            "question": "5",
            "answer": "A",
            "weight": 1
            },
            {
            "question": "6",
            "answer": "B",
            "weight": 1
            },
            {
            "question": "7",
            "answer": "A",
            "weight": 1
            },
            {
            "question": "8",
            "answer": "B",
            "weight": 1
            },
            {
            "question": "9",
            "answer": "A",
            "weight": 1
            },
            {
            "question": "10",
            "answer": "B",
            "weight": 1
            }        
        ]
    }

#### POST (test answer) - Request
    {
        "student": {
            "name" : "Maria",
            "lastName": "dos Santos",
            "studentId" : "124"
        },
        "testInfo":{
            "subject": "FISICA",
            "testNumber": "1"
        },
        "answers": [
            {
            "question": "1",
            "answer": "A",
            "weight": 1
            },
            {
            "question": "2",
            "answer": "B",
            "weight": 1
            },
            {
            "question": "3",
            "answer": "B",
            "weight": 1
            },
            {
            "question": "4",
            "answer": "B",
            "weight": 1
            },
            {
            "question": "5",
            "answer": "A",
            "weight": 1
            },
            {
            "question": "6",
            "answer": "E",
            "weight": 1
            },
            {
            "question": "7",
            "answer": "A",
            "weight": 1
            },
            {
            "question": "8",
            "answer": "B",
            "weight": 1
            },
            {
            "question": "9",
            "answer": "A",
            "weight": 1
            },
            {
            "question": "10",
            "answer": "B",
            "weight": 1
            }        
        ]
    }

#### GET (test answer) - Response (List test answers by student ID and by subject and by test number) - In this example - localhost:8080/api/v1/testanswers/124/fisica/1

    [
        {
            "testAnswersId": 4,
            "student": {
                "id": 4,
                "studentId": "124",
                "name": "Maria",
                "lastName": "dos Santos"
            },
            "testInfo": {
                "testInfoId": 7,
                "subject": "FISICA",
                "testNumber": "1"
            },
            "answers": [
                {
                    "answerId": 61,
                    "question": "1",
                    "answer": "A",
                    "weight": 1
                },
                {
                    "answerId": 62,
                    "question": "2",
                    "answer": "B",
                    "weight": 1
                },
                {
                    "answerId": 63,
                    "question": "3",
                    "answer": "B",
                    "weight": 1
                },
                {
                    "answerId": 64,
                    "question": "4",
                    "answer": "B",
                    "weight": 1
                },
                {
                    "answerId": 65,
                    "question": "5",
                    "answer": "A",
                    "weight": 1
                },
                {
                    "answerId": 66,
                    "question": "6",
                    "answer": "E",
                    "weight": 1
                },
                {
                    "answerId": 67,
                    "question": "7",
                    "answer": "A",
                    "weight": 1
                },
                {
                    "answerId": 68,
                    "question": "8",
                    "answer": "B",
                    "weight": 1
                },
                {
                    "answerId": 69,
                    "question": "9",
                    "answer": "A",
                    "weight": 1
                },
                {
                    "answerId": 70,
                    "question": "10",
                    "answer": "B",
                    "weight": 1
                }
            ]
        }
    ]

#### GET (grade) - Response (List all grades)

    [
        {
            "gradeId": 1,
            "testInfo": {
                "testInfoId": 10,
                "subject": "FISICA",
                "testNumber": "1"
            },
            "student": {
                "id": 7,
                "studentId": "124",
                "name": "Maria",
                "lastName": "dos Santos"
            },
            "grade": 8.0
        },
        {
            "gradeId": 2,
            "testInfo": {
                "testInfoId": 11,
                "subject": "FISICA",
                "testNumber": "2"
            },
            "student": {
                "id": 8,
                "studentId": "124",
                "name": "Maria",
                "lastName": "dos Santos"
            },
            "grade": 5.0
        },
        {
            "gradeId": 3,
            "testInfo": {
                "testInfoId": 12,
                "subject": "FISICA",
                "testNumber": "3"
            },
            "student": {
                "id": 9,
                "studentId": "124",
                "name": "Maria",
                "lastName": "dos Santos"
            },
            "grade": 6.0
        },
        {
            "gradeId": 4,
            "testInfo": {
                "testInfoId": 13,
                "subject": "FISICA",
                "testNumber": "1"
            },
            "student": {
                "id": 10,
                "studentId": "123",
                "name": "José",
                "lastName": "Silva"
            },
            "grade": 9.0
        },
        {
            "gradeId": 5,
            "testInfo": {
                "testInfoId": 14,
                "subject": "FISICA",
                "testNumber": "2"
            },
            "student": {
                "id": 11,
                "studentId": "123",
                "name": "José",
                "lastName": "Silva"
            },
            "grade": 7.0
        },
        {
            "gradeId": 6,
            "testInfo": {
                "testInfoId": 15,
                "subject": "FISICA",
                "testNumber": "3"
            },
            "student": {
                "id": 12,
                "studentId": "123",
                "name": "José",
                "lastName": "Silva"
            },
            "grade": 8.0
        }
    ]

#### GET (final grade) - Response (List all final grades)

    [
        {
            "finalGradeId": 1,
            "subject": "FISICA",
            "student": {
                "id": 25,
                "studentId": "124",
                "name": "Maria",
                "lastName": "dos Santos"
            },
            "finalGrade": 6.333333333333333,
            "status": "REPROVED"
        },
        {
            "finalGradeId": 2,
            "subject": "FISICA",
            "student": {
                "id": 32,
                "studentId": "123",
                "name": "José",
                "lastName": "Silva"
            },
            "finalGrade": 8.0,
            "status": "APPROVED"
        }
    ]

#### GET (final grade) - Response (List final grades by student ID) - In this example - localhost:8080/api/v1/finalgrades/123

    [
        {
            "finalGradeId": 4,
            "subject": "FISICA",
            "student": {
                "id": 58,
                "studentId": "123",
                "name": "José",
                "lastName": "Silva"
            },
            "finalGrade": 8.0,
            "status": "APPROVED"
        }
    ]

#### GET (final grade) - Response (List final grades by subject and by status) - In this example - localhost:8080/api/v1/finalgrades/fisica/approved

    [
        {
            "finalGradeId": 6,
            "subject": "FISICA",
            "student": {
                "id": 78,
                "studentId": "123",
                "name": "José",
                "lastName": "Silva"
            },
            "finalGrade": 8.0,
            "status": "APPROVED"
        }
    ]
