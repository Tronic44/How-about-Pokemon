package data;

import java.net.URL;

public enum Pokemon {

	BISASAM(1, "Bisasam", PokemonType.GRASS, PokemonType.POISON, 45, 49, 49, 65, 65, 15),
	BISAKNOSP(2, "Bisaknosp", PokemonType.GRASS, PokemonType.POISON, 60, 62, 63, 80, 80, 60),
	BISAFLOR(3, "Bisaflor", PokemonType.GRASS, PokemonType.POISON, 80, 82, 83, 10, 100, 80);

	private Pokemon(int index, String name, PokemonType type1, int hp, int atk, int def, int spA, int spD, int spe) {
		this.index = index;
		this.name = name;
		this.type1 = type1;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.spA = spA;
		this.spD = spD;
		this.spe = spe;
		this.total = hp + atk + def + spA + spD + spe;
		this.average = (double) total / 6;
		this.type2 = null;
		this.pic1 = null;
		this.pic2 = null;
		this.ability1 = null;
		this.ability2 = null;
		this.ability3 = null;
	}

	private Pokemon(int index, String name, PokemonType type1, PokemonType type2, int hp, int atk, int def, int spA,
			int spD, int spe) {
		this.index = index;
		this.name = name;
		this.type1 = type1;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.spA = spA;
		this.spD = spD;
		this.spe = spe;
		this.total = hp + atk + def + spA + spD + spe;
		this.average = (double) total / 6;
		this.type2 = type2;
		this.pic1 = null;
		this.pic2 = null;
		this.ability1 = null;
		this.ability2 = null;
		this.ability3 = null;
	}

	private Pokemon(int index, String name, PokemonType type1, PokemonType type2, int hp, int atk, int def, int spA,
			int spD, int spe, URL pic1) {
		this.index = index;
		this.name = name;
		this.type1 = type1;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.spA = spA;
		this.spD = spD;
		this.spe = spe;
		this.total = hp + atk + def + spA + spD + spe;
		this.average = (double) total / 6;
		this.type2 = type2;
		this.pic1 = pic1;
		this.pic2 = null;
		this.ability1 = null;
		this.ability2 = null;
		this.ability3 = null;
	}

	private Pokemon(int index, String name, PokemonType type1, PokemonType type2, int hp, int atk, int def, int spA,
			int spD, int spe, URL pic1, URL pic2) {
		this.index = index;
		this.name = name;
		this.type1 = type1;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.spA = spA;
		this.spD = spD;
		this.spe = spe;
		this.total = hp + atk + def + spA + spD + spe;
		this.average = (double) total / 6;
		this.type2 = type2;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.ability1 = null;
		this.ability2 = null;
		this.ability3 = null;
	}

	private Pokemon(int index, String name, PokemonType type1, PokemonType type2, int hp, int atk, int def, int spA,
			int spD, int spe, URL pic1, URL pic2, Ability ability1, Ability ability2, Ability ability3) {
		this.index = index;
		this.name = name;
		this.type1 = type1;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.spA = spA;
		this.spD = spD;
		this.spe = spe;
		this.total = hp + atk + def + spA + spD + spe;
		this.average = (double) total / 6;
		this.type2 = type2;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.ability1 = ability1;
		this.ability2 = ability2;
		this.ability3 = ability3;
	}

	private final int index;
	private final String name;
	private final PokemonType type1;
	private final PokemonType type2;
	private final int hp;
	private final int atk;
	private final int def;
	private final int spA;
	private final int spD;
	private final int spe;
	private final int total;
	private final double average;
	private final URL pic1;
	private final URL pic2;
	private final Ability ability1;
	private final Ability ability2;
	private final Ability ability3;

}
