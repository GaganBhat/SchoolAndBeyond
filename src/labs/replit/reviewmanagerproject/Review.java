package labs.replit.reviewmanagerproject;

public class Review {

	private String brand;
	private String productName;
	private String title;
	private String content;
	private int rating;

	public Review(String brand, String productName, String title, String content, int rating){
		this.brand = brand;
		this.productName = productName;
		this.title = title;
		this.content = content;
		this.rating = rating;
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
}
