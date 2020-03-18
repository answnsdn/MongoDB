package mapred.exam.productsort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;
	
public class MyKey implements WritableComparable<MyKey> {
	private String userId;
	private String productId;
	
	public MyKey() {
		
	}

	public MyKey(String userId, String productId) {
		super();
		this.userId = userId;
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	

	@Override
	public String toString() {
		return (new StringBuffer()).append(productId).append(" ").append(userId).toString();
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		productId = WritableUtils.readString(in);
		userId = WritableUtils.readString(in);
		
	}

	@Override
	public void write(DataOutput out) throws IOException {
		WritableUtils.writeString(out, productId);
		WritableUtils.writeString(out, userId);
		
	}

	@Override
	public int compareTo(MyKey obj) {
		int result = productId.compareTo(obj.productId);
		if(result==0) {
			result = userId.compareTo(obj.userId);
		}
		return result;
	}
	
	
	
}
