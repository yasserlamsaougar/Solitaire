package solitaire.controle;

import solitaire.application.Colonne;
import solitaire.application.Tas;
import solitaire.presentation.PColonne;
import solitaire.usine.CUsine;

/**
 * Controle d'une colonne
 */
public class CColonne extends Colonne implements ICDragable, ICDropable{
	/**
	 * Présentation de la colonne
	 */
	PColonne presentation;

	/**
	 * Constructeur d'un controleur de colonne 
	 * @param nom : nom de la colonne
	 * @param u : Usine de controleurs 
	 */
	public CColonne(String nom, CUsine u) {
		super(nom, u);

		CTasDeCartes ccachees = (CTasDeCartes) cachees;
		CTasDeCartesAlternees cvisibles = (CTasDeCartesAlternees) visibles;
		presentation = new PColonne(ccachees.getPresentation(), cvisibles.getPresentation(), this);
	}

	/**
	 * Retourne la carte du tas de face cachées
	 * @pre : la carte est retournable
	 */
	public void retournerCarte(){
		if(isCarteRetournable()){
			try {
				super.retournerCarte();
				presentation.retournerCarte();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Rempli le tas de cartes cachees
	 * @pre : il n'y a pas de cartes visibles
	 */
	public void setReserve(Tas tas) {
		if(visibles.isVide()){
			try {
				super.setReserve(tas);
				presentation.setReserve(((ICTasDeCartes) tas).getPresentation());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return the p : retourne la presentation de la colonne
	 */
	public PColonne getPresentation() {
		return presentation;
	}

	//////////////////////////////////méthodes de gestion du drag&drop//////////////////////////////////////////
	/**
	 * Controle du début du drag&drop
	 * On déplace toujours des tasde cartes afin de conserver le même schéma pour tous les drag&drop
	 * @param c : la carte de base du tas de cartes à déplacer
	 */
	public void debutDND(CCarte c)  {
		//initialisation du tas de cartes à déplacer
		CTasDeCartes tdc = (CTasDeCartes) CSolitaire.u.newTasDeCartes("drag&drop", CSolitaire.u);
		
		try{
			//Ajout des cartes au tas de cartes
			int carteTrouvee = 0;
			
			for(int i=visibles.getNombre(); i>0; i--){
				if(c.equals(visibles.getCarte(i)))
					carteTrouvee = i;
				if(carteTrouvee != 0)
					tdc.empiler(visibles.getCarte(i));
			}
			
			//suppression des cartes dans la colonne
			for(int i=0; i< carteTrouvee; i++)
				visibles.depiler();
			
			//Lancement du drag&drop si on a trouvé la carte
			if(carteTrouvee != 0)
				presentation.debutDNDOK(tdc.getPresentation());
			else presentation.debutDNDKO(tdc.getPresentation());
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
		else {
			if(visibles.isVide())
				retournerCarte();
			presentation.finDNDOK(tdc.getPresentation());
		}
	}

	/**
	 * Gestion du drop d'un tas de cartes
	 * @param tdc : le tas de cartes à transférer
	 */
	public void drop(ICTasDeCartes tdc) {
		if(isEmpilable(tdc)){
			empiler(tdc);
			presentation.dropOK();
		}
		else presentation.dropKO();
	}

	/**
	 * Gestion de l'entrée d'un composant dans le champ d'action d'une cible d'un drag
	 * @param tdc : tas de carte potentiellement dropable
	 */
	public void dragEnter(ICTasDeCartes tdc){
		if(isEmpilable(tdc)){
			presentation.showEmpilable();
		}
		else presentation.showNotEmpilable();
	}

	/**
	 * Gestion de la sortie d'un composant dans le champ d'action d'une cible d'un drag
	 */
	public void dragExit(){
		presentation.showNeutre();
	}
}