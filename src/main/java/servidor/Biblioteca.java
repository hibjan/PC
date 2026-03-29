package servidor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import conc.RWSem;

public class Biblioteca {
	
	//Book -> Users
	private Map<String, Set<String>> libros;
	private RWSem mutex;
		
	public Biblioteca() {
		this.libros = new HashMap<String, Set<String>>();
		this.mutex = new RWSem();
	}
	
	public void escribir(String libro, String usuario) {
		mutex.requestWrite();
		
		if(!libros.containsKey(libro)) {
			libros.put(libro, new HashSet<String>());
		}
		libros.get(libro).add(usuario);
		
		mutex.releaseWrite();
	}

	public void borrar(String username) {
		mutex.requestWrite();
		
		List<String> empty = new LinkedList<>();
		
		for (String libro : libros.keySet()) {
			libros.get(libro).remove(username);
			if(libros.get(libro).isEmpty()) {
				empty.add(libro);
			}
	    }
		
		for(String libro : empty) {
			libros.remove(libro);
		}
		
		mutex.releaseWrite();
	}
	
	public String[] read_books(){
		mutex.requestRead();
		
		String[] books = libros.keySet().toArray(new String[0]);
		
		mutex.releaseRead();
		
		return books;
	}

	public String[] read_users(String book) {
		mutex.requestRead();
		
		String[] users = new String[0];
		if(libros.containsKey(book)) {
			users = libros.get(book).toArray(new String[0]);
		}
		
		mutex.releaseRead();
		
		return users;
	}
    
}
