package tad.conjuntoDinamico;

import tad.listasEncadeadas.NodoListaEncadeada;

public class MeuConjuntoDinamicoEncadeado implements ConjuntoDinamicoIF<Integer> {

    private NodoListaEncadeada<Integer> cabeca;

    public MeuConjuntoDinamicoEncadeado() {
        this.cabeca = new NodoListaEncadeada<>();
    }

    @Override
    public void inserir(Integer item) {
        NodoListaEncadeada<Integer> novoNodo = new NodoListaEncadeada<>(item);
        if (!contem(item)) {
            novoNodo.setProximo(cabeca.getProximo());
            cabeca.setProximo(novoNodo);
        }
    }

    @Override
    public Integer remover(Integer item) {
        NodoListaEncadeada<Integer> anterior = cabeca;
        NodoListaEncadeada<Integer> corrente = cabeca.getProximo();
        while (corrente != null) {
            if (corrente.getChave().equals(item)) {
                anterior.setProximo(corrente.getProximo());
                return corrente.getChave();
            }
            anterior = corrente;
            corrente = corrente.getProximo();
        }
        return null;
    }

    @Override
    public Integer predecessor(Integer item) {
        NodoListaEncadeada<Integer> corrente = cabeca.getProximo();
        Integer predecessor = null;
        while (corrente != null && corrente.getChave() < item) {
            predecessor = corrente.getChave();
            corrente = corrente.getProximo();
        }
        return predecessor;
    }

    @Override
    public Integer sucessor(Integer item) {
        NodoListaEncadeada<Integer> corrente = cabeca.getProximo();
        while (corrente != null && corrente.getChave() <= item) {
            corrente = corrente.getProximo();
        }
        return corrente != null ? corrente.getChave() : null;
    }

    @Override
    public int tamanho() {
        int tamanho = 0;
        NodoListaEncadeada<Integer> corrente = cabeca.getProximo();
        while (corrente != null) {
            tamanho++;
            corrente = corrente.getProximo();
        }
        return tamanho;
    }

    @Override
    public Integer buscar(Integer item) {
        NodoListaEncadeada<Integer> corrente = cabeca.getProximo();
        while (corrente != null) {
            if (corrente.getChave().equals(item)) {
                return corrente.getChave();
            }
            corrente = corrente.getProximo();
        }
        return null;
    }

    @Override
    public Integer minimum() {
        NodoListaEncadeada<Integer> corrente = cabeca.getProximo();
        return corrente != null ? corrente.getChave() : null;
    }

    @Override
    public Integer maximum() {
        NodoListaEncadeada<Integer> corrente = cabeca.getProximo();
        NodoListaEncadeada<Integer> maximo = null;
        while (corrente != null) {
            if (maximo == null || corrente.getChave() > maximo.getChave()) {
                maximo = corrente;
            }
            corrente = corrente.getProximo();
        }
        return maximo != null ? maximo.getChave() : null;
    }

    private boolean contem(Integer item) {
        NodoListaEncadeada<Integer> corrente = cabeca.getProximo();
        while (corrente != null) {
            if (corrente.getChave().equals(item)) {
                return true;
            }
            corrente = corrente.getProximo();
        }
        return false;
    }
}

