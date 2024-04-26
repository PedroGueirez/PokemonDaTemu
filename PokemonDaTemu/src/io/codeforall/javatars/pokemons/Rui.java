package io.codeforall.javatars.pokemons;

public class Rui extends Pokemon{
    public Rui() {
        maxHp = 30;
        hp = maxHp;
        attack = 12;
        moves[0] = "Pseudocódigo";
        moves[1] = "Documentação";
    }

    @Override
    public String toString() {
        return "Rui";
    }
}
