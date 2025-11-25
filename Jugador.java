class Jugador {
    public final String nombre;
    private Carta[] mano;
    private int tamañoMano;

    public Jugador(String nombre) {
        this.nombre = nombre;
        mano = new Carta[12];
        tamañoMano = 0;
    }

    public void recibirCarta(Carta c) {
        mano[tamañoMano++] = c;
    }

    public Carta[] obtenerMano() {
        Carta[] copia = new Carta[tamañoMano];
        for (int i = 0; i < tamañoMano; i++) copia[i] = mano[i];
        return copia;
    }

    public int obtenerPuntaje() {
        int total = 0;
        int ases = 0;

        for (int i = 0; i < tamañoMano; i++) {
            int val = mano[i].obtenerValorNumerico();
            total += val;
            if (mano[i].valor.equals("A")) ases++;
        }

        while (total > 21 && ases > 0) {
            total -= 10;
            ases--;
        }

        return total;
    }

    public void limpiarMano() {
        mano = new Carta[12];
        tamañoMano = 0;
    }

    public void imprimirMano() {
        for (int i = 0; i < tamañoMano; i++)
            System.out.println("  " + mano[i]);
    }
}
