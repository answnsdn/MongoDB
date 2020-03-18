package multiple.multiple2;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//하둡을 실행할때 사용자가 입력하는 옵션을 Mappler내부에서 사용할 수 있도록
//옵션이 어떤 값으로 입력되었냐에 따라 다른 작업을 할 수 있도록 처리
public class AirMultipleMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	Text outputKey = new Text(); // output key

	// output Value
	static final IntWritable one = new IntWritable(1);

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if (key.get() > 0) {
			String[] line = value.toString().split(",");
			if (line != null & line.length > 0) {
				// 출발지연
				if (!line[15].equals("NA") && 
						Integer.parseInt(line[15]) > 0) {
					outputKey.set("departure," + line[1]);
					context.write(outputKey, one);
				} // 도착지연
				else if (!line[14].equals("NA") 
						&& Integer.parseInt(line[14]) > 0) {
					outputKey.set("arrival," + line[1]);
					context.write(outputKey, one);
				} else if (line[14].equals("NA")) {
					outputKey.set("na14," + line[1]);
					context.write(outputKey, one);
				} else if (line[15].equals("NA")) {
					outputKey.set("na15," + line[1]);
					context.write(outputKey, one);
				}
			}

		}
	}

}
