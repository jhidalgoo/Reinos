package ui.Tablero;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JPanel;

import bl.Construccion.Construccion;
import bl.Construccion.Juego.Juego;
import bl.Construccion.Recursos.Gemas.Tipo.Azul;
import bl.Construccion.Recursos.Gemas.Tipo.Blanca;
import bl.Construccion.Recursos.Gemas.Tipo.Verde;
import bl.Construccion.Recursos.PowerUps.PowerUp;
import bl.Construccion.Tablero.Casilla;
import bl.Construccion.Tablero.Tablero;
import bl.Construccion.Tropa.Tropa;
import ui.contenedor.Controles.pnlDado;
import ui.eConfiguracion;
import ui.eIMG;

@SuppressWarnings("serial")
public class pnlTablero extends JPanel {

	@SuppressWarnings("unused")
	private eIMG eIMGIniciaConstructor = new eIMG();
	private static Tropa tropaSeleccionada;
	public static boolean isAtaque;
	private pnlCasilla[][] casillasUI;
	private int ancho; // width
	private int largo; // height
	private Juego juego;
	private pnlDado pnlDado;
	private Cursor mano = new Cursor(Cursor.HAND_CURSOR);
	private Color[] gris = new Color[] { new Color(200, 200, 200, 200), new Color(200, 200, 200, 200) };
	//private Color[] grisClaro = new Color[] { new Color(230, 230, 230, 255), new Color(240, 240, 240, 255) };
	//private Color[] Yellow = new Color[] { new Color(255, 255, 92, 200), new Color(255, 255, 162, 200) };
	private Color[] Red = new Color[] { new Color(255, 45, 100, 100), new Color(255, 45, 100, 100) };
	private Color[] Green = new Color[] { new Color(45, 255, 100, 100), new Color(45, 255, 100, 100) };
	private Color[] White = new Color[] { new Color(255, 255, 255, 255), new Color(255, 255, 255, 255) };
	private Color[] Blue = new Color[] { new Color(45, 100, 255, 100), new Color(45, 100, 255, 100) };

	/**
	 * Create the panel.
	 */
	public pnlTablero(int anchoTablero, int largoTablero, Juego juego, pnlDado pnlDado) {
		this.setLayout(null);
		this.setSize(anchoTablero, largoTablero);
		this.setBackground(eConfiguracion.COLOR_FONDO);
		this.setForeground(eConfiguracion.COLOR_LETRA);
		this.setJuego(juego);
		this.setAncho(getJuego().getTablero().getAncho());
		this.setLargo(getJuego().getTablero().getLargo());
		this.setPnlDado(pnlDado);

		int sizeCasillaW = (int) anchoTablero / this.getLargo();
		int sizeCasillaH = (int) largoTablero / this.getAncho();

		--sizeCasillaW;
		--sizeCasillaH;
		construirCasillas(sizeCasillaW, sizeCasillaH);
		repintarCasillas();

	}

	private void construirCasillas(int sizeCasillaW, int sizeCasillaH) {
		int x, y;
		casillasUI = new pnlCasilla[this.getAncho()][this.getLargo()];
		for (int i = 0; i < this.getLargo(); ++i) {
			x = (i * sizeCasillaW) + 2;
			for (int j = 0; j < this.getAncho(); ++j) {
				y = (j * sizeCasillaH) + 2;
				casillasUI[j][i] = new pnlCasilla(this);
				casillasUI[j][i].setBounds(x, y, sizeCasillaW, sizeCasillaH);
				casillasUI[j][i].i = j;
				casillasUI[j][i].j = i;
				casillasUI[j][i].setFondoCasilla(getColorDefault());
				this.add(casillasUI[j][i]);
			}
		}
	}

	public void repintarCasillas() {
		// Pintar casillas que no están vacias:
		for (Casilla[] i : juego.getTablero().getCasillas()) {
			for (Casilla j : i) {
				if (false == j.tienePieza() && false == j.tieneRecurso()) {
					// Se pinta el color default
					construirEnCasilla(j.getX(), j.getY(), "");
				} else {
					if (j.tienePieza()) {
						Construccion laPieza = j.getPieza();
						if (null != laPieza) {
							casillasUI[j.getX()][j.getY()].setCursor(mano);
							String nombrePieza = laPieza.getNombre();
							construirEnCasilla(j.getX(), j.getY(), nombrePieza);
						}
					}
					if (j.tieneRecurso()) {
						if (j.getRecurso() instanceof PowerUp) {
							construirEnCasilla(j.getX(), j.getY(), "PowerUp");
						} else {
							if (j.getRecurso() instanceof Azul) {
								// pintarCasilla(j.getX(), j.getY(), Blue);
								construirEnCasilla(j.getX(), j.getY(), "GemaAzul");
							} else if (j.getRecurso() instanceof Blanca) {
								// pintarCasilla(j.getX(), j.getY(), White);
								construirEnCasilla(j.getX(), j.getY(), "GemaBlanca");
							} else if (j.getRecurso() instanceof Verde) {
								// pintarCasilla(j.getX(), j.getY(), Green);
								construirEnCasilla(j.getX(), j.getY(), "GemaVerde");
							}
						}
					}
				}
			}
		}
		pnlDado.actualizarNumero();
	}

	public void construirEnCasilla(int i, int j, String nombrePieza) {
		// System.out.println("nombrePieza: " + nombrePieza);

		if (nombrePieza.trim().length() < 1) {
			// Se pinta el color default y no se pone imagen (null).
			casillasUI[i][j].setFondoCasilla(this.getColorDefault());
			casillasUI[i][j].setImgActual(null);
			casillasUI[i][j].repaint();

		} else {

			switch (nombrePieza.toUpperCase()) {

			case "CASTILLO":
				casillasUI[i][j].setImgActual(eIMG.IMAGE_CASTILLO1);
				break;

			case "BALLESTA":
				casillasUI[i][j].setImgActual(eIMG.IMAGE_BALLESTA);
				break;
			case "CATAPULTA":
				casillasUI[i][j].setImgActual(eIMG.IMAGE_CATAPULTA);
				break;
			case "ARQUERO":
				casillasUI[i][j].setImgActual(eIMG.IMAGE_ARQUERO);
				break;
			case "ESPADACHIN":
				casillasUI[i][j].setImgActual(eIMG.IMAGE_ESPADACHIN);
				break;
			case "BERSEQUER":
				casillasUI[i][j].setImgActual(eIMG.IMAGE_BERSEQUER);
				break;
			case "MAGO":
				casillasUI[i][j].setImgActual(eIMG.IMAGE_MAGO);
				break;
			case "ASESINO":
				casillasUI[i][j].setImgActual(eIMG.IMAGE_ASESINO);
				break;
			case "JINETE":
				casillasUI[i][j].setImgActual(eIMG.IMAGE_JINETE);
				break;
			case "ESPIA":
				casillasUI[i][j].setImgActual(eIMG.IMAGE_ESPIA);
				break;
			case "TROPA":
				casillasUI[i][j].setImgActual(eIMG.IMAGE_TROPA);
				break;
			case "POWERUP":
				if (null == eIMG.IMAGE_POWERUP)
					casillasUI[i][j].setFondoCasilla(Red);
				else
					casillasUI[i][j].setImgActual(eIMG.IMAGE_POWERUP);
				break;
			case "GEMAAZUL":
				if (null == eIMG.IMAGE_GEMAAZUL)
					casillasUI[i][j].setFondoCasilla(Blue);
				else
					casillasUI[i][j].setImgActual(eIMG.IMAGE_GEMAAZUL);
				break;
			case "GEMABLANCA":
				if (null == eIMG.IMAGE_GEMABLANCA)
					casillasUI[i][j].setFondoCasilla(White);
				else
					casillasUI[i][j].setImgActual(eIMG.IMAGE_GEMABLANCA);
				break;
			case "GEMAVERDE":
				if (null == eIMG.IMAGE_GEMAVERDE)
					casillasUI[i][j].setFondoCasilla(Green);
				else
					casillasUI[i][j].setImgActual(eIMG.IMAGE_GEMAVERDE);
				break;

			}

			casillasUI[i][j].repaint();
		}
	}

	public int[] getCoordenadas(pnlCasilla casilla) {
		for (int i = 0; i < this.getAncho(); ++i) {
			for (int j = 0; j < this.getLargo(); ++j) {
				if (this.casillasUI[i][j] == casilla) {
					return new int[] { i, j };
				}
			}
		}
		return new int[2];
	}

	public void moverPieza(int origenX, int origenY, int destinoX, int destinoY) {
		juego.moverPieza(origenX, origenY, destinoX, destinoY);
		repintarCasillas();
	}

	public void ponerPiezaEnJuego(int origenX, int origenY, int destinoX, int destinoY) {
		juego.ponerPiezaEnJuego(origenX, origenY, destinoX, destinoY, getTropaSeleccionada());
		repintarCasillas();
	}

	public Tablero getTableroLogica() {
		return juego.getTablero();
	}

	public static Tropa getTropaSeleccionada() {
		return tropaSeleccionada;
	}

	public static void setTropaSeleccionada(Tropa tropaSeleccionada) {
		pnlTablero.tropaSeleccionada = tropaSeleccionada;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getLargo() {
		return largo;
	}

	public void setLargo(int largo) {
		this.largo = largo;
	}

	public Color[] getColorDefault() {
		return this.gris;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public Juego getJuego() {
		return juego;
	}

	public ui.contenedor.Controles.pnlDado getPnlDado() {
		return pnlDado;
	}

	public void setPnlDado(ui.contenedor.Controles.pnlDado pnlDado) {
		this.pnlDado = pnlDado;
	}
}
