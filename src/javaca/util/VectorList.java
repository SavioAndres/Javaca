package javaca.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class VectorList<E> {
	private Object[] elementData;
	private int size;
	
	public VectorList() {
		this(10);
	}
	
	public VectorList(int initialCapacity) {
		elementData = new Object[10];
		size = 0;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(E o) {
		return indexOf(o) != -1;
	}

	public Object[] toArray() {
		return Arrays.copyOf(elementData, size);
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if (a.length < size)
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
	}

	public VectorList<E> add(E e) {
		return add(size, e);
	}
	
	public VectorList<E> add(int index, E element) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		
		if (size == elementData.length) {
			Object[] elementData2 = new Object[size + (size / 2)];
			
			for (int i = 0; i < elementData.length; i++) {
				elementData2[i] = elementData[i];
			}
			
			elementData = elementData2;
		}
		
		if (elementData[index] == null) {
			size++;
		} else {
			for (int i = size; i > index; i--) {
				elementData[i] = elementData[i - 1];
			}
		}
			
		elementData[index] = element;
		
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public VectorList<E> addAll(E... elements) {
		for (E e : elements) {
			add(e);
		}
		
		return this;
	}

	public VectorList<E> addAll(Collection<? extends E> c) {
		for (E e : c) {
			add(e);
		}
		
		return this;
	}

	public boolean removeAll(Collection<E> c) {
		boolean isRemoveAll = false;
		
		for (E e : c) {
			isRemoveAll = remove(e);
		}
		
		return isRemoveAll;
	}
	
	@SuppressWarnings("unchecked")
	public boolean removeAll(E... elements) {
		boolean isRemoveAll = false;
		
		for (E e : elements) {
			isRemoveAll = remove(e);
		}
		
		return isRemoveAll;
	}

	public VectorList<E> clear() {
		elementData = new Object[10];
		size = 0;
		return this;
	}

	@SuppressWarnings("unchecked")
	public E get(int index) {
		Objects.checkIndex(index, size);
		return (E) elementData[index];
	}
	
	public E getFirst() {
		return size == 0 ? null : get(0);
	}
	
	public E getLast() {
		return size == 0 ? null : get(size - 1);
    }

	public E set(int index, E element) {
		if (index >= size || index < 0)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		
		if (elementData[index] == null) {
			size++;
		}
			
		elementData[index] = element;
		
		return element;
	}

	public VectorList<E> remove(int index) {
		if (index >= size || index < 0)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		
		for (int i = index; i < size - 1; i++) {
			elementData[i] = elementData[i + 1];
		}
		
		elementData[size - 1] = null;
		
		size--;
		
		return this;
	}
	
	public boolean remove(E o) {
		int indexOf = lastIndexOf(o);
		if (indexOf != -1) {
			remove(indexOf);
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public E removeLast() {
        if (size == 0) {
        	return null;
        }
        E element = (E) elementData[size - 1];
        elementData[size - 1] = null;
		size--;
        
        return element;
    }

	public int indexOf(E o) {
		int indexOf = -1;
		for (int i = 0; i < size - 1; i++) {
			if (elementData[i].equals(o)) {
				indexOf = i;
				break;
			}
		}
		return indexOf;
	}

	public int lastIndexOf(E o) {
		int indexOf = -1;
		for (int i = size - 1; i >= 0; i--) {
			if (elementData[i].equals(o)) {
				indexOf = i;
				break;
			}
		}
		return indexOf;
	}

	@Override
	public String toString() {
		if (elementData == null)
            return "null";

        int iMax = size - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(elementData[i]));
            if (i == iMax)
                return b.append(']').toString() + " | " + size + " - " + elementData.length;
            b.append(", ");
        }
	}
	
	
}
