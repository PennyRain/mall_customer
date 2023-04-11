package pennyrain.mall_customer;

	public class Customer {
		
		private Integer customerID;
	    private String gender;
	    private Integer age;
	    private Integer annualIncome;
	    private Integer spendingScore;
	
	    public Customer(int customerID, String gender, int age, int annualIncome, int spendingScore) {
	        this.customerID = customerID;
	        this.gender = gender;
	        this.age = age;
	        this.annualIncome = annualIncome;
	        this.spendingScore = spendingScore;
	    }
	
		public Integer getCustomerID() {
			return customerID;
		}
	
		public void setCustomerID(Integer customerID) {
			this.customerID = customerID;
		}
	
		public String getGender() {
			return gender;
		}
	
		public void setGender(String gender) {
			this.gender = gender;
		}
	
		public Integer getAge() {
			return age;
		}
	
		public void setAge(Integer age) {
			this.age = age;
		}
	
		public Integer getAnnualIncome() {
			return annualIncome;
		}
	
		public void setAnnualIncome(Integer annualIncome) {
			this.annualIncome = annualIncome;
		}
	
		public Integer getSpendingScore() {
			return spendingScore;
		}
	
		public void setSpendingScore(Integer spendingScore) {
			this.spendingScore = spendingScore;
		}
	    
	}
