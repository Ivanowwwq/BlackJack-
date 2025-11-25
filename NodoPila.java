class NodoPila {
    public Carta carta;
    public NodoPila siguiente;

    public NodoPila(Carta c) {
        carta = c;
    }
}

class PilaHistorial {
    private NodoPila tope;

    public void apilar(Carta c) {
        NodoPila n = new NodoPila(c);
        n.siguiente = tope;
        tope = n;
    }
}



