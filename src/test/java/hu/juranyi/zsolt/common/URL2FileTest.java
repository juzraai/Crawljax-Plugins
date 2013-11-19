package hu.juranyi.zsolt.common;

import static org.junit.Assert.assertEquals;
import hu.juranyi.zsolt.common.URL2File;

import org.junit.Test;

public class URL2FileTest {

	@Test
	public void forceHtmlExtOnFileWithNoExtension() {
		String url = "http://subdomain.domain.tld/pa/th/file";
		URL2File u2f = new URL2File(url, true);
		assertEquals("/subdomain.domain.tld/pa/th/", u2f.getDirectory());
		assertEquals("file.html", u2f.getFile());
	}

	@Test
	public void forceHtmlExtOnHtmFile() {
		String url = "http://subdomain.domain.tld/pa/th/file.htm";
		URL2File u2f = new URL2File(url, true);
		assertEquals("/subdomain.domain.tld/pa/th/", u2f.getDirectory());
		assertEquals("file.htm", u2f.getFile());
	}

	@Test
	public void forceHtmlExtOnHtmlFile() {
		String url = "http://subdomain.domain.tld/pa/th/file.html";
		URL2File u2f = new URL2File(url, true);
		assertEquals("/subdomain.domain.tld/pa/th/", u2f.getDirectory());
		assertEquals("file.html", u2f.getFile());
	}

	@Test
	public void forceHtmlExtOnMissingFilename() {
		String url = "http://subdomain.domain.tld/pa/th/";
		URL2File u2f = new URL2File(url, true);
		assertEquals("/subdomain.domain.tld/pa/th/", u2f.getDirectory());
		assertEquals("index.html", u2f.getFile());
	}

	@Test
	public void forceHtmlExtOnStandardFile() {
		String url = "http://subdomain.domain.tld/pa/th/file.ext";
		URL2File u2f = new URL2File(url, true);
		assertEquals("/subdomain.domain.tld/pa/th/", u2f.getDirectory());
		assertEquals("file.ext.html", u2f.getFile());
	}

	@Test
	public void noFileExtension() {
		String url = "http://subdomain.domain.tld/pa/th/file";
		URL2File u2f = new URL2File(url);
		assertEquals("/subdomain.domain.tld/pa/th/", u2f.getDirectory());
		assertEquals("file", u2f.getFile());
	}

	@Test
	public void noFilename() {
		String url = "http://subdomain.domain.tld/pa/th/";
		URL2File u2f = new URL2File(url);
		assertEquals("/subdomain.domain.tld/pa/th/", u2f.getDirectory());
		assertEquals("index.html", u2f.getFile());
	}

	@Test
	public void standardFile() {
		String url = "http://subdomain.domain.tld/pa/th/file.ext";
		URL2File u2f = new URL2File(url);
		assertEquals("/subdomain.domain.tld/pa/th/", u2f.getDirectory());
		assertEquals("file.ext", u2f.getFile());
	}
}
