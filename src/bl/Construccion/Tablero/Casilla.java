package bl.Construccion.Tablero;

import bl.Construccion.Construccion;
import bl.Construccion.Recursos.IRecurso;

public class Casilla {
    private Construccion pieza;
    private IRecurso recurso;
    private int x;
    private int y;

    public Casilla(int x, int y) {
        setX(x);
        setY(y);
    }

    public Construccion getPieza() {
        return pieza;
    }

    public void setPieza(Construccion pieza) {
        this.pieza = pieza;
    }

    public IRecurso getRecurso() {
        return recurso;
    }

    public void setRecurso(IRecurso recurso) {
        this.recurso = recurso;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean tieneRecurso(){
    	// Si no es null entonces si tiene un recurso.
        return recurso != null;
    }

    public boolean tienePieza(){
    	// Si no es null entonces si tiene una pieza.
        return pieza != null;
    }

    public void removerRecurso(){
        recurso = null;
    }
}
