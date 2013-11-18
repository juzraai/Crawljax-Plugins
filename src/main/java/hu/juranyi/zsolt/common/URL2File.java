package hu.juranyi.zsolt.common;

/**
 * Useful tool for converting an URL to a filename. It builds up a directory
 * path and a filename, so you can create the directories first, then write the
 * file. A '.html' suffix will be added if the URL doesn't end with '.html' or
 * '.htm'. URLs with '/' ending will get an 'index.html' suffix. Special
 * characters will be replaced with '_'.
 * 
 * @author Zsolt Juranyi
 * 
 */
public class URL2File {

	/*
	 * scheme://domain:port/path?query_string#fragment_id
	 * (http://en.wikipedia.org/wiki/Uniform_resource_locator)
	 */

	private String url;
	private String directory;
	private String file;

	/**
	 * Creates the directory name and the filename from the given URL.
	 * 
	 * @param url
	 *            URL to create the path and filename from.
	 */
	public URL2File(String url) {
		this.url = url;
		build();
	}

	private void build() {
		String u = url;

		u = "/" + u.replaceFirst("^.*?:/", ""); // remove protocol

		u = u.replaceFirst("#.*$", ""); // remove fragment_id

		u = u.replaceAll("/+", "/"); // clear

		if (u.indexOf('?') >= 0) { // if there is a query string
			String[] p = u.split("\\?", 2); // make sure it won't
			u = p[0] + "_" + p[1].replaceAll("/", "_"); // create deeper folders
		}

		u = u.replaceAll("[?*:]", "_").replaceAll("_+", "_"); // clear

		int pos = u.lastIndexOf('/'); // divide the URL now

		directory = u.substring(0, pos + 1);
		// directory = directory.replaceAll("[/]", File.separator));
		// throws exception, but why?
		// it works well anyway, but it's annoying :)

		file = u.substring(pos + 1);

		if (file.isEmpty()) { // if URL is like http://domain/path/
			file = "index.html"; // generate filename: domain/path/index.html
		}

		if (!file.endsWith(".html") && !file.endsWith(".htm")) {
			file = file + ".html";
		}
	}

	public String getDirectory() {
		return directory;
	}

	public String getFile() {
		return file;
	}
}
