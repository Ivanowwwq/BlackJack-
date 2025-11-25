class Carta {
    public final String valor;  
    public final String palo;   

    public Carta(String valor, String palo) {
        this.valor = valor;
        this.palo = palo;
    }

    /** Retorna el valor numérico de la carta segun reglas de Blackjack */
    public int obtenerValorNumerico() {
        if (valor.equals("A")) return 11;
        if (valor.equals("J") || valor.equals("Q") || valor.equals("K")) return 10;
        return Integer.parseInt(valor);
    }

    /** Nombre bonito para imprimir */
    @Override
    public String toString() {
        String nombrePalo;
        switch (palo) {
            case "♠": nombrePalo = "Picas"; break;
            case "♥": nombrePalo = "Corazones"; break;
            case "♦": nombrePalo = "Diamantes"; break;
            case "♣": nombrePalo = "Tréboles"; break;
            default: nombrePalo = palo;
        }
        return valor + " de " + nombrePalo;
    }
}