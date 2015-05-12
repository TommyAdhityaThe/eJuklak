package unpar.ftis.ejuklak;

import java.util.ArrayList;


/**
 * Class DrawerItem contains the items for Navigation Drawer
 * Contains the String name for the title, and the List of String for the inner chapters
 * @author ftis unpar
 * @author Henry Bagus Hermawan
 * @author Alexander Indrawan
 * @author Ivan Lukman
 * @author Tommy Adhitya The
 * @author Tevin Odysseus
 * @version 1.0
 */
public class DrawerItem {
	
	/**
	 * Title for each item
	 */
	public String name;
	
	
	/**
	 * List of String for inner chapters
	 */
	public ArrayList<String> innerList = new ArrayList<String>();
}
