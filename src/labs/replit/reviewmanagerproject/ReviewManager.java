package labs.replit.reviewmanagerproject;

import java.util.HashMap;
import java.util.HashSet;

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

	}

	public void addReview(Review review){

	}

}
