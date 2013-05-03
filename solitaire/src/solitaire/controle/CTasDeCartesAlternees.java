package solitaire.controle;

import solitaire.application.Carte;
import solitaire.application.TasDeCartesAlternees;
import solitaire.presentation.PTasDeCartes;
import solitaire.usine.CUsine;

/**
 * Controle d'un tas de cartes altern�es
 */
public class CTasDeCartesAlternees extends TasDeCartesAlternees implements ICTasDeCartes{
	/**
	 * presentation du tas de cartes alternees
	 */
	private PTasDeCartes p;

	/**
	 * Constructeur du controleur du tas de cartes altern�es
	 * @param nom
	 * @param u
	 */
	public CTasDeCartesAlternees(String nom,CUsine u){
		super(nom,u);
		p=new PTasDeCartes(this);

	}
	
	/**
	 * D�pile une carte du tas de carte
	 */
	public void depiler() throws Exception{
		CCarte cc = (CCarte) getSommet();
		super.depiler();
		p.depiler(cc.getPresentation());
	}
	
	/**
	 * Empile une carte du tas de cartes
	 * @param carte : la carte � empiler
	 * @pre la carte est empilable
	 */
	public void empiler(Carte carte){
		super.empiler(carte);
		CCarte cc = (CCarte)carte;
		try{
			if(cc==getSommet()){
				p.empiler(cc.getPresentation());
			}
		}
		catch(Exception e){}
	}

	/**
	 * Getter de la presentation
	 * @return p : retourne la presentation du tas de cartes
	 */
	public PTasDeCartes getPresentation() {
		return p;
	}
}
