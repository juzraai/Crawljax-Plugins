Crawljax Plugins
================

**My useful plugins for [Crawljax](http://crawljax.com) + some handy classes!**

##SaveHTML - HTML output plugin
HTML output plugin for Crawljax. It's an OnNewStatePlugin, so it's called when the DOM changes in the browser, so you can **save every state to file**. This plugin provides that each state will be stored **only once**, equality testing is based on MD5 hashes. Filenames will be **generated from URLs**, SaveHTML will create the directories just as they are in the URL. Directories and files will be created within the **Crawljax output directory**, so you should specify this in the Crawljax configuration. With this, you can store a mirror of a site, but links will not be modified, so they won't work in all cases. If Crawljax meets different DOM states on the same URL, SaveHTML will add a counter to the end of the filename.

##Handy classes

###URL2File - Convert URL to filename
Useful tool for converting an URL to a filename. It builds up a directory path and a filename, so you can create the directories first, then write the file. A '.html' suffix will be added if the URL doesn't end with '.html' or '.htm'. URLs with '/' ending will get an 'index.html' suffix. Special characters will be replaced with '_'.
