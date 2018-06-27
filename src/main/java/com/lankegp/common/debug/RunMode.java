package com.lankegp.common.debug;

import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * Created by liugongrui on 2018/6/25.
 */
public class RunMode {

    public static boolean isDebug() {
        List<String> arguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
        boolean debuging = false;
        for (String str : arguments) {
            if (str.startsWith("-agentlib")) {
                debuging = true;
            }
        }
        return debuging;
    }
}
