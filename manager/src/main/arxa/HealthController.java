package arxa;

import com.sun.management.OperatingSystemMXBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;

@RestController
@EnableAutoConfiguration
public class HealthController {

    private OperatingSystemMXBean operatingSystemBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    @RequestMapping(value="/health", method=RequestMethod.GET, produces="application/json")
    public HealthReport getHealthReport() {
        return new HealthReport(
                operatingSystemBean.getSystemLoadAverage(),
                operatingSystemBean.getProcessCpuLoad(),
                operatingSystemBean.getTotalPhysicalMemorySize());
    }

}
