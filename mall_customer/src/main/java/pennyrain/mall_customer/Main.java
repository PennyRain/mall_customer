package pennyrain.mall_customer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
        List<Customer> customers = new ArrayList<Customer>();

        // Import data file
        try (BufferedReader br = new BufferedReader(new FileReader("../asset/Mall_Customers.csv"))) {
            String line;
            br.readLine(); // Ignore header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int customerID = Integer.parseInt(values[0]);
                String gender = values[1];
                int age = Integer.parseInt(values[2]);
                int annualIncome = Integer.parseInt(values[3]);
                int spendingScore = Integer.parseInt(values[4]);

                Customer customer = new Customer(customerID, gender, age, annualIncome, spendingScore);
                customers.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Call K-means algorithm
        int k = 3;
        KMeans.classifyCustomers(customers, k);
    }
	
}
