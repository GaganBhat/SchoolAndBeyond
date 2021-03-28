package labs.highschool.replit.reviewmanagerproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import static java.lang.Double.NaN;

/**
 * The ReviewManager class tracks and manages a list of Review objects through a
 * map containing sets as values sorted based on brands
 * that co-relate to reviews on electronic goods from
 * e-retailers such as amazon and best buy. It contains the ability to sort
 * based on the average rating, retrieve average rating for each e-retailer,
 * retrieve average rating for a specified product, retrieve
 * individual Review objects based on unique identification usernames.
 *
 * @author Gagan Bhat
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 3
 * Due Date: 5/18/2020
 */

public class ReviewManager {

	private final HashMap<String, HashSet<Review>> reviewMap;

	/**
	 * Initializes an empty hashmap for reviewMap.
	 */
	public ReviewManager(){
		reviewMap = new HashMap<>();
	}

	/**
	 * A constructor that calls the loading of data on
	 * construction of the review manager class
	 * @param csvFile file to load review data from
	 */
	public ReviewManager(String csvFile){
		this();
		loadData(csvFile);
	}

	/**
	 * Loads data into the reviewMap hashmap using helper methods
	 * through the data from the file
	 * @param dataPath file path for the file to load data from
	 */
	public void loadData(String dataPath){
		long startTime = System.nanoTime();

		System.out.println("---------------------");
		System.out.println("INDEXING REVIEW DATA");
		Scanner s = null;
		try {
			s = new Scanner(new File(dataPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		assert s != null;
		s.nextLine();

		while (s.hasNextLine()) {
			String[] reviewData = s.nextLine().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			if(reviewData.length != 6) {
				List<String> tempList = new ArrayList<>(Arrays.asList(reviewData));
				tempList.add("5");
				reviewData = tempList.toArray(reviewData);
			}
			addReview(new Review(reviewData));
		}
		System.out.println("DONE!");
		System.out.println("Processing Time was " + (System.nanoTime() - startTime) / 1e9 + " seconds.");
		System.out.println("---------------------");
		System.out.println();
	}

	/**
	 * Adds a review to the existing review map.
	 * @param review review instance to add
	 */
	public void addReview(Review review){
		if(reviewMap.containsKey(review.getBrand()))
			reviewMap.get(review.getBrand()).add(review);
		else
			reviewMap.put(review.getBrand(), new HashSet<>(Collections.singletonList(review)));
	}

	/**
	 * Gets all the unique brands in all reviews combined
	 * @return list of brands
	 */
	public String getBrands(){
		return reviewMap.keySet().toString();
	}

	/**
	 * Number of unique brands in the reviews combined
	 * @return unique number of brands
	 */
	public int getNumBrands(){
		return reviewMap.keySet().size();
	}

	/**
	 * Gets total reviews in the map
	 * @return total number of reviews
	 */
	public int getTotalReviews(){
		int total = 0;
		for(String key : reviewMap.keySet())
			total += reviewMap.get(key).size();

		return total;
	}

	/**
	 * Average rating for a specific brand
	 * @param brand brand to check average rating for
	 * @return the average rating across all reviews for a single brand
	 */
	public double getAverageRating(String brand){
		if(!reviewMap.containsKey(brand)) {
			return NaN;
		}
		double total = 0;
		for(Review review : reviewMap.get(brand))
			total += review.getRating();

		return total / reviewMap.get(brand).size();
	}

	/**
	 * Removes a review based on the unique username who wrote it
	 * @param uniqueUsername review to remove based on username
	 */
	public void removeReview(String uniqueUsername){
		for(String key : reviewMap.keySet())
			reviewMap.get(key).removeIf(review ->
					review.getUniqueUsername().equals(uniqueUsername));
	}

	/**
	 * Gets the review based on unique username
	 * @param uniqueUsername unique username of review to retrieve
	 * @return review created by the username
	 */
	public Review getReview(String uniqueUsername) {
		for(String key : reviewMap.keySet())
			for(Review review : reviewMap.get(key))
				if(review.getUniqueUsername().equals(uniqueUsername))
					return review;
		return null;
	}

	/**
	 * Deletes all reviews in the review map and empties it.
	 */
	public void deleteAllReviews(){
		reviewMap.clear();
	}

	/**
	 * Prints the review map in its entirety.
	 * @return string with review map info
	 */
	@Override
	public String toString() {
		return "ReviewManager{" +
				"reviewMap=" + reviewMap +
				'}';
	}


}
