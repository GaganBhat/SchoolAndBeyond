package labs.replit.reviewmanagerproject;

public class Main {

	public static void main(String[] args) {
		ReviewManager reviewManager = new ReviewManager();
		reviewManager.loadData("review_data.csv");
		System.out.println("Total Reviews Indexed = " + reviewManager.getTotalReviews());
		System.out.println("Brands with Reviews : " + reviewManager.getBrands());
		System.out.println("Total Number of Brands" + reviewManager.getNumBrands());
	}

}
