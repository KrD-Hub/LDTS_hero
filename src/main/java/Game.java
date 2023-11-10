import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class Game {
    private final Screen screen;
    private Arena arena;

    public Game() throws IOException {
        TerminalSize terminalSize = new TerminalSize(40, 20);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // Esconde o cursor do terminal
        screen.startScreen();
        screen.doResizeIfNecessary();

        arena = new Arena(40, 20); // Inicializa a arena com largura e altura
    }

    private void draw() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        arena.draw(graphics); // Chama o método draw da Arena
        screen.refresh();
    }

    private void processKey(KeyStroke key) {
        arena.processKey(key); // Delega o processamento de tecla para a Arena
    }

    public void run() throws IOException {
        while (true) {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            if (key.getKeyType() == KeyType.EOF) {
                break; // Sai do loop se a tecla EOF for pressionada
            }
        }
    }
}
