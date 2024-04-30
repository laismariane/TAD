package tad.listasEncadeadas;

public class ListaDuplamenteEncadeadaImpl<T extends Comparable<T>> implements ListaDuplamenteEncadeadaIF<T> {

    NodoListaDuplamenteEncadeada<T> cabeca;
    NodoListaDuplamenteEncadeada<T> cauda;

    public ListaDuplamenteEncadeadaImpl() {
        cabeca = new NodoListaDuplamenteEncadeada<>();
        cauda = new NodoListaDuplamenteEncadeada<>();
        cabeca.setProximo(cauda);
        cabeca.setAnterior(cauda);
        cauda.setAnterior(cabeca);
        cauda.setProximo(cabeca);
    }

    @Override
    public boolean isEmpty() {
        return cabeca.getProximo().equals(cauda);
    }

    @Override
    public int size() {
        int tamanho = 0;
        NodoListaDuplamenteEncadeada<T> corrente = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        while (corrente != cauda) {
            tamanho++;
            corrente = (NodoListaDuplamenteEncadeada<T>) corrente.getProximo();
        }
        return tamanho;
    }

    @Override
    public NodoListaDuplamenteEncadeada<T> search(T chave) {
        NodoListaDuplamenteEncadeada<T> corrente = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        while (corrente != cauda) {
            if (corrente.getChave().equals(chave)) {
                return corrente;
            }
            corrente = (NodoListaDuplamenteEncadeada<T>) corrente.getProximo();
        }
        return null;
    }

    @Override
    public void insert(T chave) {
        NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<>(chave);
        novoNo.setProximo(cabeca.getProximo());
        ((NodoListaDuplamenteEncadeada<T>) cabeca.getProximo()).setAnterior(novoNo);
        novoNo.setAnterior(cabeca);
        cabeca.setProximo(novoNo);
    }

    @Override
    public NodoListaEncadeada<T> remove(T chave) {
        NodoListaDuplamenteEncadeada<T> corrente = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();

        while (corrente != cauda && !corrente.getChave().equals(chave)) {
            corrente = (NodoListaDuplamenteEncadeada<T>) corrente.getProximo();
        }

        if (corrente != cauda) {
            corrente.getAnterior().setProximo(corrente.getProximo());
            corrente.getProximo().setAnterior(corrente.getAnterior());
            return corrente;
        }

        return null;
    }

    @Override
    public String imprimeEmOrdem() {
        StringBuilder valores = new StringBuilder();
        NodoListaDuplamenteEncadeada<T> corrente = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        while (corrente != cauda) {
            valores.append(corrente.getChave()).append(", ");
            corrente = (NodoListaDuplamenteEncadeada<T>) corrente.getProximo();
        }
        if (valores.length() > 2) {
            valores.setLength(valores.length() - 2); // Remove a última vírgula e espaço
        }
        return valores.toString();
    }

    @Override
    public String imprimeInverso() {
        StringBuilder valores = new StringBuilder();
        NodoListaDuplamenteEncadeada<T> corrente = cauda.getAnterior();
        while (corrente != cabeca) {
            valores.append(corrente.getChave()).append(", ");
            corrente = corrente.getAnterior();
        }
        if (valores.length() > 2) {
            valores.setLength(valores.length() - 2); // Remove a última vírgula e espaço
        }
        return valores.toString();
    }

    @Override
    public NodoListaEncadeada<T> sucessor(T chave) {
        NodoListaDuplamenteEncadeada<T> corrente = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        while (corrente != cauda) {
            if (corrente.getChave().equals(chave)) {
                return corrente.getProximo();
            }
            corrente = (NodoListaDuplamenteEncadeada<T>) corrente.getProximo();
        }
        return null;
    }

    @Override
    public NodoListaEncadeada<T> predecessor(T chave) {
        NodoListaDuplamenteEncadeada<T> corrente = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        while (corrente != cauda) {
            if (corrente.getChave().equals(chave)) {
                return corrente.getAnterior();
            }
            corrente = (NodoListaDuplamenteEncadeada<T>) corrente.getProximo();
        }
        return null;
    }

    @Override
    public T[] toArray(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, size());
        int index = 0;
        NodoListaDuplamenteEncadeada<T> corrente = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        while (corrente != cauda) {
            array[index++] = corrente.getChave();
            corrente = (NodoListaDuplamenteEncadeada<T>) corrente.getProximo();
        }
        return array;
    }

    @Override
    public void inserePrimeiro(T elemento) {
        NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<>(elemento);
        novoNo.setProximo(cabeca.getProximo());
        novoNo.setAnterior(cabeca);
        cabeca.getProximo().setAnterior(novoNo);
        cabeca.setProximo(novoNo);
    }

    @Override
    public NodoListaDuplamenteEncadeada<T> removeUltimo() {
        if (isEmpty()) {
            return null;
        }

        NodoListaDuplamenteEncadeada<T> ultimo = cauda.getAnterior();
        cauda.setAnterior(ultimo.getAnterior());
        ultimo.getAnterior().setProximo(cauda);
        return ultimo;
    }

    @Override
    public NodoListaDuplamenteEncadeada<T> removePrimeiro() {
        if (isEmpty()) {
            return null;
        }

        NodoListaDuplamenteEncadeada<T> primeiro = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
        cabeca.setProximo(primeiro.getProximo());
        primeiro.getProximo().setAnterior(cabeca);
        return primeiro;
    }

    @Override
    public void insert(T chave, int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Índice fora dos limites");
        }

        NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<>(chave);
        NodoListaDuplamenteEncadeada<T> corrente = cabeca;

        for (int i = 0; i < index; i++) {
            corrente = (NodoListaDuplamenteEncadeada<T>) corrente.getProximo();
        }

        novoNo.setProximo(corrente.getProximo());
        novoNo.setAnterior(corrente);
        corrente.getProximo().setAnterior(novoNo);
        corrente.setProximo(novoNo);
    }
}
