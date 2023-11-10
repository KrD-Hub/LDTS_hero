import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class Game {
    private final Screen screen;
    private Hero hero;

    public Game() throws IOException {
        TerminalSize terminalSize = new TerminalSize(40, 20);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // Esconde o cursor do terminal
        screen.startScreen();
        screen.doResizeIfNecessary();

        hero = new Hero(new Position(10,10)); // Inicializa o herói numa posição inicial
    }

    private void draw() throws IOException {
        screen.clear();
        hero.draw(screen); // Desenha o herói no ecrã
        screen.refresh();
    }

    private void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
            // Outros casos podem ser adicionados aqui, como 'q' para sair
        }
    }

    private void moveHero(Position position) {
        hero.setPosition(position); // Atualiza a posição do herói
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
