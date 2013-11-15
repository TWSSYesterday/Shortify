package com.nullblock.vemacs.Shortify.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

public class ShortenerGooGl implements Shortener {

	private String a = "";

	public ShortenerGooGl(String apikey) {
		a = apikey;
	}

	public String getShortenedUrl(String toshort) throws ShortifyException {
		// copypasta code from
		// http://www.glodde.com/blog/default.aspx?id=51&t=Java-Use-googl-URL-shorten-from-Java
		if (a.equals("none")) {
			throw new ShortifyException("No API key");
		}
		String shortUrl = "";
		try {
			toshort = URLDecoder.decode(toshort, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
		}
		String longUrl = toshort;
		String googUrl = "https://www.googleapis.com/urlshortener/v1/url?key="
				+ a;
		try {
			URLConnection conn = new URL(googUrl).openConnection();
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json");
			try (OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream())) {
			    wr.write("{\"longUrl\":\"" + longUrl + "\"}");
			    wr.flush();
            }

			try (BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()))) {
			    String line;

			    while ((line = rd.readLine()) != null) {
				    if (line.contains("id")) {
					    shortUrl = line.substring(8, line.length() - 2);
					    break;
				    }
			    }
            }
		} catch (MalformedURLException ex) {
			shortUrl = longUrl;
		} catch (IOException ex) {
			throw new ShortifyException("Unable to shorten via goo.gl: "
					+ ex.getMessage());
		}
		return shortUrl;
	}
}
