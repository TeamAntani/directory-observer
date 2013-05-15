package main.java.directoryobserver;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;

import java.io.File;

public class DirectoryObserver
{
	private FileAlterationObserver observer;
	private Logger log = Logger.getLogger(DirectoryObserver.class);
	
	public DirectoryObserver(File directory)
	{
        observer = new FileAlterationObserver(directory, FileFilterUtils.suffixFileFilter(".done"));
	}
	
	public void addListener(NewFileListener listener)
	{
		observer.addListener(new DoneFileAlterationListener(listener));
	}
	
	public void start() throws Exception
	{
		FileAlterationMonitor monitor = new FileAlterationMonitor(10, observer);
		monitor.start();
		
		log.info("Watching for new files...");
	}
}
