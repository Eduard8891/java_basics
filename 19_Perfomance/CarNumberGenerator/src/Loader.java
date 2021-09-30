import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class Loader {
    static char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        executor.setMaximumPoolSize(6);
        for (char firstLetter : letters) {
            BufferedWriter writer = new BufferedWriter(new FileWriter("res/number-" + firstLetter + ".txt"));
            Runner runner = new Runner(firstLetter, writer, letters);
            executor.execute(runner);
        }
        executor.shutdown();
    }
}

class Runner implements Runnable {
    char firstLetter;
    BufferedWriter writer;
    char[] letters;

    Runner(char firstLetter, BufferedWriter writer, char[] letters) {
        this.firstLetter = firstLetter;
        this.writer = writer;
        this.letters = letters;

    }

    @Override
    public void run() {
        StringBuilder builder = new StringBuilder();
        for (int regionCode = 1; regionCode < 100; regionCode++) {
            for (int number = 1; number < 1000; number++) {
                for (char secondLetter : letters) {
                    for (char thirdLetter : letters) {
                        builder.append(firstLetter);
                        if (number < 10) {
                            builder.append("00").append(number);
                        } else if (number < 100 & number > 10) {
                            builder.append('0').append(number);
                        } else builder.append(number);
                        builder.append(secondLetter).append(thirdLetter);
                        if (regionCode < 10) {
                            builder.append('0').append(regionCode);
                        } else builder.append(regionCode);
                        builder.append("\n");
                    }
                }
            }
        }
        try {
            writer.write(builder.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}