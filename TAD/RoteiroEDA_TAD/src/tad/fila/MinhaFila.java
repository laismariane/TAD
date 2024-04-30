package tad.fila;

public class MinhaFila implements FilaIF<Integer> {

    private int tamanho;
    private int cauda;
    private int cabeca;
    private Integer[] meusDados;

    public MinhaFila(int tamanhoInicial) {
        this.tamanho = tamanhoInicial;
        this.meusDados = new Integer[tamanho];
        this.cabeca = 0;
        this.cauda = 0;
    }

    public MinhaFila() {
        this(10); // Tamanho inicial padrão de 10
    }

    @Override
    public void enfileirar(Integer item) {
        if (isFull()) {
            throw new IllegalStateException("A fila está cheia");
        }
        meusDados[cauda] = item;
        cauda = (cauda + 1) % tamanho;
    }

    @Override
    public Integer desenfileirar() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia");
        }
        Integer item = meusDados[cabeca];
        cabeca = (cabeca + 1) % tamanho;
        return item;
    }

    @Override
    public Integer verificarCauda() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia");
        }
        return meusDados[(cauda - 1 + tamanho) % tamanho];
    }

    @Override
    public Integer verificarCabeca() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia");
        }
        return meusDados[cabeca];
    }

    @Override
    public boolean isEmpty() {
        return cabeca == cauda;
    }

    @Override
    public boolean isFull() {
        return (cauda + 1) % tamanho == cabeca;
    }
}

