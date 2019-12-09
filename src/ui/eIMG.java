package ui;

import java.awt.Image;
import javax.swing.ImageIcon;

public final class eIMG {

	public static final String IMG_APP = "Xprogram.png";
	public static final String IMG_SALIR = "salir.png";
	public static final String IMG_BALLESTA = "ballesta.png";
	public static final String IMG_CATAPULTA = "catapulta.png";
	public static final String IMG_ARQUERO = "arquero.png";
	public static final String IMG_ESPADACHIN = "espadachin.png";
	public static final String IMG_BERSEQUER = "berserker.png";
	public static final String IMG_MAGO = "mago.png";
	public static final String IMG_ASESINO = "asesino.png";
	public static final String IMG_JINETE = "jinete.png";
	public static final String IMG_ESPIA = "espia.png";
	public static final String IMG_TROPA = "tropa.png";

	public static final String IMG_CASTILLO1 = "castillo1.png";
	public static final String IMG_CASTILLO2 = "castillo2.png";
	public static final String IMG_CASTILLO3 = "castillo3.png";
	public static final String IMG_CASTILLO4 = "castillo4.png";

	public static Image IMAGE_CASTILLO1 = null;
	public static Image IMAGE_CASTILLO2 = null;
	public static Image IMAGE_CASTILLO3 = null;
	public static Image IMAGE_CASTILLO4 = null;

	public eIMG() {
		IMAGE_CASTILLO1 = getImage(IMG_CASTILLO1);
		IMAGE_CASTILLO2 = getImage(IMG_CASTILLO2);
		IMAGE_CASTILLO3 = getImage(IMG_CASTILLO3);
		IMAGE_CASTILLO4 = getImage(IMG_CASTILLO4);
	}

	public static Image getImage(String nombreImagen) {
		ImageIcon icono = null;
		Image xIMAGEN = null;
		try {
			icono = new ImageIcon(eIMG.class.getResource("/ui/Imagenes/" + nombreImagen));
			xIMAGEN = icono.getImage();
		} catch (Exception e) {
			if(null !=e.getMessage())
			System.err.println("Error en eIMG: " + e.getMessage());
		}
		return xIMAGEN;
	}

	public static Image getImage(String nombreImagen, int escalaWidth, int escalaHeight) {
		ImageIcon icono = null;
		Image xIMAGEN = null;
		try {
			icono = new ImageIcon(eIMG.class.getResource("/ui/Imagenes/" + nombreImagen));
			xIMAGEN = icono.getImage().getScaledInstance(escalaWidth, escalaHeight, Image.SCALE_SMOOTH);
		} catch (Exception e) {
		}
		return xIMAGEN;
	}

	public static ImageIcon getIcon(String nombreImagen) {
		ImageIcon icono = null;
		try {
			icono = new ImageIcon(eIMG.class.getResource("/ui/Imagenes/" + nombreImagen));
		} catch (Exception e) {
		}
		return icono;
	}

}
