package toDoList;
import java.time.LocalDate;

public class Task implements java.io.Serializable{
	private String title;
	private String status;
	private LocalDate date;
	
	/*public Task(String title, String status, LocalDate date) {
		this.title = title;
		this.status = status;
		this.date = date;
	}*/
	
	//Returns the value of title, date and status
	public String getTitle() {
		return title;
	}
	public String getStatus() {
		return status;
	}
	public LocalDate getDate() {
		return date;
	}
	
   //sets the value of title, date and status	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
