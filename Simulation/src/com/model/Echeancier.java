package com.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.evenement.Evenement;
public class Echeancier {
	
	public static List<Evenement> evt=new ArrayList<Evenement>();
	//lyy begin---
	public static int nombreServeur=1;
	public static int[]etatServeur=new int[nombreServeur];
	//lyy end---
	
	public static void add(Evenement e)
	{
		evt.add(e);
	}
	
	public static Evenement  remove()
	{
		return evt.remove(0);
	}
	
	@SuppressWarnings("unchecked")
	public static void sort()
	{
		Collections.sort(evt);
		for (Evenement e: evt)
		{
			System.out.println("heure debut service"+e.getHeureDebut()+ "heure hs");
		}
		
	}
	
	//lyy begin---
	public static void setNBServeur(int nbServeur) {
		nombreServeur=nbServeur;
	}
	//lyy end---
}
