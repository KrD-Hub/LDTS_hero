import java.io.IOException;

// Classe Application
public class Application {
    public static void main(String[] args) {
        try {
            Game game = new Game(); // Cria um novo jogo
            game.run(); // Executa o jogo
        } catch (IOException e) {
            e.printStackTrace(); // Imprime o erro no console
        }
    }
}
