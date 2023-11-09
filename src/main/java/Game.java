// Importações necessárias
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

// Classe Game
public class Game {
    private final Screen screen;
    private int x = 10;
    private int y = 10;

    public Game() throws IOException {
        TerminalSize terminalSize = new TerminalSize(40, 20);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // Não precisamos de um cursor visível
        screen.startScreen();           // Inicia o ecrã para interação
        screen.doResizeIfNecessary();   // Redimensiona o ecrã se for necessário
    }

    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        screen.refresh();
    }

    private void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.ArrowUp) y--;
        if (key.getKeyType() == KeyType.ArrowDown) y++;
        if (key.getKeyType() == KeyType.ArrowLeft) x--;
        if (key.getKeyType() == KeyType.ArrowRight) x++;
    }

    public void run() throws IOException {
        while (true) {
            draw(); // Chama o método draw para desenhar na tela
            KeyStroke key = screen.readInput(); // Lê tecla pressionada

            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                screen.close(); // Fecha o ecrã ao sair do jogo
                break; // Sai do loop
            } else if (key.getKeyType() == KeyType.EOF) {
                break; // Sai do loop se a janela do terminal for fechada
            }

            processKey(key); // Processa a tecla pressionada
        }
    }

    public static void main(String[] args) {
        try {
            Game game = new Game();
            game.run();
        } catch (IOException e) {
            e.printStackTrace(); // Imprime o erro no console se algo correr mal
        }
    }
}
