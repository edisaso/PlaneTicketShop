package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Message 
{
	@Id
	@GeneratedValue
private int id;

		public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

		private String senderName;
		private String email;
		private String message;
		private String price;
		private String date;
		private String maxR;
		private String maxD;
		public Message() 
		{
		}

		public Message(String senderName, String email, String message, String price, String date, String maxR, String maxD) 
		{	
				this.senderName = senderName;
				this.email = email;
				this.message = message;
				this.price = price;
				this.date = date;
				this.maxR = maxR;
				this.maxD = maxD;
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
	
		@Override
		public String toString() 
		{
				return senderName+" написа: "+message;
		}
}

