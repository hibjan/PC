package conc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RWMon {
	
	//Concurrent
	private int nr; // numero de lectores activos
    private boolean writing; // como nw <= 1, usamos un boolean por comodidad

    private final ReentrantLock lock;
    private final Condition canRead;
    private final Condition canWrite;
	
	public RWMon() {
		this.nr = 0;
		this.writing = false;
		this.lock = new ReentrantLock();
		this.canRead = lock.newCondition();
		this.canWrite = lock.newCondition();
	}
	
	public void requestWrite() {
        lock.lock();
        try {
            while (writing || nr > 0) {
            	canWrite.await();   
            }
            writing = true;
        } 
        catch (InterruptedException e) {e.printStackTrace();}
        finally {
            lock.unlock();
        }
    }

	public void releaseWrite() {
        lock.lock();
        try {
            writing = false;
            canRead.signalAll(); // activa todos los lectores
            canWrite.signal();   // activa un escritor
        } finally {
            lock.unlock();
        }
    }
	
	public void requestRead() {
        lock.lock();
        try {
            while (writing) {
                canRead.await();
            }
            nr++;
        } 
        catch (InterruptedException e) {e.printStackTrace();}
        finally {
            lock.unlock();
        }
    }

	public void releaseRead() {
        lock.lock();
        try {
            nr--;
            if (nr == 0) {
                canWrite.signal(); // activa un escritor
            }
        } finally {
            lock.unlock();
        }
    }
}
