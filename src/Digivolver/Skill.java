package Digivolver;

public class Skill{
	private String name;
	private String speciality;
	private String type;
	private String target;
	private String description;
	private String hint;
	private int MP;
	private int AP;
	
	public Skill() {}
	
	public Skill(String name, String speciality, String type, String target, String description, int MP, int AP){
		this.name = name;
		this.speciality = speciality;
		this.type = type;
		this.target = target;
		this.description = description;
		this.MP = MP;
		this.AP = AP;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	public int getMP(){
		return MP;
	}
	
	public void setMP(int mP){
		MP = mP;
	}
	
	public int getAP(){
		return AP;
	}
	
	public void setAP(int aP){
		AP = aP;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getHint(){
		return hint;
	}
	
	public void setHint(String hint){
		this.hint = hint;
	}
}
