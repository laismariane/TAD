package tad.pilha;

import tad.listasEncadeadas.ListaEncadeadaIF;
import tad.listasEncadeadas.ListaEncadeadaImpl;

public class MinhaPilhaEncadeada implements PilhaIF<Integer> {

    private ListaEncadeadaIF<Integer> listaEncadeada;

    public MinhaPilhaEncadeada() {
        listaEncadeada = new ListaEncadeadaImpl<>();
    }

    @Override
    public void empilhar(Integer item) {
        listaEncadeada.insert(item);
    }

    @Override
    public Integer desempilhar() throws PilhaVaziaException {
        if (isEmpty()) {
            throw new PilhaVaziaException("A pilha está vazia");
        }
        Integer topo = listaEncadeada.remove(listaEncadeada.size() - 1).getChave();
        return topo;
    }

    @Override
    public Integer topo() {
        if (isEmpty()) {
            return null;
        }
        return listaEncadeada.search(listaEncadeada.size() - 1).getChave();
    }

    @Override
    public PilhaIF<Integer> multitop(int k) throws PilhaCheiaException {
        PilhaIF<Integer> multiTop = new MinhaPilhaEncadeada();
        if (isEmpty() || k <= 0) {
            return multiTop;
        }
        int count = Math.min(k, listaEncadeada.size());
        for (int i = listaEncadeada.size() - 1; i >= listaEncadeada.size() - count; i--) {
            multiTop.empilhar(listaEncadeada.search(i).getChave());
        }
        return multiTop;
    }

    @Override
    public boolean isEmpty() {
        return listaEncadeada.isEmpty();
    }

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
}

