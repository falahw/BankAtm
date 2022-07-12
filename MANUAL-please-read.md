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
- databases are saved in text files, not sql/non-sql database
- LinkedList() is used as a medium to CRUD data
- LinkedList() is chosen because of its efficiency in random access memory/data indexes compared to ArrayList()
- you may find user password unmasked if you use linux/mac os

## Note
>>> if you want to edit database file, make sure to leave a space of empty row after editing the file <<<
- if you miss the step above, new data will be created at the last line after last data, causing app SimpleBank having difficulty in reading the database you edit after
>>> moving any files from its origin will cause error because it makes the app's algorith can not locate the app's files properly <<<
- joni99, adm007, & cust00 are users who have many transaction records if you want to test feature number '4)check transaction record' in customer main menu


## Unfinish work
- in folder dbbank/dbbank_userlog, you will find files with name format 'userlog_domestic-[accNo]-[userId].txt'
- those files are useles at the moment
- they are intended to record transaction coming from other app SimpleBank.jar but this feature has not developed yet