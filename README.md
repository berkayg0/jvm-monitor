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
<img width="981" height="689" alt="Image" src="https://github.com/user-attachments/assets/a68806a7-cde6-4fb4-8cfc-5bd2270013e3" />

### GC Tab
<img width="980" height="688" alt="Image" src="https://github.com/user-attachments/assets/a0a61098-9013-476c-8159-6533774cf3c2" />

### Thread Tab
<img width="980" height="688" alt="Image" src="https://github.com/user-attachments/assets/7323e9a7-6efa-4162-9063-a12033bb7b15" />

### Class Tab
<img width="980" height="690" alt="Image" src="https://github.com/user-attachments/assets/6469ad39-7bae-434a-8288-6e409b1b7328" />

### CPU Tab
<img width="980" height="687" alt="Image" src="https://github.com/user-attachments/assets/846a19e8-daa7-49d8-9e8b-a017665be4b4" />

## 🛠️ Built With

* **Java (Swing):** For the native graphical user interface.
* **ManagementFactory API:** For extracting deep JVM metrics.
* **XChart:** For rendering high-performance, real-time charts.

## 🚀 Getting Started

You don't need to compile the code to use the tool! 

1. Go to the [Releases](../../releases) page.
2. Download the latest `jvm_monitor.jar` file.
3. Double-click the `.jar` file, or run it via terminal:
   ```bash
   java -jar jvm_monitor.jar
