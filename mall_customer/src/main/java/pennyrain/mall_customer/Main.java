package pennyrain.mall_customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		List<Customer> customers = new ArrayList<Customer>();

		// Import data file
		try (Stream<String> stream = Files.lines(Paths.get(
				"C:/Users/Admin/Desktop/Btl_KPDL/mall_customer/mall_customer/src/main/java/pennyrain/asset/Mall_Customers.csv"))) {
			stream.skip(1) // Ignore header line
					.forEach(line -> {
						String[] values = line.split(",");
						int customerID = Integer.parseInt(values[0]);
						String gender = values[1];
						int age = Integer.parseInt(values[2]);
						int annualIncome = Integer.parseInt(values[3]);
						int spendingScore = Integer.parseInt(values[4]);
						Customer customer = new Customer(customerID, gender, age, annualIncome, spendingScore);
						customers.add(customer);
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Customers size: " + customers.size());

		int k = 3;
		KMeans.classifyCustomers(customers, k);
	}

}
