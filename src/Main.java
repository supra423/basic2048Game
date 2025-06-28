public class Main {
    public static void main(String[] args) {
        Window mainWindow = new Window();
        GameEngine newGame = new GameEngine(mainWindow);
        mainWindow.add(newGame);
    }
}