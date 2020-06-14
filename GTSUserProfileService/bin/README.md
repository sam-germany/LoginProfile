
Table of Contents

• Basic Overview

• Why should i use it

• Deployment

• Installation

• Usage

• Security Perspective


1.	Basic Overview

• Hier is the Trainer module, we uses Spring framework, Spring Boot, Spring Security, Mysql, Hibernate ,JPA and Postman.

2.	What should i use

•   JDK 1.8

•   Spring Tool Suit 4    

•   MySQL 8.0         https://www.youtube.com/watch?v=GIRcpjg-3Eg

•   Postman 7



Install MySQL and create table 

 
                   create table users 
                      (
                      user_id              int NOT NULL,
                      email       varchar(256)  NOT NULL,
                      password    varchar(256)  NOT NULL,
                      name        varchar(256) NOT NULL,
                      lastname   varchar(256) NOT NULL,
                      mobile    varchar(256) NOT NULL,
                      isactive  varchar(256) NOT NULL,
                      PRIMARY KEY(email),
                      UNIQUE (user_id)
                                  );
                     


• id should be unique • email should be unique

Pull the Project from Github and run it 

Go to Github https://github.com/kabitagorai2210/GTSProfileService and pull the project in the Spring Tool Suit. 

In spring Tool Suit right click on the project name -> Maven -> Update project Then manven will fetch the requeired Jars from the centeral repository. 

 In the Project open application.properties -> change the credentials for database

 Go to project name right click -> Run as -> Spring boot app

At this time MySQL should also be running so that we can insert data into Table through Psotman calls
Postman calls

• Create User through Postman
•  id , email must be unique

Request
--------
 choose :   POST   localhost:9999/users/create          


         {
            "user_id" : "1",
            "email" : "ddd@hotmail.de",
            "password" : "bbbbb",
            "name" : "sunny",
            "lastname" :"sss",
            "mobile" : "123456",
            "isactive" : "true"
                                }


Response
--------         
        {
        "user_id": 1,
        "email": "ddd@hotmail.de",
        "encryptedpassword": "$2a$10$DU9tCRhphx4MGvUjQYHl1e568yWDyp5Ms8Hsewb/3cToB/SL.TDKe",
        "name": "sunny",
        "lastname": "sss",
        "mobile": "123456",
        "isactive": true
                                }
     
     
• Update User through Postman    
• while updating id, email must be same we can not update them only name and password can be updated    
         
         
choose :   PUT   localhost:9999/users/update/1
Request
--------
         
         {
          "user_id": 1,
          "email": "ddd@hotmail.de",
          "password" : "bbbbb",
          "name" : "nnnn",
          "lastname" :"sss",
          "mobile" : "123456",
          "isactive" : "true"
                            }
         
 
Response
--------        
      {
       "user_id": 1,
       "email": "bbb@hotmail.de",
       "encryptedpassword": "$2a$10$vzaWq4BDpW4L3UdFzOMQheP0lb1XGVLT2zxFAobUkGEAHTWfZbTT2",
       "name": "nnnn",
       "lastname": "sss",
       "mobile": "123456",
       "isactive": true
                           }
         
• Retrive all users through Postman          
• choose : GET      localhost:9999/users/getAll       
         
         
• Retrive single user by Id  through Postman          
• choose : GET       localhost:9999/users/getUser/1      



• Delete single user by Id  through Postman     
• choose : DELETE    localhost:9999/users/delete/1  


     
         
