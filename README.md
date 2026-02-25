# JVM Monitoring Tool

A lightweight, real-time desktop application built in Java to monitor the performance and memory usage of the Java Virtual Machine (JVM). 

This tool provides a clean and responsive dashboard to track critical system metrics natively without heavy overhead.

## ✨ Features

* **Real-time JVM metrics visualization:** Smooth and continuous XChart graphs updating every second.
* **Memory Monitoring:** Live tracking of Heap and Non-Heap memory usage.
* **Garbage Collection Statistics:** Cumulative GC run counts and execution times.
* **Thread Monitoring:** Tracks the number of active live threads.
* **CPU Usage Tracking:** Real-time percentage of both JVM-specific and overall System CPU usage.
* **Class Loading Metrics:** Displays loaded, unloaded, and total loaded classes.
* **Report Export:** Generate and save a detailed `.csv` snapshot of all metrics at any given moment.
* **Force GC Trigger:** Manually invoke the Garbage Collector via the dashboard.

## 📸 Screenshots

### Memory Tab
<img width="983" height="687" alt="Image" src="https://github.com/user-attachments/assets/d55f1dd1-c204-4307-aff2-2ecbace16d85" />

### GC Tab
<img width="980" height="688" alt="Image" src="https://github.com/user-attachments/assets/a0a61098-9013-476c-8159-6533774cf3c2" />

### Thread Tab
<img width="983" height="688" alt="Image" src="https://github.com/user-attachments/assets/8e1b2a6f-3240-48f7-9f94-75aca04558ec" />

### Class Tab
<img width="981" height="688" alt="Image" src="https://github.com/user-attachments/assets/86f521c0-9891-46de-a6e3-d2d575bb17fa" />

### CPU Tab
<img width="983" height="689" alt="Image" src="https://github.com/user-attachments/assets/b2f400ad-946f-4da2-b0f9-bf33a872a007" />

## 🛠️ Built With

* **Java (Swing):** For the native graphical user interface.
* **ManagementFactory API:** For extracting deep JVM metrics.
* **XChart:** For rendering high-performance, real-time charts.

## Getting Started

You don't need to compile the code to use the tool! 

1. Go to the [Releases](../../releases) page.
2. Download the latest `jvm-monitor.jar` file.
3. Double-click the `.jar` file, or run it via terminal:
   ```bash
   java -jar jvm-monitor.jar
