package pakiet_arena;


import java.io.*;
import java.util.Random;

public class QLearningAgent {

    private double[][] qTable;
    private double learningRate;
    private double discountFactor;
    // Parametr epsilon używany do eksploracji losowej
    private double epsilon;


    public QLearningAgent(int stateSize, int actionSize) {
        // Inicjalizacja tablicy Q
        qTable = new double[stateSize][actionSize];
        learningRate = 0.15;
        discountFactor = 0.9999;
        epsilon = 0.2;
    }

    // Metoda do wyboru akcji przez agenta z uwzględnieniem eksploracji losowej
    public int chooseAction(int state) {
        if (Math.random() < epsilon) {
            // Wybór losowej akcji z pewnym prawdopodobieństwem epsilon
            return new Random().nextInt(qTable[state].length);
        } else {
            // Wybór akcji na podstawie wartości Q
            int bestAction = 0;
            for (int i = 1; i < qTable[state].length; i++) {
                if (qTable[state][i] > qTable[state][bestAction]) {
                    bestAction = i;
                }
            }

            // Dodatkowa eksploracja - zamiana akcji 1 na 0 z pewnym prawdopodobieństwem (1 -leczenie 0 - atak)
            // oslabienie leczenia, poniewaz najkorzystniejszym wyborem zawsze bedzie leczenie
            if (bestAction == 1 && Math.random() < 0.5) {
                return 0;
            } else {
                return bestAction;
            }
        }
    }

    // Metoda do nauki agenta na podstawie otrzymanych nagród
    public void learn(int state, int action, int nextState, int reward) {
        double predict = qTable[state][action];
        double target = discountFactor * maxQValue(nextState);
        qTable[state][action] += learningRate * (reward + target - predict);
    }

    // Metoda zwracająca maksymalną wartość Q dla danego stanu
    private double maxQValue(int state) {
        double maxQ = qTable[state][0];
        for (int i = 1; i < qTable[state].length; i++) {
            if (qTable[state][i] > maxQ) {
                maxQ = qTable[state][i];
            }
        }
        return maxQ;
    }

    // Metoda do zapisu tabeli Q do pliku
    public void saveQTableToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(qTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metoda do wczytania tabeli Q z pliku
    public void loadQTableFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            qTable = (double[][]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Metoda do wyświetlenia zawartości tabeli Q
    public void displayQTable() {
        for (int i = 0; i < qTable.length; i++) {
            for (int j = 0; j < qTable[i].length; j++) {
                System.out.print(qTable[i][j] + "\t");
            }
            System.out.println();
        }
    }
}