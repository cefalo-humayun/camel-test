package http;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author humayun
 */
public class MyStopWatch {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Stopwatch stopwatch;
    private Stopwatch countTime;

    public MyStopWatch() {
        stopwatch = new Stopwatch();
    }

    public void start() {
        stopwatch.start();
    }

    public void stop() {
        countTime = stopwatch.stop();
        //stopwatch.reset();
    }

    public void sleep() {
        try {
            Thread.sleep(3000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getCountTime() {
        System.err.println(countTime);
    }
}
