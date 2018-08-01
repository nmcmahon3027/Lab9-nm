package edu.luc.cs271.myhashmap;

import java.util.*;
import java.lang.Object;
import javafx.util.Pair;


/**
 * A generic HashMap custom implementation using chaining. Concretely, the table is an ArrayList of
 * chains represented as LinkedLists.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class MyHashMap<K, V> implements Map<K, V> {
  //implements the Map Interface
  // MUST have certain methods listed in Interface!
  private static final int DEFAULT_TABLE_SIZE = 11; // a prime

  private List<List<Entry<K, V>>> table;

  public MyHashMap() {//IF USER CREATES THIS, THEN DEFUALT
    this(DEFAULT_TABLE_SIZE);
  }

  //PARAMETER(tableSize)
  public MyHashMap(final int tableSize) {//USER PASSES PARAMETER SPECIFIES SIZE
    //...then this construcor will be called
    // allocate a table of the given size
    table = new ArrayList<>(tableSize);//ArrayList OF tableSize
    //hashTable & ArrayList ARE THE SAME
    // then create an empty chain at each position
    for (int i = 0; i < tableSize; i += 1) {
      table.add(new LinkedList<>());
      //for every element in ArrayList(aka table) create a new LL
    }
  }

  @Override
  public int size() {
    // TODO add the sizes of all the chains
    //NEED a loop that goes throgh every element of that ArrayList

    int result = 0;

    for (int i = 0; i < table.size(); i++) {
      result += table.get(i).size();
    }
    return result;
  }
    /*int numKeys=0;
    int result = numKeys;
    return result;*/
    //Good^




  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public boolean containsKey(final Object key) {
    // TODO follow basic approach of remove below (though this will be much simpler)
    final int index = calculateIndex(key);
    final Iterator<Entry<K, V>> iter = table.get(index).iterator();
    while (iter.hasNext()) {
      final Entry<K, V> entry = iter.next();
      if (entry.getKey() == key) {
        return true;
      }
    }

    return false;
  }

    /*final int index = calculateIndex(key);
    final Iterator<Entry<K, V>> iter = table.get(index).iterator();
    while (iter.hasNext()) {
      final Entry<K, V> entry = iter.next();
      if (entry.getKey().equals(key)) {
        return true;
      }
    }
    return false;*/
  //Good^






  @Override
  public boolean containsValue(final Object value) {
    // *TODO follow basic approach of remove below (though this will be much simpler)
    //if match return true, if no match return false
    //search through values set

    for (List<Entry<K, V>> temp : table) {
      for (Entry<K, V> entry : temp) {
        if (entry.getValue().equals(value)) {
          return true;
        }
      }
    }
    return false;
  }

   /* for (int i = 0; i < table.size(); i += 1) {
      final Iterator<Entry<K, V>> iter = table.get(i).iterator();
      while (iter.hasNext()) {
        final Entry<K, V> entry = iter.next();
        if (entry.getValue().equals(value)) {
          return true;
        }
      }
    }
    return false;
  }
*/



  @Override
  public V get(final Object key) {
    final int index = calculateIndex(key);
    final Iterator<Entry<K, V>> iter = table.get(index).iterator();
    while (iter.hasNext()) {
      final Entry<K, V> entry = iter.next();
      if (entry.getKey().equals(key)) {
        return entry.getValue();
      }
    }
    return null;
  }







   /* final int index = calculateIndex(key);
    final Iterator<Entry<K, V>> iter = table.get(index).iterator();
    if (table.get(index) == null) {
      return null;
    }
    while (iter.hasNext()) {
      final Entry<K, V> entry = iter.next();
      //    for(Entry<K, V> entry : table.get(index)) {
      if (entry.getKey().equals(key)) {
        final V value = entry.getValue();
        //        return entry.getValue();
        return value;
      }
    }
    return null;
  }*///Good^

    //* TODO follow basic approach of remove below (though this will be simpler)

  @Override
  public V put(final K key, final V value) {
    final int index = calculateIndex(key);
    for (Entry<K, V> temp : table.get(index)) {
      if (temp.getKey().equals(key)) {
        final V oldValue = temp.getValue();
        return oldValue;
      }
    }
    table.get(index).add(0, new AbstractMap.SimpleEntry<K, V>(key, value));
    return null;
  }

    /*final int index = calculateIndex(key);

    final Iterator<Entry<K, V>> iter = table.get(index).iterator();
    //    for (Entry<K, V> entry : table.get(index)) {
    while (iter.hasNext()) {
      final Entry<K, V> entry = iter.next();
      if (entry.getKey().equals(key)) {
        V oldVal = entry.getValue();
        entry.setValue(value);
        return oldVal;
      }
    }
    table.get(index).add(new AbstractMap.SimpleEntry<K, V>(key, value));
    int numKeys = 0;
    numKeys++;
    return null;*/


    //**
    //table.put(key, value);
    //put(key, value);
    //calculateIndex(key);


    /*final int index = calculateIndex(key);
    final Iterator<Entry<K, V>> iter = table.get(index).iterator();
    while (iter.hasNext()) {
      final Entry<K, V> entry = iter.next();
      if (entry.getKey().equals(key)) {
        final V oldValue = entry.getValue();

        return oldValue;

      }
    }


    table.get(index).add(new AbstractMap.SimpleEntry<K, V>(key, value));


    return null;
  }*/



  @Override
  public V remove(final Object key) {
    final int index = calculateIndex(key);
    final Iterator<Entry<K, V>> iter = table.get(index).iterator();
    while (iter.hasNext()) {
      final Entry<K, V> entry = iter.next();
      if (entry.getKey().equals(key)) {
        final V oldValue = entry.getValue();
        iter.remove();
        return oldValue;
      }
    }
    return null;
  }

  @Override
  public void putAll(final Map<? extends K, ? extends V> m) {
    // TODO add each entry in m's entrySet
    //this.put(for every entry set)
    //final int index = calculateIndex(key);
    for(Map.Entry<? extends K, ? extends V> add : m.entrySet()){
      this.put(add.getKey(), add.getValue());
    }

   /* Set<? extends Entry<? extends K, ? extends V>> mySet = m.entrySet();
    List<Entry<K, V>> list = new LinkedList<>();
    Iterator it = mySet.iterator();
    while (it.hasNext()) {
      //      Map.Entry entry = (Entry) it.next();
      list.add((Entry<K, V>) it.next());
    }
    table.add(list);*/ //Good^
  }



  @Override
  public void clear() {
    for(List<Entry<K, V>> temp : table){
      temp.clear();
    }
    // TODO clear each chain
    //clear hash table
    //iterate
    //get(i) ==>get sub i
    //1 loop, 2 lines

    //table.clear();
    //int numKeys = 0;
  }

    /*for (int i = 0; i < size(); i++) { //size (as capacity)
      table.get(i).clear();
    }*/




  /**
   * The resulting keySet is not "backed" by the Map, so we keep it unmodifiable.
   */
  @Override
  public Set<K> keySet() {
    final Set<K> result = new HashSet<>();
    // TODO populate the set
    for (int i = 0; i < table.size(); i++) {
      final Iterator<Entry<K, V>> iter = table.get(i).iterator();
      while (iter.hasNext()) {
        result.add(iter.next().getKey());
      }
    }
    return Collections.unmodifiableSet(result);
  }


   /* final Set<K> result = new HashSet<>();
    // TODO populate the set
    for (int i = 0; i < table.size(); i += 1) {
      final Iterator<Entry<K, V>> iter = table.get(i).iterator();
      while (iter.hasNext()) {
        final Entry<K, V> entry = iter.next();
        result.add(entry.getKey());
      }
    }
    return Collections.unmodifiableSet(result);
  }*///Good^



  /**
   * The resulting values collection is not "backed" by the Map, so we keep it unmodifiable.
   */
  @Override
  public Collection<V> values() {
    final List<V> result = new LinkedList<>();
    // TODO populate the list
    for (int i = 0; i < table.size(); i++) {
      final Iterator<Entry<K, V>> iter = table.get(i).iterator();
      while (iter.hasNext()) {
        result.add(iter.next().getValue());
      }
    }
    return Collections.unmodifiableCollection(result);
  }

    /*final List<V> result = new LinkedList<>();

    for (int i = 0; i < table.size(); i += 1) {
      final Iterator<Entry<K, V>> iter = table.get(i).iterator();
      while (iter.hasNext()) {
        final Entry<K, V> entry = iter.next();
        result.add(entry.getValue());
      }
    }
    return Collections.unmodifiableCollection(result);
  }*///Good^



  /**
   * The resulting entrySet is not "backed" by the Map, so we keep it unmodifiable.
   */
  @Override
  public Set<Entry<K, V>> entrySet() {
    final Set<Entry<K, V>> result = new HashSet<>();
    // TODO populate the set
    for (int i = 0; i < table.size(); i++) {
      final Iterator<Entry<K, V>> iter = table.get(i).iterator();
      while (iter.hasNext()) {
        result.add(iter.next());
      }
    }
    return Collections.unmodifiableSet(result);
  }


    /*final Set<Entry<K, V>> result = new HashSet<>();
    // TODO populate the set
    for (int i = 0; i < table.size(); i += 1) {
      final Iterator<Entry<K, V>> iter = table.get(i).iterator();
      while (iter.hasNext()) {
        final Entry<K, V> entry = iter.next();
        result.add(entry);
      }
    }
    return Collections.unmodifiableSet(result);
  }*///Good^




  @Override
  public String toString() {
    // TODO return the string representation of the underlying table
    return "" + table;
    //OR
    // return table.toString()




  }

  public boolean equals(final Object that) {
    if (this == that) {
      return true;
    } else if (!(that instanceof Map)) {
      return false;
    } else {
      return this.entrySet().equals(((Map) that).entrySet());
    }

  }







  private int calculateIndex(final Object key) {//send the word
    //Key= word, Value= wordCount
    // positive remainder (as opposed to %)
    // required in case hashCode is negative!
    return Math.floorMod(key.hashCode(), table.size());
    //takes hashCode of word
    //gives it (dividend, diviser) which ==index
    //calculate index everytime want hashtable
    //index mean, what BUCKET #
    //every element of hashTable has a bucket which contains a LL
    //that will tell you which bucket/LL this word is located at
  }
}
