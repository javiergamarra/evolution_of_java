package com.pilyemaya.java7;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class TryWithResourcesTest {

	@Test
	public void newFileTest() throws IOException {
		Assert.assertEquals(true, newFileInOlderJava());
		Assert.assertEquals(true, newFileInJava7());
	}

	public boolean newFileInJava7() {
		boolean result = false;
		try (FileOutputStream fos = new FileOutputStream("path.txt");
				DataOutputStream dos = new DataOutputStream(fos);) {
			dos.writeBytes("prueba");
			dos.writeBytes("\r\n");
			result = true;
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean newFileInOlderJava() {
		boolean result = false;
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		try {
			fos = new FileOutputStream("path.txt");
			dos = new DataOutputStream(fos);

			dos.writeBytes("prueba");
			dos.writeBytes("\r\n");
			result = true;
		} catch (final IOException e) {
			e.printStackTrace();
		} finally { // close resources
			try {
				dos.close();
				fos.close();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
