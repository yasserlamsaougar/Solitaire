package solitaire.controle;

import solitaire.application.Carte;
import solitaire.application.TasDeCartes;
import solitaire.presentation.PTasDeCartes;
import solitaire.usine.CUsine;

public class CTasDeCartes extends TasDeCartes implements ICTasDeCartes{

	/**
	 * presentation du tas de cartes 
	 */
	private PTasDeCartes presentation;
	
	/**
	 * Constructeur du controleur du tas de cartes
	 * @param nom : nom du tas
	 * @param u : Abstract Factory 
	 */
	public CTasDeCartes(String nom, CUsine u) {
		super(nom, u);
		presentation= new PTasDeCartes(this);
	}
	
	/**
	 * Empile une carte du tas de cartes
	 * @param carte : la carte à empiler
	 * @pre la carte est empilable
	 */
	public void empiler(Carte carte) {
		if(isEmpilable(carte)){
			super.empiler(carte);
			
			CCarte cc = (CCarte)carte;
			presentation.empiler(cc.getPresentation());
		}
	}
	
	/**
	 * Dépile une carte du tas de carte
	 */
	public void depiler() throws Exception
	{
		CCarte cc = (CCarte)getSommet();
		super.depiler();
		presentation.depiler(cc.getPresentation());
	}
		
	/**
	 * Getter de la presentation
	 * @return p : retourne la presentation du tas de cartes
	 */
	public PTasDeCartes getPresentation() {
		return presentation;
	}
}