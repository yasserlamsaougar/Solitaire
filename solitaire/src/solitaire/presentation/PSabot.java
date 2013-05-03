package solitaire.presentation;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragSource;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import solitaire.controle.CSabot;
import solitaire.listener.CacheesListener;
import solitaire.listener.ComposantTransferable;
import solitaire.listener.TDCDragListener;


/**
 * Présentation d'un sabot
 */
public class PSabot extends JPanel implements IPDragable{
	private CSabot  controle;
	private PTasDeCartes cachees;
	private PTasDeCartes visibles;
	
	/**
	 * Attributs de gestion du drag&drop
	 */
	private DragSource source;
	private EventObject event;
	
	private Point cursorLocation;		//coordonnées du curseur par rapport au tas de cartes
	private PTasDeCartes tdcDND;		//Tas de cartes concerné par le drag and drop
	
	/**
	 * Constructeur de la presentation d'un sabot
	 * @param presentation
	 * @param presentation2
	 * @param cSabot
	 */
	public PSabot(PTasDeCartes cachees, PTasDeCartes visibles, CSabot controle) {
		this.controle = controle;
		this.cachees = cachees;
		this.visibles = visibles;
		
		init();
	}

	/**
	 * Retourne 3 cartes du tas du tas de crates cachées
	 */
	public void retournerCarte() {
	}

	/**
	 * Rempli le sabot à l'aide d'un tas de cartes
	 * @param tdc : presentation du tas de cartes
	 */
	public void setReserve(PTasDeCartes tdc) {
	}

	/**
	 * Retourner toutes les cartes dans le sabot
	 */
	public void retourner() {
		
	}
	
	/**
	 * Getter du controle du sabot
	 * @return le controle du sabot
	 */
	public CSabot getControle() {
		return controle;
	}
	
	/**
	 * Initialisation du sabot
	 */
	private void init()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setOpaque(false);
		
		//decalage entre chaques cartes des tas de cartes
		visibles.setDxDy(20, 0);
		
		//Indiquer visuellement qu'il y a un tas de cartes même lorsqu'il n'y a plus de cartes dedans
		cachees.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		this.add(cachees);
		this.add(visibles);
		
		//listeners
		cachees.addMouseListener(new CacheesListener(this));

		//listener du drag&drop du tas de cartes visibles
		setDragable(visibles);
	}
	
	//////////////////////////////////méthodes de gestion du drag//////////////////////////////////////////	
	/**
	 * Rend possible un drag sur le composant
	 * @param tdcDragable : le tas de cartes dragable
	 */
	public void setDragable(PTasDeCartes tdcDragable) {
		source = new DragSource();
		TDCDragListener dragTDC = new TDCDragListener(this);
		
		source.createDefaultDragGestureRecognizer(tdcDragable, DnDConstants.ACTION_MOVE, dragTDC);
		source.addDragSourceListener(dragTDC);
		source.addDragSourceMotionListener(dragTDC);
	}
	
	/**
	 * Début du drag and drop
	 * @param tdc : tas de cartes à déplacer
	 */
	public void debutDNDOK(PTasDeCartes tdc){
		source.startDrag((DragGestureEvent) event, DragSource.DefaultMoveDrop, new ComposantTransferable(tdc), source.getDragSourceListeners()[0]);
		
		//Initialisation du déplacement
		tdcDND = tdc;
		getRootPane().add(tdcDND, 0);
	}

	/**
	 * Annulation du début du drag and drop
	 * @param tdc : tas de cartes à déplacer
	 */
	public void debutDNDKO(PTasDeCartes tdc) {
	}

	/**
	 * Affichage du déplacement lors du drag&drop
	 */
	public void deplacementDND(Point p) {
		tdcDND.setLocation((int) (p.getX()-getRootPane().getLocationOnScreen().getX()-cursorLocation.getX()),
				(int) (p.getY()-getRootPane().getLocationOnScreen().getY()-cursorLocation.getY()));
	}

	/**
	 * Traitement de fin d'un drag&drop réussi
	 * @param tdc : tas de cartes concerné par le drag&drop
	 */
	public void finDNDOK(PTasDeCartes tdc) {
		getRootPane().remove(tdc);
		getRootPane().repaint();
	}

	/**
	 * Traitement de fin d'un drag&drop raté
	 * @param tdc : tas de cartes concerné par le drag&drop
	 */
	public void finDNDKO(PTasDeCartes tdc) {
		getRootPane().remove(tdc);
		getRootPane().repaint();
	}
	
	/**
	 * Modification de l'évenement du drag
	 * @param dge : l'événement
	 */
	public void setDragEvent(EventObject dge) {
		this.event = dge;
	}
	
	/**
	 * Modification des coordonnées du curseur par rapport au tas de cartes
	 * @param cursorLocation : coordonnées du point
	 */
	public void setCursorLocation(Point cursorLocation) {
		this.cursorLocation = cursorLocation;
	}
}