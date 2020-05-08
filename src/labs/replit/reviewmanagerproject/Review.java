package labs.replit.reviewmanagerproject;

public class Review {

	private String brand;
	private String productName;
	private String title;
	private String content;
	private String uniqueUsername;
	private int rating;


	public Review(String brand, String productName, String title, String content, String uniqueUsername, int rating){
		this.brand = brand;
		this.productName = productName;
		this.title = title;
		this.content = content;
		this.uniqueUsername = uniqueUsername;
		this.rating = rating;
	}

	public Review(String[] compressedReviewData){
		this(
				compressedReviewData[0],
				compressedReviewData[1],
				compressedReviewData[2],
				compressedReviewData[3],
				compressedReviewData[4],
				Integer.parseInt(compressedReviewData[5])
		);
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getUniqueUsername() {
		return uniqueUsername;
	}

	public void setUniqueUsername(String uniqueUsername) {
		this.uniqueUsername = uniqueUsername;
	}

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
