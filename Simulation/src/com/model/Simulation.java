package com.model;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.evenement.Debut;
import com.evenement.Evenement;
public class Simulation {
	
	private String filename;
	private float duree;
	private Evenement event;
	private List<Client> clients;
	
	public Simulation(Evenement evenement,String string,float f) {
		this.filename=string;
		this.duree=f;
		event=evenement;
		clients=new ArrayList<Client>();
	}

	//on commence � t=1 et pas 0
	public void simulate() throws IOException
	{
		//lyy begin---
//		int count=0;
		//lyy end---
		CSVWritter.startCsvWriting(filename);
		event.setHs(1);
		event.setAire_B(0);
		event.setAire_Q(0);
		
		Debut deb=new Debut(TypeEvtTraite.Deb,event,clients);
		deb.setHs((float)1);
		deb.setHeureDebut((float)1);
		Echeancier.add(deb);
		//lyy begin---
//		while(!Echeancier.evt.isEmpty() && count<duree)
		String maxTempAttent="";
		List<Client> clients=new ArrayList<Client>();
		float tempAttentMax=0;
		float tempAttentGlobal=0;
		Echeancier.etatServeur=new int[Echeancier.nombreServeur];
		for(int i=0;i<Echeancier.nombreServeur;i++) {
			Echeancier.etatServeur[i]=0;
		}
		while(!Echeancier.evt.isEmpty() && event.getHs()<duree)
		//lyy end---
		{
			Echeancier.sort();
			event.setAire_B(event.getAire_B() + (event.getHs()-event.getPrecHs())*event.getB());
			event.setAire_Q(event.getAire_Q() + (event.getHs()-event.getPrecHs())*event.getQ());
			System.out.println(Echeancier.evt.get(0).getTypeEvt().name());		
			event.setPrecHs(event.getHs());
			event.setHs(Echeancier.evt.get(0).getHeureDebut());
			Echeancier.evt.get(0).setHs(event.getTotalClientNumber());
			String evtCree=Echeancier.evt.get(0).executer(event.getHs());
			//lyy begin---
			if(Echeancier.evt.get(0).getTypeEvt().ordinal()==1) {
				Client client=new Client();
				client.setDateArrivee(Echeancier.evt.get(0).getHeureDebut());
				clients.add(client);
			}else if (Echeancier.evt.get(0).getTypeEvt().ordinal()==3) {
				clients.get(0).setDateAccSrv(Echeancier.evt.get(0).getHeureDebut());
				tempAttentGlobal=tempAttentGlobal+(clients.get(0).getDateAccSrv()-clients.get(0).getDateArrivee());
				if(tempAttentMax<=(clients.get(0).getDateAccSrv()-clients.get(0).getDateArrivee())) {
					tempAttentMax=(clients.get(0).getDateAccSrv()-clients.get(0).getDateArrivee());
					maxTempAttent=Float.toString(tempAttentMax);
				}
				clients.remove(0);
			}
			//Echeancier.evt.get(0).getTypeEvt().name()+" "+Float.toString(Echeancier.evt.get(0).getHeureDebut())
//			CSVWritter.writeLine(Float.toString(Echeancier.evt.get(0).getHeureDebut()), Echeancier.evt.get(0).getTypeEvt().name(),evtCree, Integer.toString(Echeancier.evt.get(0).getB()), Integer.toString(Echeancier.evt.get(0).getQ()),"", Integer.toString(Echeancier.evt.get(0).getTotalClientNumber()), Float.toString(Echeancier.evt.get(0).getAttenteGlobale()));
			CSVWritter.writeLine(Float.toString(Echeancier.evt.get(0).getHeureDebut()), Echeancier.evt.get(0).getTypeEvt().name(),evtCree, Integer.toString(Echeancier.evt.get(0).getB()), Integer.toString(Echeancier.evt.get(0).getQ()),"", Integer.toString(Echeancier.evt.get(0).getTotalClientNumber()), Float.toString(tempAttentGlobal));
//			System.out.println(Float.toString(Echeancier.evt.get(0).getHeureDebut()) + " "+Echeancier.evt.get(0).getTypeEvt().name()+" "+evtCree);
			//lyy end---
			Echeancier.remove();
			//lyy begin---
//			count++;
			//lyy end---
		}
		CSVWritter.writeLine(Float.toString(event.getHs()), "Fin","","","","","","");
		//lyy begin---
		String tempAttentMoyen=Float.toString(tempAttentGlobal / Echeancier.evt.get(0).getTotalClientNumber());
		CSVWritter.writeResult(tempAttentMoyen, maxTempAttent);
		//lyy end---
		CSVWritter.stopCsvWriting();
		Desktop d=Desktop.getDesktop();
		File file= new File(filename);
		if(file.exists())
		{
			d.open(file);
		}
	}	
	
	public void simulate2() throws IOException
	{
		CSVWritter.startCsvWriting(filename);
		event.setHs(1);
		event.setAire_B(0);
		event.setAire_Q(0);
		
		Debut deb=new Debut(TypeEvtTraite.Deb,event,clients);
		deb.setHs((float)1);
		deb.setHeureDebut((float)1);
		Echeancier.add(deb);
		while(!Echeancier.evt.isEmpty() && event.getHs()<1440)
		{
			Echeancier.sort();
			event.setAire_B(event.getAire_B() + (event.getHs()-event.getPrecHs())*event.getB());
			event.setAire_Q(event.getAire_Q() + (event.getHs()-event.getPrecHs())*event.getQ());
			System.out.println(Echeancier.evt.get(0).getTypeEvt().name());
			event.setPrecHs(event.getHs());
			event.setHs(Echeancier.evt.get(0).getHeureDebut());
			Echeancier.evt.get(0).setHs(event.getTotalClientNumber());
			String evtCree=Echeancier.evt.get(0).executer(event.getHs());
			//CSVWritter.writeLine(Float.toString(Echeancier.evt.get(0).getHeureDebut()), Echeancier.evt.get(0).getTypeEvt().name(),evtCree, Integer.toString(Echeancier.evt.get(0).getB()), Integer.toString(Echeancier.evt.get(0).getQ()),"", Integer.toString(Echeancier.evt.get(0).getTotalClientNumber()), Float.toString(Echeancier.evt.get(0).getAttenteGlobale()));
			Echeancier.remove();
		}
		while(!Echeancier.evt.isEmpty() && event.getHs()<1440+duree)
		{
			Echeancier.sort();
			event.setAire_B(event.getAire_B() + (event.getHs()-event.getPrecHs())*event.getB());
			event.setAire_Q(event.getAire_Q() + (event.getHs()-event.getPrecHs())*event.getQ());
			System.out.println(Echeancier.evt.get(0).getTypeEvt().name());
			event.setPrecHs(event.getHs());
			event.setHs(Echeancier.evt.get(0).getHeureDebut());
			Echeancier.evt.get(0).setHs(event.getTotalClientNumber());
			String evtCree=Echeancier.evt.get(0).executer(event.getHs());
			CSVWritter.writeLine(Float.toString(Echeancier.evt.get(0).getHeureDebut()), Echeancier.evt.get(0).getTypeEvt().name(),evtCree, Integer.toString(Echeancier.evt.get(0).getB()), Integer.toString(Echeancier.evt.get(0).getQ()),"", Integer.toString(Echeancier.evt.get(0).getTotalClientNumber()), Float.toString(Echeancier.evt.get(0).getAttenteGlobale()));
			Echeancier.remove();
		}
		CSVWritter.writeLine(Float.toString(event.getHs()), "Fin","","","","","","");
		CSVWritter.stopCsvWriting();
		Desktop d=Desktop.getDesktop();
		File file= new File(filename);
		if(file.exists())
		{
			d.open(file);
		}
	}	
}
