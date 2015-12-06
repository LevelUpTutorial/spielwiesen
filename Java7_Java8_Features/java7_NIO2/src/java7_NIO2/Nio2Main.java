package java7_NIO2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Nio2Main {

	public Path createDirectory(String directoryPath) throws IOException,
			URISyntaxException {
		FileSystem fs = FileSystems.getDefault();
		Path path = fs.getPath(directoryPath);

		path = Files.createDirectory(path);
		System.out.println("created: " + path.toString());
		if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)
				&& Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
			System.out.println(path.toString() + " is a directory.");
		}

		return path;
	}

	public Path createFile(String filePath) throws URISyntaxException,
			IOException {
		Path path = Paths.get(filePath);
		if (Files.deleteIfExists(path)) {
			System.out.println("cleared: " + path.toString());
		}

		path = Files.createFile(path);
		System.out.println("created: " + path.toString());
		if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)
				&& Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS)) {
			System.out.println(path.toString() + " is a file.");
		}
		if (Files.isExecutable(path)) {
			System.out.println(path.toString() + " is executable.");
		}
		if (!Files.isHidden(path)) {
			System.out.println(path.toString() + " is not hidden.");
		}
		if (Files.isReadable(path)) {
			System.out.println(path.toString() + " is readable.");
		}
		if (Files.isWritable(path)) {
			System.out.println(path.toString() + " is writable.");
		}
		if (!Files.isSymbolicLink(path)) {
			System.out.println(path.toString() + " is not a symbolic link.");
		}

		return path;
	}

	public void deleteDirQuietly(String dir) throws IOException {
		Path directory = Paths.get(dir);
		if (Files.exists(directory)) {			
			Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file,
						BasicFileAttributes attrs) throws IOException {
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}
				
				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc)
						throws IOException {
					Files.delete(dir);
					System.out.println("deleted: " + dir.toString());
					return FileVisitResult.CONTINUE;
				}
				
			});
		}
	}

	public WatchKey watchDirectory(String dir) throws IOException {
		FileSystem fs = FileSystems.getDefault();
		Path directory = fs.getPath(dir);
		WatchService watcher = fs.newWatchService();
		WatchKey watchKey = directory.register(watcher,
				StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);
		System.out.println("path being watched: " + watchKey.watchable());

		return watchKey;
	}

	public void reportWatchEvents(WatchKey watchKey) {
		if (watchKey.isValid()) {
			for (WatchEvent<?> event : watchKey.pollEvents()) {
				System.out.println("---------------------------------");
				System.out.println("Kind: " + event.kind());
				System.out.println("Context: " + event.context());
				System.out.println("Count: " + event.count());
				System.out.println("---------------------------------");
			}

			boolean valid = watchKey.reset();
			if (!valid) {
				// The watchKey is not longer registered
			}
		} else {
			throw new RuntimeException("watchKey not valid anymore");
		}
	}
	
	public void writeToFile(Path file, Object obj) throws IOException {
		List<String> lines = Arrays.asList(obj.toString());
		writeToFile(file, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
	}
	
	public void writeToFile(Path file, List<String> lines, Charset charset, OpenOption options) throws IOException {
		if (Files.exists(file)) {
			Files.write(file, lines, charset, options);
		}
		else {
			throw new RuntimeException("File '"+file.toString()+"' does not exist!");
		}
	}
	
	public void printSmallFile(Path file) throws IOException {
		List<String> lines = Files.readAllLines(file);
		System.out.println("--- File: "+file.toString()+" ---");
		for (String s : lines) {
			System.out.println(s);
		}
		System.out.println("--- EOF: "+file.toString()+" ---");
	}
	
	public void printBigFile(Path file) throws IOException {
		Stream<String> lines = Files.lines(file);
		System.out.println("--- File: "+file.toString()+" ---");
		Iterator<String> iter = lines.iterator();
		while (iter.hasNext()) {
			String s = iter.next();
			System.out.println(s);
		}
		
		lines.close();
		System.out.println("--- EOF: "+file.toString()+" ---");
	}

	public static void main(String[] args) {
		Nio2Main test = new Nio2Main();
		try {
			String temp = "temp";
			test.deleteDirQuietly(temp);
			System.out.println("---------------------------------");
			Path dir = test.createDirectory(temp);
			WatchKey watchKey = test.watchDirectory(temp);

			dir = test.createDirectory(temp + "/nio2");
			WatchKey watchKey2 = test.watchDirectory(dir.toString());
			Path textFile = test.createFile(dir.toString() + "/test.txt");
			
			test.writeToFile(textFile, "Hallo");
			test.writeToFile(textFile, "Welt");
			test.writeToFile(textFile, "! ^_^ !");
			test.printSmallFile(textFile);	
			test.printBigFile(textFile);
			
			test.reportWatchEvents(watchKey);
			test.reportWatchEvents(watchKey2);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
