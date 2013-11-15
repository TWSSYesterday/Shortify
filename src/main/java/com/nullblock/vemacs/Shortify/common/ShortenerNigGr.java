package com.nullblock.vemacs.Shortify.common;

import com.nullblock.vemacs.Shortify.util.ShortifyUtility;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ShortenerNigGr implements Shortener {
	public String getShortenedUrl(String toshort) throws ShortifyException {
		// "special"
		try {
			toshort = URLDecoder.decode(toshort, "UTF-8");
		} catch (UnsupportedEncodingException ignored) {
		}
		return "http://nig.gr/"
				+ ShortifyUtility.getUrlSimple("http://nig.gr/src/web/api/"
						+ toshort);
	}
}
