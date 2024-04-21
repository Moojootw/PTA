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
public class SisimsData {
	
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
	//makes a category data entity with extra details such as item count
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
			this.items = category.getItems().stream().map(CategoryItemDetail::new).collect(Collectors.toList());
			itemCount = category.getItemCountInCategory();
		}
	}

	@Data
	@NoArgsConstructor
	public static class TransactionData {
		private Long transactionId;
		private Long transactionItemCount;
		private Double transactionTotal;
		private String transactionDate;
		private String transactionDetails;
		private EmployeeTransactionInfo employee;
		private List<TransactionItemsData> transactionItems;
		
		public TransactionData(Transaction transaction) {
			transactionId = transaction.getTransactionId();
			transactionItemCount = transaction.getTransactionItemCount();
			transactionTotal = transaction.getTransactionTotal();
			transactionDate = transaction.getTransactionDate();
			transactionDetails = transaction.getTransactionDetails();
			employee = new EmployeeTransactionInfo(transaction.getEmployee());
		}
	}

		@Data
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
		public static class TransactionItemsData {
		    private Long transactionId;
		    private Long employeeId;
		    private List<ItemDetail> items;

		    public TransactionItemsData(List<TransactionItems> transactionItemsList) {
		        if (!transactionItemsList.isEmpty()) {
		            Transaction transaction = transactionItemsList.get(0).getTransaction();
		            this.transactionId = transaction.getTransactionId();
		            this.employeeId = transaction.getEmployee().getEmployeeId();
		            this.items = transactionItemsList.stream().map(ItemDetail::new).collect(Collectors.toList());
		        }
		    }
		}
		
		@Data
		@NoArgsConstructor
		//show relivent information on who is doing a transaction
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
		//consice information on item details when getting infomation from a category
		public static class CategoryItemDetail {
		    private Long itemId;
		    private Long itemQuantity;
		    private Long itemShelfLimit;
		    private String categoryName;

		    public CategoryItemDetail(Item item) {
		        this.itemId = item.getItemId();
		        this.itemQuantity = item.getItemQuantity();
		        this.itemShelfLimit = item.getItemShelfLimit();
		        this.categoryName = item.getCategory().getCategoryName();
		    }
		}
	
	@Data
	@NoArgsConstructor
	//verbose item information with an item get request
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
		}
	}
	
}
