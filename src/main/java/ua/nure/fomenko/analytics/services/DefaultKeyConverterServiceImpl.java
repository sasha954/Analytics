package ua.nure.fomenko.analytics.services;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fomenko on 27.05.2017.
 */
public class DefaultKeyConverterServiceImpl implements KeyConverterService {

    char[] chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-_".toCharArray();
    public Map<Character, Integer> charsWithPosition = charToIntPosition(chars);
    private final int ADDITIONAL_VALUE = 100000;

    @Override
    public String idToKey(int id) {
        int n = ADDITIONAL_VALUE+id;
        StringBuilder builder = new StringBuilder();
        while (n!=0) {
            builder.append(chars[n%chars.length]);
            n/=chars.length;
        }
        return builder.reverse().toString();
    }

    @Override
    public int keyToId(String key) {
        int result = 0;
        for (int i = 0; i < key.length(); i++) {
            char character = key.charAt(i);
            int n = charsWithPosition.get(character);
            result = result * chars.length + n;
        }
        return result-ADDITIONAL_VALUE;
    }
    private Map<Character, Integer> charToIntPosition(char[] array) {
        Map<Character, Integer>map = new HashMap<>();
        for (int i = 0; i < array.length; i++){
            map.put(chars[i], i);
        }
        return map;
    }

}
