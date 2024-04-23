package sisims.controller.model;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;
import sisims.entity.Category;
import sisims.entity.Employee;
import sisims.entity.Item;
import sisims.entity.Transaction;
import sisims.entity.TransactionItems;

@Data
@NoArgsConstructor

//this is where all the transacitonal logic takes place.
//originally separated, but after needing to import both employee and item to transaction, it no longer made much sense
public class SisimsData {
	
	
	//*****There is some custom logic in these classes. I have annotated them with "//Custom" *****
	
	@Data
	@NoArgsConstructor
	//Represent employee data for REST 
	public static class EmployeeData {
		private Long employeeId;
		private String employeeFirstName;
		private String employeeLastName;
		private String employeePhone;
		private String employeeJobTitle;
		
		//converts an employee into an Data entity
		public EmployeeData(Employee employee) {
			employeeId = employee.getEmployeeId();
			employeeFirstName = employee.getEmployeeFirstName();
			employeeLastName = employee.getEmployeeLastName();
			employeePhone = employee.getEmployeePhone();
			employeeJobTitle = employee.getEmployeeJobTitle();
		}
	}
	
	@Data
	@NoArgsConstructor
	//Custom 
	//the Category table does not have an item count, it instead gets a return of the size of the item list.
	public static class CategoryData {
		
		private Long categoryId;
		private String categoryName;
		private String categoryDescription;
		private List<CategoryItemDetail> items;
		private int itemCount;
		
		public CategoryData(Category category) {
			categoryId = category.getCategoryId();
			categoryName = category.getCategoryName();
			categoryDescription = category.getCategoryDescription();
			this.items = category.getItems().stream().map(CategoryItemDetail::new).collect(Collectors.toList()); //dynamically get the items (CategoryItemDetail) in a category
			itemCount = category.getItemCountInCategory(); //Custom
		}
	}

	@Data
	@NoArgsConstructor
	public static class TransactionData {
		//Custom
		//Uses a shortened version of Employee information, that only displays an Employee's Name and ID number
		private Long transactionId;
		private Long transactionItemCount;
		private Double transactionTotal;
		private String transactionDate;
		private String transactionDetails;
		private EmployeeTransactionInfo employee;
		//private List<TransactionItemsData> transactionItems;
		
		public TransactionData(Transaction transaction) {
			transactionId = transaction.getTransactionId();
			transactionItemCount = transaction.getTransactionItemCount();
			transactionTotal = transaction.getTransactionTotal();
			transactionDate = transaction.getTransactionDate();
			transactionDetails = transaction.getTransactionDetails();
			employee = new EmployeeTransactionInfo(transaction.getEmployee()); //create an employee with EmployeeTransactionInfo details
		}
	}

		@Data
		//Custom
		public static class EmployeeTransactionInfo {
			private Long employeeId;
			private String employeeFirstName;
			private String employeeLastName;
			
			public EmployeeTransactionInfo(Employee employee) {
					employeeId = employee.getEmployeeId();
					employeeFirstName = employee.getEmployeeFirstName();
					employeeLastName = employee.getEmployeeLastName();
			}
		}
		
		@Data
		@NoArgsConstructor
		//Custom
		//Just like with Category, we only show information that is relivent for a transaction (For transaction information, you wouldn't care about it's shelf capacity)
		public static class TransactionItemsData {
		    private Long transactionId;
		    private Long employeeId;
		    private List<ItemDetail> items; //Custom see ItemDetail below

		    public TransactionItemsData(List<TransactionItems> transactionItemsList) {
		        if (!transactionItemsList.isEmpty()) {
		            Transaction transaction = transactionItemsList.get(0).getTransaction();
		            this.transactionId = transaction.getTransactionId();
		            this.employeeId = transaction.getEmployee().getEmployeeId();
		            this.items = transactionItemsList.stream().map(ItemDetail::new).collect(Collectors.toList()); //maps the list of transaction items with ItemDetail
		        }
		    }
		}
		
		@Data
		@NoArgsConstructor
		//Custom
		//constructs information that relates to a transaction such as item and price
		public static class ItemDetail {
			private Long itemId;
			private String itemName;
			private Double itemPrice;
			
			public ItemDetail(TransactionItems transactionItems) {
				itemId = transactionItems.getItem().getItemId();
				itemName = transactionItems.getItem().getItemName();
				itemPrice = transactionItems.getItem().getItemPrice();
			}
		}
		
		@Data
		@NoArgsConstructor
		//custom
		//this gives information on item detail that is more relivent for a category inquire
		public static class CategoryItemDetail {
		    private Long itemId;
		    private String itemName;
		    private Long itemQuantity;
		    private Long itemShelfLimit;
		    private String categoryName;

		    public CategoryItemDetail(Item item) {
		        this.itemId = item.getItemId();
		        this.itemName = item.getItemName();
		        this.itemQuantity = item.getItemQuantity();
		        this.itemShelfLimit = item.getItemShelfLimit();
		        this.categoryName = item.getCategory().getCategoryName();
		    }
		}
	
	@Data
	@NoArgsConstructor
	//This is the stock item data, this is what is returned when getting information about a specific item
	//I should also note that specifying an item category Id is optional and is given the default one if it doesnt have one
	public static class ItemData {
		private Long itemId;
		private Long itemQuantity;
		private Double itemPrice;
		private Boolean itemIsFastSeller;
		private Long itemShelfLimit;
		private String itemName;
		private Boolean itemIsDiscontinued;
		private String categoryName;
		private Long categoryId;
		
		public ItemData(Item item) {
			itemId = item.getItemId();
			itemQuantity = item.getItemQuantity();
			itemPrice = item.getItemPrice();
			itemIsFastSeller = item.getItemIsFastSeller();
			itemShelfLimit = item.getItemShelfLimit();
			itemName = item.getItemName();
			itemIsDiscontinued = item.getItemIsDiscontinued();
			categoryName = item.getCategory().getCategoryName();
			categoryId = item.getCategory().getCategoryId();
		}
	}
	
}
