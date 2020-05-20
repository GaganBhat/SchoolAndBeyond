package labs.replit.reviewmanagerproject;
/**
 * Creates a Review Manager and inputs review data from a real-life dataset.
 * Tests the various functions of the Review Manager. It uses Java
 * assertions and does unit tests for the functionality of the map
 * of sets containing the review objects.
 *
 * @author Gagan Bhat
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 3
 * Due Date: 5/18/2020
 */

public class Main {

	/**
	 * Main method that tests review manager with sample data set.
	 * @param args java runtime args for the program
	 */
	public static void main(String[] args) {
		ReviewManager reviewManager = new ReviewManager("review_data.csv");

		System.out.println();
		System.out.println("Total Reviews Indexed = " + reviewManager.getTotalReviews());
		System.out.println("Brands with Reviews : " + reviewManager.getBrands());
		System.out.println("Total Number of Brands : " + reviewManager.getNumBrands());


		System.out.println("Average Toshiba Rating : "
				+ reviewManager.getAverageRating("Toshiba"));

		System.out.println("Average Non Existent Brand Rating : "
				+ reviewManager.getAverageRating("Hello"));

		System.out.println();

		System.out.println("--------------------------------");
		System.out.println("Number of Reviews BEFORE adding extra review - "
				+ reviewManager.getTotalReviews());
		System.out.println("Adding review...");
		reviewManager.addReview(new Review("NewBrand",
				"RandomProduct", "title",
				"randomContent", "uniqueUser", 5));
		System.out.println("Number of Reviews AFTER adding extra review - "
				+ reviewManager.getTotalReviews());

		System.out.println("-----------------------w---------");
		System.out.println("Number of Reviews BEFORE removing a review - "
				+ reviewManager.getTotalReviews());
		System.out.println("Removing review from username NvlSailDriver");
		reviewManager.removeReview("NvlSailDriver");
		System.out.println("Number of Reviews AFTER removing a review - "
				+ reviewManager.getTotalReviews());
		System.out.println("--------------------------------");

		System.out.println();

		System.out.println("Retrieving a review based on username CCSmooth");
		System.out.println(reviewManager.getReview("CCSmooth"));

	}

}
