import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	public static ArrayList<Digimon> digimonList = new ArrayList<Digimon>();
	public static ArrayList<String> digimonNameList = new ArrayList<String>();
	public static String[][][] dnaMatrix = new String[3][][];
	private static String[] dnaMatrixName = {"Champion + Champion", "Ultimate + Ultimate", "Mega + Mega"};
	public static JFrame jFrame;
	
	
	public static Digimon getDigimonByName(String digimon){
		int index = Main.digimonNameList.indexOf(digimon.toLowerCase());
		return Main.digimonList.get(index);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList<Digimon>[] DNAGetCombinations(Digimon subject1, boolean[] checkType, boolean[] checkLevel){
		Digimon temp1, temp2;
		ArrayList[] component = new ArrayList[2];
		component[0] = new ArrayList<Digimon>();
		component[1] = new ArrayList<Digimon>();
		for(int i=0; i<digimonList.size(); i++){
			temp1 = digimonList.get(i);
			if(!checkType[temp1.getType()] || !checkLevel[temp1.getLevel()]) continue;
			temp2 = DNADigivolve(subject1, temp1);
			if(temp2==null) continue;
			component[0].add(temp1);
			component[1].add(temp2);
		}
		return component;
	}
	
	public static ArrayList<Digimon> DNAGetSubject(Digimon subject1, Digimon fusion, boolean[] checkType, boolean[] checkLevel){
		Digimon temp;
		//System.out.println(subject1.getName()+" "+fusion.getName());
		ArrayList<Digimon> components = new ArrayList<Digimon>();
		for(int i=0; i<digimonList.size(); i++){
			temp = digimonList.get(i);
			if(!checkType[temp.getType()] || !checkLevel[temp.getLevel()]) continue;
			if(fusion == DNADigivolve(subject1, temp)){
				components.add(temp);
			}
		}
		return components;
	}
	
	@SuppressWarnings("rawtypes")
	public static void printArrayListPairs(ArrayList[] component){
		for(int i=0; i<component[0].size(); i++){
			for(int j=0; j<component.length; j++){
				@SuppressWarnings("unchecked")
				ArrayList<Digimon> list = component[j];
				System.out.print(list.get(i).getName()+"  \t\t"+Digimon.convertType(list.get(i).getType())+"\t\t"+Digimon.convertLevel(list.get(i).getLevel())+"     \t\n");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static Digimon DNADigivolve(Digimon subject1, Digimon subject2){
		//System.out.println(subject1.getName()+" + "+subject2.getName());
		if(subject1.getLevel()<subject2.getLevel()){
			Digimon temp = subject1;
			subject1 = subject2;
			subject2 = temp;
		}
		if(subject2.getLevel()==0) return null;
		while(subject1.getLevel()>subject2.getLevel()){
			subject1 = DNADigivolve(subject1, subject1);
		}
		String data2 = getData2(subject1.getLevel()-1, subject1.getData()[0], subject2.getData()[0]);
		
		Digimon temp = getDigimonByData2(data2, subject1.getLevel()-1);
		//System.out.println(temp.getName());
		
		return temp;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Digimon>[] DNAGetFusion(Digimon fusion, boolean[] checkType, boolean[] checkLevel){
		Digimon temp1, temp2;
		@SuppressWarnings("rawtypes")
		ArrayList[] component = new ArrayList[2];
		component[0] = new ArrayList<Digimon>();
		component[1] = new ArrayList<Digimon>();
		boolean[] digimonFlag = new boolean[digimonList.size()];
		for(int i=0; i<digimonList.size(); i++){
			temp1 = digimonList.get(i);
			if(!checkType[temp1.getType()] || !checkLevel[temp1.getLevel()]) continue;
			for(int j=0; j<digimonList.size(); j++){
				temp2 = digimonList.get(j);
				if(digimonFlag[j] || fusion != DNADigivolve(temp1, temp2)) continue;
				component[0].add(temp1);
				component[1].add(temp2);
			}
			digimonFlag[i] = true;
		}
		return component;
	}
	
	public static Digimon getDigimonByData2(String data2, int level){
		Digimon temp;
		for(int i=0; i<digimonList.size(); i++){
			temp = digimonList.get(i);
			if(data2.equals(temp.getData()[1]) && level==temp.getLevel()){
				return temp;
			}
		}
		return null;
	}
	
	public static String getData2(int level, String dataA, String dataB){
		int dataA1 = dataA.charAt(0)-'A';
		int dataB1 = dataB.charAt(0)-'A';
		return dnaMatrix[level][dataA1][dataB1];
	}
	
	public static void printList(ArrayList<Digimon> list){
		System.out.println("Digimon  \t\tType\t\tLevel\n-------------------------------------------------");
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i).getName()+"  \t\t"+Digimon.convertType(list.get(i).getType())+"\t\t"+Digimon.convertLevel(list.get(i).getLevel()));
		}
		System.out.println();
	}
	
	public static void printMatrixes(){
		for(int index=0; index<3;index++){
			System.out.println(dnaMatrixName[index]);
			System.out.println("--------------------------------------------------");
			for(int i=0; i<dnaMatrix[index].length; i++){
				for(int j=0; j<dnaMatrix[index].length; j++){
					System.out.print(dnaMatrix[index][i][j]+" ");
				}
				System.out.println();
			}	
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		InputReader.readInput();
		jFrame = new JFrame("Digivolver");
		jFrame.setMinimumSize(new Dimension(882, 670));
		jFrame.setResizable(false);
		jFrame.add(new JPanelDisplay());
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jFrame.setVisible(true);
            }
        });
	}
}
