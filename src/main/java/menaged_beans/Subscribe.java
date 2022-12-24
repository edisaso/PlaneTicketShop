package menaged_beans;

import java.io.Serializable;
import java.util.List;

import dao.Database;
import entities.Subscription;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("subscribe")
@ConversationScoped
public class Subscribe implements Serializable
{	
		private List<String> subscribtionCategories;
		private List<String> subscribtionSubCategories;
	
		private String selectedCategory;
		private String selectedSubCategory;
		private String email;
	
		@Inject
		private Conversation conversation;

		@PostConstruct
		private void init()
		{
				subscribtionCategories = 
									Database.getSubscribtionCategories();
				selectedCategory =
									subscribtionCategories.get(0);
		}
	
		// Get и Set Методи	
		public String getEmail()
		{
				return email;
		}

		public List<String> getSubscribtionCategories()
		{
				return subscribtionCategories;
		}

		public void setSubscribtionCategories(
										List<String> subscribtionCategories)
		{
				this.subscribtionCategories = 
																	subscribtionCategories;
		}

		public List<String> getSubscribtionSubCategories()
		{
				return subscribtionSubCategories;
		}

		public void setSubscribtionSubCategories(
								List<String> subscribtionSubCategories)
		{
				this.subscribtionSubCategories = 
															subscribtionSubCategories;
		}

		public String getSelectedCategory()
		{
				return selectedCategory;
		}

		public void setSelectedCategory(
																String selectedCategory)
		{
				this.selectedCategory = selectedCategory;
		}

		public String getSelectedSubCategory()
		{
				return selectedSubCategory;
		}

		public void setSelectedSubCategory(
														String selectedSubCategory)
		{
				this.selectedSubCategory = selectedSubCategory;
		}

		public void setEmail(String email)
		{
				this.email = email;
		}

		// Начало на разговор с удължен живот
		public void start()
		{		
    		if( conversation.isTransient() &&
			!FacesContext.getCurrentInstance().isPostback())
	    	{
	    			conversation.begin();	    		
	    	}
		}	
	 
		// Край на разговор с удължен живот
		public void end() 
		{
			 	if(!conversation.isTransient())
		 		{
					 	conversation.end();
		 		}
		}

		// Навигация между различните стъпки
		public String goToStep1() 
		{ 
				 return "subscribeStep1?faces-redirect=true";
		}
	
		public String goToStep2() 
		{
		 		// Инициализира не на подкатегориите
		 		subscribtionSubCategories = 
						Database.getSubscribtionSubCategories(
																			selectedCategory);
			 	selectedSubCategory = 
											subscribtionSubCategories.get(0);
		 
			 	return "subscribeStep2?faces-redirect=true";
		}
	    
		public String goToStep3() 
		{
			 	return "subscribeStep3?faces-redirect=true";
		}

		public String goToFinish() 
		{
				Subscription subscription = new 
							Subscription(selectedCategory, 
														selectedSubCategory, email);
				Database.addSubscription(subscription);
		
				end();	// Ако се извика без активен 
								// разговор, ще хвърли изключение

				return "subscribeStep4?faces-redirect=true";
		}
}

