import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProxyAvailableResponse{

	@SerializedName("result")
	private List<ResultItem> result;

	public List<ResultItem> getResult(){
		return result;
	}
}
