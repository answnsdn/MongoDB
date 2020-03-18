package hdfs.exam;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSCopyTest {

	public static void main(String[] args) {
		Configuration conf = new Configuration();
		FileSystem hdfs = null;
		FSDataOutputStream hdfsOut = null;
		FSDataInputStream hdfsIn = null;
		try {
			hdfs = FileSystem.get(conf);
			Path path1 = new Path(args[0]);
			Path path2 = new Path(args[1]);
			hdfsIn = hdfs.open(path1);
			String InUTF = hdfsIn.readUTF();
			System.out.println("hdfs에서 읽은 데이터 : "+InUTF);
			hdfsOut = hdfs.create(path2);
			hdfsOut.writeUTF(InUTF);
		
			}catch(IOException e){
			e.printStackTrace();
		}
	}

}
