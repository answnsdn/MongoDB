package hdfs.exam;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSCopy {

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
			hdfsOut = hdfs.create(path2);
			
			while(true) {
				int data = hdfsIn.read();
				System.out.println((char)data);
				if(data==-1) {
					break;
				}
				hdfsOut.write((char)data);
				
			}
		
			}catch(IOException e){
			e.printStackTrace();
		}
	}

}
