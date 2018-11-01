package data;

public enum PokemonType {

	NORMAL(0,new double[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.5, 1, 1, 1, 0, 1, 0.5, 1, 1 }),
	FIRE(1),
	FIGHTING(2),
	WATER(3),
	FLYING5(4),
	GRASS(5),
	POISON(6),
	ELECTRIC(7),
	GROUND(8),
	PSYCHIC(9),
	ROCK(10),
	ICE(11),
	BUG(12),
	DRAGON(13),
	GHOST(14),
	DARK(15),
	STELL(16),
	FAIRY(17);


	private final double[] stats;
	private final int typ;

	private PokemonType(int typ) {
		this.typ=typ;
		this.stats = null;
	}

	private PokemonType(int typ,double[] stats) {
		if(stats.length!=19) {
			this.stats=null;
			this.typ=-1;
		}else {
			for (double k : stats) {
				if (k != 1 && k != 2 && k != 0.5) {
					this.stats=null;
					this.typ=-1;
					return;
				}	
			}
			this.typ=typ;
			this.stats = stats;
		}
	}

	public PokemonType[] getPokemonType() {
		return new PokemonType[] { NORMAL };
	}

}
