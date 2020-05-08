package labs.replit.reviewmanagerproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

	@Override
	public String toString() {
		return "ReviewManager{" +
				"reviewMap=" + reviewMap +
				'}';
	}
}
