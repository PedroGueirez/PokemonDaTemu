package io.codeforall.javatars.pokemons;

public class Bug extends Pokemon{
    public Bug() {
        maxHp = 20;
        hp = maxHp;
        attack = 10;
        moves[0] = "NullPointerExeption";
        moves[1] = "Infinite Loop";
    }

    @Override
    public String toString() {
        return "Bug";
    }
}
