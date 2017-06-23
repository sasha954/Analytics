package ua.nure.fomenko.analytics.services;

import java.util.Map;

/**
 * Created by fomenko on 27.05.2017.
 */
public interface KeyConverterService {
    String idToKey(int id);
    int keyToId(String key);
}
