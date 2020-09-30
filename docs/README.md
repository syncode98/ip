Duke.java is a desktop app for to manage tasks. Though the utilisation of Command Line Interface(CLI) users
can quickly add,delete,find tasks or update the completion status of a task. The application then concurrently 
transfers the data to an external file, through which users would be able to load the tasks in the subsequent sessions.

## Table of Contents

* <a href ="#Initialisation">Initialisation</a> <br>
   - <a href ="#setup">Setup</a> <br>
   - <a href ="#load">Load Data</a> <br> 
   - <a href ="#begin">Start</a> <br> 
* <a href ="#features">Features</a> <br>
    - <a href ="#store">Store Tasks</a> <br>
       - <a href ="#todo">Todo</a>  <br>
       - <a href ="#deadline">Deadline</a>  <br>
       - <a href ="#event">Event</a>  <br>
    - <a href ="#list">List Tasks</a>  <br>
    - <a href ="#find">Find Tasks</a>  <br>
    - <a href ="#delete">Delete Tasks</a>  <br>
    - <a href ="#done">Complete Tasks</a>  <br>
    - <a href ="#clear">Clear Tasks</a>  <br>    
    - <a href ="#bye">Exit Application</a>  <br>
    - <a href ="#save">Save Application</a>  <br>
    - <a href ="#error">Error Detection</a>  <br>
* <a href ="#command">Command Summary</a>  <br>
## Initialisation  <a name="Initialisation"></a>

### Setup <a name="setup"></a>
Upon opening the application, the chatbot will enquire about the user's name.

<pre><code>-------------------------------------------------------------
Hello! I'm Mike!
Enter your name:
------------------------------------------------------------- </code></pre>

After the user enters the name, the chatbot then greets the user.
<pre><code>-------------------------------------------------------------
Muthu
-------------------------------------------------------------
Hello Muthu !
-------------------------------------------------------------</code></pre>


#### Load data <a name="load"></a>
If the user had no data stored previously, the user can proceed to the <a href ="#begin">start</a> 
section. Otherwise, the application will show the pending tasks that the user had saved in the previous session.

<pre><code>-------------------------------------------------------------
Here are the tasks!
D | 0 | exam(by: 10 Oct 2019 )
D | 0 | homework(by: 18:00 20 Oct 2019 )
E | 0 | charity match(at:  10 May 2019 )
E | 1 | marathon(at: 18:00 5 May 2019 )
E | 0 | marathon(at: 18:00-20:00 9 Sep 2019 )
-------------------------------------------------------------</code></pre>

The format of the task displayed is as follows: <br>
Type of task | Completion status | Task <br>
 * **The type of task**: T for todo, D for deadline and E for event. <br>
 * **Completion Status**: '' represents a task that has not been completed.Conversely, a 1 shows a 
 task that has been completed.
 * **Task**: The task itself.

Subsequently, the user can choose to keep the tasks or to delete them.

<pre><code>-------------------------------------------------------------
Do you want to keep the contents of the file?
-------------------------------------------------------------</code></pre>

If the directory does not exist, then the program creates the relevant directories and the data file for the user .

<pre><code>-------------------------------------------------------------
The directory does not exist!
-------------------------------------------------------------
A new directory has been created at C:\Users\Muthu Kumar\Desktop\cs2113T-project\data\data.txt
-------------------------------------------------------------</code></pre>

In the event that the user has the required directory but does not have the file to store the tasks, the file will
be created automatically for the user.

#### Start <a name="begin"></a>
After  loading  the data, the user can now start entering the tasks.
<pre><code>-------------------------------------------------------------
Alright Muthu , What can I do for you?
-------------------------------------------------------------</code></pre>

## Features <a name="features"></a>

### Store tasks  <a name="store"></a>

#### Usage
To keep track of the tasks and their completion statuses. 

There are three types of tasks:

* <a href ="#todo">Todo</a>
* <a href ="#deadline">Deadline</a>
* <a href ="#event">Event</a>

The tasks are created in the following manner:<br>
<pre><code>-------------------------------------------------------------
[Type of Task][Completion Status] Task
Now you have N tasks in the list.
-------------------------------------------------------------</code></pre>
The format of the task displayed is as follows: <br>
Type of task | Completion status | Task <br>
 * **The type of task**: T for todo, D for deadline and E for event. <br>
 * **Completion Status**: `[⨉]`represents a task that has not been completed.Conversely, a `[✓]` shows a task that has been completed.
 * **Task**: The task itself.
 * **N**: The number of tasks in the list.
 
## Todo:`todo` <a name="todo"></a>

Tasks that do not have any deadlines.<br>
Format:`todo DESCRIPTION` <br>
Example: Create a task about a homework that has to be done.
<pre><code>-------------------------------------------------------------
todo homework
-------------------------------------------------------------
[T][⨉]homework
-------------------------------------------------------------
Now you have 1 tasks in the list.
-------------------------------------------------------------
</code></pre>

## Deadline:`deadline` <a name="deadline"></a>

Tasks that have a deadline

The deadline can be entered in 2 formats:
* <a href ="#deadline_date">Deadline with the date</a>  <br>
* <a href ="#deadline_date_time">Deadline with the date and time</a> 
 
**Deadline with the date:**<br> <a name="deadline_date"></a>
Format:`deadline DESCRIPTION /by dd-mm-yy ` <br>
* Take note of the spacing in between the keyword `deadline` and the description of the task.
* Also, take note of the spacing between the term `/by` and the date.

Example: Create a task for a homework which has to be submitted by 10-01-2019
<pre><code>-------------------------------------------------------------
deadline homework/by 10-01-2019
-------------------------------------------------------------
[D][⨉]homework(by: 10 Oct 2019 )
-------------------------------------------------------------
Now you have 2 tasks in the list.
-------------------------------------------------------------</code></pre>
    
**Deadline with the date and time:**<br> <a name="deadline_date_time"></a>
Format:`deadline DESCRIPTION /by dd-mm-yy HH:mm` <br>
* Take note of the spacing in between the keyword `deadline` and the description of the task.
* Take note of the spacing between the term `/by` and the date.
* Task note of the spacing in between date and time. <br>
    
Example: Create a task about the homework which has to be submitted by 10-01-2019 at 18:00
<pre><code>-------------------------------------------------------------
deadline homework/by 20-10-2019 18:00
-------------------------------------------------------------
[D][⨉]homework(by: 18:00 20 Oct 2019 )
-------------------------------------------------------------
Now you have 3 tasks in the list.
-------------------------------------------------------------</code></pre>

## Event:`event` <a name="event"></a>

Tasks that represent events.

The event can be entered in 3 formats:<br>
* <a href ="#event_date">Event with the date</a>  <br>
* <a href ="#event_date_time">Event with the date and time</a> 
* <a href ="#event_date_two_time">Event with the date,start time and endtime</a> 

**Event with the date:** <br> <a name="event_date"></a>
Format:`event DESCRIPTION /at dd-mm-yyyy`  <br>
* Take note of the spacing in between the keyword `event` and the description, and the spacing
in between the preposition `/at` and the date.<br>

Example: Create an event about a charity match which occurs on 10-05-2019 
<pre><code>-------------------------------------------------------------
event charity match/at 10-05-2019
-------------------------------------------------------------
[E][⨉]charity match(at:  10 May 2019 )
-------------------------------------------------------------
Now you have 4 tasks in the list.
-------------------------------------------------------------
</code></pre>

**Event with the date and time:**<br> <a name="event_date_time"></a>
Format:`event DESCRIPTION /at dd-mm-yyyy HH:mm` <br>
* Take note of the spacing in between the keyword `event` and the description, the spacing
in between the preposition `/at` and the date and the spacing between the date and the time.

Example: Create an event about a marathon which occurs on 05-05-2019 at 6 PM
<pre><code>-------------------------------------------------------------
event marathon/at 05-05-2019 18:00
-------------------------------------------------------------
[E][⨉]marathon(at: 18:00 5 May 2019 )
-------------------------------------------------------------
Now you have 5 tasks in the list.
-------------------------------------------------------------
</code></pre>
    
**Event with the date, Start time and End time of the event:** <a name="event_date_two_time"></a>
Format:`event DESCRIPTION /at dd-mm-yyyy HH:mm - HH:mm`<br>
* Take note of the spacing in between the keyword `event` and the description, the spacing
in between the preposition `/at` and the date and the spacing between the date and the time.
* Enter the two timings appropriately.Observe the spacing between the two timings and the `-` sign. 

Example: Create an event about a marathon which occurs on 10-05-2019 at 6 PM to 8 PM
<pre><code>-------------------------------------------------------------
event marathon/at 09-09-2019 18:00 - 20:00
-------------------------------------------------------------
[E][⨉]marathon(at: 18:00-20:00 9 Sep 2019 )
-------------------------------------------------------------
Now you have 6 tasks in the list.
------------------------------------------------------------- 
</code></pre>



## List all tasks: `list` <a name="list"></a>

Shows all the tasks in the program.<br>
Format:`list`<br>

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

Find tasks for the user.

Format:`find DESCRIPTION` <br>
Example:Find tasks that has the description.
<pre><code>-------------------------------------------------------------
find marathon 
-------------------------------------------------------------
Here are the matching tasks in your list:
1. [E][⨉]marathon(at: 18:00 5 May 2019 )
2. [E][⨉]marathon(at: 18:00-20:00 9 Sep 2019 )
-------------------------------------------------------------</code></pre>

## Delete tasks: `delete` <a name="delete"></a>

Deletes the specified task from the Application.After that, it shows the user the number of remaining
tasks left.<br>

Format:`delete INDEX`<br>
Example: Delete the 4th task in the list.
<pre><code>-------------------------------------------------------------
delete 4
-------------------------------------------------------------
Noted. I've removed this task:
[E][⨉]marathon(at: 18:00 5 May 2019 )
Now you have 4 tasks in the list.
-------------------------------------------------------------</code></pre>

## Complete tasks: `done` <a name="done"></a>

Updates the completion status for a task that has been completed.<br>
Format:`done INDEX`<br>
Example:After finishing the fourth task in the list, update its completion status.
<pre><code>-------------------------------------------------------------
done 4
-------------------------------------------------------------
Nice! I've marked this task as done:
[E][✓]marathon(at: 18:00 5 May 2019 )
-------------------------------------------------------------</code></pre>
## Clear : `clear` <a name="clear"></a>
Clear all the tasks.<br>
Format:`clear`
<pre><code>-------------------------------------------------------------
clear
-------------------------------------------------------------
All the tasks have been cleared!
-------------------------------------------------------------</code></pre>

## Exit application: `bye` <a name="bye"></a>
Exits the application.<br>
Format:`bye`
<pre><code>-------------------------------------------------------------
bye
-------------------------------------------------------------
Bye.Hope to see you again soon!
-------------------------------------------------------------</code></pre>

## Save data <a name="save"></a>

The data in the files is saved automatically after the user adds/edits a task.

## Error detection <a name="error"></a>

If the user enters an inappropriate command, then they will be highlighted
through statements such as these:

Example: User enters an invalid index for features `delete` or `done`.
<pre><code>-------------------------------------------------------------
Task number must be in between 1 and 100.
-------------------------------------------------------------
</code></pre>

Example: User enters an invalid date for an event.
<pre><code>-------------------------------------------------------------
☹ Enter a valid date for event.
-------------------------------------------------------------
</code></pre>

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
Clear tasks|`clear`
Exit application|`bye`
