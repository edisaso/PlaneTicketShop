package menaged_beans;

import java.io.Serializable;

import dao.Database;
import entities.Message;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class Contacts implements Serializable
{
	@EJB
	Database db;

	private int id;
		private String senderName;
		private String email;
		private String message;
	private String price;
	private String date;
	private String maxR;
	private String maxD;
		
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getSenderName() 
		{
				return senderName;
		}
	
		public void setSenderName(String senderName) 
		{
				this.senderName = senderName;
		}
	
		public String getEmail() 
		{
				return email;
		}
	
		public void setEmail(String email) 
		{
				this.email = email;
		}
	
		public String getMessage() 
		{
				return message;
		}
	
		public void setMessage(String message) 
		{
				this.message = message;
		}
		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getMaxR() {
			return maxR;
		}

		public void setMaxR(String maxR) {
			this.maxR = maxR;
		}

		public String getMaxD() {
			return maxD;
		}

		public void setMaxD(String maxD) {
			this.maxD = maxD;
		}
			
			public String send()
			{	
					
							// Създаване на ново съобщение
							Message msg = 
										new Message(senderName, email, message, price, date, maxR, maxD);
					
							if(id==0)
							{
								//Добавяне на съобщението към базата данни
								db.addMessage(msg);
							}
							else
							{
								db.editFlight(id, msg);
							}
						
							// Добавяме променливата faces-redirect,
							// която указва на JSF да смени URL адреса
							// след пренасочване със sent
							return "sent?faces-redirect=true";
					
				}
			
	

		public String init(int id)
		{
			System.out.println("init id: "+id);
			
			if(id!=0)
			{
				Message msg = db.getMessage(id);
				senderName = msg.getSenderName();
				email = msg.getEmail();
				message = msg.getMessage();
				price = msg.getPrice();
				date = msg.getDate();
				maxR= msg.getMaxR();
				maxD = msg.getMaxD();
				this.id = id;
			}
			
			return "contacts";
		}

		
}