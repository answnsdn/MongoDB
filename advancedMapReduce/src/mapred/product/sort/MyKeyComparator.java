package mapred.product.sort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MyKeyComparator extends WritableComparator{

	protected MyKeyComparator() {
		super(Mykey.class,true);
	}

	
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable obj1, WritableComparable obj2) {
		Mykey key1 = (Mykey)obj1;
		Mykey key2 = (Mykey)obj2;
		return key1.compareTo(key2);
	}
	
}
