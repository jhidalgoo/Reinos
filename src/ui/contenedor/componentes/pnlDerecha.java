package ui.contenedor.componentes;

import javax.swing.JPanel;
import ui.eConfiguracion;

@SuppressWarnings("serial")
public class pnlDerecha extends JPanel {
	private final int ANCHO = 40;

	public pnlDerecha(int height) {
		this.setLayout(null);
		this.setSize(ANCHO, height);
		this.setBackground(eConfiguracion.COLOR_FONDO);
		this.setForeground(eConfiguracion.COLOR_LETRA);
	}

}
