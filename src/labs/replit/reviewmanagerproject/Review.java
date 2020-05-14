package labs.replit.reviewmanagerproject;
/**
 * The Review class contains information on a single
 * review given by a unique username. It has information on the
 * content, brand, product name, review title and the rating
 * on a scale from 0-5.
 *
 * @author Gagan Bhat
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 3
 * Due Date: 5/18/2020
 */

import java.util.Objects;

public class Review {

	private String brand;
	private String productName;
	private String title;
	private String content;
	private String uniqueUsername;
	private int rating;


	/**
	 * Constructs a review object by setting all fields individually
	 * @param brand brand name of the product
	 * @param productName product name the review is on
	 * @param title title of the review set by user
	 * @param content content in the review
	 * @param uniqueUsername the unique ID of the user
	 * @param rating rating that was given in the review
	 */
	public Review(String brand, String productName, String title, String content, String uniqueUsername, int rating){
		this.brand = brand;
		this.productName = productName;
		this.title = title;
		this.content = content;
		this.uniqueUsername = uniqueUsername;
		this.rating = rating;
	}

	/**
	 * A secondary constructor that allows you to pass in an array of
	 * information that is passed on in sequence to the main constructor
	 * @param compressedReviewData array of all data required to make a review
	 */
	public Review(String... compressedReviewData){
		this(
				compressedReviewData[0],
				compressedReviewData[1],
				compressedReviewData[2],
				compressedReviewData[3],
				compressedReviewData[4],
				Integer.parseInt(compressedReviewData[5])
		);
	}

	/**
	 * Returns the brand name
	 * @return brand name
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Sets the brand name
	 * @param brand brand name to set to
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Gets the product name
	 * @return product name
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Sets the product name
	 * @param productName new product name
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * Gets the title
	 * @return review title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets new title
	 * @param title new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the content of review
	 * @return review content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content of the review
	 * @param content new review content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the product name
	 * @return product name
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Sets the rating of the review
	 * @param rating new review rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * Gets the unique username
	 * @return unique username of the review
	 */
	public String getUniqueUsername() {
		return uniqueUsername;
	}

	/**
	 * Sets the username of the reivew
	 * @param uniqueUsername new unique username for the review
	 */
	public void setUniqueUsername(String uniqueUsername) {
		this.uniqueUsername = uniqueUsername;
	}

	/**
	 * Checks if the given object is equal to this.
	 * @param o object to check against
	 * @return true if equal, false if not.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Review review = (Review) o;
		return getRating() == review.getRating() &&
				Objects.equals(getBrand(), review.getBrand()) &&
				Objects.equals(getProductName(), review.getProductName()) &&
				Objects.equals(getTitle(), review.getTitle()) &&
				Objects.equals(getContent(), review.getContent()) &&
				Objects.equals(getUniqueUsername(), review.getUniqueUsername());
	}

	/**
	 * Creates a unique hashcode with instance variables
	 * in the class
	 * @return unique hashcode for the object
	 */
	@Override
	public int hashCode() {
		return Objects.hash(getBrand(), getProductName(), getTitle(),
				getContent(), getUniqueUsername(), getRating());
	}

	/**
	 * Returns identifying information on all fields
	 * in the class
	 * @return info on all instance vars
	 */
	@Override
	public String toString() {
		return "Review{" +
				"brand='" + brand + '\'' +
				", productName='" + productName + '\'' +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", uniqueUsername='" + uniqueUsername + '\'' +
				", rating=" + rating +
				'}';
	}
}
