package tad.listasEncadeadas;

public class ListaEncadeadaImpl<T extends Comparable<T>> implements ListaEncadeadaIF<T> {

    NodoListaEncadeada<T> cabeca;
    NodoListaEncadeada<T> cauda;

    public ListaEncadeadaImpl() {
        cabeca = new NodoListaEncadeada<>();
        cauda = new NodoListaEncadeada<>();
        cabeca.setProximo(cauda);
    }

    @Override
    public boolean isEmpty() {
        return cabeca.getProximo().equals(cauda);
    }

    @Override
    public int size() {
        int tamanho = 0;
        NodoListaEncadeada<T> corrente = cabeca.getProximo();
        while (corrente != cauda) {
            tamanho++;
            corrente = corrente.getProximo();
        }
        return tamanho;
    }

    @Override
    public NodoListaEncadeada<T> search(T chave) {
        NodoListaEncadeada<T> corrente = cabeca.getProximo();
        while (corrente != cauda) {
            if (corrente.getChave().equals(chave)) {
                return corrente;
            }
            corrente = corrente.getProximo();
        }
        return null;
    }

    @Override
    public void insert(T chave) {
        NodoListaEncadeada<T> novoNo = new NodoListaEncadeada<>(chave);
        novoNo.setProximo(cabeca.getProximo());
        cabeca.setProximo(novoNo);
    }

    @Override
    public NodoListaEncadeada<T> remove(T chave) {
        NodoListaEncadeada<T> anterior = cabeca;
        NodoListaEncadeada<T> corrente = cabeca.getProximo();

        while (corrente != cauda && !corrente.getChave().equals(chave)) {
            anterior = corrente;
            corrente = corrente.getProximo();
        }

        if (corrente != cauda) {
            anterior.setProximo(corrente.getProximo());
            return corrente;
        }

        return null;
    }

    @Override
    public T[] toArray(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, size());
        int index = 0;
        NodoListaEncadeada<T> corrente = cabeca.getProximo();
        while (corrente != cauda) {
            array[index++] = corrente.getChave();
            corrente = corrente.getProximo();
        }
        return array;
    }

    @Override
    public String imprimeEmOrdem() {
        StringBuilder valores = new StringBuilder();
        NodoListaEncadeada<T> corrente = cabeca.getProximo();
        while (corrente != cauda) {
            valores.append(corrente.getChave()).append(", ");
            corrente = corrente.getProximo();
        }
        if (valores.length() > 2) {
            valores.setLength(valores.length() - 2); // Remove a última vírgula e espaço
        }
        return valores.toString();
    }

    @Override
    public String imprimeInverso() {
        StringBuilder valores = new StringBuilder();
        imprimeInversoRecursivo(cabeca.getProximo(), valores);
        if (valores.length() > 2) {
            valores.setLength(valores.length() - 2); // Remove a última vírgula e espaço
        }
        return valores.toString();
    }

    private void imprimeInversoRecursivo(NodoListaEncadeada<T> corrente, StringBuilder valores) {
        if (corrente == cauda) {
            return;
        }
        imprimeInversoRecursivo(corrente.getProximo(), valores);
        valores.append(corrente.getChave()).append(", ");
    }

    @Override
    public NodoListaEncadeada<T> sucessor(T chave) {
        NodoListaEncadeada<T> corrente = cabeca.getProximo();
        while (corrente.getProximo() != cauda) {
            if (corrente.getChave().equals(chave)) {
                return corrente.getProximo();
            }
            corrente = corrente.getProximo();
        }
        return null;
    }

    @Override
    public NodoListaEncadeada<T> predecessor(T chave) {
        NodoListaEncadeada<T> anterior = cabeca;
        NodoListaEncadeada<T> corrente = cabeca.getProximo();

        while (corrente != cauda && !corrente.getChave().equals(chave)) {
            anterior = corrente;
            corrente = corrente.getProximo();
        }

        if (corrente != cauda && anterior != cabeca) {
            return anterior;
        }

        return null;
    }

    @Override
    public void insert(T chave, int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Índice fora dos limites");
        }

        NodoListaEncadeada<T> novoNo = new NodoListaEncadeada<>(chave);
        NodoListaEncadeada<T> corrente = cabeca;

        for (int i = 0; i < index; i++) {
            corrente = corrente.getProximo();
        }

        novoNo.setProximo(corrente.getProximo());
        corrente.setProximo(novoNo);
    }
}
