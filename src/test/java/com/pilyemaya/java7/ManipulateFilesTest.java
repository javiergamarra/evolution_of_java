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

	private static final String PATH_STRING = "../path.txt";

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
		final Path path = Paths.get(PATH_STRING);
		Assert.assertEquals(Paths.get("path.txt"), path.getFileName());

		// Iterable y comparable
		for (final Path name : path) {
			System.out.println(name);
		}
	}

	public File newFileInOlderJava(final String pathString) throws IOException {
		final File file = new File(pathString);
		System.out.println(file.createNewFile() ? "Fichero creado correctamente" : "El fichero ya existe");
		return file;
	}

	public Path newFileInJava7(final String pathString) throws IOException {
		final Path path = Paths.get(pathString);
		return Files.createFile(path);
	}

	public void deleteFileInJava7(final String pathString) throws IOException {
		final Path path = Paths.get(pathString);
		Files.delete(path);
		Files.deleteIfExists(path);
	}

	private void writeFileInOlderJava(final File file) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			bw.write("Older Java: This is first line");
			bw.newLine();
			bw.write("Older Java: This is second line");
			bw.newLine();
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (final IOException ex) {
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
		} catch (final IOException exception) {
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
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (final IOException ex) {
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
		} catch (final IOException exception) {
			System.out.println("Error reading file");
		}
	}

}
