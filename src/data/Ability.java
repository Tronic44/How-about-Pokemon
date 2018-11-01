package data;

public enum Ability {
	
	ASAPTABILITY ("Powers up moves of the same type as the Pokémon"),
	AERILATE("Normal-type moves become Flying-type moves. The power of those moves is boosted by 20%");
	
	
	
	
	
	
	
	
	
	
	
	
	private final String description;
	
	private Ability(String des){
		this.description=des;
	}
	
	public String getsexcription() {
		return description;
	}
	
	
}
