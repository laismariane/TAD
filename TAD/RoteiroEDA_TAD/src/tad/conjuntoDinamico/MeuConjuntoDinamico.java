package tad.conjuntoDinamico;

import java.util.Arrays;

public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer> {

    private static final int TAMANHO_INICIAL = 10;
    private static final int FATOR_AUMENTO = 2;
    
    private Integer[] meusDados;
    private int posInsercao;

    public MeuConjuntoDinamico() {
        this.meusDados = new Integer[TAMANHO_INICIAL];
        this.posInsercao = 0;
    }

    @Override
    public void inserir(Integer item) {
        if (posInsercao == meusDados.length) {
            aumentarArray();
        }
        meusDados[posInsercao] = item;
        posInsercao++;
    }

    private void aumentarArray() {
        int novoTamanho = meusDados.length * FATOR_AUMENTO;
        meusDados = Arrays.copyOf(meusDados, novoTamanho);
    }

    @Override
    public Integer remover(Integer item) {
        Integer encontrado = null;
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                encontrado = meusDados[i];
                for (int j = i; j < posInsercao - 1; j++) {
                    meusDados[j] = meusDados[j + 1];
                }
                meusDados[posInsercao - 1] = null;
                posInsercao--;
                break;
            }
        }
        return encontrado;
    }

    @Override
    public Integer predecessor(Integer item) {
        Integer predecessor = null;
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                if (i > 0) {
                    predecessor = meusDados[i - 1];
                }
                break;
            }
        }
        return predecessor;
    }

    @Override
    public Integer sucessor(Integer item) {
        Integer sucessor = null;
        for (int i = 0; i < posInsercao - 1; i++) {
            if (meusDados[i].equals(item)) {
                sucessor = meusDados[i + 1];
                break;
            }
        }
        return sucessor;
    }

    @Override
    public int tamanho() {
        return posInsercao;
    }

    @Override
    public Integer buscar(Integer item) {
        for (int i = 0; i < posInsercao; i++) {
            if (meusDados[i].equals(item)) {
                return meusDados[i];
            }
        }
        return null;
    }

    @Override
    public Integer minimum() {
        if (posInsercao == 0) {
            return null;
        }
        Integer min = meusDados[0];
        for (int i = 1; i < posInsercao; i++) {
            if (meusDados[i].compareTo(min) < 0) {
                min = meusDados[i];
            }
        }
        return min;
    }

    @Override
    public Integer maximum() {
        if (posInsercao == 0) {
            return null;
        }
        Integer max = meusDados[0];
        for (int i = 1; i < posInsercao; i++) {
            if (meusDados[i].compareTo(max) > 0) {
                max = meusDados[i];
            }
        }
        return max;
    }
}
