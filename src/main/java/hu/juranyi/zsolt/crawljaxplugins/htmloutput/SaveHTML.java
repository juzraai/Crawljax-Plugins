package hu.juranyi.zsolt.crawljaxplugins.htmloutput;

import hu.juranyi.zsolt.common.MD5;
import hu.juranyi.zsolt.common.URL2File;

import java.io.File;
import java.io.FileWriter;

import com.crawljax.core.CrawlerContext;
import com.crawljax.core.plugin.OnNewStatePlugin;
import com.crawljax.core.state.StateVertex;

/**
 * HTML output plugin for Crawljax. It's an OnNewStatePlugin, so it's called
 * when the DOM changes in the browser, so you can save every state to file.
 * This plugin provides that each state will be stored only once, equality
 * testing is based on MD5 hashes. Filenames will be generated from URLs,
 * SaveHTML will create the directories just as they are in the URL. With this,
 * you can store a mirror of a site, but links will not be modified, so they
 * won't work in all cases. If Crawljax reaches more than one state on the same
 * URL, SaveHTML will add a counter to the end of the filename.
 * 
 * @author Zsolt Juranyi
 * @version 1.0.0
 * @see URL2File
 * @see MD5
 * 
 */
public class SaveHTML implements OnNewStatePlugin {

	public void onNewState(CrawlerContext ctx, StateVertex vtx) {

		try {
			// query info
			String dom = vtx.getStrippedDom();
			String url = vtx.getUrl();
			String outdir = ctx.getConfig().getOutputDir().getAbsolutePath();

			// build output
			URL2File u2f = new URL2File(url);
			String dir = u2f.getDirectory();
			String file = u2f.getFile();
			String fn = outdir + "/" + dir + "/" + file;
			String newFilename = fn;

			// check if exists / same
			boolean same = false;
			boolean exists = new File(fn).exists();
			int counter = 0;
			String domHash = MD5.getMD5FromString(dom);

			while (exists && !same) {
				String fileHash = MD5.getMD5FromFile(newFilename);
				if (fileHash.equals(domHash)) {
					same = true;
				} else {
					counter++;
					newFilename = fn.replaceAll("\\(.html?)$", "-" + counter
							+ "$1");
					exists = new File(newFilename).exists();
				}
			}

			if (!same) {
				new File(outdir + "/" + dir).mkdirs(); // create directory
				// TODO UTF-8 ?
				FileWriter fw = new FileWriter(newFilename, false);
				fw.write(dom); // write file
				fw.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
