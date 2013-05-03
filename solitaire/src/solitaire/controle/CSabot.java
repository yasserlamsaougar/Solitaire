package solitaire.controle;

import solitaire.application.Sabot;
import solitaire.application.Tas;
import solitaire.presentation.PSabot;
import solitaire.usine.CUsine;

/**
 * Controle d'un sabot
 */
public class CSabot extends Sabot implements ICDragable{

	/**
	 * la presentation du sabot 
	 */
	private PSabot presentation ;

	/**
	 * Constructeur du controle du Sabot
	 * @param name
	 * @param u
	 */
	public CSabot(String name, CUsine u) {
		super(name, u);
		CTasDeCartes ccachees = (CTasDeCartes) cachees;
		CTasDeCartes cvisibles = (CTasDeCartes) visibles;
		presentation = new PSabot(ccachees.getPresentation(), cvisibles.getPresentation(), this);
	}

	/**
	 * Retourne 3 cartes du tas du tas de cartes cach�es
	 */
	public void retournerCarte(){
		if(!cachees.isVide()){
			try {
				for(int i=0; i<3; i++){
					super.retournerCarte();
					presentation.retournerCarte();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	

	/**
	 * Retourne la carte du tas de face cach�es
	 * @pre : la carte est retournable
	 */
	public void retourner() 
	{
		if(cachees.isVide())
		{
			try {
				super.retourner();
				presentation.retourner();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Rempli le tas de cartes cachees
	 * @pre : il n'y a pas de cartes visibles
	 */
	public void setReserve(Tas tas) {
		try {
			super.setReserve(tas);
			presentation.setReserve(((CTasDeCartes) tas).getPresentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the p : retourne la presentation de la colonne
	 */
	public PSabot getPresentation() {
		return presentation;
	}

	/**
	 * M�thode appel�e suite � un click de l'utilisateur sur le tas de carte cach�es
	 * @param carte
	 */
	public void click() {
		if(cachees.isVide())
			retourner();
		else retournerCarte();
	}
	
	//////////////////////////////////m�thodes de gestion du drag&drop//////////////////////////////////////////
	/**
	 * Controle du d�but du drag&drop
	 * On d�place toujours des tasde cartes afin de conserver le m�me sch�ma pour tous les drag&drop
	 * @param c
	 */
	public void debutDND(CCarte c)  {
		CTasDeCartes tdc = (CTasDeCartes) CSolitaire.u.newTasDeCartes("drag&drop", CSolitaire.u);

		try{
			if(c.equals(getSommet())){
				depiler();
				tdc.empiler(c);
				presentation.debutDNDOK(tdc.getPresentation());
			}
			else {
				presentation.debutDNDKO(tdc.getPresentation());
			}
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
	}

	/**
	 * Controle de fin du drag and drop
	 * @param success : bool�en indiquant le succ�s ou non du drag&drop
	 * @param tdc : le tas de carte concern� par le drag&drop
	 */
	public void finDND(boolean success, ICTasDeCartes tdc){
		if(!success){
			empiler(tdc);
			presentation.finDNDKO(tdc.getPresentation());
		}
		else presentation.finDNDOK(tdc.getPresentation());
	}
}