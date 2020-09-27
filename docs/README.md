# User Guide For Duke.java

###  **The Ultimate companion**

## Initialisation

### Setup
Upon opening the application, the chatbot will enquire about the user's name.
![name_enquiry](https://user-images.githubusercontent.com/46095141/94368780-a7251400-0118-11eb-8396-b846f185916b.jpg)

The chatbot then greets the user.
![enterted_name](https://user-images.githubusercontent.com/46095141/94368802-d045a480-0118-11eb-92e0-7efa926ece59.jpg)

#### Load data

If the user had stored any data previously, then the user would be shown the tasks.
![load_data](https://user-images.githubusercontent.com/46095141/94368858-18fd5d80-0119-11eb-88b6-69248fbfc89b.jpg)

Subsequently, the user can choose to keep the tasks or to delete them.
![keep_contents](https://user-images.githubusercontent.com/46095141/94368887-3d593a00-0119-11eb-8112-513b54dd6dfc.jpg)

If the user had no data stored previously, the user can proceed to the begin section.

If a file does not exist to store the data, then the program creates one for the user automatically.

If the directory does not exist, then the program creates the directory and the file for the user.
![directory does not exist](https://user-images.githubusercontent.com/46095141/94369217-02f09c80-011b-11eb-8646-584f9b8e3618.jpg)

#### Begin 
After the loading  the data, the user can now start entering the tasks.
![begin](https://user-images.githubusercontent.com/46095141/94368939-890be380-0119-11eb-8166-205cb677d029.jpg)

## Features 

### 1. Store tasks  

#### Usage
To keep track of the tasks and their completion statuses. 

There are three types of tasks:

1.Todo

2.Deadline

3.Event

## Add todo:`todo` 
Tasks that do not have any deadlines.
Example of usage: 

`todo homework`

Expected outcome:

`[T][X]homework`

## Add deadline:`deadline` 
Store tasks that have a deadline

The deadline can be entered in 2 formats:

1.Only the date.

Example of usage: 

`deadline homework/by 10-01-2019`

Expected outcome:

` [D][X]homework(by: 10 Oct 2019 )`


2.The date and the time.
Example of usage: 

`deadline homework/by 20-10-2019 18:00`

Expected outcome:

` [D][X]homework(by: 18:00 20 Oct 2019 )`

## Add event:`event`
Tasks that have a duration as a deadline

The deadline can be entered in 3 formats:

### 1.Only the date.

Example of usage: 

`event charity match/at 10-05-2019`

Expected outcome:

` [E][X]charity match(at:  10 May 2019 )`


### 2.The date and the time.

Example of usage: 

`event marathon/at 05-05-2019 18:00`

Expected outcome:

` [E][X]marathon(at: 18:00 5 May 2019 )`
### 3.The date,the start and end time of the event.

Example of usage: 

`event marathon/at 09-09-2019 18:00 - 20:00`

Expected outcome:
`[E][X]marathon(at: 18:00-20:00 9 Sep 2019 )`

## Error detection

If the user enters an inappropriate command, then they will be highlighted
through statements such as these:

`  Task number must be in between 1 and 100.`

## 2. List all tasks: `list`

Shows all the tasks in the program.

Example of usage:
`list`

Expected outcome:

![list_command](https://user-images.githubusercontent.com/46095141/94369341-c1acbc80-011b-11eb-8e67-7b0f83cf2ea8.jpg)

## 3. Find tasks: `find`

Finds a task for the user

Example of usage:
`find marathon`

Expected outcome:
![find_command](https://user-images.githubusercontent.com/46095141/94369361-e143e500-011b-11eb-9f34-9cc0f9760a93.jpg)



## 4. Delete tasks: `delete`

Deletes the task for which the user had given the number for.

Example of usage:
`delete 4`

Expected outcome:
![delete_command](https://user-images.githubusercontent.com/46095141/94369404-12bcb080-011c-11eb-843f-a04752bdec25.jpg)

## 5. Complete tasks: `done`

Updates the completion status for a task that has been completed.

Example of usage:
`done 4`

Expected outcome:
![done_task](https://user-images.githubusercontent.com/46095141/94368555-35989600-0117-11eb-9180-fc8e0472261f.jpg)



## 5. Exit application: `bye`

Exits the application.

Example of usage:
`bye`

Expected outcome:

![bye_command_final](https://user-images.githubusercontent.com/46095141/94369299-75617c80-011b-11eb-89a0-956d0e32e0ba.jpg)


## 6. Save data

The data in the files are saved automatically after the user adds/edits a task.

## Command Summary
Action|Examples
------|------
Add task without deadline|`todo homework` 
Add  deadline(without time)|`deadline homework/by 10-01-2019`
Add  deadline(with time)|`deadline homework/by 10-01-2019 18:00`
Add  an event(with date)|`event charity match/at 10-05-2019`
Add an event (with start time)|`event marathon/at 05-05-2019 18:00`                           
Add an event(with end time)|`event marathon/at 09-09-2019 18:00 - 20:00`
Find tasks|`find marathon`
Delete task|`delete 4`
Complete task|`done 4`
Exit application|`bye`
