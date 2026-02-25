package Main;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class ThreadService {
	
	private ThreadMXBean threadMXBean;
	
	public ThreadService() {	
		threadMXBean = ManagementFactory.getThreadMXBean();		
	}
	
	public int getThreadCount() {
		return threadMXBean.getThreadCount();		
	}

}
