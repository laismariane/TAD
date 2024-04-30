package tad.pilha;

public class MinhaPilha implements PilhaIF<Integer> {

    private int tamanho;
    private Integer[] meusDados;
    private int topo;

    public MinhaPilha(int tamanho) {
        this.tamanho = tamanho;
        this.meusDados = new Integer[tamanho];
        this.topo = -1;
    }

    public MinhaPilha() {
        this(10); // Tamanho inicial padrão de 10
    }

    @Override
    public void empilhar(Integer item) {
        if (isFull()) {
            throw new IllegalStateException("A pilha está cheia");
        }
        topo++;
        meusDados[topo] = item;
    }

    @Override
    public Integer desempilhar() {
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia");
        }
        Integer item = meusDados[topo];
        meusDados[topo] = null; // Limpa a referência para permitir a coleta de lixo
        topo--;
        return item;
    }

    @Override
    public Integer topo() {
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia");
        }
        return meusDados[topo];
    }

    @Override
    public PilhaIF<Integer> multitop(int k) throws PilhaCheiaException {
        if (isEmpty() || k <= 0) {
            throw new IllegalArgumentException("A pilha está vazia ou o valor de k é inválido");
        }
        PilhaIF<Integer> multiTop = new MinhaPilha(k);
        int count = Math.min(k, size());
        for (int i = 0; i < count; i++) {
            multiTop.empilhar(meusDados[topo - count + 1 + i]);
        }
        return multiTop;
    }

    @Override
    public boolean isEmpty() {
        return topo == -1;
    }

    private boolean isFull() {
        return topo == tamanho - 1;
    }

    @Override
    public int size() {
        return topo + 1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = topo; i >= 0; i--) {
            builder.append(meusDados[i]);
            if (i > 0) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
