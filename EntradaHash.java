class EntradaHash {
    public String llave;
    public Jugador valor;
    public boolean activa;

    public EntradaHash(String llave, Jugador valor) {
        this.llave = llave;
        this.valor = valor;
        this.activa = true;
    }
}