package com.pilyemaya.java7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class ManipulateFilesTest {

	private static final String PATH_STRING = "C:\\Users/Pili/Documents/file.txt";

	@Test
	public void manipulateFilesInOlderJavaTest() throws IOException {
		final File file = newFileInOlderJava(PATH_STRING);
		writeFileInOlderJava(file);
		readFileInOlderJava(file);
		file.delete();
		file.deleteOnExit();
	}

	@Test
	public void manipulateFilesInJava7Test() throws IOException {
		final Path file = newFileInJava7(PATH_STRING);
		writeFileInJava7(file);
		readFileInJava7(file);
		Files.delete(file);
		Files.deleteIfExists(file);
	}

	@Test
	public void infoWithPathTest() throws IOException {
		Path path = Paths.get(PATH_STRING);
		Assert.assertEquals(Paths.get("file.txt"), path.getFileName());
		// a position of path
		Assert.assertEquals(Paths.get("Users"), path.getName(0));
		// number elements of path
		Assert.assertEquals(4, path.getNameCount());
		// sequence of path
		Assert.assertEquals(Paths.get("Users/Pili"), path.subpath(0, 2));
		// parent directory
		Assert.assertEquals(Paths.get("C:\\Users/Pili/Documents"),
				path.getParent());
		// root
		Assert.assertEquals(Paths.get("C:\\"), path.getRoot());

		// compare methods
		Assert.assertEquals(false,
				path.equals(Paths.get("C:\\Users/Pili/Documents/file3.txt")));
		Assert.assertEquals(true, path.startsWith("C:\\Users"));

		// Iterable y comparable
		for (Path name : path) {
			System.out.println(name);
		}
	}

	public File newFileInOlderJava(final String pathString) throws IOException {
		File file = new File(pathString);
		if (file.createNewFile()) {
			System.out.println("Fichero creado correctamente");
		} else {
			System.out.println("El fichero ya existe");
		}
		return file;
	}

	public Path newFileInJava7(final String pathString) throws IOException {
		Path ruta = Paths.get(pathString);
		return Files.createFile(ruta);
	}

	public void deleteFileInJava7(final String pathString) throws IOException {
		Path ruta = Paths.get(pathString);
		Files.delete(ruta);
		Files.deleteIfExists(ruta);
	}

	private void writeFileInOlderJava(final File file) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			bw.write("Older Java: This is first line");
			bw.newLine();
			bw.write("Older Java: This is second line");
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException ex) {
				System.out.println("Older Java: Error writing file");
			}
		}
	}

	public void writeFileInJava7(final Path file) {
		try (BufferedWriter writer = Files.newBufferedWriter(file,
				Charset.defaultCharset())) {
			writer.append("Java 7: This is first line");
			writer.newLine();
			writer.append("Java 7: This is second line");
			writer.newLine();
			writer.flush();
		} catch (IOException exception) {
			System.out.println(" Java 7:Error writing to file");
		}
	}

	public void readFileInOlderJava(final File file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String content;
			System.out.println("Content of file in older Java .... \n");
			while ((content = br.readLine()) != null) {
				System.out.println(content);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ex) {
				System.out.println("Error reading file");
			}
		}

	}

	public void readFileInJava7(final Path file) {
		try (BufferedReader reader = Files.newBufferedReader(file,
				Charset.defaultCharset())) {
			String lineFromFile = "";
			System.out.println("Content of file in Java 7 .... \n");
			while ((lineFromFile = reader.readLine()) != null) {
				System.out.println(lineFromFile);
			}
		} catch (IOException exception) {
			System.out.println("Error reading file");
		}
	}

}
