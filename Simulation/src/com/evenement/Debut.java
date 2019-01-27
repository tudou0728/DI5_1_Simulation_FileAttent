package com.evenement;

import java.util.List;

import com.model.Client;
import com.model.Echeancier;
import com.model.TypeEvtTraite;

public class Debut extends Evenement {

	public Evenement evenement;
	private List<Client>clients;
	public Debut(TypeEvtTraite evt,Evenement event,List<Client>clients)
	{
		typeEvt=evt;
		evenement=event;
		this.clients=clients;
	}
	public  String executer(float sh)
	{
//		evenement.setB(0);
		evenement.setQ(0);
		evenement.setHs(sh);
		evenement.setPrecHs((float)0);
		evenement.setAire_B((float) 0);
		evenement.setAire_Q((float) 0);
		evenement.setAttenteGlobale((float)0);
		evenement.setServeurCourant(0);
		clients.clear();
		ArriveeClient arr= new ArriveeClient(TypeEvtTraite.ArrCl,evenement,clients); 
		arr.setHs(evenement.getHs());
		arr.setServeurCourant(0);
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
		for(int i=0;i<Echeancier.evt.size();i++)
		{
			Echeancier.evt.get(i).setTotalClientNumber(evenement.getTotalClientNumber());
			Echeancier.evt.get(i).setHs(evenement.getHs());
			Echeancier.evt.get(i).setB(evenement.getB());
			Echeancier.evt.get(i).setQ(evenement.getQ());
			Echeancier.evt.get(i).setAttenteGlobale(evenement.getAttenteGlobale());
			Echeancier.evt.get(i).setTempMoyenAttente(evenement.getTempMoyenAttente());
		}
		String evcree= arr.getTypeEvt().toString()+" "+arr.getHeureDebut();
		return evcree;
	}

}
