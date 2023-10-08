/* Реализуем Существ через абстрактный класс, чтобы иметь общие признаки и методы, но
* иметь возможность гибко настраивать частные реализации (например, добавить возможность лечения Игроку).
* Помимо этого мы можем расширять функционал игры, добавляя, например, существо-Босса или существо-Союзника */

public abstract class Creature {
    private String name; // Название монстра или имя Игрока
    private int attack; // Атака существа
    private int defence; // Защита существа
    private int health; // Здоровье существа
    private int lowDamage; // Нижняя граница наносимого урона (если урон это диапазон M-N, то это значение соответсвует M)
    private int highDamage; // Верхняя граница наносимого урона (если урон это диапазон M-N, то это значение соответсвует N)
    private boolean alive; // Живое ли сейчас существо


    // Геттеры
    public String getName(){
        return this.name;
    }
    public int getAttack(){
        return this.attack;
    }

    public int getDefence(){
        return this.defence;
    }

    public int getHealth(){
        return this.health;
    }

    public int getLowDamage(){
        return this.lowDamage;
    }

    public int getHighDamage(){
        return this.highDamage;
    }

    public boolean isAlive(){
        return this.alive;
    }

    // Сеттеры

    public void setAttack(int _attack) {
        if ((_attack < 1) || (_attack > 30)){
            System.out.println("Показатели атаки и защиты должны находиться в пределах от 1 до 30. Установлено значение по умолчанию (15)");
            this.attack = 15;
        } else {
            this.attack = _attack;
        }
    }

    public void setDefence(int _defence){
        if ((_defence < 1) || (_defence > 30)){
            System.out.println("Показатели атаки и защиты должны находиться в пределах от 1 до 30. Установлено значение по умолчанию (15)");
            this.defence = 15;
        } else {
            this.defence = _defence;
        }
    }


    public void setHealth(int _health){
        this.health = _health;
    }

    public void setLowDamage(int _lowDamage){
        this.lowDamage = _lowDamage;
    }

    public void setHighDamage(int _highDamage){
        this.highDamage = _highDamage;
    }

    public void setAlive(boolean _alive){
        this.alive = _alive;
    }

    public void setName(String _name){
        this.name = _name;
    }

    // Метода Атаки
    public abstract void kick(Creature attacked);

    @Override
    public String toString(){
        String a = isAlive() ? "Живо" : "Мертво";
        return "Характеристики существа " + getName() + ":\n"
                + "Атака: " + getAttack() + "\n"
                + "Здоровье: " + getHealth() + "\n"
                + "Защита: " + getDefence() + "\n"
                + "Урон: " + getLowDamage() + "-" + getHighDamage() + "\n"
                + "Статус: " + a;
    }

}
