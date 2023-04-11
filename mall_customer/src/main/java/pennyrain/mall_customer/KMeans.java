package pennyrain.mall_customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans {
    public static void classifyCustomers(List<Customer> customers, int k) {
        // Initialize random centroids
        List<double[]> centroids = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < k; i++) {
            double[] centroid = {rand.nextInt(2), rand.nextInt(100), rand.nextInt(100), rand.nextInt(100)};
            centroids.add(centroid);
        }
        List<List<Customer>> clusters = new ArrayList<List<Customer>>();

        // Main K-means algorithm loop
        boolean changed = true;
        while (changed) {
            changed = false;
            clusters.clear();
            for (int i = 0; i < k; i++) {
                clusters.add(new ArrayList<Customer>());
            }

            for (Customer customer : customers) {
                int closestCentroidIndex = 0;
                double minDistance = Double.MAX_VALUE;
                double[] customerAttributes = {customer.getGender().equals("Male") ? 0 : 1, (double) customer.getAge(), (double) customer.getAnnualIncome(), (double) customer.getSpendingScore()};
                for (int i = 0; i < k; i++) {
                    double[] centroid = centroids.get(i);
                    double distance = 0;
                    for (int j = 0; j < centroid.length; j++) {
                        distance += Math.pow(customerAttributes[j] - centroid[j], 2);
                    }
                    distance = Math.sqrt(distance);
                    if (distance < minDistance) {
                        minDistance = distance;
                        closestCentroidIndex = i;
                    }
                }
                clusters.get(closestCentroidIndex).add(customer);
            }
            
            for (int i = 0; i < k; i++) {
                List<Customer> cluster = clusters.get(i);
                if (!cluster.isEmpty()) {
                    double[] newCentroid = new double[4];
                    for (Customer customer : cluster) {
                        newCentroid[0] += customer.getGender().equals("Male") ? 0 : 1;
                        newCentroid[1] += customer.getAge();
                        newCentroid[2] += customer.getAnnualIncome();
                        newCentroid[3] += customer.getSpendingScore();
                    }

                    for (int j = 0; j < 4; j++) {
                        newCentroid[j] /= cluster.size();
                    }

                    boolean centroidChanged = false;
                    for (int j = 0; j < 4; j++) {
                        if (newCentroid[j] != centroids.get(i)[j]) {
                            centroidChanged = true;
                            break;
                        }
                    }

                    if (centroidChanged) {
                        changed = true;
                        centroids.set(i, newCentroid);
                    }
                }
            }
        }
        
        System.out.printf("%-12s %-6s %-4s %-13s %-14s %-8s%n", "CustomerID", "Gender", "Age", "Annual Income", "Spending Score", "Cluster");
        for (int i = 0; i < k; i++) {
            for (Customer customer : clusters.get(i)) {
                System.out.printf("%-12d %-6s %-4d %-13d %-14d %-8d%n", customer.getCustomerID(), customer.getGender(), customer.getAge(), customer.getAnnualIncome(), customer.getSpendingScore(), i + 1);
            }
        }
    }
}

