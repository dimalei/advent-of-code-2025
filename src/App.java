import puzzles.day1.SecretEntrance;
import puzzles.day2.GiftShop;
import puzzles.day3.Lobby;
import puzzles.day4.PrintingDepartment;
import puzzles.day5.Cafeteria;
import puzzles.day6.TrashCompactor;
import puzzles.day7.Laboratories;

public class App {
    public static void main(String[] args) throws Exception {
        String fileName = getFileName(args);

        // day1
        var day1 = new SecretEntrance(fileName);
        day1.solve();

        // day2
        var day2 = new GiftShop(fileName);
        day2.solve();

        // day3
        var day3 = new Lobby(fileName);
        day3.solve();

        // day4
        var day4 = new PrintingDepartment(fileName);
        day4.solve();

        // day5
        var day5 = new Cafeteria(fileName);
        day5.solve();

        // day6
        var day6 = new TrashCompactor(fileName);
        day6.solve();

        // day7
        var day7 = new Laboratories(fileName);
        day7.solve();
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
