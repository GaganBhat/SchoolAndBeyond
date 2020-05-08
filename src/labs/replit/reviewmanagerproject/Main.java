package labs.replit.reviewmanagerproject;

public class Main {

	public static void main(String[] args) {
		ReviewManager reviewManager = new ReviewManager();
		reviewManager.loadData("review_data.csv");
		System.out.println("Total Reviews Indexed = " + reviewManager.getTotalReviews());
		System.out.println("Brands with Reviews : " + reviewManager.getBrands());
		System.out.println("Total Number of Brands : " + reviewManager.getNumBrands());

		System.out.println("********************************");

		System.out.println("Average Toshiba Rating : " + reviewManager.getAverageRating("Toshiba"));
		System.out.println("Average Non Existent Brand Rating : " + reviewManager.getAverageRating("Hello"));

		System.out.println("Number of Reviews BEFORE adding extra review - " + reviewManager.getTotalReviews());
		System.out.println("Adding review...");
		reviewManager.addReview(new Review("NewBrand", "RandomProduct", "title", "randomContent", "uniqueUser", 5));
		System.out.println("Number of Reviews AFTER adding extra review - " + reviewManager.getTotalReviews());



	}

}
