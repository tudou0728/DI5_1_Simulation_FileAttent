package com.model;

import java.io.FileWriter;
import java.io.IOException;


public class CSVWritter {
	
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	//CSV file header
	private static final String FILE_HEADER1= ",,,,VAR ETAT SYSTEME,,,VAR STATS,";
	private static final String FILE_HEADER2 = "HS,TYPE EVT TRAITE, ECHEANCIER, ETAT GUICHET(0=LIBRE/1=OCCUPE),NB CLIENT DANS LA QUEUE,QUEUE,NB CLIENTS RECUS, ATTENTE GLOBALE AVANT SERVICE";

	private static FileWriter fileWriter;
	
	public static void startCsvWriting(String fileName)
	{
		fileWriter = null;
		
		try {
			fileWriter = new FileWriter(fileName);

			//Write the CSV file header
			fileWriter.append(FILE_HEADER1.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.append(FILE_HEADER2.toString());
			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			
			
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		}
	}
	public static void stopCsvWriting() {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
			
	}
	
	public static void writeLine(String HS,String evt,String Ech,String Egui,String NbCq,String Q,String NbCr,String AttenteGlobale){
		try {
			
		fileWriter.append(HS);
		fileWriter.append(COMMA_DELIMITER);
		fileWriter.append(evt);
		fileWriter.append(COMMA_DELIMITER);
		fileWriter.append(Ech);
		fileWriter.append(COMMA_DELIMITER);
		fileWriter.append(Egui);
		fileWriter.append(COMMA_DELIMITER);
		fileWriter.append(NbCq);
		fileWriter.append(COMMA_DELIMITER);
		fileWriter.append(Q);
		fileWriter.append(COMMA_DELIMITER);
		fileWriter.append(NbCr);
		fileWriter.append(COMMA_DELIMITER);
		fileWriter.append(AttenteGlobale);
		fileWriter.append(COMMA_DELIMITER);
		fileWriter.append(NEW_LINE_SEPARATOR);
		}
		catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		}
	}
	
	//lyy begin---
	public static void writeResult(String tempAttentMoyen, String maxTempAttent) {
		try {
			fileWriter.append("Le temps d'attente moyen");
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(tempAttentMoyen);
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.append("Le temps d'attente maximal");
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(maxTempAttent);
		} catch (IOException e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		}
	}
//	
//	public static String calculMaxAttent() {
//		
//	}
	//lyy end---
}