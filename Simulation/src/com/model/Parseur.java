package com.model;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Parseur {
	List<Float> dateApp;
	List<Float> dureeServ;
	public boolean parse(String chemin) 
	{
		
		try {
			Path path = Paths.get(chemin);
			 Charset charset = Charset.forName("ISO-8859-1");
		      List<String> lines = Files.readAllLines(path,charset);
		       dateApp=new ArrayList<Float>();
		       dureeServ=new ArrayList<Float>();
		      for (String line : lines) {
		    	  
		    	  if(!line.isEmpty())
		    	  {
		    		  String [] val=line.split(" ");
		    		  
				        dateApp.add(Float.parseFloat(val[0]));
				        dureeServ.add(Float.parseFloat(val[1]));
		    	  }
		        
		      }
		    } catch (IOException e) {
		    		return false;
		    }
		return true;
		
	}
	
	public float calculMoyenneInterArrivee()
	{
		float somme=(float) 0;
		for(int i = 1; i < dateApp.size(); i++){
	         somme += dateApp.get(i)-dateApp.get(i-1);
	      }
	      float moyenne = (float) somme / dateApp.size();
		
		return moyenne;
		
	}
	
	public float calculMoyenneReponse()
	{
		float somme=(float) 0;
		for(int i = 1; i < dureeServ.size(); i++){
	         somme += dureeServ.get(i);
	      }
	      float moyenne = (float) somme / dureeServ.size();
		
		return moyenne;
		
	}
	public void write(String filename)
	{
		CSVWritter.startCsvWriting(filename);
		//while(!finAlgo)
		//{
			//CSVWritter.writeLine(HS, evt, Ech, Egui, NbCq, Q, NbCr, AttenteGlobale);
		//}
		CSVWritter.writeLine("", "", "Deb 0", "", "", "", "", "");
		CSVWritter.writeLine("0", "Deb", "ArrCl 0,4", "0", "0", "Vide", "0", "0");
		CSVWritter.stopCsvWriting();
	}
	public static void main(String[] args)
	{
		Parseur sim=new Parseur();
		sim.parse("DataAppels.txt");
		System.out.println("Moyenne inter arrivee: "+ sim.calculMoyenneInterArrivee());
		System.out.println("Moyenne réponse: "+ sim.calculMoyenneReponse());
		sim.write("sim.csv");
	}
}
