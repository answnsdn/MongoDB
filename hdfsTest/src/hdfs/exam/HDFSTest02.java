package hdfs.exam;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSTest02 {
	public static void main(String[] args) {
		Configuration con = new Configuration();
		FileSystem hdfs = null;
		FSDataInputStream hdfsinput = null;
		try {
			hdfs = FileSystem.get(con);
			Path path = new Path(args[0]);
			hdfsinput = hdfs.open(path);
			String data = hdfsinput.readUTF();
			System.out.println("hdfs에서 읽은 데이터"+data);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
