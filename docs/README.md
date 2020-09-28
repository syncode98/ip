Duke.java is a desktop app for to manage tasks. Though the utilisation of Command Line Interface(CLI) users
can quickly add,delete,find tasks or update the completion status of a task.

## Table of Contents

* [Initialisation](https://github.com/syncode98/ip#initialisation) <br>
   - [Setup](https://github.com/syncode98/ip#setup) <br>
   - [Load Data](https://github.com/syncode98/ip#load-data) <br>
   - [Begin](https://github.com/syncode98/ip#begin) <br> 
* [Features](https://github.com/syncode98/ip#features) <br>
    - [Store tasks](https://github.com/syncode98/ip#store-tasks) <br>
       - [Todo](https://github.com/syncode98/ip#add-todotodo) <br>
       - [Deadline](https://github.com/syncode98/ip#add-deadlinedeadline) <br>
       - [Event](https://github.com/syncode98/ip#add-eventevent) <br>
    - [Error detection](https://github.com/syncode98/ip#error-detection) <br>
    - [List Tasks](https://github.com/syncode98/ip#list-all-tasks-list) <br>
    - [Find Tasks](https://github.com/syncode98/ip#find-tasks-find) <br>
    - [Delete Tasks](https://github.com/syncode98/ip#delete-tasks-delete) <br>
    - [Complete tasks](https://github.com/syncode98/ip#5-complete-tasks-done) <br>
    - [Exit Application](https://github.com/syncode98/ip#5-exit-application-bye) <br>
    - [Save Application](https://github.com/syncode98/ip#6-save-data) <br>
* [Command Summary](https://github.com/syncode98/ip#command-summary) <br>
## Initialisation

### Setup
Upon opening the application, the chatbot will enquire about the user's name.

<pre><code>-------------------------------------------------------------<br>
Muthu <br>
------------------------------------------------------------- <br>
Hello! I'm Mike!<br>
Enter your name:<br>
------------------------------------------------------------- </code></pre>

After the user enters the name, the chatbot then greets the user.

<pre><code>-------------------------------------------------------------<br>
Hello Muthu !<br>
-------------------------------------------------------------</code></pre>


#### Load data
If the user had no data stored previously, the user can proceed to the [begin](https://github.com/syncode98/ip#begin) 
section.

If the user had stored any task previously, the application shows the tasks to the user.

<pre><code>-------------------------------------------------------------<br>
Here are the tasks!<br>
D | 0 | exam(by: 10 Oct 2019 )<br>
D | 0 | homework(by: 18:00 20 Oct 2019 )<br>
E | 0 | charity match(at:  10 May 2019 )<br>
E | 1 | marathon(at: 18:00 5 May 2019 )<br>
E | 0 | marathon(at: 18:00-20:00 9 Sep 2019 )<br>
-------------------------------------------------------------</code></pre>

The format of the task displayed is as follows: <br>
Type of task - Completion status - Task <br>
 * **The type of task**: T for todo, D for deadline and E for event. <br>
 * **Completion Status**:The digit represents the completion status of the task. 0 represents a task that has not been completed.Conversely, a 1 shows a task that has been completed.
 * **Task** The task itself.

Subsequently, the user can choose to keep the tasks or to delete them.

<pre><code>-------------------------------------------------------------<br>
Do you want to keep the contents of the file?<br>
-------------------------------------------------------------</code></pre>

If a file does not exist to store the data, then the program creates one for the user automatically.

If the directory does not exist, then the program creates the directory and the data file for the user .

<pre><code>-------------------------------------------------------------<br>
The directory does not exist!<br>
-------------------------------------------------------------<br>
A new directory has been created at C:\Users\Muthu Kumar\Desktop\cs2113T-project\data\data.txt<br>
-------------------------------------------------------------</code></pre>



#### Begin 
After the loading  the data, the user can now start entering the tasks.
<pre><code>-------------------------------------------------------------<br>
Alright Muthu , What can I do for you?<br>
-------------------------------------------------------------</code></pre>

## Features 

### Store tasks  

#### Usage
To keep track of the tasks and their completion statuses. 

There are three types of tasks:

* [Todo](https://github.com/syncode98/ip#add-todotodo) <br>
* [Deadline](https://github.com/syncode98/ip#add-deadlinedeadline) <br>
* [Event](https://github.com/syncode98/ip#add-eventevent) <br>

## Todo:`todo` 

Tasks that do not have any deadlines.

Example of usage: 

`todo homework`

Expected outcome:

`[T][X]homework`

## Deadline:`deadline` 

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

## Event:`event`

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

## List all tasks: `list`

Shows all the tasks in the program.

Example of usage:
`list`

Expected outcome:

<pre><code>-------------------------------------------------------------
Here are the tasks in your list:<br>
1.[D][X]exam(by: 10 Oct 2019 )<br>
2.[D][X]homework(by: 18:00 20 Oct 2019 )<br>
3.[E][X]charity match(at:  10 May 2019 )<br>
4.[E][X]marathon(at: 18:00 5 May 2019 )<br>
5.[E][X]marathon(at: 18:00-20:00 9 Sep 2019 )<br>
-------------------------------------------------------------
</code></pre>

## Find tasks: `find`

Finds a task for the user

Example of usage:
`find marathon`

Expected outcome:

<pre><code>-------------------------------------------------------------
Here are the matching tasks in your list:<br>
1. [E][X]marathon(at: 18:00 5 May 2019 )<br>
2. [E][X]marathon(at: 18:00-20:00 9 Sep 2019 )
-------------------------------------------------------------</code></pre>

## Delete tasks: `delete`

Deletes the task for which the user had given the number for.

Example of usage:
`delete 4`

Expected outcome:

<pre><code>-------------------------------------------------------------<br>
Noted. I've removed this task:<br>
[E][⨉]marathon(at: 18:00 5 May 2019 )<br>
Now you have 4 tasks in the list.<br>
-------------------------------------------------------------</code></pre>

## Complete tasks: `done`

Updates the completion status for a task that has been completed.

Example of usage:
`done 4`

Expected outcome:

<pre><code>-------------------------------------------------------------<br>
Nice! I've marked this task as done:<br>
[E][✓]marathon(at: 18:00 5 May 2019 )<br>
-------------------------------------------------------------</code></pre>

## Exit application: `bye`

Exits the application.

Example of usage:
`bye`

Expected outcome:

<pre><code>-------------------------------------------------------------<br>
Noted. I've removed this task:<br>
[E][✓]marathon(at: 18:00 5 May 2019 )<br>
Now you have 4 tasks in the list.<br>
-------------------------------------------------------------</code></pre>

## Save data

The data in the files is saved automatically after the user adds/edits a task.

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
