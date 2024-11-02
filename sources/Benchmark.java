import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Benchmark {

    static String data = """
        URLs:
        http://www.google.com
        https://www.google.com
        http://google.com
        https://google.com
        http://www.google.com/
        https://www.google.com/
        http://google.com/
        https://google.com/
        http://www.google.com/index.html
        https://www.google.com/index.html
        http://google.com/index.html
        https://google.com/index.html
        """;

    public static void main(String... args) {
        //System.out.println(data);
        measure(data, Pattern.compile("([^a-zA-Z]|^)(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w\\.-]*)*\\/?([^a-zA-Z]|$)").matcher(""));
    }

    private static void measure(String data, Matcher matcher) {
        System.out.println("tic...");

        long startTime = System.nanoTime();

        int count = 0;
        for (int i=1; i<=10000; i++) {
            matcher.reset(data).matches();
            while (matcher.find()) {
                //System.out.println(data.substring(matcher.start(), matcher.end()));
                count += 1;
            }
        }

        long elapsed = System.nanoTime() - startTime;

        System.out.println(" toc (" + count + " matches).");
        System.out.println((elapsed / 1e9) + " seconds");
    }
}