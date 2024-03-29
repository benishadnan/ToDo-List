package toDoList;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner; 
import java.time.LocalDate;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;



public class TaskManager {
	
	//Create a TreeMap with project as the key and an Array list of task class as the value.
	ArrayList<Task> TaskList;
	public TreeMap<String, ArrayList<Task>> toDoList;
	LocalDate date = LocalDate.now();
	Scanner userInput = new Scanner(System.in);
	String filename = "demoFile.ser";
	
	public TaskManager() {
		TaskList = new ArrayList<>();
		toDoList = new TreeMap<>();
		
		//Set the key value in TreeMap as predefined projects
		toDoList.put("project1",new ArrayList<>());
		toDoList.put("project2",new ArrayList<>());
		toDoList.put("project3",new ArrayList<>());	
		readFile();
	}
	
	//Prints the first menu
	 public void startMenu() {
		System.out.println("---------------------------");
		System.out.println("<< Welcome to ToDo List");
		System.out.println("---------------------------");
		System.out.println("Today is " + date);
		System.out.println("<< You have X tasks to do " + "and Y tasks are done");
		System.out.println("<< Pick an option");
		System.out.println("---------------------------");
		System.out.println("<< (1) Show task List (by date or project)");
		System.out.println("<< (2) Add new Task");
		System.out.println("<< (3) Edit Task (Update, mark as done, remove)");
		System.out.println("<< (4) Save and Quit");
		System.out.println("---------------------------");
					
		mainMenu();		
	 }

	 //Prints the main menu giving the user option to edit, add, delete  and sort tasks 
	public void mainMenu() {
		//initial value of command set to 1
		int command = 1;
		
		while(command !=0)
		{
			try {
				String userCommand;
				command = userInput.nextInt();
				userInput.nextLine();
				switch(command) {
				//Sort toDoList by projects or date
				case 1:{ 
						 System.out.println("---------------------------");
						 System.out.println("Review the task list by date or project \nPress '1' for date or '2' for project");
						 System.out.println("___________________________");
						 int reader = userInput.nextInt();
						 userInput.nextLine();
						 
						 if (reader==1) {
							 
							 System.out.println("Enter the date in format yyyy-MM-dd ");
							 userCommand = userInput.nextLine();
							 LocalDate date = LocalDate.parse(userCommand);
							 showAllTasksByDate(date);	
						 }
						 
						 else if(reader==2) {
							 System.out.println("Enter a project to sort the tasks by\nOptions are \n \tproject1 \n \tproject2 \n \tproject3");
							 userCommand = userInput.nextLine();
							 
							 	if(toDoList.containsKey(userCommand)) {
								 showAllTasksByProject(userCommand);
							 	}
						 }
						 else System.out.println("Enter the correct option");
				}
				break;
				
				//Add new task
				case 2:{ System.out.println("---------------------------");
						 System.out.println("Enter a project to add the task \nOptions are \n \tproject1 \n \tproject2 \n \tproject3");
						 System.out.println("---------------------------");
						 userCommand = userInput.nextLine();
						
						 if(toDoList.containsKey(userCommand)) {
							 addNewTask(userCommand);
						 }
						 else System.out.println("Enter a valid option");	 
						 
						 }
				break;
				
				//Edit task (Update, mark as done, remove)
				case 3:{ System.out.println("______________________________");
						 System.out.println("Enter a option \n 1 for removing a task from ToDoList \n 2 for updating status of task \n 3 for updating an existing task");
						 System.out.println("______________________________");
						 int reader = userInput.nextInt();
						 userInput.nextLine();
						 
						 //Remove task from toDoList
						 if(reader==1) { 
							 System.out.println("Enter a project to remove the task from \nOptions are \n \tproject1 \n \tproject2 \n \tproject3");
							 System.out.println("---------------------------");
							 userCommand = userInput.nextLine();
						
							 if(toDoList.containsKey(userCommand)) {
								 removeTask(userCommand);
							 }
							 
						 }
						 
						 //Change status of a task
						 else if(reader==2) {
							 System.out.println("Enter a project to see all tasks in it \nOptions are \n \tproject1 \n \tproject2 \n \tproject3");
							 System.out.println("---------------------------");
							 userCommand = userInput.nextLine();
						
							 if(toDoList.containsKey(userCommand)) {
								 changeTaskStatus(userCommand);
							 }
						 }
						 
						 //Update value of a task
						 else if(reader==3) {
							 System.out.println("Enter a project to see all tasks in it \nOptions are \n \tproject1 \n \tproject2 \n \tproject3");
							 System.out.println("---------------------------");
							 userCommand = userInput.nextLine();
						
							 if(toDoList.containsKey(userCommand)) {
								 updateTask(userCommand);
							 }
						 }
						
						 else System.out.println("Enter a valid option");
				}
				break;
				
				//Save to File
				case 4:
					saveFile();
				break;
				//Exit the toDoList
				case 0: System.out.println("Have a great day!");
						System.exit(0);
				break;
				
				default: System.out.println("Invalid command. Enter again or press '0' to quit");
				break;
				}
			}
			catch(java.util.InputMismatchException ime) {
				System.out.println("You must enter a valid number between 0-4");
				userInput.nextLine();
			
			}
			
		}
		
	}
	
	public void showAllTasksByDate(LocalDate date) {
		 for(Map.Entry<String, ArrayList<Task>> entry : toDoList.entrySet()) {
			 String key = entry.getKey();
			 TaskList = entry.getValue();
			 for(Task temp: TaskList) {
				 if((temp.getDate().compareTo(date)) ==0) {
				 System.out.println(key + "-->"+ temp.getTitle()+"\t"+temp.getStatus()+ "\t"+temp.getDate() +"\t");
				 }
			 }
			
		 }
		 returnToMainMenu();
	}
	
	//prints a list of all tasks within a project
	public void showAllTasksByProject(String projectName) {
		TaskList = toDoList.get(projectName);
	 	for(Task temp: TaskList) {
	 		System.out.println(temp.getTitle()+"\t"+temp.getStatus()+ "\t"+temp.getDate() +"\t");
	 		}
	 	returnToMainMenu();
	 	
	}
	
	//Calls the Task class to set values of title, date and status and then save that object to TreeMap
	public void addNewTask(String projectName) {
		
		String userCommand;
		String title;
		String status;
		
		System.out.println("Enter title of task");
		title = userInput.nextLine();
		
		System.out.println("Enter status of task");
		status = userInput.nextLine();
		
		System.out.println("Enter date of task in format yyyy-MM-dd");
		userCommand = userInput.nextLine();
		LocalDate date = LocalDate.parse(userCommand);
		
		addTaskToTodoList(projectName,title,date,status);
		
		returnToMainMenu();
	}
	
	public void addTaskToTodoList(String projectName,String title,LocalDate date,String status)
	{
		Task task = new Task();
		task.setTitle(title);
		task.setDate(date);
		task.setStatus(status);
		
		
		TaskList = toDoList.get(projectName);
		TaskList.add(task);
		toDoList.put(projectName, TaskList);
	}
	
	
	//remove an object by searching TreeMap first by key and then iterating on Array list of object Tasks
	public void removeTask(String projectName) {
		
		showAllTasksByProject(projectName);
		System.out.println("Enter the title of task that you wish to remove");
		String reader = userInput.nextLine();
		TaskList = toDoList.get(projectName);
		Iterator<Task> itr = TaskList.iterator();
	 	
		while(itr.hasNext()) {
	 		Task temp = (Task) itr.next();
	 		if(temp.getTitle().equals(reader)) {
	 			itr.remove();
	 			System.out.println("Task Removed");
	 		}
	 	}
		showAllTasksByProject(projectName);
		returnToMainMenu();
	}
	
	//change the status of task in a project
	public void changeTaskStatus(String projectName) {
		 String reader; 
		 showAllTasksByProject(projectName);
		 System.out.println("Enter the title of task that you wish to change the status of");
		 reader = userInput.nextLine();
		 TaskList = toDoList.get(projectName);
		 Iterator<Task> itr = TaskList.iterator();
		 
		 while(itr.hasNext()) {
			 Task temp = (Task) itr.next();
			 if(temp.getTitle().equals(reader)) {
				 System.out.println("Enter the new status of task");
				 reader=userInput.nextLine();
				 temp.setStatus(reader);
			 }
		 }
		 showAllTasksByProject(projectName);
		 returnToMainMenu();
	}
	
	//Update value of existing task in toDoList
	public void updateTask(String projectName) {
		 String reader; 
		 showAllTasksByProject(projectName);
		 System.out.println("Enter the title of task that you wish to update");
		 reader = userInput.nextLine();
		 TaskList = toDoList.get(projectName);
		 Iterator<Task> itr = TaskList.iterator();
		 
		 while(itr.hasNext()) {
			 Task temp = (Task) itr.next();
			 if(temp.getTitle().equals(reader)) {
				 System.out.println("Enter the new title");
				 reader= userInput.nextLine();
				 temp.setTitle(reader);
				 System.out.println("Enter the new status");
				 reader= userInput.nextLine();
				 temp.setStatus(reader);
				 System.out.println("Enter the new date in format yyyy-MM-dd");
				 reader= userInput.nextLine();
				 LocalDate date = LocalDate.parse(reader);
				 temp.setDate(date);	 
			 }
		 }
		 showAllTasksByProject(projectName);
		 returnToMainMenu();
	}
	//returns to the main menu where user can choose another option to do
	public void returnToMainMenu() {
		System.out.println("\n Press 1 to Perform another operation \n Or 0 to quit the ToDoList ");
		if(userInput.nextInt()==1) {
			startMenu();
		}
		else 
		System.exit(0);
	}
	
	
	// writing into file
	public void saveFile() {
		  try
	        {    
	            //Saving of object in a file 
	            FileOutputStream file = new FileOutputStream(filename); 
	            ObjectOutputStream out = new ObjectOutputStream(file); 
	              
	            // Method for serialization of object 
	            out.writeObject(toDoList); 
	              
	            out.close(); 
	            file.close(); 
	              
	            System.out.println("ToDoList saved"); 	  
	        } 	          
	        catch(IOException ex) 
	        { 
	            System.out.println("IOException is caught"); 
	        } 
		  returnToMainMenu();
		
	}
/*		ObjectMapper mapper = new ObjectMapper();
		String example = "Hello there";
		//mapper.writeValue(new File("d:/temp/output.json"), listOfStudents);
		//ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
		 try {
			 mapper.writeValue(new FileOutputStream("test.json"), example);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }		 
	}*/
	
	public void readFile() {
		   try
	        {    
	            // Reading the object from a file 
	            FileInputStream file = new FileInputStream(filename); 
	            ObjectInputStream in = new ObjectInputStream(file); 
	              
	            // Method for deseriallization of object 
	            toDoList = (TreeMap<String, ArrayList<Task>>)in.readObject(); 
	              
	            in.close(); 
	            file.close(); 
	        } 
	          
	       catch(IOException ex) 
	        { 
	            System.out.println("IOException is caught"); 
	        }
		   catch(ClassNotFoundException ex) 
	        { 
	            System.out.println("ClassNotFoundException is caught"); 
	        } 
	}
	
	/*public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}*/
}
