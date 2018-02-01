package Digivolver;
public class Digivolution{
	private Digimon digimon;
	private int minDp = 0;
	private int maxDp = 0;
	
	public boolean isWithinDp(int minDp, int maxDp){
		return this.minDp<=maxDp && this.maxDp>=minDp;
	}
	
	public Digivolution(Digimon digimon, int minDp, int maxDp) {
		this.digimon = digimon;
		this.minDp = minDp;
		this.maxDp = maxDp;
	}
	
	public Digimon getDigimon() {
		return digimon;
	}
	
	public void setDigimon(Digimon digimon) {
		this.digimon = digimon;
	}
	
	public int getMinDp(){
		return minDp;
	}
	public int getMaxDp(){
		return maxDp;
	}
}
