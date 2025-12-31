import puzzles.day1.HistorianHysteria;
import puzzles.day2.RedNosedReports;

public class App {
    public static void main(String[] args) throws Exception {

        // day 1
        var day1 = new HistorianHysteria("final.txt");
        day1.solve();

        // day 2
        var day2 = new RedNosedReports("final.txt");
        day2.solve();

    }
}
