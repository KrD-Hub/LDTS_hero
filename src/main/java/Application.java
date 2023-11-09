

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try {
            // Configura o tamanho do terminal para 40 colunas e 20 linhas
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);

            // Cria o terminal com o tamanho definido
            Terminal terminal = terminalFactory.createTerminal();

            // Cria o ecrã baseado no terminal criado e inicia-o
            Screen screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);  // Não precisamos de um cursor visível
            screen.startScreen();            // Inicia o ecrã para interação
            screen.doResizeIfNecessary();    // Redimensiona o ecrã se for necessário

            // Limpa o ecrã e desenha um caracter 'X' na posição (10, 10)
            screen.clear();
            screen.setCharacter(10, 10, TextCharacter.fromCharacter('X')[0]);
            screen.refresh();  // Atualiza o ecrã para mostrar as alterações feitas

            // Código para manter o programa em execução até que seja fechado ou interrompido
            // Por exemplo, podemos esperar por uma tecla ser pressionada antes de terminar
            screen.readInput();

            // Encerrar corretamente o ecrã ao finalizar o programa
            screen.close();
        } catch (IOException e) {
            e.printStackTrace();  // Imprime o erro no console se algo correr mal
        }
    }
}
