package com.nullblock.vemacs.Shortify;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ShortenerBitLy implements Shortener {

	private String u, a = "";
	
	public ShortenerBitLy(String user, String apikey) {
		u = user;
		a = apikey;
	}
	
	public String getShortenedUrl(String toshort) throws ShortifyException {
	    URL shorted = null;
		try {
			shorted = new URL("http://api.bit.ly/v3/shorten?login=" + u + "&apiKey=" + a + "&longUrl=" + toshort + "&format=txt");
		} catch (MalformedURLException e1) {

		}
        String inputLine = null;
        try {
        BufferedReader in = new BufferedReader(
        new InputStreamReader(shorted.openStream()));
        while ((inputLine = in.readLine()) != null)
    		return inputLine;
        in.close();
        }
	    catch (IOException ex)
	    {    
	    	throw new ShortifyException("Unable to shorten: "+ex.getMessage());
	    }
		return inputLine;
	}

}
