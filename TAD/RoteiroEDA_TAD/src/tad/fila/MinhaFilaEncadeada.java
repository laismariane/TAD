package tad.fila;

class No<T> {
    T dado;
    No<T> proximo;

    public No(T dado) {
        this.dado = dado;
        this.proximo = null;
    }
}

public class MinhaFilaEncadeada implements FilaIF<Integer> {

    private No<Integer> cabeca;
    private No<Integer> cauda;

    @Override
    public void enfileirar(Integer item) throws FilaCheiaException {
        No<Integer> novoNo = new No<>(item);
        if (cabeca == null) {
            cabeca = novoNo;
            cauda = novoNo;
        } else {
            cauda.proximo = novoNo;
            cauda = novoNo;
        }
    }

    @Override
    public Integer desenfileirar() throws FilaVaziaException {
        if (isEmpty()) {
            throw new FilaVaziaException("A fila está vazia");
        }
        Integer item = cabeca.dado;
        cabeca = cabeca.proximo;
        if (cabeca == null) {
            cauda = null;
        }
        return item;
    }

    @Override
    public Integer verificarCauda() {
        if (isEmpty()) {
            return null;
        }
        return cauda.dado;
    }

    @Override
    public Integer verificarCabeca() {
        if (isEmpty()) {
            return null;
        }
        return cabeca.dado;
    }

    @Override
    public boolean isEmpty() {
        return cabeca == null;
    }

    @Override
    public boolean isFull() {
        // A fila com listas encadeadas não possui um limite máximo de elementos, então nunca estará cheia
        return false;
    }
}

