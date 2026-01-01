import puzzles.day1.SecretEntrance;
import puzzles.day2.GiftShop;
import puzzles.day3.Lobby;

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
