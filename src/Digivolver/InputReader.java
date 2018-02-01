package Digivolver;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputReader {
	private static Scanner scanner;
	
	private static void readSkillsFile(){
		Skill skill = null;
		String digimon = null;
		String temp = null;
		
		try {
			scanner = new Scanner(new File("tables/Skills.txt"));
			scanner.useDelimiter(Pattern.compile("[\\n&]", Pattern.DOTALL));
			
			while(scanner.hasNext()){
				skill = new Skill();
				skill.setName(scanner.next());
				//System.out.println("Name: "+skill.getName());
				
				scanner.next();
				skill.setSpeciality(scanner.next());
				//System.out.println("Speciality: "+skill.getSpeciality());
				
				skill.setType(scanner.next());
				//System.out.println("Type: "+skill.getType());
				
				skill.setTarget(scanner.next());
				//System.out.println("Target: "+skill.getTarget());
				
				skill.setMP(scanner.nextInt());
				//System.out.println("MP: "+skill.getMP());
				
				skill.setAP(Integer.parseInt(scanner.next()));
				//System.out.println("AP: "+skill.getAP());
				
				skill.setDescription(scanner.next());
				//System.out.println("Desc: "+skill.getDescription());
				
				digimon = scanner.next();
				//System.out.println("DG: "+digimon);
				
				temp = scanner.next();
				
				if(!temp.equals("-")){
					skill.setHint(temp);
					//System.out.println("Hint: "+temp);
					scanner.next();
				}
				if(!digimon.equals("???")) Main.getDigimonByName(digimon).setSkill(skill);
			}
			scanner.reset();
		}catch(FileNotFoundException e){
			System.err.println("Error: cannot find file Skills.txt");
			e.printStackTrace();
		}finally{
			scanner.useDelimiter(" ");
		}
	}
	
	private static void readDigivolutionsFile(){
		String file = null;
		String antiDigimon = null;
		String previousDigimon = null;
		String digimon = null;
		int previousMinDp = 0;
		int minDp = 0;
		int maxDp = 0;
		
		try {
			scanner = new Scanner(new File("tables/Digivolutions.txt"));
			antiDigimon = scanner.nextLine();
			while(scanner.hasNext()){
				while(scanner.hasNext()){
					previousDigimon = digimon;
					previousMinDp = minDp;
					digimon = scanner.next();
					if(scanner.hasNextInt()){
						//HAS DP
						minDp = scanner.nextInt();
					}else{
						minDp = 100;
					}
					if(previousDigimon!=null){
						//ADD THINGS MIN
						maxDp = minDp-1;
						//System.out.println(antiDigimon+" digivolve para: "+previousDigimon+" com dp: "+previousMinDp+"-"+maxDp);
						Main.getDigimonByName(previousDigimon).addAntievolution(new Digivolution(Main.getDigimonByName(antiDigimon), previousMinDp, maxDp));
						Main.getDigimonByName(antiDigimon).addDigivolution(new Digivolution(Main.getDigimonByName(previousDigimon), previousMinDp, maxDp));
					}
					if(minDp==100){
						antiDigimon = digimon;
						digimon = null;
					}
				}
			}
			Main.getDigimonByName(digimon).addAntievolution(new Digivolution(Main.getDigimonByName(antiDigimon), minDp, 99));
			Main.getDigimonByName(antiDigimon).addDigivolution(new Digivolution(Main.getDigimonByName(digimon), minDp, 99));
		} catch (FileNotFoundException e) {
			System.err.println("Error: cannot find file "+file+".txt");
			e.printStackTrace();
		}
	}
	
	private static void readDigimonFiles(){
		String file = null;
		String temp;
		
		for(int j=0; j<3; j++){
			switch (j) {
			case 0:
				file = "Vaccine";
				break;

			case 1:
				file = "Virus";
				break;
				
			case 2:
				file = "Data";
				break;
			}
			
			try {
				scanner = new Scanner(new File("tables/"+file+".txt"));
				for(int i=-1; i<4;){
					if(scanner.hasNextInt()) {
						scanner.nextLine();
						i++;
					}

					try{
						temp = scanner.nextLine();
					}catch(NoSuchElementException e){
						break;
					}
					
					if(i==0 || i==3){
						Digimon newDigimon = new Digimon(temp, j, i, scanner.nextLine());
						newDigimon.setData2(newDigimon.getData()[0]);
						Main.digimonList.add(newDigimon);
					}else{
						Main.digimonList.add(new Digimon(temp, j, i, scanner.nextLine(), scanner.nextLine()));
					}
					
					Main.digimonNameList.add(temp.toLowerCase());
				}
			} catch (FileNotFoundException e) {
				System.err.println("Error: cannot find file "+file+".txt");
				e.printStackTrace();
			}
		}
	}
	
	private static void readDNAMatrixes(){
		String fileName = null;
		int matrixSize = 0;
		
		for(int index=0; index<3; index++){
			switch (index) {
			case 0:
				fileName = "Champion";
				break;

			case 1:
				fileName = "Ultimate";
				break;
				
			case 2:
				fileName = "Mega";
				break;
			}
			
			try {
				scanner = new Scanner(new File("tables/"+fileName+".txt"));
				matrixSize = Integer.parseInt(scanner.nextLine());
				Main.dnaMatrix[index] = new String[matrixSize][matrixSize];
				
				for(int i=0; i<matrixSize; i++){
					for(int j=0; j<matrixSize; j++){
						Main.dnaMatrix[index][i][j] = scanner.next();
					}
				}
			} catch (FileNotFoundException e) {
				System.err.println("Error: cannot find file "+fileName+".txt");
				e.printStackTrace();
			}
		}
	}
	
	public static void readInput(){
		readDigimonFiles();
		readDNAMatrixes();
		readDigivolutionsFile();
		readSkillsFile();
	}
}
