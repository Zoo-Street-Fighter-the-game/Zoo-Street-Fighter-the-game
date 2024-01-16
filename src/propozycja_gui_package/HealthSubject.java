package propozycja_gui_package;

import propozycja_gui_package.HealthObserver;

import java.util.ArrayList;
import java.util.List;

public class HealthSubject {
    private int health;
    private List<HealthObserver> observers = new ArrayList<>();

    public int getHealth() {
        return health;
    }

    public void setHealth(int newHealth) {
        this.health = newHealth;
        notifyObservers();
    }

    public void addObserver(HealthObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(HealthObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (HealthObserver observer : observers) {
            observer.updateHealth(health);
        }
    }
}

