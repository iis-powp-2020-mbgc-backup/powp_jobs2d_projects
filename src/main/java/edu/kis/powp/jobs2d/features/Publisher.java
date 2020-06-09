package edu.kis.powp.jobs2d.features;

import edu.kis.powp.observer.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Publisher {
    boolean isNotyfying = false;
    private String nameOfPublisher = null;

    ArrayList<Subscriber> subscribers = new ArrayList<>();
    public static Map<String, Publisher> publishers = new HashMap<>();

    public Publisher(String nameOfPublisher){
        this.nameOfPublisher = nameOfPublisher;
        publishers.put(nameOfPublisher, this);
    }

    public static Publisher getPublisher(String publisherName){
        return publishers.get(publisherName);
    }

    public void addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    public ArrayList<Subscriber> getSubscribers(){
        ArrayList<Subscriber> subscribers = new ArrayList<>();

        for(Subscriber s : subscribers)
            subscribers.add(s);
        return subscribers;
    }

    public void clearObservers(){
        subscribers.clear();
    }

}