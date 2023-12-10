import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class CompletableFuturesDemo {
    public static void show() {
        // Runnable task = () -> System.out.println("1");
        // var future = CompletableFuture.runAsync(task); // does not return a value
        // Supplier<Integer> task = () -> 1;
        // var future = CompletableFuture.supplyAsync(task); // returns a value

        // try {
        // var result = future.get();
        // System.out.println(result);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // } catch (ExecutionException e) {
        // e.printStackTrace();
        // }
        // ************************************************
        // 9- running code on completion
        // var future = CompletableFuture.supplyAsync(() -> 1);

        // !IMPORTANT .thenRun to run after the future
        // future.thenRun(() -> { // main
        // future.thenRunAsync(() -> { // ForkJoinPool.commonPool-worker-1

        // !IMPORTANT .thenAccept to consume the result of the future
        // future.thenAccept(result -> { // main
        // future.thenAcceptAsync(result -> { // ForkJoinPool.commonPool-worker-1
        // System.out.println(Thread.currentThread().getName());
        // System.out.println(result);
        // });

        // ************************************************
        // 11- transforming a completable future

        // var future = CompletableFuture.supplyAsync(() -> 20);
        // !IMPORTANT .thenApply to run intermediate operation on the result of the
        // future
        // future
        // .thenApply(celcius -> (celcius * 1.8) + 32) // convert to fahrenheit
        // .thenAccept(fahrenheit -> System.out.println(fahrenheit)); // print the
        // result

        // ************************************************
        // 12- composing completable futures

        // !IMPORTANT .thenCompose to run a completable future after another
        // CompletableFuture.supplyAsync(() -> "email")
        // .thenCompose(email -> CompletableFuture.supplyAsync(() -> "playlist"))
        // .thenAccept(playlist -> System.out.println(playlist));

        // ************************************************
        // 13- combining completable futures
        // !IMPORTANT .thenCombine to combine result of 2 async operations

        // var first = CompletableFuture
        // .supplyAsync(() -> "20USD")
        // .thenApply(str -> {
        // var price = str.replace("USD", "");
        // return Integer.parseInt(price);
        // });
        // var second = CompletableFuture.supplyAsync(() -> 0.9);

        // first
        // .thenCombine(second, (f, s) -> f * s)
        // .thenAccept(result -> System.out.println(result));

        // ************************************************
        // 14- waiting for many tasks to complete
        // !IMPORTANT .allOf to wait for all tasks to complete

        // var first = CompletableFuture.supplyAsync(() -> 1);
        // var second = CompletableFuture.supplyAsync(() -> 2);
        // var third = CompletableFuture.supplyAsync(() -> 3);

        // var all = CompletableFuture.allOf(first, second, third);
        // all.thenRun(() -> {
        // try {
        // var firstResult = first.get();
        // System.out.println(firstResult);
        // } catch (InterruptedException | ExecutionException e) {
        // e.printStackTrace();
        // }
        // System.out.println("All tasks completed successfully.");
        // });

        // ************************************************
        // 15- waiting for the first task
        // !IMPORTANT .anyOf to wait for the first task to complete

        var first = CompletableFuture.supplyAsync(() -> {
            LongTask.simulate();
            return 20;
        });
        var second = CompletableFuture.supplyAsync(() -> 2);

        CompletableFuture.anyOf(first, second)
                .thenAccept(result -> System.out.println(result));

    }
}
