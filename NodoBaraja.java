import java.util.Random;

class NodoBaraja {
    public Carta carta;
    public NodoBaraja siguiente;

    public NodoBaraja(Carta c) {
        this.carta = c;
        this.siguiente = null;
    }
}

class ListaEnlazadaBaraja {
    private NodoBaraja cabeza;
    private int tamaño;

    public ListaEnlazadaBaraja() {
        cabeza = null;
        tamaño = 0;
    }

    /** Crea las 52 cartas estándar */
    public void inicializarBaraja() {
        String[] valores = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] palos = {"♠","♥","♦","♣"};

        for (String p : palos) {
            for (String v : valores) {
                agregarFinal(new Carta(v, p));
            }
        }
    }

    /** Agrega una carta al final de la lista */
    private void agregarFinal(Carta c) {
        NodoBaraja nuevo = new NodoBaraja(c);
        if (cabeza == null) cabeza = nuevo;
        else {
            NodoBaraja actual = cabeza;
            while (actual.siguiente != null) actual = actual.siguiente;
            actual.siguiente = nuevo;
        }
        tamaño++;
    }

    /** Baraja la lista enlazada usando Fisher-Yates */
    public void mezclar(Random r) {
        if (tamaño <= 1) return;

        Carta[] arreglo = new Carta[tamaño];
        NodoBaraja act = cabeza;
        int i = 0;
        while (act != null) {
            arreglo[i++] = act.carta;
            act = act.siguiente;
        }

        // Mezclar
        for (int j = arreglo.length - 1; j > 0; j--) {
            int k = r.nextInt(j + 1);
            Carta temp = arreglo[j];
            arreglo[j] = arreglo[k];
            arreglo[k] = temp;
        }

        // reconstruir
        cabeza = null;
        tamaño = 0;
        for (Carta c : arreglo) agregarFinal(c);
    }

    /** Robar la primera carta */
    public Carta robarCarta() {
        if (cabeza == null) return null;
        Carta c = cabeza.carta;
        cabeza = cabeza.siguiente;
        tamaño--;
        return c;
    }

    public int getTamaño() { return tamaño; }
}