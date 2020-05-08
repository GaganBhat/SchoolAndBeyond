package labs.replit.reviewmanagerproject;

public class Main {

	public static void main(String[] args) {
		ReviewManager reviewManager = new ReviewManager();
		reviewManager.loadData("review_data.csv");
		System.out.println(reviewManager.getBrands());
		System.out.println(reviewManager.getNumBrands());
	}

}
