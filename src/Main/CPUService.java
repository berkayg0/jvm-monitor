package Main;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class CPUService {
	
	private OperatingSystemMXBean osBean;
	
	public CPUService() {
		
		osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);		
		
	}
	
	public int getJVMCpuUsage() {
		
		double realCPU = osBean.getProcessCpuLoad();
		int currentCPU = (int) Math.round((realCPU * 100));
		
		return currentCPU;
		
	}
	
	public int getSystemCpuUsage() {
		
		double realCPU = osBean.getCpuLoad();
		int currentCPU = (int) Math.round((realCPU * 100));
		
		return currentCPU;
			
	}

}
