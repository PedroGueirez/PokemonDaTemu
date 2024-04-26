package io.codeforall.javatars.pokemons;

public class Alex extends Pokemon{

    public Alex() {
        maxHp = 40;
        hp = maxHp;
        attack = 10;
        moves[0] = "Big F";
        moves[1] = "Cachorão";
    }

    @Override
    public String toString() {
        return "Alex";
    }
}
