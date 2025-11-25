class PilaHistorial {
    private NodoPila tope;

    public void apilar(Carta c) {
        NodoPila n = new NodoPila(c);
        n.siguiente = tope;
        tope = n;
    }
}

