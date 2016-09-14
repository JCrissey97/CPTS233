class LinearHashTable<K, V> extends HashTableBase<K, V>
{
	//determines whether or not we need to resize
	//to turn off resize, just always return false
    protected boolean needsResize()
    {
    	//linear probing seems to get worse after a load factor of about 70%
		if (_number_of_elements > (0.7 * _primes[_local_prime_index]))
		{
			return true;
		}
		return false;
    }
    
    //called to check to see if we need to resize
    protected HashTableBase<K, V> resizeCheck()
    {
    	//Right now, resize when load factor > .75; it might be worth it to experiment with 
		//this value for different kinds of hashtables
		if (needsResize())
		{
			_local_prime_index++;

			HasherBase<K> hasher = HasherFactory.copyHasher(_hasher);
			LinearHashTable<K, V> new_hash = new LinearHashTable<K, V>(hasher, _primes[_local_prime_index]);
			
			for (HashItem<K, V> item: _items)
			{
				if (item.isEmpty() == false)
				{
					//add to new hash table
					new_hash.addElement(item.getKey(), item.getValue());
				}
			}

			return new_hash;
		}
		
		return this;
    }
    
    public LinearHashTable()
    {
    	super();
    }
    
    public LinearHashTable(HasherBase<K> hasher)
    {
    	super(hasher);
    }
    
    public LinearHashTable(HasherBase<K> hasher, int number_of_elements)
    {
    	super(hasher, number_of_elements);
    }
    
    //copy constructor
    public LinearHashTable(LinearHashTable<K, V> other)
    {
    	super(other);
    }
    
    //concrete implementation for parent's addElement method
    public void addElement(K key, V value)
    {
    	//check for size restrictions
    	resizeCheck();
    	
    	//calculate hash based on key
    	int hash = super.getHash(key);
    	
    	//MA #1 TODO: find empty slot to insert (update hash variable as necessary)
    	while(!_items.elementAt(hash).isEmpty())
    	{
    		if(hash >= _items.size()) hash %= _items.size();
    		else hash++;
    	}
    	
    	HashItem<K, V> new_item = new HashItem<>();
    	new_item.setKey(key);
    	new_item.setValue(value);
    	new_item.setIsEmpty(false);
    	
    	_items.setElementAt(new_item, hash);
		
    	//remember how many things we are presently storing
    	_number_of_elements++;
    }
    
    //removes supplied key from hash table
    public void removeElement(K key)
    {
    	//calculate hash
    	int hash = super.getHash(key);
    	int probes = 0;
    	if(!containsElement(key)) throw new IllegalArgumentException("Element doesn\'t exist");
    	else while(_items.elementAt(hash).getValue() != getElement(key))
    	{
    		hash = (hash + 1) % _items.size();
    		probes++;
    		if(probes >= _items.size() || _items.elementAt(hash).isEmpty()) throw new IllegalArgumentException("Element doesn\'t exist");
    	}
    	_items.elementAt(hash).setIsEmpty(true);
    	//MA #1 TODO: find slot to remove. Remember to check for infinite loop!
    	
    	//decrement hashtable size
    	_number_of_elements--;
    }
    
    //returns true if the key is contained in the hash table
    public boolean containsElement(K key)
    {
    	int hash = super.getHash(key);
    	HashItem<K, V> slot = _items.elementAt(hash);
    	
    	//left incomplete to avoid hints :)
    	return false;
    }
    
    //returns the item pointed to by key
    public V getElement(K key)
    {
    	int hash = super.getHash(key);
    	HashItem<K, V> slot = _items.elementAt(hash);
    	
    	//left incomplete to avoid hints :)
    	return null;
    }
}