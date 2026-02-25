package Main;

public class GCInfo {
	
	private long count;
	private long ms;
	private String name;
	
	
	public GCInfo(long count, long ms, String name) {
		this.count = count;
		this.ms = ms;
		this.name = name;
	}
	
	public long getCount() { return this.count; }
	public long getTime() { return this.ms; }
	public String getName() { return this.name; }

}
