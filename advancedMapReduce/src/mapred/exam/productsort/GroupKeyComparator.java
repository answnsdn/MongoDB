package mapred.exam.productsort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupKeyComparator extends WritableComparator{
//그룹키 비교기
	protected GroupKeyComparator() {
		super(MyKey.class,true);
		
	}
	//Warning발생 원인 : WritableComparable의 타입이 정확하지 않기 때문에 발생
	//@SuppressWarnings("rawtypes")를 이용해 무시하고 처리하겠다는 의미
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable obj1, WritableComparable obj2) {
		MyKey key1 = (MyKey)obj1;
		MyKey key2 = (MyKey)obj2;
		return key1.getProductId().compareTo(key2.getProductId());
	}
	
}
