package solitaire.listener;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.io.IOException;

import solitaire.presentation.IPDropable;
import solitaire.presentation.PTasDeCartes;

/**
 * Listener d'un drop de tas de cartes
 */
public class TDCDropListener extends DropTargetAdapter{
	private IPDropable target;

	/**
	 * Constructeur du listener
	 * @param target : cible du drop
	 */
	public TDCDropListener(IPDropable target) {
		this.target = target;
	}

	/**
	 * Action du drop de l'objet vers la cible
	 */
	public void drop(DropTargetDropEvent e) {
		try {
			DataFlavor data = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType);
			PTasDeCartes tdc = (PTasDeCartes) e.getTransferable().getTransferData(data);
			
			target.setDropEvent(e);
			target.getControle().drop(tdc.getControle());
		} catch (UnsupportedFlavorException | IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Detection de l'entrée potentielle d'un élément sur la cible
	 */
	public void dragEnter(DropTargetDragEvent dtde) {
		try {
			DataFlavor data = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType);
			PTasDeCartes tdc = (PTasDeCartes) dtde.getTransferable().getTransferData(data);
			
			target.getControle().dragEnter(tdc.getControle());
		} catch (ClassNotFoundException | UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Detection de la sortie potentielle d'un élément sur la cible
	 */
	public void dragExit(DropTargetEvent dte) {
		target.getControle().dragExit();
	}
}