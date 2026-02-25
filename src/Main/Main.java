package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	

	
	static ArrayList<byte[]> deneme = new ArrayList<>();
	static Map<Integer, String> kabusHaritasi = new HashMap<>();


	public static void main(String[] args) throws InterruptedException {

		
		for(int i = 0; i < 25; i++) {
			deneme.add(new byte[1024*1024]);
		}

		MemoryService memoryService = new MemoryService();
		GCService gcService = new GCService();
		ThreadService threadService = new ThreadService();
		
		while(true) {	
			

			for (int i = 0; i < 500_000; i++) {
				kabusHaritasi.put(i, "GC için Kabus Veri " + i);
            }

			System.gc(); 
			
			GCInfo gcInfo = gcService.getGcInfo();

			System.out.println("Used Heap: " + memoryService.getUsedHeapMB() + " MB | Used Non-Heap: " + memoryService.getUsedNonHeapMB() + " MB");
			System.out.println("GC Count : " + gcInfo.getCount());
			System.out.println("GC Time  : " + gcInfo.getTime() + " ms");
			System.out.println("GC Name  : " + gcInfo.getName());
			System.out.println("Threads  : " + threadService.getThreadCount());


			kabusHaritasi.clear();
			
			Thread.sleep(1000);
		}
		

	}

}
