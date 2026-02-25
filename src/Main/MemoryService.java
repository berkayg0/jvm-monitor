package Main;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class MemoryService {
	
	private MemoryMXBean memoryMXBean;
	
	public MemoryService() {		
		memoryMXBean = ManagementFactory.getMemoryMXBean();
	}
	
	public long getUsedHeapMB() {
		MemoryUsage heap = memoryMXBean.getHeapMemoryUsage();	
		return heap.getUsed() / 1024 / 1024;		
	}
	
	public long getUsedNonHeapMB() {
		MemoryUsage heap = memoryMXBean.getNonHeapMemoryUsage();	
		return heap.getUsed() / 1024 / 1024;		
	}
	

}
