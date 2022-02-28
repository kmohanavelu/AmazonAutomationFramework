package automation.utitilites;

import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Utilities {

    private static Logger log = Logger.getLogger(Utilities.class);


    /**
     * This method runs Shell/Batch script based on the script path that is provided.
     * It prints the shell output
     * **/
    public static void executeScript(String scriptPath) {
        try {
            Process process;
            File sourceDir = new File("src");
            File script = new File(sourceDir, scriptPath);
            if (System.getProperty("os.name").contains("Windows")) {
                String[] cmd = {script.getAbsolutePath()};
                process = new ProcessBuilder(cmd).start();
            } else {
                Runtime.getRuntime().gc();
                String[] cmd = {"sh", script.getAbsolutePath()};
                process = new ProcessBuilder(cmd).start();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            String s;
            log.info("execution "+scriptPath+" script...");
            while ((s = reader.readLine()) != null) {
                log.info("$: " + s);
            }
            process.destroy();
        } catch (Exception e) {
            log.error("Exception occurred while executing shell script ",e);
        }
    }
}
