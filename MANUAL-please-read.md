# BankAtm
A simple bank application (with roles of admin & user)

## How-to-Use
- extract SimpleBank.zip. You should find folder 'SimpleBank'
- inside folder 'SimpleBank', you should find app 'SimpleBank.jar' and folder 'dbbank'
- open console/terminal from your pc/laptop
- locate this folder 'SimpleBank' in your console/terminal
- to run SimpleBank.jar, type "java -jar SimpleBank.jar"
- make sure you have Java installed in your pc/laptop
- you should find a table contain pairs of user-id and password
- use one of them to login and try app SimpleBank

## Techs
- SimpleBank.jar is app for console mode
- databases in app SimpleBank.jar are saved in files in folder 'dbbank'
- databases are saved in text files (not sql) but still in RDB (Relational Database) model by using unique identifier like account number, user id, and transaction id called 'sequence'
- calling join operation like in sql is still possible eventhough it has to be manually coded in java
- LinkedList() is used as a medium to CRUD data
- LinkedList() is chosen because of its efficiency in random access memory/data index compared to ArrayList()
- when you run app SimpleBank.jar, you may find user password unmasked if you use linux/mac os

## App Files
- BankApp.java --> work like a home page in a website. It shows some users login and their pass to be tested and direct users to BankLogin.java
- BankLogin.java --> checking validity of users login. If they are valid, users will be redirected to page-menu that fits their role
- BankMenuAdmin.java --> serve all admin features that can be used by users with role of admin
- BankMenuCustomer.java --> serve all customer features that be used by users with role of customer
- BankKuDat.java --> all database requests are processed here. Any page that want to access database with their features has to make object of BankKuDat and use its available methods to request database they need.

## Note/WARNING!
>> if you want to edit database file, make sure to leave a space of empty row after editing the file
- if you miss the step above, new data will be created next to the last data line, causing app SimpleBank having difficulty in reading the database you edit after
>> moving any files from its origin will cause error because it makes the app's algorithm can not locate the app's files properly
- joni99, adm007, & cust00 are users who have many transaction records if you want to test feature number '4)check transaction record' in customer main menu


## Unfinish work
- in folder dbbank/dbbank_userlog, you will find files with format name 'userlog_domestic-[accNo]-[userId].txt'
- those files are useles at the moment
- they are intended to record transaction coming from other app SimpleBank.jar but this feature has not developed yet