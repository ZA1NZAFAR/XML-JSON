package ovh.zain.exo1;/*
 * Created on 26 nov. 2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */


/**
 * @author Salim
 * <p>
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

public class Transf {

	public static void main(String[] args) {
		try {
			System.out.println("debut");
			Parser parseur = new Parser();


			String filename = "TP3/src/main/resources/bib.xml";

			parseur.parse(filename);
			System.out.println("fin");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
