package fr.ans.rmac;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Hello world!
 *
 */
public class DiskFileExplorer 


{
	private String initialpath = "";
	private Boolean recursivePath = false;
	public int filecount = 0;
	public int dircount = 0;

	/**
	 * Constructeur
	 * @param path chemin du rÃ©pertoire
	 * @param subFolder analyse des sous dossiers
	 */
	public DiskFileExplorer(String path, Boolean subFolder) {
		super();
		this.initialpath = path;
		this.recursivePath = subFolder;
	}

	public void list() {
		this.listDirectory(this.initialpath);
	}
	public void unzip() {
		this.unzipDirectory(this.initialpath);
	}
	

	public void unzipDirectory(String dir) {
		String OUTPUT_FOLDER;
		String FILE_PATH;
		File file = new File(this.initialpath);
		File[] files = file.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				FILE_PATH = files[i].getAbsolutePath();
				OUTPUT_FOLDER = FILE_PATH.substring(0,FILE_PATH.length()-3);
				File folder = new File(OUTPUT_FOLDER);
				if (!folder.exists()) {
					folder.mkdirs();
				}
				// Créez un buffer (Tampon).
				byte[] buffer = new byte[1024];

				ZipInputStream zipIs = null;
				try {
					// Créez un objet ZipInputStream pour lire les fichiers à partir d'un chemin (path).
					zipIs = new ZipInputStream(new FileInputStream(FILE_PATH));

					ZipEntry entry = null;
					// Parcourir chaque Entry (de haut en bas jusqu'à la fin)
					while ((entry = zipIs.getNextEntry()) != null) {
						String entryName = entry.getName();
						String outFileName = OUTPUT_FOLDER + File.separator + entryName;
						//System.out.println("Unzip: " + outFileName);

						if (entry.isDirectory()) {
							// Créer des dossiers.
							new File(outFileName).mkdirs();
						} else {
							// Créez un Stream pour graver des données dans le fichier.
							FileOutputStream fos = new FileOutputStream(outFileName);

							int len;
							// ​​​​​​​
							// Lisez les données sur Entry présent
							while ((len = zipIs.read(buffer)) > 0) {
								fos.write(buffer, 0, len);
							} 
							fos.close();
						} 
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						zipIs.close();
					} catch (Exception e) {
					}
				}
			} 
		}
	}



	private void listDirectory(String dir) {
		File file = new File(dir);
		boolean acr = file.getAbsolutePath().contains("ACR");
		boolean crl = file.getAbsolutePath().contains("CRL");
		File[] files = file.listFiles();
		int j;
		int nbFiles;
		
		if (!acr && !crl) {
			if (files.length == 0) {
				j = file.getAbsolutePath().indexOf("Publication");
				System.out.print("Dossier: " + file.getAbsolutePath().substring(j) + " => ");
				System.out.println("0 fichier");
			}
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory() == false) {
						nbFiles = files.length;
						filecount = filecount + nbFiles;
						dircount++;
						j = file.getAbsolutePath().indexOf("Publication");
						if (j>0) {
							System.out.print("Dossier: " + file.getAbsolutePath().substring(j) + " => ");
							if (nbFiles == 1) {
								System.out.println("1 fichier");
							} else {
								System.out.println(nbFiles + " fichiers");
							}
						}
						

						break;
					} 
					if (files[i].isDirectory() == true && this.recursivePath == true) {
						this.listDirectory(files[i].getAbsolutePath());
					}
				}
			}
		}
	}  

	private String findType(String fileName) {
		
		String type = "";
		String [] types = {"EL_PP","EL_ORG","FO_PP","FO_ORG","ST_PP","ST_ORG"};
		for (int i = 0; i < types.length; i++) {
			if (fileName.contains(types[i])) {
				type = types[i];
			}
		}
		return type;
		
	}
	
	private String convertTypeToStingToFind(String type) {
		String stringToFind = null;
		switch(type){
		case "EL_ORG": 
			stringToFind= "ELEMENTAIRE ORGANISATIONS";
			break;
		case "EL_PP": 
			stringToFind= "ELEMENTAIRE PERSONNES";
			break;
		case "FO_ORG": 
			stringToFind= "FORT ORGANISATIONS";
			break;
		case "FO_PP": 
			stringToFind= "FORT PERSONNES";
			break;	
		case "ST_ORG": 
			stringToFind= "STANDARD ORGANISATIONS";
			break;
		case "ST_PP": 
			stringToFind= "STANDARD PERSONNES";
			break;	
		}
		return stringToFind;
	}
	
	private boolean verifyType(String fileName) {
		String type = this.findType(fileName);
		boolean typeOK = false;
		if (!type.equals("")){
			String stringToFind = this.convertTypeToStingToFind(type);
			String line;
		
			try {
				FileInputStream fis = new FileInputStream(fileName);
				BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
				while ((line = reader.readLine()) != null) {
					if (line.toString().contains(stringToFind) ) {
						typeOK = true;
						break;
					}
				}
				reader.close();
	
			} catch (Exception e) {
				 typeOK = false;
			}
			
		} else {
			typeOK = true;
		}
		return typeOK;
	}
	public void listAndVerifyType() {
		this.listAndVerifyType(this.initialpath);
	}

	private void listAndVerifyType(String dir) {
		File file = new File(dir);
		boolean acr = file.getAbsolutePath().contains("ACR");
		boolean crl = file.getAbsolutePath().contains("CRL");
		File[] files = file.listFiles();
		int j;

		if (!acr && !crl) {
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory() == false) {
						if (this.verifyType(files[i].getAbsolutePath())) {
							//System.out.println("Fichier: " + files[i].getAbsolutePath() + " OK");
							

						} else {
							j = file.getAbsolutePath().indexOf("Publication");
							if (j>0) {
								System.out.println("Fichier: " + files[i].getAbsolutePath().substring(j) + " NOK");
							} else {
								System.out.println("Fichier: " + files[i].getAbsolutePath() + " NOK");
							}
						}

					} 
					if (files[i].isDirectory() == true && this.recursivePath == true) {
						this.listAndVerifyType(files[i].getAbsolutePath());
					}
				}
			}
		}
	} 

	public static void main( String[] args )
	{
		//String pathToExplore = "C:\\Users\\flabatie\\Documents\\ENV-ANS\\Comptage-Certificats\\dossiers-compresses";
		String pathToExplore = args [0];
		DiskFileExplorer diskFileExplorer = new DiskFileExplorer(pathToExplore, true);
		Long start = System.currentTimeMillis();
		diskFileExplorer.unzip();
		diskFileExplorer.list();
		diskFileExplorer.listAndVerifyType();
		System.out.println("----------");
		System.out.println("Analyse de " + pathToExplore + " en " + (System.currentTimeMillis() - start) + " mses");
		System.out.println(diskFileExplorer.dircount + " dossiers");
		System.out.println(diskFileExplorer.filecount + " fichiers");


	}
}
