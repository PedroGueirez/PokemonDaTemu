package io.codeforall.javatars.pokemons;

public abstract class Pokemon {
    protected int hp;
    protected int maxHp;
    protected String[] moves = new String[4];
    protected int level = 5;
    protected int attack;

    public void attack(Pokemon pokemon) {
        pokemon.hp -= attack;
    }

    public void levelUp() {
        level++;
        attack *= 1.2;
        maxHp *= 1.2;
    }

    public void restoreHp(){
        hp=maxHp;
    }

    public int getHp() {
        return hp;
    }

    public String[] getMoves() {
        return moves;
    }

    public int getMaxHp() {
        return maxHp;
    }
}
