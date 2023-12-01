import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{
    public Monster(int x, int y) {
        super(x, y);
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "M");
    }

    public Position nextMove() {
        Position newPos;
        Random random = new Random();
        int r = random.nextInt(4);
        newPos = switch (r) {
            case 0 -> new Position(getPosition().getX() + 1, getPosition().getY());
            case 1 -> new Position(getPosition().getX() - 1, getPosition().getY());
            case 2 -> new Position(getPosition().getX(), getPosition().getY() + 1);
            case 3 -> new Position(getPosition().getX(), getPosition().getY() - 1);
            default -> new Position(getPosition().getX(), getPosition().getY());
        };
        return newPos;
    }

    public void move(Position position) {
        getPosition().setX(position.getX());
        getPosition().setY(position.getY());
    }
}
