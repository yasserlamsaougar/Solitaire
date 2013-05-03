package solitaire.controle;

import solitaire.application.Carte;
import solitaire.application.TasDeCartesColorees;
import solitaire.presentation.PTasDeCartesColorees;
import solitaire.usine.CUsine;

/**
 * Controle d'un tas de cartes colorées
 */
public class CTasDeCartesColorees extends TasDeCartesColorees implements ICTasDeCartes, ICDragable, ICDropable{
	/**
	 * presentation du tas de cartes 
	 */
	private PTasDeCartesColorees presentation;	

	public CTasDeCartesColorees(String nom, int couleur, CUsine u) {
		super(nom, couleur, u);

		presentation = new PTasDeCartesColorees(this, couleur);
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
	 * Getter de la presentation
	 * @return p : retourne la presentation du tas de cartes
	 */
	public PTasDeCartesColorees getPresentation() {
		return presentation;
	}

	//////////////////////////////////méthodes de gestion du drag&drop//////////////////////////////////////////
	/**
	 * Controle du début du drag&drop
	 * On déplace toujours des tasde cartes afin de conserver le même schéma pour tous les drag&drop
	 * @param c : la carte de base du tas de cartes à déplacer
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
	 * @param success : booléen indiquant le succès ou non du drag&drop
	 * @param tdc : le tas de carte concerné par le drag&drop
	 */
	public void finDND(boolean success, ICTasDeCartes tdc){
		if(!success){
			empiler(tdc);
			presentation.finDNDKO(tdc.getPresentation());
		}
		else presentation.finDNDOK(tdc.getPresentation());
	}

	/**
	 * Gestion du drop d'un tas de cartes
	 * Dans le cas du tas de cartes colorees, on ne peut accepter qu'une seule carte à la fois
	 * @param tdc : le tas de cartes à transférer
	 */
	public void drop(ICTasDeCartes tdc) {
		try {
			if(tdc.getNombre() == 1 && isEmpilable(tdc.getSommet())){
				empiler(tdc);
				presentation.dropOK();
			}
			else presentation.dropKO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gestion de l'entrée d'un composant dans le champ d'action d'une cible d'un drag
	 * @param tdc : tas de carte potentiellement dropable
	 */
	public void dragEnter(ICTasDeCartes tdc){
		try {
			if(tdc.getNombre() == 1 && isEmpilable(tdc.getSommet())){
				presentation.showEmpilable();
			}
			else presentation.showNotEmpilable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gestion de la sortie d'un composant dans le champ d'action d'une cible d'un drag
	 */
	public void dragExit(){
		presentation.showNeutre();
	}
}
