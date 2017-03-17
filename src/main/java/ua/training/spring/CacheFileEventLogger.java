package ua.training.spring;

import java.util.List;

/**
 * Created by Dmytro_Deinichenko on 3/17/2017.
 */
public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String fileName) {
        super(fileName);
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if(cacheSize == cache.size()){
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        for(Event ev : cache){
            super.logEvent(ev);
        }
    }

}
