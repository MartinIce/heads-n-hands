public class Monster extends Creature {

    public Monster(String _name, int _attack, int _defence, int _health, int _lowDamage, int _highDamage, boolean _alive){
        this.setName(_name);
        this.setAttack( _attack);
        this.setDefence(_defence);
        this.setHealth(_health);
        this.setLowDamage(_lowDamage);
        this.setHighDamage(_highDamage);
        this.setAlive(_alive);
    }

    public void kick(Creature attacked){
        // Рассчитываем можификатор атаки
        int attackMod = 1;
        int temp = this.getAttack() - attacked.getDefence() + 1;
        if (temp > 1){
            attackMod = temp;
        }

        // Рассчитываем урон - добавляем случайное значение из диапазона столько раз, сколько выпадет 5 или 6 на "кубике"
        int dmg = 0;
        for (int i = 0; i < attackMod; i++){
            int chance = (int) Math.round((Math.random()*(5)+1));
            if (chance > 4){
                dmg += (int) (Math.random()*(this.getHighDamage()-this.getLowDamage())+this.getLowDamage());
            }
        }
        attacked.setHealth(attacked.getHealth() - dmg);
        System.out.println("Нанесено урона игроку: " + dmg);
    }

}
