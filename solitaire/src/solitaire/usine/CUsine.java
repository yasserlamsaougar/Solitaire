package solitaire.usine;
import solitaire.application.Carte;
import solitaire.application.Colonne;
import solitaire.application.Sabot;
import solitaire.application.Solitaire;
import solitaire.application.TasDeCartes;
import solitaire.application.TasDeCartesAlternees;
import solitaire.application.TasDeCartesColorees;
import solitaire.application.Usine;
import solitaire.controle.CCarte;
import solitaire.controle.CColonne;
import solitaire.controle.CSabot;
import solitaire.controle.CSolitaire;
import solitaire.controle.CTasDeCartes;
import solitaire.controle.CTasDeCartesAlternees;
import solitaire.controle.CTasDeCartesColorees;

/**
 * Usine de controle
 */
public class CUsine extends Usine {
	
	/**
	 * 
	 * cr�e un Controleur Carte 
	 * @return Carte : Controleur de carte cr�e
	 */
	public Carte newCarte(int valeur,int couleur){
		return new CCarte(valeur, couleur);
	}
	
	/** 
	 * cr�e un Controleur TasDeCartes 
	 * @return TasDeCartes : Controleur de TasDeCartes cr�e
	 */
	public TasDeCartes newTasDeCartes(String name,Usine u){
		return new CTasDeCartes(name, (CUsine)u);
	}
	
	/** 
	 * cr�e un Controleur TasDeCartesAlternees 
	 * @return TasDeCartesAlternees : Controleur de TasDeCartesAlternees cr�e
	 */
	public TasDeCartesAlternees newTasDeCartesAlternees(String name,Usine u){
		return new CTasDeCartesAlternees(name, (CUsine)u);
	}
	
	/** 
	 * cr�e un Controleur TasDeCartesColorees
	 * @return TasDeCartesColorees : Controleur de TasDeCartesColorees cr�e
	 */
	public TasDeCartesColorees newTasDeCartesColorees(String name, int couleur, Usine u){
		return new CTasDeCartesColorees(name, couleur, (CUsine)u);
	}
	
	/** cr�e un Controleur de Colonne 
	 * @return Colonne : Controleur de Colonne cr�e
	 */
	public Colonne newColonne(String name, Usine u){
		return new CColonne(name, (CUsine)u);
	}
	
	/**cr�e un Controleur de Sabot
	 * @return Colonne : Controleur de Sabot cr�e
	 */
	public Sabot newSabot(String name,Usine u){
		return new CSabot(name, (CUsine)u);
	}
	
	/**cr�e un controle de Solitaire
	 * @return Solitaire : Controleur de Solitaire cr�e
	 */
	public Solitaire newSolitaire(String name, Usine u){
		return new CSolitaire(name, (CUsine)u);
	}
}
