package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.Timer;
import javax.swing.UIManager;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;

public class MainFrame extends JFrame{
	

	 
	private XChartPanel<XYChart> memoryChartPanel;
	private XChartPanel<XYChart> gcCountChartPanel;
	private XChartPanel<XYChart> gcTimeChartPanel;
	private XChartPanel<XYChart> threadChartPanel;
	private XChartPanel<XYChart> classChartPanel;
	private XChartPanel<XYChart> cpuChartPanel;
	
	
	private LinkedList<Integer> timeData = new LinkedList<>();
	private LinkedList<Integer> heapData = new LinkedList<>();
	private LinkedList<Integer> nonHeapData = new LinkedList<>();
	private LinkedList<Integer> gcCountData = new LinkedList<>();
	private LinkedList<Integer> gcTimeData = new LinkedList<>();
	private LinkedList<Integer> threadData = new LinkedList<>();
	private LinkedList<Integer> loadedClassData = new LinkedList<>();
	private LinkedList<Integer> unLoadedClassData = new LinkedList<>();
	private LinkedList<Integer> totalLoadedClassData = new LinkedList<>();
	private LinkedList<Integer> jvmCpuData = new LinkedList<>();
	private LinkedList<Integer> systemCpuData = new LinkedList<>();
	
	private List<String> reportDataList = new ArrayList<>();
	
	private JLabel uptimeLabel = new JLabel("Uptime: 00:00:00");
	
	private GCService gcService;
	private MemoryService memoryService;
	private ThreadService threadService;
	private ClassService classService;
	private CPUService cpuService;


    
	private int time = 0;
	
	
	public MainFrame() {
		
		gcService = new GCService();
		memoryService = new MemoryService();
		threadService = new ThreadService();
		classService = new ClassService();
		cpuService = new CPUService();
		
        timeData.add(time);
        heapData.add(0);
        nonHeapData.add(0);
        gcCountData.add(0);
        gcTimeData.add(0);
        threadData.add(0);
        loadedClassData.add(0);
        unLoadedClassData.add(0);
        totalLoadedClassData.add(0);
        jvmCpuData.add(0);
        systemCpuData.add(0);

        XYChart memoryChart = new XYChartBuilder().title("Memory Usage").xAxisTitle("Time (s)").yAxisTitle("MB").build();
        memoryChart.getStyler().setChartBackgroundColor(UIManager.getColor("Panel.background"));
        memoryChart.getStyler().setPlotBackgroundColor(Color.white);
        memoryChart.getStyler().setMarkerSize(0);
        memoryChart.addSeries("Heap", timeData, heapData);
        memoryChart.addSeries("Non-Heap", timeData, nonHeapData);
        memoryChartPanel = new XChartPanel<>(memoryChart);
        
        XYChart gcChartCount = new XYChartBuilder().title("GC Count").xAxisTitle("Time (s)").yAxisTitle("Count").build();
        gcChartCount.getStyler().setChartBackgroundColor(UIManager.getColor("Panel.background"));
        gcChartCount.getStyler().setPlotBackgroundColor(Color.white);
        gcChartCount.getStyler().setMarkerSize(0);
        gcChartCount.addSeries("GC Count", timeData, gcCountData);
        gcCountChartPanel = new XChartPanel<>(gcChartCount);
        
        XYChart gcChartTime = new XYChartBuilder().title("GC Time").xAxisTitle("Time (s)").yAxisTitle("Time (ms)").build();
        gcChartTime.getStyler().setChartBackgroundColor(UIManager.getColor("Panel.background"));
        gcChartTime.getStyler().setPlotBackgroundColor(Color.white);
        gcChartTime.getStyler().setMarkerSize(0);
        gcChartTime.addSeries("GC Time", timeData, gcTimeData);
        gcTimeChartPanel = new XChartPanel<>(gcChartTime);
        
        XYChart threadChart = new XYChartBuilder().title("Active Thread").xAxisTitle("Time (s)").yAxisTitle("Count").build();
        threadChart.getStyler().setChartBackgroundColor(UIManager.getColor("Panel.background"));
        threadChart.getStyler().setPlotBackgroundColor(Color.white);
        threadChart.getStyler().setMarkerSize(0);
        threadChart.addSeries("Thread", timeData, threadData);
        threadChartPanel = new XChartPanel<>(threadChart);
        
        XYChart classChart = new XYChartBuilder().title("Class").xAxisTitle("Time (s)").yAxisTitle("Count").build();
        classChart.getStyler().setChartBackgroundColor(UIManager.getColor("Panel.background"));
        classChart.getStyler().setPlotBackgroundColor(Color.white);
        classChart.getStyler().setMarkerSize(0);
        classChart.addSeries("Loaded Class", timeData, loadedClassData);
        classChart.addSeries("Unloaded Class", timeData, unLoadedClassData);
        classChart.addSeries("Total Loaded Class", timeData, totalLoadedClassData);
        classChartPanel = new XChartPanel<>(classChart);
        
        XYChart cpuChart = new XYChartBuilder().title("CPU").xAxisTitle("Time (s)").yAxisTitle("Usage").build();
        cpuChart.getStyler().setChartBackgroundColor(UIManager.getColor("Panel.background"));
        cpuChart.getStyler().setPlotBackgroundColor(Color.white);
        cpuChart.getStyler().setYAxisMin(0.0);
        cpuChart.getStyler().setYAxisMax(100.0);
        cpuChart.getStyler().setMarkerSize(0);
        cpuChart.addSeries("JVM CPU", timeData, jvmCpuData);
        cpuChart.addSeries("System CPU", timeData, systemCpuData);
        cpuChartPanel = new XChartPanel<>(cpuChart);
        
        JPanel gcPanel = new JPanel(new GridLayout(2,1));
        gcPanel.add(gcCountChartPanel);
        gcPanel.add(gcTimeChartPanel);
        
        UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
        UIManager.put("TabbedPane.contentOpaque", false);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFocusable(false);
        tabbedPane.add("Memory", memoryChartPanel);
        tabbedPane.addTab("GC", gcPanel);
        tabbedPane.add("Thread", threadChartPanel);
        tabbedPane.add("Class", classChartPanel);
        tabbedPane.add("CPU", cpuChartPanel);

        JPanel bottomContainer = new JPanel(new BorderLayout());
        
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 0, 0));     
        leftPanel.setPreferredSize(new Dimension(250, 40));
        leftPanel.add(uptimeLabel);
        
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        JButton btnReport = new JButton("Report");
        btnReport.setFocusPainted(false);
        btnReport.setBackground(new Color(221,221,221));
        JButton btnGC = new JButton("Force GC");
        btnGC.setFocusPainted(false);
        btnGC.setBackground(new Color(221,221,221));
        centerPanel.add(btnReport);
        centerPanel.add(btnGC);
        
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        rightPanel.setPreferredSize(new Dimension(250, 40));
        
        JPanel rightTextPanel = new JPanel(new GridLayout(2, 1));
        rightTextPanel.setOpaque(false); 
        
        JLabel javaVersion = new JLabel("Java Version: " + System.getProperty("java.version"));
        JLabel jvmVersion = new JLabel("JVM: " + System.getProperty("java.vm.name"));
        
        rightTextPanel.add(javaVersion);
        rightTextPanel.add(jvmVersion);
        
        rightPanel.add(rightTextPanel);
        
        bottomContainer.add(leftPanel, BorderLayout.WEST);
        bottomContainer.add(centerPanel, BorderLayout.CENTER);
        bottomContainer.add(rightPanel, BorderLayout.EAST);
        
              
        
        this.setLayout(new BorderLayout());
        this.add(tabbedPane, BorderLayout.CENTER);
        this.add(bottomContainer, BorderLayout.SOUTH);

        
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateChart();
            }
        });
        timer.start();
        
        btnReport.addActionListener(e -> {
        	
        	String fileName = "JVM_Report.csv";

            try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
                

                writer.println("Time;Heap(MB);Non-Heap(MB);GC Count;GC Time(ms);GC Type;Threads;Loaded Classes;Unloaded Classes;Total Loaded Classes;JVM CPU(%);System CPU(%)");

                for (String line : reportDataList) {
                    writer.println(line);
                }
 
                JOptionPane.showMessageDialog(this, "Report successfully generated!\nFile Name: " + fileName, "JVM Monitor", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An error occurred while saving the report:\n" + ex.getMessage(), "JVM Monitor", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnGC.addActionListener(e ->{
        	System.gc();
        	JOptionPane.showMessageDialog(this, "Garbage Collector triggered successfully!", "JVM Monitor", JOptionPane.INFORMATION_MESSAGE);
     	
        });
        
        URL iconURL = getClass().getResource("/icon.png");       
        ImageIcon icon = new ImageIcon(iconURL);

        this.setIconImage(icon.getImage());
        this.setTitle("JVM Monitor");
        this.pack();
        this.setResizable(false);
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			
	}

	
	private void updateChart() {
		
		long uptimeMs = ManagementFactory.getRuntimeMXBean().getUptime();
		long seconds = uptimeMs / 1000;
		
		long uptimeSeconds = seconds % 60;
		long uptimeMinutes = (seconds / 60) % 60;
		long uptimeHours = seconds / 3600;
		
		uptimeLabel.setText(String.format("Uptime: %02d:%02d:%02d", uptimeHours, uptimeMinutes, uptimeSeconds));
		
		time++;

		GCInfo gcInfo = gcService.getGcInfo();
		
        String timeStamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));


		System.out.println("Time: " + timeStamp);
		System.out.println("Used Heap: " + memoryService.getUsedHeapMB() + " MB | Used Non-Heap: " + memoryService.getUsedNonHeapMB() + " MB");
		System.out.println("GC Count : " + gcInfo.getCount());
		System.out.println("GC Time  : " + gcInfo.getTime() + " ms");
		System.out.println("GC Name  : " + gcInfo.getName());
		System.out.println("Threads  : " + threadService.getThreadCount());
		System.out.println("Loaded Class  : " + classService.getLoadedClass());
		System.out.println("Unloaded Class  : " + classService.getUnloadedClass());
		System.out.println("Total Loaded Class  : " + classService.getTotalLoadedClass());
		System.out.println("JVM CPU Usage : %" + cpuService.getJVMCpuUsage());
		System.out.println("System CPU Usage : %" + cpuService.getSystemCpuUsage());
		       
        String reportLine = timeStamp + ";" + 
        		memoryService.getUsedHeapMB() + ";" + 
        		memoryService.getUsedNonHeapMB() + ";" + 
        		gcInfo.getCount() + ";" + 
        		gcInfo.getTime() + ";" + 
        		gcInfo.getName() + ";" +
        		threadService.getThreadCount() + ";" + 
        		classService.getLoadedClass() + ";" + 
        		classService.getUnloadedClass() + ";" + 
        		classService.getTotalLoadedClass() + ";" +
        		cpuService.getJVMCpuUsage() + ";" + 
        		cpuService.getSystemCpuUsage();
        
        reportDataList.add(reportLine);

        timeData.add(time);
        heapData.add((int) memoryService.getUsedHeapMB());
        nonHeapData.add((int) memoryService.getUsedNonHeapMB());
        gcCountData.add((int) gcInfo.getCount());
        gcTimeData.add((int) gcInfo.getTime());
        threadData.add(threadService.getThreadCount());
        loadedClassData.add(classService.getLoadedClass());
        unLoadedClassData.add((int) classService.getUnloadedClass());
        totalLoadedClassData.add((int) classService.getTotalLoadedClass());
        jvmCpuData.add((int) cpuService.getJVMCpuUsage());
        systemCpuData.add((int) cpuService.getSystemCpuUsage());
        
        

        if (timeData.size() > 60) {
            timeData.removeFirst();
            heapData.removeFirst();
            nonHeapData.removeFirst();
            gcCountData.removeFirst();
            gcTimeData.removeFirst();
            threadData.removeFirst();
            loadedClassData.removeFirst();
            unLoadedClassData.removeFirst();
            totalLoadedClassData.removeFirst();
            jvmCpuData.removeFirst();
            systemCpuData.removeFirst();
        }

        memoryChartPanel.getChart().updateXYSeries("Heap", timeData, heapData, null);
        memoryChartPanel.getChart().updateXYSeries("Non-Heap", timeData, nonHeapData, null);
        memoryChartPanel.repaint();
        
        gcCountChartPanel.getChart().updateXYSeries("GC Count", timeData, gcCountData, null);
        gcCountChartPanel.repaint();
        gcTimeChartPanel.getChart().updateXYSeries("GC Time", timeData, gcTimeData, null);
        gcTimeChartPanel.repaint();
        
        threadChartPanel.getChart().updateXYSeries("Thread", timeData, threadData, null);
        threadChartPanel.repaint();
        
        classChartPanel.getChart().updateXYSeries("Loaded Class", timeData, loadedClassData, null);
        classChartPanel.repaint();
        classChartPanel.getChart().updateXYSeries("Unloaded Class", timeData, unLoadedClassData, null);
        classChartPanel.repaint();
        classChartPanel.getChart().updateXYSeries("Total Loaded Class", timeData, totalLoadedClassData, null);
        classChartPanel.repaint();
        
        cpuChartPanel.getChart().updateXYSeries("JVM CPU", timeData, jvmCpuData, null);
        cpuChartPanel.getChart().updateXYSeries("System CPU", timeData, systemCpuData, null);
        cpuChartPanel.repaint();
        
        
    }
	
	
	
	

}
