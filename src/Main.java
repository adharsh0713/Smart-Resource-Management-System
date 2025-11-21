import com.smartresourcemanagement.controller.ResourceManager;
import com.smartresourcemanagement.controller.TaskScheduler;
import com.smartresourcemanagement.controller.AutoBackupThread;
import com.smartresourcemanagement.view.ConsoleUI;
import com.smartresourcemanagement.view.MainGUI;

public class Main {

    public static void main(String[] args) {

        // Create global resource manager
        ResourceManager manager = new ResourceManager();
        TaskScheduler scheduler = new TaskScheduler();

        // Start auto backup thread (optional but safe)
        AutoBackupThread backupThread = new AutoBackupThread(
            "backup.text",
            manager.getAllResources(),
            500000
        );
        backupThread.start();

        // Choose UI mode: console or GUI
        boolean useGUI = true;  // change to false for console mode

        if (useGUI) {
            MainGUI gui = new MainGUI(manager, scheduler);
            gui.setVisible(true);
        } else {
            ConsoleUI console = new ConsoleUI(manager);
            console.start();  // run console menu loop
        }
    }
}
