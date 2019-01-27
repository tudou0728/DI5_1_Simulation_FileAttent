package com.evenement;

import java.util.List;

import com.model.Client;
import com.model.Echeancier;
import com.model.TypeEvtTraite;

public class AccesFileAttente extends Evenement {
	
	
	
	public Evenement evenement;
	private List<Client>clients;
	
	public AccesFileAttente(TypeEvtTraite evt,Evenement event, List<Client>clients)
	{
		this.typeEvt=evt;
		this.evenement=event;
		this.clients=clients;
	}
	public String executer(float sh)
	{
		this.evenement.setQ(this.evenement.getQ()+1);
		for(int i=0;i<Echeancier.nombreServeur;i++) {
			if(Echeancier.etatServeur[i]==0) {
				Echeancier.etatServeur[i]=1;
				this.serveurCourant=i;
				evenement.setHs(sh);
				evenement.setB(1);
				AccesService acc=new AccesService(TypeEvtTraite.AccSrv,evenement,clients);
				acc.setHs(evenement.getHs());
				acc.setHeureDebut(evenement.getHs());
				acc.setB(1);
				acc.setQ(evenement.getQ());
				acc.setAttenteGlobale(evenement.getAttenteGlobale());
				acc.setTotalClientNumber(evenement.getTotalClientNumber());
				acc.setTempMoyenAttente(evenement.getTempMoyenAttente());
				acc.setDureeService(evenement.getDureeService());
				acc.setInterArrivee(evenement.getInterArrivee());
				acc.setServeurCourant(i);
				Echeancier.add(acc);
				String evcree= acc.getTypeEvt().toString()+" "+acc.getHeureDebut()+" : serveur"+acc.getServeurCourant();
				return evcree;
			}
		}
		for(int i=0;i<Echeancier.evt.size();i++)
		{
			Echeancier.evt.get(i).setTotalClientNumber(evenement.getTotalClientNumber());
			Echeancier.evt.get(i).setHs(evenement.getHs());
			Echeancier.evt.get(i).setB(evenement.getB());
			Echeancier.evt.get(i).setQ(evenement.getQ());
			Echeancier.evt.get(i).setAttenteGlobale(evenement.getAttenteGlobale());
			Echeancier.evt.get(i).setTempMoyenAttente(evenement.getTempMoyenAttente());
		}
		return "";
	}

}
