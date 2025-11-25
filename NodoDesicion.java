class NodoDecision {
    public boolean esHoja;
    public boolean pedir; // true = pedir carta
    public NodoDecision izquierda;
    public NodoDecision derecha;

    public NodoDecision(boolean hoja) {
        esHoja = hoja;
    }

    /** Lógica: si puntaje < 17 → pedir */
    public static NodoDecision crearArbolDealer() {
        NodoDecision raiz = new NodoDecision(false);

        NodoDecision hojaPedir = new NodoDecision(true);
        hojaPedir.pedir = true;

        NodoDecision hojaPlantarse = new NodoDecision(true);
        hojaPlantarse.pedir = false;

        raiz.izquierda = hojaPedir;
        raiz.derecha = hojaPlantarse;

        return raiz;
    }

    public boolean evaluar(int puntaje) {
        if (puntaje < 17) return izquierda.pedir;
        return derecha.pedir;
    }
}