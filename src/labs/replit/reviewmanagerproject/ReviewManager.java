package labs.replit.reviewmanagerproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Double.NaN;

public class ReviewManager {

	HashMap<String, HashSet<Review>> reviewMap;

	public ReviewManager(){
		reviewMap = new HashMap<>();
	}

	public ReviewManager(String csvFile){
		this();
		loadData(csvFile);
	}

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
				List<String> tempList = new ArrayList<>(List.of(reviewData));
				tempList.add("5");
				reviewData = tempList.toArray(reviewData);
			}
			addReview(new Review(reviewData));
		}
		System.out.println("DONE!");
		System.out.println("---------------------");

		System.out.println("** Processing Time was " + (System.nanoTime() - startTime) / 1e9 + " seconds **");
	}

	public void addReview(Review review){
		if(reviewMap.containsKey(review.getBrand()))
			reviewMap.get(review.getBrand()).add(review);
		else
			reviewMap.put(review.getBrand(), new HashSet<>(Collections.singletonList(review)));
	}

	public String getBrands(){
		return reviewMap.keySet().toString();
	}

	public int getNumBrands(){
		return reviewMap.keySet().size();
	}

	public int getTotalReviews(){
		int total = 0;
		for(String key : reviewMap.keySet())
			total += reviewMap.get(key).size();

		return total;
	}

	public double getAverageRating(String brand){
		if(!reviewMap.containsKey(brand)) {
			return NaN;
		}
		double total = 0;
		for(Review review : reviewMap.get(brand))
			total += review.getRating();

		return total / reviewMap.get(brand).size();
	}

	public void removeReview(String uniqueUsername){
		for(String key : reviewMap.keySet())
			reviewMap.get(key).removeIf(review ->
					review.getUniqueUsername().equalsIgnoreCase(uniqueUsername));
	}

	@Override
	public String toString() {
		return "ReviewManager{" +
				"reviewMap=" + reviewMap +
				'}';
	}
}
