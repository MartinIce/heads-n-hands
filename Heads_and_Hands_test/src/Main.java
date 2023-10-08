import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Monster enemy1 = new Monster("Волк", 5, 3, 20, 2, 4, true);
        Monster enemy2 = new Monster("Орк", 7, 12, 50, 5, 7, true);
        Monster enemy3 = new Monster("Дракон", 12, 15, 90, 9, 14, true);
        Player player = initPlayer();

        System.out.println("Бой 1. Ваш противник - " + enemy1.getName());
        fight(player, enemy1);

        if (!player.isAlive()) {
            System.out.println("Вы погибли! Игра окончена");
        } else {
            System.out.println("Вы победили в бою! Начинается следующий бой");
            Thread.sleep(1000);
            System.out.println("Бой 2. Ваш противник - " + enemy2.getName());
            fight(player, enemy2);
        }

        if (!player.isAlive()) {
            System.out.println("Вы погибли! Игра окончена");
        } else {
            System.out.println("Вы победили в бою! Начинается следующий бой");
            Thread.sleep(1000);
            System.out.println("Бой 3. Ваш противник - " + enemy3.getName());
            fight(player, enemy3);
        }

        if(player.isAlive())
            System.out.println("Поздравляем, вы победили!");
        else
            System.out.println("Вы погибли! Игра окончена");
    }

    public static Player initPlayer(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Введите имя игрока");
        String _name = scan.nextLine();
        System.out.println("Введите показатель атаки (целое число от 1 до 30)");
        int _attack = 0;
        while ((_attack < 1) || (_attack > 30)){
            _attack = scan.nextInt();
            if ((_attack < 1) || (_attack > 30))
                System.out.println("Число должно быть в диапазоне от 1 до 30");
        }

        System.out.println("Введите показатель защиты (целое число от 1 до 30)");
        int _defence = 0;
        while ((_defence < 1) || (_defence > 30)){
            _defence = scan.nextInt();
            if ((_defence < 1) || (_defence > 30))
            System.out.println("Число должно быть в диапазоне от 1 до 30");
        }

        System.out.println("Введите количество здоровья (целое число большее нуля)");
        int _health = 0;
        while (_health < 1){
            _health = scan.nextInt();
            if (_health < 1)
                System.out.println("Число должно быть больше 0");
        }

        System.out.println("Введите нижнюю границу урона (целое число большее нуля)");
        int _lowDamage = 0;
        while (_lowDamage < 1){
            _lowDamage = scan.nextInt();
            if (_lowDamage < 1)
                System.out.println("Число должно быть больше 0");
        }

        System.out.println("Введите верхнюю границу урона (целое число большее нижней границы)");
        int _highDamage = 0;
        while (_highDamage <= _lowDamage){
            _highDamage = scan.nextInt();
            if (_highDamage <= _lowDamage)
                System.out.println("Число должно быть больше нижней границы");
        }

        return new Player(_name, _attack, _defence, _health, _lowDamage, _highDamage, true);
    }

    public static void fight(Player player, Creature enemy) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        int healCounter = 0;
        int turn = 1;
        // Сражение происходит, пока один из сражающихся не погибнет
        while ((player.isAlive()) && (enemy.isAlive())){
            // Начинает и далее по нечётным ходам ходит Игрок, по чётным - Монстр/Противник
            if (turn % 2 == 1){
                int action = 0;
                // Даём выбрать - атаковать или вылечиться
                System.out.println("Ваш ход! Выберите действие: \n"
                + "1 - Атаковать\n"
                + "2 - Лечиться");
                action = scan.nextInt();
                while ((action != 1) && (action != 2)){
                    System.out.println("Пожалуйста, выберите действие 1 или 2");
                    action = scan.nextInt();
                }
                if (action == 1){
                    System.out.println("Вы атакуете противника");
                    player.kick(enemy);
                }
                // Проверяем число исцелений и лечимся, если возможно
                else if ((healCounter < 4) && (player.getHealth() != player.maxHealth)){
                    System.out.println("Вы лечитесь");
                    player.heal();
                    healCounter++;
                } else {
                    System.out.println("Вы лечились максимальное количество раз за бой или уже имеете максимальное " +
                            "количество здоровья. Вы автоматически атакуете");
                    player.kick(enemy);
                }
            } else {
                // У обычного противника только одно действие - атака
                System.out.println("Противник атакует!");
                enemy.kick(player);
            }
            turn++;

            // Выводим информацию о состоянии сражающихся после каждого хода
            if (player.getHealth() > 0 && enemy.getHealth() > 0){
                System.out.println("Ваше здоровье: " + player.getHealth() + "\n"
                                + "Здоровье противника: " + enemy.getHealth() + "\n");
            } else if (player.getHealth() <= 0){
                player.setAlive(false);
            } else {
                enemy.setAlive(false);
            }

            Thread.sleep(1500);
        }
    }
}