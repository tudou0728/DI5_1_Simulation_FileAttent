package com.model;

public enum TypeEvtTraite {
	
Deb,
ArrCl,
AccFA,
AccSrv,
DepCl,
Fin;

public static int compare(TypeEvtTraite typeEvt1, TypeEvtTraite comparetype2) {
	
	if(comparetype2==TypeEvtTraite.DepCl)
	{
		return -1;
	}
	else 
	{
		return 1;
	}
}
};