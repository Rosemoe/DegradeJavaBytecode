package io.github.rosemoe.djb;

import com.android.tools.r8.D8;
import com.googlecode.dex2jar.tools.Dex2jarCmd;

import java.io.File;

public class Main {

    /**
     * Enter your jar path as the first argument
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("No argument. Pass your jar file path");
        }
        System.out.println("Running D8...");
        D8.main(new String[]{"--min-api", "21", "--release", args[0]});
        System.out.println("Running dex2jar...");;
        Dex2jarCmd.main("-o", "classes_degraded.jar", "classes.dex");
        System.out.println("Remove cache...");
        if (!new File("classes.dex").delete()) {
            System.err.println("Failed to delete classes.dex");
        }
        System.out.println("Task finished");
    }
}
