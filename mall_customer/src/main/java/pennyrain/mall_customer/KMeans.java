package pennyrain.mall_customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class KMeans {
	public static void classifyCustomers(List<Customer> customers, int k) {
        // Initialize random centroids
        List<Integer[]> centroids = new ArrayList<Integer[]>();
        Random rand = new Random();
        for (int i = 0; i < k; i++) {
        	Integer[] centroid = {rand.nextInt(100),rand.nextInt(100)};
            centroids.add(centroid);
        }

        // Assign customers to clusters based on the nearest centroid
        List<List<Customer>> clusters = new ArrayList<List<Customer>>();
        
        // Main K-means algorithm loop
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < k; i++) {
                clusters.add(new ArrayList<Customer>());
            }

            for (Customer customer : customers) {
                int closestCentroidIndex = 0;
                double minDistance = Double.MAX_VALUE;
                for (int i = 0; i < k; i++) {
                	Integer[] centroid = centroids.get(i);
                    Double distance = Math.sqrt(Math.pow(customer.getAnnualIncome()-centroid[0],2) + Math.pow(customer.getSpendingScore()-centroid[1],2));
                    if (distance < minDistance) {
                        minDistance = distance;
                        closestCentroidIndex = i;
                    }
                }
                clusters.get(closestCentroidIndex).add(customer);
            }

            // Update centroids based on the mean of the assigned customers
            for (int i = 0; i < k; i++) {
                List<Customer> cluster = clusters.get(i);
                if (!cluster.isEmpty()) {
                    int sumAnnualIncome = 0;
                    int sumSpendingScore = 0;
                    for (Customer customer : cluster) {
                        sumAnnualIncome += customer.getAnnualIncome();
                        sumSpendingScore += customer.getSpendingScore();
                    }

                    Integer[] newCentroid = {sumAnnualIncome / cluster.size(), sumSpendingScore / cluster.size()};
                    if (newCentroid[0] != centroids.get(i)[0] || newCentroid[1] != centroids.get(i)[1]) {
                        changed = true;
                        centroids.set(i, newCentroid);
                    }
                }
            }
        }

        // Print out the results
        System.out.println("Clusters:");
        for (int i = 0; i < k; i++) {
            System.out.println("Cluster " + (i + 1) + " centroid Annual Income = " + centroids.get(i)[0] + ", Spending Score = " + centroids.get(i)[1]);
            for (Customer customer : clusters.get(i)) {
                System.out.println("CustomerID: " + customer.getCustomerID() + ", Annual Income = " + customer.getAnnualIncome() + ", Spending Score = " + customer.getSpendingScore());
            }
        }
    }
}
