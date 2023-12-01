import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Arena {
    private final int width;
    private final int height;
    private final Hero hero;
    private final List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        hero = new Hero(10, 10);
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    public void processKey(KeyStroke key) {
        for (Monster monster : monsters) {
            moveMonster(monster);
        }
        switch (key.getKeyType()) {
            case ArrowLeft -> moveHero(hero.moveLeft());
            case ArrowRight -> moveHero(hero.moveRight());
            case ArrowUp -> moveHero(hero.moveUp());
            case ArrowDown -> moveHero(hero.moveDown());
        }
    }

    private void moveMonster(Monster monster) {
        Position newPos = monster.nextMove();
        if (canHeroMove(newPos)) {
            monster.move(newPos);
        }
    }

    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for (Wall wall : walls) {
            wall.draw(graphics);
        }
        for (Coin coin : coins) {
            coin.draw(graphics);
        }
        for (Monster monster : monsters) {
            monster.draw(graphics);
        }
    }

    private void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);
        }
        retrieveCoins();
    }

    private boolean canHeroMove(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().getX() == position.getX() && wall.getPosition().getY() == position.getY()) {
                return false;
            }
        }
        return 0 <= position.getX() && position.getX() < width && position.getY() < height && 0 <= position.getY();
    }

    private void retrieveCoins() {
        for (Coin coin : coins) {
            if (coin.getPosition().equals(hero.getPosition())) {
                coins.remove(coin);
                break;
            }
        }
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for(int i=0; i<5; i++){
            Coin newcoin = new Coin(random.nextInt(width-2) + 1, random.nextInt(height-2)+1);
            if(!coins.contains(newcoin) && !newcoin.getPosition().equals(hero.getPosition()))
                coins.add(newcoin);
        }
        return coins;
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for(int i=0; i<5; i++){
            Monster newMonsters = new Monster(random.nextInt(width-2) + 1, random.nextInt(height-2)+1);
            if(!monsters.contains(newMonsters) && !newMonsters.getPosition().equals(hero.getPosition()))
                monsters.add(newMonsters);
        }
        return monsters;
    }
}
