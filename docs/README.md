Duke.java is a desktop app for to manage tasks. Though the utilisation of Command Line Interface(CLI) users
can quickly add,delete,find tasks or update the completion status of a task.

## Table of Contents

* <a href ="#Initialisation">Initialisation</a> <br>
   - <a href ="#setup">Setup</a> <br>
   - <a href ="#load">Load Data</a> <br> 
   - <a href ="#begin">Begin</a> <br> 
* <a href ="#features">Features</a> <br>
    - <a href ="#store">Store Tasks</a> <br>
       - <a href ="#todo">Todo</a>  <br>
       - <a href ="#deadline">Deadline</a>  <br>
       - <a href ="#event">Event</a>  <br>
    - <a href ="#error">Error Detection</a>  <br>
    - <a href ="#list">List Tasks</a>  <br>
    - <a href ="#find">Find Tasks</a>  <br>
    - <a href ="#delete">Delete Tasks</a>  <br>
    - <a href ="#done">Complete Tasks</a>  <br>
    - <a href ="#bye">Exit Application</a>  <br>
    - <a href ="#save">Save Application</a>  <br>
* <a href ="#command">Command Summary</a>  <br>
## Initialisation  <a name="Initialisation"></a>

### Setup <a name="setup"></a>
Upon opening the application, the chatbot will enquire about the user's name.

<pre><code>-------------------------------------------------------------<br>
Hello! I'm Mike!<br>
Enter your name:<br>
------------------------------------------------------------- </code></pre>

After the user enters the name, the chatbot then greets the user.

<pre><code>-------------------------------------------------------------<br>
Hello Muthu !<br>
-------------------------------------------------------------</code></pre>


#### Load data <a name="load"></a>
If the user had no data stored previously, the user can proceed to the [begin](https://github.com/syncode98/ip#begin) 
section. Otherwise, the application will show the pending tasks that the user had saved in the previous session.

<pre><code>-------------------------------------------------------------<br>
Here are the tasks!<br>
D | 0 | exam(by: 10 Oct 2019 )<br>
D | 0 | homework(by: 18:00 20 Oct 2019 )<br>
E | 0 | charity match(at:  10 May 2019 )<br>
E | 1 | marathon(at: 18:00 5 May 2019 )<br>
E | 0 | marathon(at: 18:00-20:00 9 Sep 2019 )<br>
-------------------------------------------------------------</code></pre>

The format of the task displayed is as follows: <br>
Type of task | Completion status | Task <br>
 * **The type of task**: T for todo, D for deadline and E for event. <br>
 * **Completion Status**: The digit represents the completion status of the task. 0 represents a task that has not been completed.Conversely, a 1 shows a task that has been completed.
 * **Task**: The task itself.

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



#### Begin <a name="begin"></a>
After the loading  the data, the user can now start entering the tasks.
<pre><code>-------------------------------------------------------------<br>
Alright Muthu , What can I do for you?<br>
-------------------------------------------------------------</code></pre>

## Features <a name="features"></a>

### Store tasks  <a name="store"></a>

#### Usage
To keep track of the tasks and their completion statuses. 

There are three types of tasks:

* [Todo](https://github.com/syncode98/ip#add-todotodo) <br>
* [Deadline](https://github.com/syncode98/ip#add-deadlinedeadline) <br>
* [Event](https://github.com/syncode98/ip#add-eventevent) <br>

## Todo:`todo` <a name="todo"></a>

Tasks that do not have any deadlines.

Example of usage: 

`todo homework`

Expected outcome:

`[T][⨉]homework`

## Deadline:`deadline` <a name="deadline"></a>

Store tasks that have a deadline

The deadline can be entered in 2 formats:

1.With date.

Example of usage: 

`deadline homework/by 10-01-2019`

Expected outcome:

` [D][⨉]homework(by: 10 Oct 2019 )`


2.With date and the time.

Example of usage: 

`deadline homework/by 20-10-2019 18:00`

Expected outcome:

` [D][⨉]homework(by: 18:00 20 Oct 2019 )`

## Event:`event` <a name="event"></a>

Tasks that have a duration as a deadline

The deadline can be entered in 3 formats:

* Date.
<
    Example of usage: 

    `event charity match/at 10-05-2019`

    Expected outcome:

    ` [E][⨉]charity match(at:  10 May 2019 )`


### 2.The date and the time.

Example of usage: 

`event marathon/at 05-05-2019 18:00`

Expected outcome:

` [E][⨉]marathon(at: 18:00 5 May 2019 )`

### 3.The date,the start and end time of the event.

Example of usage: 

`event marathon/at 09-09-2019 18:00 - 20:00`

Expected outcome:

`[E][⨉]marathon(at: 18:00-20:00 9 Sep 2019 )`

## Error detection <a name="error"></a>

If the user enters an inappropriate command, then they will be highlighted
through statements such as these:

`  Task number must be in between 1 and 100.`

## List all tasks: `list` <a name="list"></a>

Shows all the tasks in the program.

<pre><code>-------------------------------------------------------------
list
-------------------------------------------------------------
Here are the tasks in your list:
1.[D][⨉]exam(by: 10 Oct 2019 )
2.[D][⨉]homework(by: 18:00 20 Oct 2019 )
3.[E][⨉]charity match(at:  10 May 2019 )
4.[E][⨉]marathon(at: 18:00 5 May 2019 )
5.[E][⨉]marathon(at: 18:00-20:00 9 Sep 2019 )
-------------------------------------------------------------
</code></pre>

## Find tasks: `find` <a name="find"></a>

Finds a task for the user

<pre><code>-------------------------------------------------------------
find marathon 
-------------------------------------------------------------
Here are the matching tasks in your list:
1. [E][⨉]marathon(at: 18:00 5 May 2019 )
2. [E][⨉]marathon(at: 18:00-20:00 9 Sep 2019 )
-------------------------------------------------------------</code></pre>

## Delete tasks: `delete` <a name="delete"></a>

Deletes the task for which the user had given the number for.

<pre><code>-------------------------------------------------------------
delete 4
-------------------------------------------------------------
Noted. I've removed this task:
[E][⨉]marathon(at: 18:00 5 May 2019 )
Now you have 4 tasks in the list.
-------------------------------------------------------------</code></pre>

## Complete tasks: `done` <a name="done"></a>

Updates the completion status for a task that has been completed.

<pre><code>-------------------------------------------------------------
done 4
-------------------------------------------------------------
Nice! I've marked this task as done:
[E][✓]marathon(at: 18:00 5 May 2019 )
-------------------------------------------------------------</code></pre>

## Exit application: `bye` <a name="bye"></a>

Exits the application.

<pre><code>-------------------------------------------------------------
bye
-------------------------------------------------------------
Noted. I've removed this task:
[E][✓]marathon(at: 18:00 5 May 2019 )
Now you have 4 tasks in the list.
-------------------------------------------------------------</code></pre>

## Save data <a name="save"></a>

The data in the files is saved automatically after the user adds/edits a task.

## Command Summary <a name="summary"></a>
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
