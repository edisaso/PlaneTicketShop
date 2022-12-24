package menaged_beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dao.Database;
import entities.Message;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class MessagesFilter implements Serializable
{	
		@EJB
		Database db;

		private String filterText;
		private String filterBy="част от име на авиокомпания";
	
		private String[] filterByOptions = 
				{"име на авиокомпания", "част от име на авиокомпания","Всички билети"};
		 List<Message> filteredMessages = new ArrayList<Message>();;
		
		
		
		public String getFilterText() 
		{
				return filterText;
		}

		public void setFilterText(String filterText) 
		{
				this.filterText = filterText;
		}

		public String getFilterBy() 
		{
				return filterBy;
		}

		public void setFilterBy(String filterBy) 
		{
				this.filterBy = filterBy;
		}

		// fileredMessage има само get метод, защото
		// е предвидено само да се получава списъка 
		public List<Message> getFilteredMessages() 
		{
				return filteredMessages;
		}
	
		// fileredByOptions има само get метод, защото
		// е предвидено само да се получава списъка 
		public String[] getFilterByOptions() 
		{
				return filterByOptions;
		}

		public String filter() 
		{
				if(filterBy.equals(filterByOptions[0]))
						filteredMessages = 
								db.getMessagesFilteredByName(filterText);
		
				if(filterBy.equals(filterByOptions[1]))
						filteredMessages = 
				db.getMessagesFilteredByNameWithLike(filterText);
		if(filterBy.equals(filterByOptions[2]))
			filteredMessages = 
			db.getAllMessages();
				return "";
		}

		public String deleteMessage(int id)
		{
				db.deleteMessage(id);
				filter();
		
				return "";
		}
}
