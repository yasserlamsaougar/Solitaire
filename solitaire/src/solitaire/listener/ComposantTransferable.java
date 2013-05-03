package solitaire.listener;

import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ComposantTransferable implements Transferable{
	public static DataFlavor componentFlavor;
	protected static DataFlavor[] supportedFlavors = {componentFlavor};
	private Component c;

	/**
	 * Constructeur du tas de cartes tranferable
	 * @param tdc : tas de cartes � transf�rer
	 */
	public ComposantTransferable(Component c){
		this.c = c;
		
		try {
			componentFlavor = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Getter des donn�es brutes du tas de cartes tranferable
	 * @param flavor : donn�es � r�cup�rer
	 */
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if(flavor.equals(componentFlavor))
			return c;
		else if(flavor.equals(DataFlavor.stringFlavor))
			return c.toString();
		else return new UnsupportedFlavorException(flavor);
	}

	/**
	 * Getter des donn�es trnasform�es du tas de crates transferable
	 */
	public DataFlavor[] getTransferDataFlavors() {
		return supportedFlavors;
	}

	/**
	 * Indique si oui ou non les donn�es pass�es en apram�tres sont support�es
	 * @param flavor : donn�es � tester
	 */
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return (c.equals(flavor) || flavor.equals(DataFlavor.stringFlavor));
	}
}
