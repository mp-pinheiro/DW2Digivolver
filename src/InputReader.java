import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class InputReader {
	private static Scanner scanner;
	
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
	}
}
