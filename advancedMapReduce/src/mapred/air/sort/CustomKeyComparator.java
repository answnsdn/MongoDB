package mapred.air.sort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class CustomKeyComparator extends WritableComparator{
//그룹키 비교기
	protected CustomKeyComparator() {
		super(CustomKey.class,true);
		
	}
	//Warning발생 원인 : WritableComparable의 타입이 정확하지 않기 때문에 발생
	//@SuppressWarnings("rawtypes")를 이용해 무시하고 처리하겠다는 의미
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable obj1, WritableComparable obj2) {
		CustomKey key1 = (CustomKey)obj1;
		CustomKey key2 = (CustomKey)obj2;
		return key1.compareTo(key2);
	}
	
}
