package com.evenement;

import java.util.List;

import com.model.Client;
import com.model.Echeancier;
import com.model.TypeEvtTraite;

public class ArriveeClient extends Evenement {
	
	public Evenement evenement;
	private List<Client>clients;
	public ArriveeClient(TypeEvtTraite evt,Evenement event, List<Client>clients)
	{
		typeEvt=evt;
		evenement=event;
		this.clients=clients;
	}
	public   String executer(float sh)
	{
		evenement.setHs(sh);
		Client nouveau=new Client();
		nouveau.setDateArrivee(evenement.getHs());
		clients.add(nouveau);
		evenement.setTotalClientNumber(evenement.getTotalClientNumber()+1);
		
		AccesFileAttente acces=new AccesFileAttente(TypeEvtTraite.AccFA,evenement,clients);
		acces.setHs(evenement.getHs());
		acces.setHeureDebut(evenement.getHs());
		acces.setB(evenement.getB());
		acces.setQ(evenement.getQ());
		acces.setAttenteGlobale(evenement.getAttenteGlobale());
		acces.setTotalClientNumber(evenement.getTotalClientNumber());
		acces.setTempMoyenAttente(evenement.getTempMoyenAttente());
		acces.setDureeService(evenement.getDureeService());
		acces.setInterArrivee(evenement.getInterArrivee());
		acces.setServeurCourant(0);
		Echeancier.add(acces);
		
		ArriveeClient arr= new ArriveeClient(TypeEvtTraite.ArrCl,evenement,clients); 
		arr.setHs(evenement.getHs());
		arr.setHeureDebut(evenement.getHs()+evenement.getInterArrivee());
		arr.setB(evenement.getB());
		arr.setQ(evenement.getQ());
		arr.setAttenteGlobale(evenement.getAttenteGlobale());
		arr.setTotalClientNumber(evenement.getTotalClientNumber());
		arr.setTempMoyenAttente(evenement.getTempMoyenAttente());
		arr.setDureeService(evenement.getDureeService());
		arr.setInterArrivee(evenement.getInterArrivee());
		arr.setServeurCourant(0);
		Echeancier.add(arr);
		String evcree= acces.getTypeEvt().toString()+" "+acces.getHeureDebut() +" : serveur"+acces.getServeurCourant()+ "  |   "+arr.getTypeEvt().toString()+" "+arr.getHeureDebut();
		
		for(int i=0;i<Echeancier.evt.size();i++)
		{
			Echeancier.evt.get(i).setTotalClientNumber(evenement.getTotalClientNumber());
			Echeancier.evt.get(i).setHs(evenement.getHs());
			Echeancier.evt.get(i).setB(evenement.getB());
			Echeancier.evt.get(i).setQ(evenement.getQ());
			Echeancier.evt.get(i).setAttenteGlobale(evenement.getAttenteGlobale());
			Echeancier.evt.get(i).setTempMoyenAttente(evenement.getTempMoyenAttente());
		}
		return evcree;
		
	}

}
