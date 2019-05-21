package arxa;

import com.sun.management.OperatingSystemMXBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.management.ManagementFactory;

@RestController
@EnableAutoConfiguration
@ControllerAdvice
public class HealthController {

    /**
     * Used to access system information
     */
    private OperatingSystemMXBean operatingSystemBean =
            (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    /**
     * Global exception handling for this controller
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        return new ResponseEntity<>("Failed to create system health report!\n"
                +exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/health", method=RequestMethod.GET, produces="application/json")
    public HealthReport getHealthReport() {
        return new HealthReport(
                operatingSystemBean.getSystemLoadAverage(),
                operatingSystemBean.getProcessCpuLoad(),
                operatingSystemBean.getTotalPhysicalMemorySize());
    }

}
