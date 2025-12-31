import puzzles.sample.day1.HistorianHysteria;
import puzzles.sample.day2.RedNosedReports;

public class App {
    public static void main(String[] args) throws Exception {

        // samples

        // // test1
        var day1 = new HistorianHysteria("final.txt");
        day1.solve();

        // // test2
        var day2 = new RedNosedReports("final.txt");
        day2.solve();

    }
}
