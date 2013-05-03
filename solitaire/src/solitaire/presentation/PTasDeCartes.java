package solitaire.presentation;

import javax.swing.JPanel;

import solitaire.controle.ICTasDeCartes;


/**
 * Présentation d'un tas de cartes
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
	 * Dépile une carte de la présentation du tas et ajuste la taille du tas en conscéquence
	 * @param c : présentation de la carte à dépiler
	 */
	public void depiler(PCarte c){
		remove(c);
		resize();
	}
	
	/**
	 * Empile une carte dans la présentation du tas et ajuste la taille du tas en conscéquence
	 * @param c : présentation de la carte à empiler
	 */
	public void empiler(PCarte c){
		add(c, 0);	
		resize();
	}
	
	/**
	 * Actualise la taille à l'affichage du tas de cartes
	 * La taille minimum est la taille d'une carte pour conserver facilement la structure du programme
	 */
	public void resize(){
		//Calcul de la taille du conteneur
		if(getComponentCount()>0)
			setSize(PCarte.largeur+(getComponentCount()-1)*dx, PCarte.hauteur+(getComponentCount()-1)*dy);
		else setSize(PCarte.largeur, PCarte.hauteur);	//On garde toujours une taille au moins égale à une carte pour garder un affichage homogène
		setPreferredSize(getSize());
		
		//Calcul de l'emplacement de chaque composant
		for(int i=0; i<getComponentCount(); i++)
			getComponent(getComponentCount()-i-1).setLocation(dx*i, dy*i);
		
		repaint();
	}
	
	/**
	 * Modifie le décalage de chaque carte du tas à l'affichage
	 * @param dx : décalage horizontal
	 * @param dy : décalage vertical
	 */
	public void setDxDy(int dx, int dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	/**
	 * Getter du décalage entre chaque carte du tas
	 * @return le décalage en pixels
	 */
	public int getDx() {
		return dx;
	}
	
	/**
	 * Getter du décalage entre chaque carte du tas
	 * @return le décalage en pixels
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