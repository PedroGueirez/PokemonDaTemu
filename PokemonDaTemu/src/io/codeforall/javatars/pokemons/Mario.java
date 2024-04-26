package io.codeforall.javatars.pokemons;

public class Mario extends Pokemon{
    public Mario() {
        maxHp = 50;
        hp = maxHp;
        attack = 8;
        moves[0] = "Oh Anabela";
        moves[1] = "Em Java... Há regras";
    }

    @Override
    public String toString() {
        return "Mário";
    }
}
