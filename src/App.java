import puzzles.day1.SecretEntrance;
import puzzles.sample.day1.HistorianHysteria;
import puzzles.sample.day2.RedNosedReports;

public class App {
    public static void main(String[] args) throws Exception {
        String fileName = getFileName(args);

        // samples

        // // test1
        // var test1 = new HistorianHysteria("final.txt");
        // test1.solve();

        // // test2
        // var test2 = new RedNosedReports("final.txt");
        // test2.solve();

        // day1
        var day1 = new SecretEntrance(fileName);
        day1.solve();
    }

    private static String getFileName(String[] args) {
        for (String arg : args) {
            if (arg.equals("-final")) {
                return "final.txt";
            }
        }
        return "test.txt";
    }
}
