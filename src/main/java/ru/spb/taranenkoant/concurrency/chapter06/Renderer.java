package ru.spb.taranenkoant.concurrency.chapter06;


import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 28.10.2025
 */
public class Renderer {

    private final ExecutorService executor;

    public Renderer(ExecutorService executorService) {
        this.executor = executorService;
    }

    void renderPage(CharSequence source) {
        final List<ImageInfo> info = scanForImageInfo(source);
        CompletionService<ImageData> completionService = new ExecutorCompletionService<>(executor);
        for (final ImageInfo imageInfo : info) {
            completionService.submit(() -> imageInfo.downloadImage());
        }
        renderText(source);

        try {
            for (int t = 0, n = info.size(); t < n; t++) {
                Future<ImageData> f = completionService.take();
                ImageData imageData = f.get();
                renderImage(imageData);
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw  launderThrowable(e.getCause());
        }
    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        return Collections.emptyList();
    }

    private void renderText(CharSequence source) {

    }

    private void renderImage(ImageData imageData) {

    }

    private RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException)
            return (RuntimeException) t;
        else if (t instanceof Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Не является непроверяемым", t);
    }
}
