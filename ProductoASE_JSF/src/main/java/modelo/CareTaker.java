package modelo;

import java.util.ArrayList;

public class CareTaker {

    private final ArrayList<Memento> mementos = new ArrayList<>();

    public void agregar(Memento m) {
        mementos.add(m);
    }

    public Memento getUltimo() {
        return mementos.get(mementos.size() - 1);
    }

    public boolean isExiste(Memento m) {
        boolean existe = false;
        for (Memento memento : mementos) {
            if (!mementos.isEmpty()) existe |= m.getEstado().equals(memento.getEstado());
            if (existe) break;
        }
        return existe;
    }
    
    
}
