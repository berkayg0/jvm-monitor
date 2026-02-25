package Main;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

public class ClassService {
	
	private ClassLoadingMXBean classBean;
	
	public ClassService() {
		classBean = ManagementFactory.getClassLoadingMXBean();		
	}
	
	public int getLoadedClass() {
		return classBean.getLoadedClassCount();
	}
	
	public long getUnloadedClass() {
		return classBean.getUnloadedClassCount();		
	}
	
	public long getTotalLoadedClass() {
		return classBean.getTotalLoadedClassCount();
	}

}
