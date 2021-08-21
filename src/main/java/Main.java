import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static final String srcFolder = "src/main/resources/imgSrc";
    static final String dstFolder = "src/main/resources/imgDst";
    static final int cores = Runtime.getRuntime().availableProcessors();

    //set new width for resizing images
    static int newWidth = 300;
    static File srcDir = new File(srcFolder);
    static File[] srcFiles = srcDir.listFiles();


    public static void main(String[] args) throws InterruptedException {

        //Multi-thread
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(cores);
        for (File file : srcFiles
        ) {
            executorService.submit(new ImgResizerMultiThread(file, dstFolder, newWidth));
        }
        executorService.shutdown();
        System.out.println("Duration in Multi-thread, millis:  " + (System.currentTimeMillis() - start));

//**********************************
//        Mono-Thread
//        ImgResizer.setNewWidth(300);
//        ImgResizer.doResize();
//        ImgResizer.getDestinationFolder();

    }
}
