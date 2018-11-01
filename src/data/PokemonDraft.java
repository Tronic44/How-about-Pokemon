package data;

import java.util.ArrayList;
import java.util.List;

import client.MyException;
import draftpanels.DraftGui;

/**
 * Die Klasse Data, dient einzig und alleine dem Speichern von Programm
 * übergreifenden Daten, wie dem pokedex oder der Tierlist.
 * 
 * @author Yannick Dreher, Realchicken
 *
 */
public class PokemonDraft {

	private PokemonDraft() {

	}

	/**
	 * Gibt den pokedex zurück.
	 * 
	 * @return String[] den pokedex
	 */
	public static String[] getPokedex() {
		return pokedex;
	}

	/**
	 * Gibt den Eintrag des pokedex an der Stelle i zurück.
	 * 
	 * @param i - int, der zu betrachtende Eintrag im pokedex
	 * @return String - Der Eintrag im pokedex
	 */
	public static String getPokedex(int i) {
		return pokedex[i];
	}

	public static int getPokedex(String a) {
		for (int k = 0; k < pokedex.length; k++) {
			if (getPokedex(k).trim().equalsIgnoreCase((a.trim()))) {
				return k;
			}
		}
		return 0;
	}

	/**
	 * Der pokedex in einem einzige StringArray.
	 */
	private static String[] pokedex = { "Bisasam", "Bisaknosp", "Bisaflor", "Bisaflor Mega", "Glumanda", "Glutexo",
			"Glurak", "Glurak Mega X", "Glurak Mega Y", "Schiggy", "Schillok", "Turtok", "Turtok Mega", "Raupy",
			"Safcon", "Smettbo", "Hornliu", "Kokuna", "Bibor", "Bibor Mega", "Taubsi", "Tauboga", "Tauboss",
			"Tauboss Mega", "Rattfratz", "Rattikarl", "Rattfratz Alola", "Rattikarl Alola", "Habitak", "Ibitak",
			"Rettan", "Arbok", "Pikachu", "Raichu", "Raichu Alola", "Sandan", "Sandan Alola", "Sandamer",
			"Sandamer Alola", "Nidoran♀", "Nidorina", "Nidoqueen", "Nidoran♂", "Nidorino", "Nidoking", "Piepi", "Pixi",
			"Vulpix", "Vulnona", "Vulnona Alola", "Pummeluff", "Knuddeluff", "Zubat", "Golbat", "Myrapla", "Duflor",
			"Giflor", "Paras", "Parasek", "Bluzuk", "Omot", "Digda", "Digda Alola", "Digdri", "Digdri Alola", "Mauzi",
			"Mauzi Alola", "Snobilikat", "Snobilikat Alola", "Enton", "Entoron", "Menki", "Rasaff", "Fukano", "Arkani",
			"Quapsel", "Quaputzi", "Quappo", "Abra", "Kadabra", "Simsala", "Simsala Mega", "Machollo", "Maschock",
			"Machomei", "Knofensa", "Ultrigaria", "Sarzenia", "Tentacha", "Tentoxa", "Kleinstein", "Georok", "Geowaz",
			"Ponita", "Gallopa", "Flegmon", "Lahmus", "Lahmus Mega", "Magnetilo", "Magneton", "Porenta", "Dodu",
			"Dodri", "Jurob", "Jugong", "Sleima", "Sleima Alola", "Sleimok", "Sleimok", "Muschas", "Austos", "Nebulak",
			"Alpollo", "Gengar", "Gengar Mega", "Onix", "Traumato", "Hypno", "Krabby", "Kingler", "Voltobal",
			"Lektrobal", "Owei", "Kokowei", "Kokowei Alola", "Tragosso", "Knogga", "Knogga", "Kicklee", "Nockchan",
			"Schlurp", "Smogon", "Smogmog", "Rihorn", "Rizeros", "Chaneira", "Tangela", "Kangama", "Kangama Mega",
			"Seeper", "Seemon", "Goldini", "Golking", "Sterndu", "Starmie", "Pantimos", "Sichlor", "Rossana", "Elektek",
			"Magmar", "Pinsir", "Pinsir Mega", "Tauros", "Karpador", "Garados", "Garados Mega", "Lapras", "Ditto",
			"Evoli", "Aquana", "Blitza", "Flamara", "Porygon", "Amonitas", "Amoroso", "Kabuto", "Kabutops",
			"Aerodactyl", "Aerodactyl Mega", "Relaxo", "Arktos", "Zapdos", "Lavados", "Dratini", "Dragonir", "Dragoran",
			"Mewtu", "Mewtu Mega X", "Mewtu Mega Y", "Mew", "Endivie", "Lorblatt", "Meganie", "Feurigel", "Igelavar",
			"Tornupto", "Karnimani", "Tyracroc", "Impergator", "Wiesor", "Wiesenior", "Hoothoot", "Noctuh", "Ledyba",
			"Ledian", "Webarak", "Ariados", "Iksbat", "Lampi", "Lanturn", "Pichu", "Pii", "Fluffeluff", "Togepi",
			"Togetic", "Natu", "Xatu", "Voltilamm", "Waaty", "Ampharos", "Ampharos Mega", "Blubella", "Marill",
			"Azumarill", "Mogelbaum", "Quaxo", "Hoppspross", "Hubelupf", "Papungha", "Griffel", "Sonnkern", "Sonnflora",
			"Yanma", "Felino", "Morlord", "Psiana", "Nachtara", "Kramurx", "Laschoking", "Traunfugil",
			"Icognito Alle Formen", "Woingenau", "Girafarig", "Tannza", "Forstellka", "Dummisel", "Skorgla", "Stahlos",
			"Stahlos Mega", "Snubbull", "Granbull", "Baldorfish", "Scherox", "Scherox Mega", "Pottrott", "Skaraborn",
			"Skaraborn Mega", "Sniebel", "Teddiursa", "Ursaring", "Schneckmag", "Magcargo", "Quiekel", "Keifel",
			"Corasonn", "Remoraid", "Octillery", "Botogel", "Mantax", "Panzaeron", "Hunduster", "Hundemon",
			"Hundemon Mega", "Seedraking", "Phanpy", "Donphan", "Porygon2", "Damhirplex", "Farbeagle", "Rabauz",
			"Kapoera", "Kussilla", "Elekid", "Magby", "Miltank", "Heiteira", "Raikou", "Entei", "Suicune", "Larvitar",
			"Pupitar", "Despotar", "Despotar Mega", "Lugia", "Ho-Oh", "Celebi", "Geckarbor", "Reptain", "Gewaldro",
			"Gewaldro Mega", "Flemmli", "Jungglut", "Lohgock", "Lohgock Mega", "Hydropi", "Moorabbel", "Sumpex",
			"Sumpex Mega", "Fiffyen", "Magnayen", "Zigzachs", "Geradaks", "Waumpel", "Schaloko", "Papinella", "Panekon",
			"Pudox", "Loturzel", "Lombrero", "Kappalores", "Samurzel", "Blanas", "Tengulist", "Schwalbini",
			"Schwalboss", "Wingull", "Pelipper", "Trasla", "Kirlia", "Guardevoir", "Guardevoir Mega", "Gehweiher",
			"Maskeregen", "Knilz", "Kapilz", "Bummelz", "Muntier", "Letarking", "Nincada", "Ninjask", "Ninjatom",
			"Flurmel", "Krakeelo", "Krawumms", "Makuhita", "Hariyama", "Azurill", "Nasgnet", "Eneco", "Enekoro",
			"Zobiris", "Zobiris Mega", "Flunkifer", "Flunkifer Mega", "Stollunior", "Stollrak", "Stolloss",
			"Stolloss Mega", "Meditie", "Meditalis", "Meditalis Mega", "Frizelbliz", "Voltenso", "Voltenso Mega",
			"Plusle", "Minun", "Volbeat", "Illumise", "Roselia", "Schluppuck", "Schlukwech", "Kanivanha", "Tohaido",
			"Tohaido Mega", "Wailmer", "Wailord", "Camaub", "Camerupt", "Camerupt Mega", "Qurtel", "Spoink", "Groink",
			"Pandi", "Knacklion", "Vibrava", "Libelldra", "Tuska", "Noktuska", "Wablu", "Altaria", "Altaria Mega",
			"Sengo", "Vipitis", "Lunastein", "Sonnfel", "Schmerbe", "Welsar", "Krebscorps", "Krebutack", "Puppance",
			"Lepumentas", "Liliep", "Wielie", "Anorith", "Armaldo", "Barschwa", "Milotic", "Formeo ", "Kecleon",
			"Shuppet", "Banette", "Banette Mega", "Zwirrlicht", "Zwirrklop", "Tropius", "Palimpalim", "Absol",
			"Absol Mega", "Isso", "Schneppke", "Firnontor", "Firnontor Mega", "Seemops", "Seejong", "Walraisa", "Perlu",
			"Aalabyss", "Saganabyss", "Relicanth", "Liebiskus", "Kindwurm", "Draschel", "Brutalanda", "Tanhel",
			"Metang", "Metagross", "Metagross Mega", "Regirock", "Regice", "Registeel", "Latias", "Latias Mega",
			"Latios", "Latios Mega", "Kyogre", "Kyogre Proto", "Groudon", "Groudon Proto", "Rayquaza", "Rayquaza Mega",
			"Jirachi", "Deoxys", "Deoxys Angriffsform", "Deoxys Initiativeform", "Deoxys Verteidigungsform", "Chelast",
			"Chelcarain", "Chelterrar", "Panflam", "Panpyro", "Panferno", "Plinfa", "Pliprin", "Impoleon", "Staralili",
			"Staravia", "Staraptor", "Bidiza", "Bidifas", "Zirpurze", "Zirpeise", "Sheinux", "Luxio", "Luxtra",
			"Knospi", "Roserade", "Koknodon", "Rameidon", "Schilterus", "Bollterus", "Burmy ", "Burmadame Lumpenumhang",
			"Burmadame Pflanzenumhang", "Burmadame Sandumhang", "Moterpel", "Wadribie", "Honweisel", "Pachirisu",
			"Bamelin", "Bojelin", "Kikugi", "Kinoso", "Schalellos ", "Gastrodon ", "Ambidiffel", "Driftlon",
			"Drifzepeli", "Haspiror", "Schlapor", "Schlapor Mega", "Traunmagil", "Kramshef", "Charmian", "Shnurgarst",
			"Klingplim", "Skunkapuh", "Skuntank", "Bronzel", "Bronzong", "Mobai", "Pantimimi", "Wonneira", "Plaudagei",
			"Kryppuk", "Kaumalat", "Knarksel", "Knakrack", "Knakrack Mega", "Mampfaxo", "Riolu", "Lucario",
			"Lucario Mega", "Hippopotas", "Hippoterus", "Pionskora", "Piondragi", "Glibunkel", "Toxiquak", "Venuflibis",
			"Finneon", "Lumineon", "Mantirps", "Shnebedeck", "Rexblisar", "Rexblisar Mega", "Snibunna", "Magnezone",
			"Schlurplek", "Rihornior", "Tangoloss", "Elevoltek", "Magbrant", "Togekiss", "Yanmega", "Folipurba",
			"Glaziola", "Skorgro", "Mamutel", "Porygon-Z", "Galagladi", "Galagladi Mega", "Voluminas", "Zwirrfinst",
			"Frosdedje", "Rotom", "Rotom Frost-Form", "Rotom Hitze-Form", "Rotom Schneid-Form", "Rotom Wasch-Form",
			"Rotom Wirbel-Form", "Selfe", "Vesprit", "Tobutz", "Dialga", "Palkia", "Heatran", "Regigigas",
			"Giratina Urform", "Giratina Wandelform", "Cresselia", "Phione", "Manaphy", "Darkrai", "Shaymin L",
			"Shaymin Z", "Arceus ", "Victini", "Serpifeu", "Efoserp", "Serpiroyal", "Floink", "Ferkokel", "Flambirex",
			"Ottaro", "Zwottronin", "Admurai", "Nagelotz", "Kukmarda", "Yorkleff", "Terribark", "Bissbark", "Felilou",
			"Kleoparda", "Vegimak", "Vegichita", "Grillmak", "Grillchita", "Sodamak", "Sodachita", "Somniam",
			"Somnivora", "Dusselgurr", "Navitaub", "Fasasnob", "Elezeba", "Zebritz", "Kiesling", "Sedimantur",
			"Brockoloss", "Fleknoil", "Fletiamo", "Rotomurf", "Stalobor", "Ohrdoch", "Ohrdoch Mega", "Praktibalk",
			"Strepoli", "Meistagrif", "Schallquap", "Mebrana", "Branawarz", "Jiutesto", "Karadonis", "Strawickl",
			"Folikon", "Matrifol", "Toxiped", "Rollum", "Cerapendra", "Waumboll", "Elfun", "Lilminip", "Dressella",
			"Barschuft Alle Formen", "Ganovil", "Rokkaiman", "Rabigator", "Flampion", "Flampivian", "Maracamba",
			"Lithomith", "Castellith", "Zurrokex", "Irokex", "Symvolara", "Makabaja", "Echnatoll", "Galapaflos",
			"Karippas", "Flapteryx", "Aeropteryx", "Unratütox", "Deponitox", "Zorua", "Zoroark", "Picochilla",
			"Chillabell", "Mollimorba", "Hypnomorba", "Morbitesse", "Monozyto", "Mitodos", "Zytomega", "Piccolente",
			"Swaroness", "Gelatini", "Gelatroppo", "Gelatwino", "Sesokitz", "Kronjuwild", "Emolga", "Laukaps",
			"Cavalanzas", "Tarnpignon", "Hutsassa", "Quabbel", "Apoquallyp", "Mamolida", "Wattzapf", "Voltula",
			"Kastadur", "Tentantel", "Klikk", "Kliklak", "Klikdiklak", "Zapplardin", "Zapplalek", "Zapplarang",
			"Pygraulon", "Megalon", "Lichtel", "Laternecto", "Skelabra", "Milza", "Sharfax", "Maxax", "Petznief",
			"Siberio", "Frigometri", "Schnuthelm", "Hydragil", "Flunschlik", "Lin-Fu", "Wie-Shu", "Shardrago", "Golbit",
			"Golgantes", "Gladiantri", "Caesurio", "Bisofank", "Geronimatz", "Washakwil", "Skallyk", "Grypheldis",
			"Furnifraß", "Fermicula", "Kapuno", "Duodino", "Trikephalo", "Ignivor", "Ramoth", "Kobalium", "Terrakium",
			"Viridium", "Boreos Inkarnationsform", "Boreos Tiergeistform", "Voltolos Inkarnationsform",
			"Voltolos Tiergeistform", "Reshiram", "Zekrom", "Demeteros Inkarnationsform", "Demeteros Tiergeistform",
			"Kyurem", "Kyurem Schwarz", "Kyurem Weiß", "Keldeo", "Meloetta Gesangsform", "Meloetta Tanzform",
			"Genesect", "Igamaro", "Igastarnish", "Brigaron", "Fynx", "Rutena", "Fennexis", "Froxy", "Amphizel",
			"Quajutsu", "Quajutsu Ash", "Scoppel", "Grebbit", "Dartiri", "Dartignis", "Fiaro", "Purmel", "Puponcho",
			"Vivillon Alle Formen", "Leufeo", "Pyroleo", "Flabébé", "Floette", "Florges", "Mähikel", "Chevrumm",
			"Pam-Pam", "Pandagro", "Coiffwaff", "Psiau", "Psiaugon", "Gramokles", "Duokles", "Durengard", "Parfi",
			"Parfinesse", "Flauschling", "Sabbaione", "Iscalar", "Calamanero", "Bithora", "Thanathora", "Algitt",
			"Tandrak", "Scampisto", "Wummer", "Eguana", "Elezard", "Balgoras", "Monargoras", "Amarino", "Amagarga",
			"Feelinara", "Resladero", "Dedenne", "Rocara", "Viscora", "Viscargot", "Viscogon", "Clavion", "Paragoni",
			"Trombork", "Irrbis L", "Irrbis M", "Irrbis S", "Irrbis XL", "Pumpdjinn L", "Pumpdjinn M", "Pumpdjinn S",
			"Pumpdjinn XL", "Pumdjinn alle", "Arktip", "Arktilas", "eF-eM", "UHaFnir", "Xerneas", "Yveltal",
			"Zygarde 10%", "Zygarde 50%", "Zygarde Optimum", "Diancie", "Diancie Mega", "Hoopa Entfesselte Form",
			"Hoopa Gebannte Form", "Volcanion", "Bauz", "Arboretoss", "Silvarro", "Flamiau", "Miezunder", "Fuegro",
			"Robball", "Marikeck", "Primarene", "Peppeck", "Trompeck", "Tukanon", "Mangunior", "Manguspektor", "Mabula",
			"Akkup", "Donarion", "Krabbox", "Krawell", "Choreogel Flamenco", "Choreogel Cheerleading", "Choreogel Hula",
			"Choreogel Buyo", "Wommel", "Bandelby", "Wuffels", "Wolwerock Nachtform", "Wolwerock Tagform",
			"Lusardin Einzelform", "Lusardin Schwarmform", "Garstella", "Aggrostella", "Pampuli", "Pampross", "Araqua",
			"Aranestro", "Imantis", "Mantidea", "Bubungus", "Lamellux", "Molunk", "Amfira", "Velursi", "Kosturso",
			"Frubberl", "Frubaila", "Fruyal", "Curelei", "Kommandutan", "Quartermak", "Reißlaus", "Tectass", "Sankabuh",
			"Colossand", "Gufa", "Typ:Null", "Amigento", "Meteno", "Koalelu", "Tortunator", "Togedemaru", "Mimigma",
			"Knirfish", "Sen-Long", "Moruda", "Miniras", "Mediras", "Grandiras", "Kapu-Riki", "Kapu-Fala", "Kapu-Toro",
			"Kapu-Kime", "Cosmog", "Cosmovum", "Solgaleo", "Lunala", "Anego", "Masskito", "Schabelle", "Voltriant",
			"Kaguron", "Katagami", "Schlingking", "Necrozma", "Necrozma Abendmähne", "Necrozma Morgenschwingen",
			"Necrozma Ultra", "Magearna", "Marshadow", "Venicro", "Agoyon", "Muramura", "Kopplosio", "Zeraora" };

	private static ArrayList<String> pokemontier1 = new ArrayList<>();
	private static ArrayList<String> pokemontier2 = new ArrayList<>();
	private static ArrayList<String> pokemontier3 = new ArrayList<>();
	private static ArrayList<String> pokemontier4 = new ArrayList<>();
	private static ArrayList<String> pokemontier5 = new ArrayList<>();
	private static ArrayList<String> pokemontier6 = new ArrayList<>();
	private static ArrayList<String> pokedexlist = new ArrayList<>();

	public static List<String> getPokedexlist() {
		if (pokedexlist.isEmpty()) {
			for (String k : getPokedex()) {
				pokedexlist.add(k);
			}
		}
		return pokedexlist;
	}

	/**
	 * Die Tierlist, ist dazu da jedem Pokemon ein Tier zuzuweisen.
	 */
	private static char[] tierlist;

	/**
	 * Gibt die Tierlist als ein String ohne Zeichentrenner zurück.
	 * 
	 * @return String
	 */
	public static char[] getTierlist() {
		initPoketier();
		return tierlist;
	}

	public static char getTierlist(int a) {
		initPoketier();
		return tierlist[a];
	}

	/**
	 * Setzt die gesamte Tierlist.
	 * 
	 * @param a - char[] der Länge des pokedex
	 */
	public static void setTierlist(char[] a) {
		tierlist = a;
	}

	public static void editTierlist(int place, char ch) {
		initPoketier();
		tierlist[place] = ch;
	}

	/**
	 * Ein Clone der Tierlist um Änderungen Rückgängig machen zu können. Muss
	 * manuell initialisiert werden.
	 */
	private static char[] tierlistclone;

	public static void cloneTierlist() {
		if (tierlistclone == null) {
			tierlistclone = getTierlist().clone();
		}
	}

	public static void restoreTierlist() throws MyException {
		if (tierlistclone != null) {
			setTierlist(tierlistclone.clone());
			tierlistclone = null;
		} else {
			throw new MyException();
		}
	}

	private static void initPoketier() {
		if (tierlist == null) {
			tierlist = new char[pokedex.length];
			for (int i = 0; i < PokemonDraft.getPokedex().length; i++) {
				PokemonDraft.editTierlist(i, '0');
			}
		}
	}

	/**
	 * Gibt den Tierlistclone zurück. Als String wenn schon initialisiert, wenn
	 * nicht als JSONOBject.NULL.
	 * 
	 * @return Object - String oder JSONObject.NULL
	 */
	public static Object getTierlistclone() {
		try {
			return new String(tierlistclone);
		} catch (Exception e) {
			return org.json.JSONObject.NULL;
		}
	}

	public static String[] getTierPokemon(int a) {
		switch (a) {
		case 0:
			return pokemontier1.toArray(new String[0]);
		case 1:
			return pokemontier2.toArray(new String[0]);
		case 2:
			return pokemontier3.toArray(new String[0]);
		case 3:
			return pokemontier4.toArray(new String[0]);
		case 4:
			return pokemontier5.toArray(new String[0]);
		case 5:
			return pokemontier6.toArray(new String[0]);
		default:
			break;
		}
		return new String[] {};
	}

	public static void initTierPokemon() {
		for (int k = 0; k < pokedex.length; k++) {
			switch (tierlist[k]) {
			case '0':
				break;
			case 'S':
				if (!pokemontier1.contains(pokedex[k])) {
					pokemontier1.add(pokedex[k]);
				}
				removePokemonfromTier('S', k);
				break;
			case 'A':
				if (!pokemontier2.contains(pokedex[k])) {
					pokemontier2.add(pokedex[k]);
				}
				removePokemonfromTier('A', k);
				break;
			case 'B':
				if (!pokemontier3.contains(pokedex[k])) {
					pokemontier3.add(pokedex[k]);
				}
				removePokemonfromTier('B', k);
				break;
			case 'C':
				if (!pokemontier4.contains(pokedex[k])) {
					pokemontier4.add(pokedex[k]);
				}
				removePokemonfromTier('C', k);
				break;
			case 'D':
				if (!pokemontier5.contains(pokedex[k])) {
					pokemontier5.add(pokedex[k]);
				}
				removePokemonfromTier('D', k);
				break;
			case 'E':
				if (!pokemontier6.contains(pokedex[k])) {
					pokemontier6.add(pokedex[k]);
				}
				removePokemonfromTier('E', k);
				break;
			case 'X':
				removePokemonfromTier('X', k);
				break;
			default:
				break;
			}
		}
	}

	private static void removePokemonfromTier(char ch, int k) {
		if (ch != 'S' && pokemontier1.contains(pokedex[k])) {
			int ort = pokemontier1.indexOf(pokedex[k]);
			pokemontier1.remove(ort);
			DraftGui.getwindow().getPanelDraft().renewDraftauswahl(ort);
		}
		if (ch != 'A' && pokemontier2.contains(pokedex[k])) {
			int ort = pokemontier2.indexOf(pokedex[k]);
			pokemontier2.remove(ort);
			DraftGui.getwindow().getPanelDraft().renewDraftauswahl(ort);
		}
		if (ch != 'B' && pokemontier3.contains(pokedex[k])) {
			int ort = pokemontier3.indexOf(pokedex[k]);
			pokemontier3.remove(ort);
			DraftGui.getwindow().getPanelDraft().renewDraftauswahl(ort);
		}
		if (ch != 'C' && pokemontier4.contains(pokedex[k])) {
			int ort = pokemontier4.indexOf(pokedex[k]);
			pokemontier4.remove(ort);
			DraftGui.getwindow().getPanelDraft().renewDraftauswahl(ort);
		}
		if (ch != 'D' && pokemontier5.contains(pokedex[k])) {
			int ort = pokemontier5.indexOf(pokedex[k]);
			pokemontier5.remove(ort);
			DraftGui.getwindow().getPanelDraft().renewDraftauswahl(ort);
		}
		if (ch != 'E' && pokemontier6.contains(pokedex[k])) {
			int ort = pokemontier6.indexOf(pokedex[k]);
			pokemontier6.remove(ort);
			DraftGui.getwindow().getPanelDraft().renewDraftauswahl(ort);
		}
	}

}