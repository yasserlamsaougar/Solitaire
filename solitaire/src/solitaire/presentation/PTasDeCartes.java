package solitaire.presentation;

import javax.swing.JPanel;

import solitaire.controle.ICTasDeCartes;


/**
 * Pr�sentation d'un tas de cartes
 */
public class PTasDeCartes extends JPanel {
	/**
	 * Attributs 
	 */
	private ICTasDeCartes controle;
	protected int dx, dy;
	
	/**
	 * Constructeur de la presentation  du tas de cartes
	 * @param controle : Controleur du tas de cartes
	 */
	public PTasDeCartes(ICTasDeCartes controle) {
		this.controle = controle;
		init();
	}
	
	/**
	 * D�pile une carte de la pr�sentation du tas et ajuste la taille du tas en consc�quence
	 * @param c : pr�sentation de la carte � d�piler
	 */
	public void depiler(PCarte c){
		remove(c);
		resize();
	}
	
	/**
	 * Empile une carte dans la pr�sentation du tas et ajuste la taille du tas en consc�quence
	 * @param c : pr�sentation de la carte � empiler
	 */
	public void empiler(PCarte c){
		add(c, 0);	
		resize();
	}
	
	/**
	 * Actualise la taille � l'affichage du tas de cartes
	 * La taille minimum est la taille d'une carte pour conserver facilement la structure du programme
	 */
	public void resize(){
		//Calcul de la taille du conteneur
		if(getComponentCount()>0)
			setSize(PCarte.largeur+(getComponentCount()-1)*dx, PCarte.hauteur+(getComponentCount()-1)*dy);
		else setSize(PCarte.largeur, PCarte.hauteur);	//On garde toujours une taille au moins �gale � une carte pour garder un affichage homog�ne
		setPreferredSize(getSize());
		
		//Calcul de l'emplacement de chaque composant
		for(int i=0; i<getComponentCount(); i++)
			getComponent(getComponentCount()-i-1).setLocation(dx*i, dy*i);
		
		repaint();
	}
	
	/**
	 * Modifie le d�calage de chaque carte du tas � l'affichage
	 * @param dx : d�calage horizontal
	 * @param dy : d�calage vertical
	 */
	public void setDxDy(int dx, int dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	/**
	 * Getter du d�calage entre chaque carte du tas
	 * @return le d�calage en pixels
	 */
	public int getDx() {
		return dx;
	}
	
	/**
	 * Getter du d�calage entre chaque carte du tas
	 * @return le d�calage en pixels
	 */
	public int getDy() {
		return dy;
	}
	
	/**
	 * Getter du controleur du tas de cartes
	 * @return le controleur du tas de carte
	 */
	public ICTasDeCartes getControle () {
	    return controle;
	}
	
	/**
	 * Initialisation de la JPanel 
	 */
	private void init(){
		setLayout(null);
		setDxDy(0, 0);
		
		setOpaque(false);
	}
}