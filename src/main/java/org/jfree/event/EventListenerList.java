package org.jfree.event;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EventListenerList {
	@SuppressWarnings("rawtypes")
	private Map<Class, List<EventListener>> listeners = new HashMap<>();

	public <T extends EventListener> void add(Class<T> eventClass, T event) {
		if (!listeners.containsKey(eventClass)) {
			listeners.put(eventClass, new ArrayList<>());
		}
		listeners.get(eventClass).add(event);
	}

	@SuppressWarnings("unchecked")
	public <T extends EventListener> T[] getListeners(Class<T> eventClass) {
		if (listeners.containsKey(eventClass)) {
			List<EventListener> listenersList = listeners.get(eventClass);
			return (T[]) listenersList.toArray((T[]) Array.newInstance(eventClass, listenersList.size()));
		}
		return (T[]) new EventListener[] {};
	}

	public <T extends EventListener> void remove(Class<T> eventClass, T event) {
		if (listeners.containsKey(eventClass)) {
			List<EventListener> listenersList = listeners.get(eventClass);
			listenersList.remove(event);
			if (listenersList.isEmpty()) {
				listeners.remove(eventClass);
			}
		}
	}

	public Object[] getListenerList() {
		List<Object> objects = new ArrayList<>();
		for (Entry<Class, List<EventListener>> entry : listeners.entrySet()) {
			for (EventListener listener : entry.getValue()) {
				objects.add(entry.getKey());
				objects.add(listener);
			}
		}
		return objects.toArray(new Object[objects.size()]);
	}
}
