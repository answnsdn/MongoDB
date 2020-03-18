package kr.multi.bigdataShop.product.comment;

public class CommentResultDTO {

	public String key;
	public String val;
	
	public CommentResultDTO() {
		
	}

	public CommentResultDTO(String key, String val) {
		super();
		this.key = key;
		this.val = val;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return "CommentResultDTO [key=" + key + ", val=" + val + "]";
	}
	
	
	
}
