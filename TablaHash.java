class TablaHash {
    private EntradaHash[] tabla;
    private int capacidad;
    private int tamaño;

    public TablaHash(int cap) {
        capacidad = cap;
        tabla = new EntradaHash[cap];
        tamaño = 0;
    }

    private int hash(String clave) {
        int h = 0;
        for (int i = 0; i < clave.length(); i++)
            h = (31 * h + clave.charAt(i)) % capacidad;
        return h;
    }

    public void insertar(String clave, Jugador jugador) {
        int pos = hash(clave);

        // Buscar siguiente espacio disponible usando direccionamiento lineal
        while (tabla[pos] != null && tabla[pos].activa && !tabla[pos].llave.equals(clave)) {
            pos = (pos + 1) % capacidad;
        }

        // Guardar entrada
        tabla[pos] = new EntradaHash(clave, jugador);
        tamaño++;
    }

    public Jugador obtener(String clave) {
        int pos = hash(clave);

        // Buscar hasta encontrar una coincidencia o un espacio vacío
        while (tabla[pos] != null) {
            if (tabla[pos].activa && tabla[pos].llave.equals(clave))
                return tabla[pos].valor;

            pos = (pos + 1) % capacidad;
        }
        return null;
    }
}
