package Main;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class GCService {
	
	
	private boolean isGcHappened = false;
	private long lastCount = 0;
	private long lastTime = 0;
	
	public GCInfo getGcInfo() {
		
		long totalCount = 0;
		long totalTime = 0;
		StringBuilder gcNamesBuilder = new StringBuilder();
		
		
		List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
		
		for(GarbageCollectorMXBean gc : gcBeans) {
			
			long count = gc.getCollectionCount();
			long time = gc.getCollectionTime();
			
			if(count != - 1) {	
				totalCount += count;			
			}	
			if(time != - 1) {	
				totalTime += time;			
			}
			
			if(gcNamesBuilder.length() > 0) {
				gcNamesBuilder.append(" & ");
			}
			gcNamesBuilder.append(gc.getName());
		}
		
		String gcNames = gcNamesBuilder.toString();
		
		if(!isGcHappened) {
			lastCount = totalCount;
			lastTime = totalTime;
			isGcHappened = true;
			return new GCInfo(0, 0, gcNames);		
		}
		
		long countDiff = totalCount - lastCount;
		long timeDiff = totalTime - lastTime;
		
		lastCount = totalCount;
		lastTime = totalTime;

		
		return new GCInfo(countDiff, timeDiff, gcNames);
		
		
	}

}
