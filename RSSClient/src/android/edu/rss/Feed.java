package android.edu.rss;

class Feed {
	private String title;
	private String imageUrl;
	
	public Feed(String title, String imageUrl){
		this.title = title;
		this.imageUrl = imageUrl;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getImageUrl(){
		return imageUrl;
	}
}
