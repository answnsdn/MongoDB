package mapred.air.sort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;
//복합키를 정의 - 사용자정의 키(정렬할 기준을 컬럼으로 갖고 있는 객체)
//맵리듀스 프레임워크 내부에서 키와 value는 네트워크에서 주고 받는 값이므로
//네트워크 전송을 하기 위해 제공되는 Writable 타입이어야 하므로 WritableComparable을 상속받아 작성한다.	
public class CustomKey implements WritableComparable<CustomKey> {
	private String year;
	private Integer month;
<<<<<<< HEAD
	private Long mapkey;
=======
>>>>>>> f9663043fa54918552602d961c1003106d2b8a82
	public CustomKey() {
		
	}
	
<<<<<<< HEAD
	
	
	public CustomKey(String year, Integer month, Long mapkey) {
		super();
		this.year = year;
		this.month = month;
		this.mapkey = mapkey;
	}



=======
	public CustomKey(String year, Integer month) {
		super();
		this.year = year;
		this.month = month;
	}
	
	
>>>>>>> f9663043fa54918552602d961c1003106d2b8a82
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

<<<<<<< HEAD
	public Long getMapkey() {
		return mapkey;
	}



	public void setMapkey(Long mapkey) {
		this.mapkey = mapkey;
	}



=======
>>>>>>> f9663043fa54918552602d961c1003106d2b8a82
	@Override
	public String toString() {
		return (new StringBuffer()).append(year).append(",").append(month).toString();
	}//+로 받으면 객체가 여러개 But! StringBuffer를 통해서 객체 하나로  받을 수 있다.
	
	//데이터를 쓰고 읽는 작업을 처리
	//데이터 쓰기 - 직렬화
	//데이터 읽기 - 역직렬화
	//하둡의 맵리듀스 내부에서 이런 작업을 처리할 수 있도록 구현된 메소드를 호출해서 처리
	@Override
	public void write(DataOutput out) throws IOException {
		WritableUtils.writeString(out, year);
		out.writeInt(month);
<<<<<<< HEAD
		out.writeLong(mapkey);
=======
>>>>>>> f9663043fa54918552602d961c1003106d2b8a82
	}
	//역직렬화할때 호출
	@Override
	public void readFields(DataInput in) throws IOException {
		year = WritableUtils.readString(in);
		month = in.readInt();
<<<<<<< HEAD
		mapkey = in.readLong();
=======
>>>>>>> f9663043fa54918552602d961c1003106d2b8a82
		
	}
	//사용자가 만들어 놓은 키를 기준으로 정렬하기 위해서 비교하게 할 메소드
	//year로 비교하고 year가 같으면 month로 비교 => 비교 상태(기준)만 넘겨준다.
	@Override
	public int compareTo(CustomKey obj) {
		int result = year.compareTo(obj.year);
		if(result==0) {//year가 같다.
			result = month.compareTo(obj.month);
		}
		return result;
	}
	
}
