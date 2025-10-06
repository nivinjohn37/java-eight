import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

public class CustomTestThree {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        //Future<String> future = executor.submit(() -> "Hello Future");
        //System.out.println(future.get());  // blocks until result is available
        Callable<String> callableTask = () -> {
            String currentThreadName = Thread.currentThread().getName();
            System.out.println("Current thread name: " + currentThreadName);
            System.out.printf("[%s] Task started at: %s\n", currentThreadName, LocalTime.now().format(FORMATTER));

            Thread.sleep(2000);
            System.out.printf("[%s] Task ended at: %s\n", currentThreadName, LocalTime.now().format(FORMATTER));
            return "Hello Future after 2 seconds";
        };

        try {
            Future<String> future = executor.submit(callableTask);
            String mainThreadName = Thread.currentThread().getName();

            System.out.printf("\n[%s] Task submitted. Future object received immediately.\n", mainThreadName);

            // --- THE BLOCKING POINT ---
            System.out.printf("\n[%s] Calling future.get(). Main thread will now BLOCK and wait...\n", mainThreadName);

            String result = future.get();
            // The code only proceeds after the worker thread finishes
            System.out.printf("\n[%s] BLOCKING COMPLETE! Result received at: %s\n", mainThreadName, LocalTime.now().format(FORMATTER));
            System.out.println("Final Result: " + result);
        } catch (InterruptedException e) {
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
