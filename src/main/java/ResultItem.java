import com.google.gson.annotations.SerializedName;

public class ResultItem{

	@SerializedName("available")
	private int available;

	@SerializedName("location")
	private String location;

	@SerializedName("id")
	private String id;

	public int getAvailable(){
		return available;
	}

	public String getLocation(){
		return location;
	}

	public String getId(){
		return id;
	}
}
