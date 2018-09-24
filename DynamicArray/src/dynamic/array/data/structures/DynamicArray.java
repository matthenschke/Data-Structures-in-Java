
package dynamic.array.data.structures;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import dynamic.array.data.structures.DynamicArrayException.ExceptionType;

public class DynamicArray<Type> {
	Type [] array;
	int size;
	int itemCount;
	
	/**
	 * default constructor where the default size is 10
	 */
	public DynamicArray(){
		this.size = 10;
		this.array = (Type[]) new Object[size];
		this.itemCount = 0;
		
	}
	/**
	 * Constructor with one argument
	 * @param size of array
	 */
	public DynamicArray(int size) {
		this.size = size;
		this.array = (Type[]) new Object[size];
		this.itemCount = 0;
	}
	
	/**
	 * function that checks if array is empty
	 * @return if the array is empty
	 */
	public boolean isEmpty() {
		return this.itemCount == 0; 
	}
	
	//getters (we do not need setters if we are setting value of the size in the constructor)
	
	/**
	 * 
	 * @return size of array
	 */
	public int getSize() {
		return this.size;
	}   
	/**
	 * 
	 * @return itemCount of array
	 */
	public int getCount() {
		return this.itemCount;
	}

	/**
	 * default add function where a value would be added at next index specified by the itemCount property
	 * @param the value that is to be inserted
	 */
	public void add (Type value) {
		//increase size if itemCount hits the size of array
		if (this.itemCount == this.size)
			increaseSize();
		
		this.array[itemCount] = value;
		this.itemCount++;	
	}
	/**
	 * add function that will add value to specific index
	 * @param value that will be added to array
	 * @param the index of the array where the value will be inserted to
	 */
	public void add (Type value, int index) {
		if (this.itemCount + 1 >= this.size || this.itemCount == this.size)
			increaseSize();
		for (int i = this.itemCount; i >= index; i--)
			this.array[i + 1] = this.array[i];
		this.array[index] = value;
		this.itemCount++;
			
	}
	/**
	 * removes element at the specified index
	 * @param index that specifies what entry that is being removed
	 * @throws DynamicArrayException
	 */
	public void removeAtIndex(int index) throws DynamicArrayException{
		if (this.isEmpty())
			throw new DynamicArrayException(ExceptionType.EMPTY_ARRAY);
		if (index < 0 || index > this.itemCount)
			throw new DynamicArrayException(ExceptionType.INDEX_OUT_OF_BOUNDS);
		for (int i = index; i < this.itemCount; i++) 
			this.array[i] = this.array[i + 1];
		this.itemCount--;
	
		
	}
	/**
	 * returns a list that contains all indexes of the value
	 * @param value that will be searched for 
	 * @return
	 */
	private List<Integer> getAllIndexes(Type value){
		List<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0; i < this.itemCount; i++) {
			if (this.array[i].equals(value))
				indexes.add(i); //auto-boxing to get the index values
		}
		
		if (indexes.isEmpty())
		   return null;
		
		return indexes;
	}
	/**
	 * function that removes all occurences of specific value from array
	 * @param value that will be deleted from array
	 * @throws DynamicArrayException 
	 */
	public void removeAll(Type value) throws DynamicArrayException {
		List<Integer> indexes = this.getAllIndexes(value);
		if (indexes.isEmpty())
			throw new DynamicArrayException(ExceptionType.VALUE_IS_NOT_IN_ARRAY);
		Collections.sort(indexes, Collections.reverseOrder());
		for (Integer index : indexes)
			this.removeAtIndex(index);
		
	}

	
	
	/**
	 * function that increases size of array when itemcount equals size
	 */
	public void increaseSize() {
		Type [] temp = (Type []) new Object [this.size];
		System.arraycopy(this.array, 0, temp, 0, this.itemCount); //copy array to a temp array
		
		this.size *= 2;
		this.array = (Type []) new Object [this.size]; //array will become new array with size doubled
		System.arraycopy(temp, 0, this.array, 0, this.itemCount); //copy the elements from temp back to the new created array

		
	}
	
	/**
	 * Sets the itemCount back to 0, and the size back to 10
	 */
	public void clear() {
		this.itemCount = 0;
		this.size = 10;
		this.array = (Type []) new Object[this.size];
	}
	
	
	//print out array
	public void printArray() {
		for (int i = 0; i < this.itemCount; i++) {
			System.out.print(this.array[i] + " ");
		}
		System.out.print('\n');
	}
	//toString, equals, and hashCode method
	@Override
	public String toString() {
		return "DynamicArray [array=" + Arrays.toString(array) + ", size=" + size + ", itemCount=" + itemCount + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(array);
		result = prime * result + itemCount;
		result = prime * result + size;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DynamicArray other = (DynamicArray) obj;
		if (!Arrays.equals(array, other.array))
			return false;
		if (itemCount != other.itemCount)
			return false;
		if (size != other.size)
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Testing generic array
		DynamicArray<Integer> intArray = new DynamicArray<Integer>(10);
		
		
		intArray.add(3,0);
		intArray.add(4,0);
		intArray.add(3,0);
		intArray.add(4,0);
		intArray.add(3,0);
		intArray.add(4,0);
		intArray.add(3,0);
		intArray.add(4,0);
		intArray.add(3,0);
		intArray.add(4,0);
		intArray.add(44,1);
		intArray.add(23,1);
		try {
		intArray.removeAll(3);
		intArray.removeAll(4);
			}
			catch (DynamicArrayException e) {
			System.err.println(e.getMessage());
			}
        
		intArray.printArray();
	}
	}
	

