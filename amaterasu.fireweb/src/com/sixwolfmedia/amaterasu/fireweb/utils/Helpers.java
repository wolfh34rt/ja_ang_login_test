package com.sixwolfmedia.amaterasu.fireweb.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Helpers {
	public static String GetMD5Hash(String string_to_hash) throws NoSuchAlgorithmException {
		String result_of_hash = string_to_hash;

		if (string_to_hash != null) {
			MessageDigest message_digest = MessageDigest.getInstance("MD5");
			message_digest.update(string_to_hash.getBytes());
			BigInteger hash = new BigInteger(1, message_digest.digest());
			result_of_hash = hash.toString(16);

			while (result_of_hash.length() < 32) {
				// add missing 0
				result_of_hash = "0" + result_of_hash;
			}
		}

		return result_of_hash;
	}

	public static boolean WriteStringToDisk(String string_to_write, String filename) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

		try {
			writer.write(string_to_write);
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			/// TODO: log this
			System.out.println(e.toString());
			return false;
		} finally {
			writer.close();
		}

		return true;
	}

	public static String ReadInputStreamToString(InputStream input_stream) throws IOException {
		Reader reader = new BufferedReader(
				new InputStreamReader(input_stream, Charset.forName(StandardCharsets.UTF_8.name())));
		StringBuilder text_builder = new StringBuilder();

		try {
			int read_char = 0;

			while ((read_char = reader.read()) != -1) {
				text_builder.append((char)read_char);
			}
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			/// TODO: Log this
			System.out.println(e.toString());
			return null;
		} finally {
			reader.close();
		}

		return text_builder.toString();
	}
	
	public static String GenerateIdentityKey( ) {
		StringBuilder key = new StringBuilder();
		
		for( int i = 0 ; i < 32 ; i++) {
			Boolean isNumberValid = false;
			int ascii_code = 0;
			
			do {
				ascii_code = (int)(Math.floor(Math.random() * (122 - 48 + 1) + 48));
				
				/// TODO: Replace with RE. I hate long IF statements like this.
				if ((ascii_code >= 48 && ascii_code <= 57) || (ascii_code >= 65 && ascii_code <= 90) || (ascii_code >= 97 && ascii_code <= 122)) {
					isNumberValid = true;
				}
			} while (!isNumberValid);
			
			key.append((char)ascii_code);
		}
	
		return key.toString();
	}
}
