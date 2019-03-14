# ToDo-List
Task Manager

A basic toDoList that shows user the tasks grouped by project. The tasks are shown with title, date and status, status can be done, undone and inprocess. The user is provided with a simple to use textbase interface where user can add, delete and update task. User can also sort the tasks by project and date. 
The text base interface uses numbers as options for user to choose what s/he wants to do. 
The codse is structured as three classes namely task, taskManager and main class. Main class calls an instance of taskManager and calls the start menu. 
Data structure is TreeMap where project is the key and is set when TaskManager is instantiated. The value is an arrayu list of tasks. The task class has setters and getters method that are used in the add and update function. 
The TaskManager handles all the functionality that user sees in the main menu. there are four options to choose from 1.Sort tasks by project or date: 2.Add new tasks 3.Edit the task which further has three option 3.1- Update 3.2-mark as done 3.3-remove task and option 4 is save the tasklist. 
The functionality of all these tasks is handled in seperate methods outside the main menu method and every operation has a method for it. 
The program quits by calling system.exit(0)
