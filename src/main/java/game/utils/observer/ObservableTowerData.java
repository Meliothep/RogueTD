package game.utils.observer;

import game.datas.towerdatas.TowerData;

import java.util.ArrayList;
import java.util.List;

public abstract class ObservableTowerData {

    private final List<NormalTowerObserver> observers = new ArrayList<>();

    public void addObserver(NormalTowerObserver obs) {
        if (!observers.contains(obs)) {
            observers.add(obs);
        }
    }

    public void removeObserver(NormalTowerObserver obs) {
        observers.remove(obs);
    }

    public void notifyObs(TowerData data) {
        observers.forEach(it -> it.update(data));
    }

}
