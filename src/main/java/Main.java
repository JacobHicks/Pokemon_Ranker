import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;
import me.sargunvohra.lib.pokekotlin.model.PokemonStat;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
//637 Non legendaries

public class Main {
    public static void main(String[] args) {
        PokeApi pokeApi = new PokeApiClient();
        Object[][] pokemen = new Object[807][9];
        for(int i = 1; i <= 807; i++) {
            Pokemon pokemon = pokeApi.getPokemon(i);
            List<PokemonStat> pokemonStats = pokemon.getStats();
            pokemen[i - 1][0] = pokemon.getName();
            pokemen[i - 1][2] = pokemon.getBaseExperience();
            for(PokemonStat pokemonStat : pokemonStats) {
                NamedApiResource nar = pokemonStat.getStat();
                pokemen[i - 1][nar.getId() + 2] = pokemonStat.getBaseStat();
            }
            pokemen[i - 1][1] = (int)pokemen[i - 1][2]/4.0 + ((int)pokemen[i - 1][3]/3.0 + (int)pokemen[i - 1][5]/2.0 + (int)pokemen[i - 1][7]/2.0) + (int)pokemen[i - 1][4]/2.0 + (int)pokemen[i - 1][6]/2.0 + (int)pokemen[i - 1][8] / 5.0;
            if(i % 10 == 0) System.out.println(i);
        }
        for(int i = 0; i < pokemen.length; i++) {
            for(int x = i + 1; x < pokemen.length; x++) {
                if((Double)pokemen[i][1] < (Double)pokemen[x][1]) {
                    Object[] tmp = pokemen[i];
                    pokemen[i] = pokemen[x];
                    pokemen[x] = tmp;
                }
            }
        }
        System.out.println("rank name      scor exp hp  spe sdf sat def att");
        for (int i = 0; i < pokemen.length; i++) {
            System.out.printf("%3s. %.10s %3.1f %3d %3d %3d %3d %3d %3d %3d%n", (i + 1), pokemen[i][0], pokemen[i][1], pokemen[i][2], pokemen[i][3], pokemen[i][4], pokemen[i][5], pokemen[i][6], pokemen[i][7], pokemen[i][8]);
        }
    }
}
