package modernjavainaction.chap08;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MainMethod {
    private static List<String> sendCodeList = Arrays.asList("DKC_OSBL001", "DKC_OSBL002", "DKC_OSBL003", "DKC_OSBL004");
    private static Map<String, Integer> ageOfFriends = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);
    private static Map<String, List<String>> friendsToMovies = new HashMap<>();
    private static Map<String, String> favouriteMovies = new HashMap<>();
    private static Map<String, String> map1 = new HashMap<>() {
        {
            put("key1", "val1");
            put("key2", "val2-1");
            put("key4", null);
        }
    };
    private static Map<String, String> map2 = Map.of("key2", "val2-2", "key3", "val3", "key4", "val4");
    private static HashMap<String, Integer> hashMap = new HashMap<>() {
        {
            put("k1", 1);
            put("k2", 2);
            put("k3", 11);
        }
    };

    private static ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>(hashMap);

    public static void main(String[] args) {
//        sendCodeList.replaceAll(String::toUpperCase);
//        System.out.println(sendCodeList);


//        ageOfFriends.forEach((name, age) -> System.out.println(name + " is " + age + " years old"));
//        ageOfFriends.entrySet().stream()
//                .sorted(Map.Entry.comparingByKey())
//                .forEach(System.out::println);


//        friendsToMovies.computeIfAbsent("Mike", name -> new ArrayList())
//                .add("Monkey King");
//        friendsToMovies.computeIfAbsent("Mike", name -> new ArrayList())
//                .add("Monkey King2");
//        System.out.println(friendsToMovies);
//
//        favouriteMovies.computeIfAbsent("Shock", key -> "Journey to the west");
//        favouriteMovies.remove("Shock", "ourney to the west");
//        System.out.println(favouriteMovies);
//        favouriteMovies.remove("Shock", "Journey to the west");
//        System.out.println(favouriteMovies);


//        map1.putAll(map2);
//        System.out.println(map1);


//        map1.merge("key2", "new val", (v1, v2) -> v1 + v2);
//        map2.forEach((k, v) -> map1.merge(k, v, (v1, v2) -> v1 + v2));
//        System.out.println(map1);


//        hashMap.entrySet().removeIf(e -> e.getValue() < 11);
//        System.out.println(hashMap);


//        TODO
        System.out.println(concurrentHashMap);
        String reduce = concurrentHashMap.reduce(1, (k, v) -> k + v, (s1, s2) -> s1 + ", " + s2);
        System.out.println(reduce);
        ConcurrentHashMap.KeySetView<String, Integer> keySetView = concurrentHashMap.keySet();
        System.out.println(keySetView);
        System.out.println(concurrentHashMap.mappingCount());
        System.out.println(concurrentHashMap.size());
    }
}
