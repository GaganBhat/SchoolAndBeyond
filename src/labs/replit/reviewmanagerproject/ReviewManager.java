package labs.replit.reviewmanagerproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

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

		String[] reviewData = s.nextLine().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
		System.out.println(Arrays.toString(reviewData));


	}

	public void addReview(Review review){

	}

}
