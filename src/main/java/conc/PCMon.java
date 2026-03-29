package conc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import java.util.LinkedList;
import java.util.Queue;

// Implementacion de un monitor genérico 
// Con tipo y capacidad pre-establecidos

public class PCMon<T> {
    private final Queue<T> buffer = new LinkedList<>();
    private final int capacity;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public PCMon(int capacity) {
        this.capacity = capacity;
    }

    public void deposit(T item) throws InterruptedException {
        lock.lock();
        try {
            while (buffer.size() == capacity) {
                notFull.await();
            }
            buffer.add(item);
            notEmpty.signal(); // notificamos la posibilidad de consumir
        } finally {
            lock.unlock();
        }
    }

    public T fetch() throws InterruptedException {
        lock.lock();
        try {
            while (buffer.isEmpty()) {
                notEmpty.await();
            }
            T item = buffer.poll();
            notFull.signal(); // notificamos la posibilidad de producir
            return item;
        } finally {
            lock.unlock();
        }
    }
}
