import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Demonstrates how Future.get() blocks the calling thread until the result
 * from the asynchronous task is available.
 */
public class FutureBlockingDemo {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) {
        // 1. Setup the Executor
        // We use a single thread pool so we know the task will run immediately.
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        System.out.println("--- Future.get() Blocking Demonstration ---");
        System.out.println("Start Time: " + LocalTime.now().format(FORMATTER));
        
        // Define a task that takes 5 seconds
        Callable<String> longRunningTask = () -> {
            String workerName = Thread.currentThread().getName();
            System.out.printf("[%s] Task started at: %s\n", workerName, LocalTime.now().format(FORMATTER));
            
            // Simulate 5 seconds of work (e.g., network latency, file processing)
            Thread.sleep(5000); 
            
            System.out.printf("[%s] Task finished at: %s\n", workerName, LocalTime.now().format(FORMATTER));
            return "Task Complete after 5 seconds!";
        };

        try {
            // 2. Submit the task (Async)
            Future<String> future = executor.submit(longRunningTask);
            String mainThreadName = Thread.currentThread().getName();

            System.out.printf("\n[%s] Task submitted. Future object received immediately.\n", mainThreadName);
            
            // --- THE BLOCKING POINT ---
            System.out.printf("\n[%s] Calling future.get(). Main thread will now BLOCK and wait...\n", mainThreadName);
            
            
            // 3. Call get() (Synchronous Retrieval)
            String result = future.get(); // This line blocks for 5 seconds

            // The code only proceeds after the worker thread finishes
            System.out.printf("\n[%s] BLOCKING COMPLETE! Result received at: %s\n", mainThreadName, LocalTime.now().format(FORMATTER));
            System.out.println("Final Result: " + result);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            // Cleanup
            executor.shutdown();
            try {
                if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        }
        System.out.println("\nEnd Time: " + LocalTime.now().format(FORMATTER));
        System.out.println("----------------------------------------------");
    }
}
