# User Guide For Duke.java

###  **The Ultimate companion**

## Features 

### 1.Store tasks  


#### Usage
To keep track of the tasks and their completion statuses. 

There are three types of tasks:

1.Todo

2.Deadline

3.Event

## `todo` 
Tasks that do not have any deadlines.
Example of usage: 

`todo homework`

Expected outcome:

`[T][X]homework`

## `deadline` 
Store tasks that have a deadline

The deadline can be entered in 2 formats:

1.Only the date :date:.

Example of usage: 

`deadline homework/by 10-01-2019`

Expected outcome:

` [D][X]homework(by: 10 Oct 2019 )`


2.The date and the time.
Example of usage: 

`deadline homework/by 20-10-2019 18:00`

Expected outcome:

` [D][X]homework(by: 18:00 20 Oct 2019 )`

## `event`
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
