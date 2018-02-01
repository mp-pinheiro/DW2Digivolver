package Digivolver;
import java.util.ArrayList;

public class Digimon {
	private String name;
	private int level;
	private int type;
	private String[] data = new String[2];
	private int el;
	private ArrayList<Digivolution> antievolution;
	private ArrayList<Digivolution> digivolution;
	private Skill skill;
	
	public static String convertLevel(int i){
		switch (i) {
		case 0:
			return "Rookie";
		case 1:
			return "Champion";
		case 2:
			return "Ultimate";
		case 3:
			return "Mega";
		}
		return "";
	}
	
	public static String convertType(int i){
		switch (i) {
		case 0:
			return "Vaccine";
		case 1:
			return "Virus";
		case 2:
			return "Data";
		}
		return "";
	}
	
	public Digimon(String name, int type, int level, String data1, String data2) {
		this.setName(name);
		this.setLevel(level);
		this.type = type;
		getData()[0] = data1;
		getData()[1] = data2;
		antievolution = new ArrayList<>();
		digivolution = new ArrayList<>();
	}
	
	public Digimon(String name, int type, int level, String data1) {
		this.setName(name);
		this.setLevel(level);
		this.type = type;
		getData()[0] = data1;
		antievolution = new ArrayList<>();
		digivolution = new ArrayList<>();
	}

	public void addDigivolution(Digivolution digivolution){
		this.digivolution.add(digivolution);
	}
	
	public ArrayList<Digivolution> getNextDigivolution(Digimon digimon){
		ArrayList<Digivolution> temp = new ArrayList<>();
		for(int i=0; i<digivolution.size(); i++){
			if(digivolution.get(i).getDigimon().getLevel()-digimon.getLevel()==1){
				temp.add(digivolution.get(i));
			}
		}
		return temp;
	}
	
	public ArrayList<Digivolution> getDigivolutions(int minDp, int maxDp){
		ArrayList<Digivolution> temp = new ArrayList<>();
		for(int i=0; i<digivolution.size(); i++){
			if(digivolution.get(i).isWithinDp(minDp, maxDp)){
				Digivolution digi = digivolution.get(i);
				temp.add(digi);
				temp.addAll(digi.getDigimon().getDigivolutions(minDp, maxDp));
			}
		}
		return temp;
	}
	
	public void addAntievolution(Digivolution antievolution){
		this.antievolution.add(antievolution);
	}
	
	public ArrayList<Digivolution> getPreviousDigivolution(Digimon digimon){
		ArrayList<Digivolution> temp = new ArrayList<>();
		for(int i=0; i<antievolution.size(); i++){
			if(digimon.getLevel()-antievolution.get(i).getDigimon().getLevel()==1){
				temp.add(antievolution.get(i));
			}
		}
		return temp;
	}
	
	public ArrayList<Digivolution> getAntievolutionsDp(int minDp, int maxDp){
		ArrayList<Digivolution> temp = new ArrayList<>();
		for(int i=0; i<antievolution.size(); i++){
			if(antievolution.get(i).isWithinDp(minDp, maxDp)){
				Digivolution anti = antievolution.get(i);
				temp.add(anti);
				temp.addAll(anti.getDigimon().getAntievolutionsDp(minDp, maxDp));
			}
		}
		return temp;
	}
	
	public ArrayList<Digimon> getAntievolutions(int minDp, int maxDp){
		ArrayList<Digimon> temp = new ArrayList<>();
		for(int i=0; i<antievolution.size(); i++){
			if(antievolution.get(i).isWithinDp(minDp, maxDp)){
				Digimon anti = antievolution.get(i).getDigimon();
				temp.add(anti);
				temp.addAll(anti.getAntievolutions(minDp, maxDp));
			}
		}
		return temp;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String[] getData() {
		return data;
	}

	public void setData(String[] data) {
		this.data = data;
	}
	
	public void setData1(String data) {
		this.data[0] = data;
	}
	
	public void setData2(String data) {
		this.data[1] = data;
	}

	public int getEl() {
		return el;
	}

	public void setEl(int el) {
		this.el = el;
	}

	public Skill getSkill(){
		return skill;
	}
	
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	
}
