package arxa;

public class HealthReport {

    private double systemLoadAverage;
    private double cpuUsage;
    private long memoryUsage;

    public HealthReport(double systemLoadAverage, double cpuUsage, long memoryUsage) {
        this.systemLoadAverage = systemLoadAverage;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
    }

    public double getSystemLoadAverage() {
        return systemLoadAverage;
    }

    public void setSystemLoadAverage(double systemLoadAverage) {
        this.systemLoadAverage = systemLoadAverage;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public long getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(long memoryUsage) {
        this.memoryUsage = memoryUsage;
    }
}
