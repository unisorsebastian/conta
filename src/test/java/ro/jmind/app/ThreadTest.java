package ro.jmind.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class ThreadTest {
    private static Logger LOG = LoggerFactory.getLogger(ThreadTest.class);
    int threadPoolSize = 8;
    private String[] context = {"ADM", "INT", "PEP", "SAE"};
    //    private String[] context = {"ADM"};
    private List<File> fileList;
    private String filePath = "c:/users/sebastian/workspace/testData/";


    @Before
    public void setup() {
        fileList = new ArrayList<>(threadPoolSize);
        int count = 1;
        File f;
        StringBuilder textToAppend = new StringBuilder();
        while (count < threadPoolSize+5) {
            fileList.add(f = new File(String.format("%sfileNumber%d.txt", filePath, count)));
            count++;
            if (f.length() > 0) {
                continue;
            }
            textToAppend = new StringBuilder();
            for (int i = 1; i <= 10; i++) {
                textToAppend.append(String.format("lineNumber: %d file: %sfileNumber%d.txt\n", i, filePath, count));
            }
            appendToFile(f, textToAppend.toString());

        }
    }

    @Test
    public void multiThreadOneTaskPerFileTest() {
        ExecutorService pool = Executors.newFixedThreadPool(threadPoolSize);
        List<Future> todos = new ArrayList<>();
        int threadCount = 0;
        for (File f : fileList) {
            if (threadCount < threadPoolSize) {
                final Future todo = pool.submit(new SomeTask(f));
                todos.add(todo);
                threadCount++;
            } else {
                //wait for a thread


            }
        }
        pool.shutdown();
        while(!pool.isTerminated()){
            System.out.println("wait here");
        }
    }


    private void appendToFile(File file, String text) {
        BufferedWriter writer;
        System.out.println("append------------" + text);
        try {
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.append(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class SomeTask implements Callable {
        File file;

        SomeTask(File file) {
            this.file = file;
        }


        @Override
        public Object call() throws Exception {
            Map<String, String> m = new HashMap<>();
            final Set<Map.Entry<String, String>> entries = m.entrySet();
            return job();

        }

        private List<ProcessResult> job() {
            List<ProcessResult> result = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            int i = 0;
            boolean isWorking = true;
            ProcessResult pr= new ProcessResult();;
            while (isWorking) {
                for (String ctx : context) {
                    final long l = System.currentTimeMillis();
                    pr = new ProcessResult();
                    pr.startTime = System.currentTimeMillis();
                    pr.context = ctx;
                    pr.file = file;
                    sb.append(String.format("thread %s context %s file %s\n", Thread.currentThread().getName(), ctx, file));

                    if (i > 100) {
                        appendToFile(file, sb.toString());
                        sb.setLength(0);
                        isWorking = false;
                        pr.endTime = System.currentTimeMillis();
                    }
                }
                i++;
            }
            return result;
        }
    }


}

class ProcessResult {
    File file;
    String context;
    Long startTime;
    Long endTime;
    String threadId;
    int timeTook = (int) (endTime - startTime);

    public ProcessResult() {
    }
}
