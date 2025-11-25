class NodoCola {
    public String jugador;
    public NodoCola siguiente;

    public NodoCola(String j) {
        jugador = j;
    }
}

class ColaTurnos {
    private NodoCola frente, finalCola;

    public void encolar(String jugador) {
        NodoCola n = new NodoCola(jugador);
        if (finalCola == null) {
            frente = finalCola = n;
        } else {
            finalCola.siguiente = n;
            finalCola = n;
        }
    }

    public String desencolar() {
        if (frente == null) return null;
        String j = frente.jugador;
        frente = frente.siguiente;
        if (frente == null) finalCola = null;
        return j;
    }
}