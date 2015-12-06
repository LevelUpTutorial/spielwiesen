package java7_NIO2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.PrimitiveIterator.OfLong;
import java.util.Random;
import java.util.stream.LongStream;

import org.junit.Test;

public class PerformanceWithBigFiles {
	private static final String TEMP_DIR = "temp";
	private static final String NIO2_DIR = TEMP_DIR+"/nio2";
	private static final String TEXT1 = NIO2_DIR+"/text1.txt";
	private static final String TEXT2 = NIO2_DIR+"/text2.txt";
	private static final long line_count = 1000;
	private static final long longs_per_line = 100;
	
	private Nio2Main util = new Nio2Main();
	private ResultWrapper result = new ResultWrapper();
	
	@Test
	public void messung() throws IOException, URISyntaxException {
		prepareFiles();
		
		messureReadAllLines();
		messureReadAsStream();
		
		result.printResult();
	}

	private void prepareFiles() throws IOException, URISyntaxException {
		util.deleteDirQuietly(TEMP_DIR);
		util.createDirectory(TEMP_DIR);
		util.createDirectory(NIO2_DIR);
		Path text1 = util.createFile(TEXT1);
		
		Random rnd = new Random(System.currentTimeMillis());
		LongStream longs = rnd.longs(longs_per_line);
		OfLong iter = longs.iterator();
		StringBuffer line = new StringBuffer();
		while (iter.hasNext()) {
			Long l = iter.next();
			line.append(l);
		}
		
		for (int i=0; i<line_count; i++) {
			util.writeToFile(text1, line);
		}
		Path text2 = Files.copy(text1, Paths.get(TEXT2), StandardCopyOption.REPLACE_EXISTING);
		result.setSize1(Files.size(text1));
		result.setSize2(Files.size(text2));
	}

	private void messureReadAllLines() throws IOException {
		long start = System.currentTimeMillis();
		util.printSmallFile(Paths.get(TEXT1));
		long end = System.currentTimeMillis();
		
		result.setPrint1(end - start);
	}
	
	private void messureReadAsStream() throws IOException {
		long start = System.currentTimeMillis();
		util.printBigFile(Paths.get(TEXT2));
		long end = System.currentTimeMillis();
		
		result.setPrint2(end - start);
	}
	
	private class ResultWrapper {
		private long size1;
		private long size2;
		private long print1;
		private long print2;
		
		public void printResult() {
			System.out.println("------- Result Report -------");
			System.out.println("Size1 = "+size1);
			System.out.println("Size2 = "+size2);
			System.out.println("Duration ReadAllLines   = "+print1);
			System.out.println("Duration Read as Stream = "+print2);
			System.out.println("------- End of Report -------");
		}

		public void setSize1(long size1) {
			this.size1 = size1;
		}
		public void setSize2(long size2) {
			this.size2 = size2;
		}
		public void setPrint1(long print1) {
			this.print1 = print1;
		}
		public void setPrint2(long print2) {
			this.print2 = print2;
		}
	}
}
