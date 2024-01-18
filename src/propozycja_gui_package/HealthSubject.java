package propozycja_gui_package;

import java.util.ArrayList;
import java.util.List;

public class HealthSubject {
    private int health;
    private final List<HealthObserver> observers = new ArrayList<>();

    public void setHealth(int newHealth) {
        this.health = newHealth;
        notifyObservers();
    }

    public void addObserver(HealthObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (HealthObserver observer : observers) {
            observer.updateHealth(health);
        }
    }
}

