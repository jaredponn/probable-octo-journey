package poj;

/**
 * Packed Vector data structure. O(1) insertion and deletion of indicies with
 * pop and swap.
 * Date: February 20, 2019
 * @author  Haiyang He and constributions from:
 https://github.com/jaredponn/improved-octo-waffle/blob/master/src/packedvector.h
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.Collections;

import poj.Logger.*;

public class PackedVector<T>
{

	private ArrayList<Integer> m_sparse_vector;
	private ArrayList<Integer> m_packed_indices;
	private ArrayList<T> m_packed_data;
	private int m_next_free_index;

	public static int INVALID_INDEX = -1;

	/**
	 * Constructs the PackedVector with a specified capacity
	 *
	 * @param  capacity capacity
	 */
	public PackedVector(int capacity)
	{
		m_sparse_vector = new ArrayList<Integer>(
			Collections.nCopies(capacity, INVALID_INDEX));
		m_packed_indices = new ArrayList<Integer>(capacity);
		m_packed_data = new ArrayList<T>(capacity);
		m_next_free_index = 0;
	}

	/**
	 * add element at sparse vector
	 *
	 * @param  index index
	 * @param  val value to add
	 * @return      void
	 */
	public void add_element_at_sparse_vector(final int index, final T val)
	{
		Logger.lassert(
			(index >= m_sparse_vector.size()),
			"MAJOR ERROR IN PACKEDVECTOR. Too many entities in this engine! increase buffer size.");
		Logger.lassert(
			(m_sparse_vector.get(index) != INVALID_INDEX),
			"MAJOR ERROR IN PACKEDVECTOR. You are adding an entity at this index, but an entity already exist at this index.");
		m_sparse_vector.set(index, m_next_free_index);
		m_packed_indices.add(index);
		m_packed_data.add(val);

		++m_next_free_index;
	}

	/**
	 * delete element at sparse vector
	 *
	 * @param  index index
	 * @return      void
	 */
	public void delete_element_at_sparse_vector(final int index)
	{

		int toBeDeletedIndexInPackedIndicies =
			m_sparse_vector.get(index);

		if (toBeDeletedIndexInPackedIndicies == INVALID_INDEX) {
			// Logger.logMessage( "Deleting an entity that has
			// already been deleted.");
			return;
		}

		int lastElementInPackedIndicies =
			m_packed_indices.get(m_packed_data.size() - 1);

		Collections.swap(m_sparse_vector, index,
				 lastElementInPackedIndicies);
		Collections.swap(m_packed_indices,
				 toBeDeletedIndexInPackedIndicies,
				 m_packed_indices.size() - 1);
		Collections.swap(m_packed_data,
				 toBeDeletedIndexInPackedIndicies,
				 m_packed_data.size() - 1);

		m_packed_data.remove(m_packed_data.size() - 1);
		m_packed_indices.remove(m_packed_indices.size() - 1);

		m_sparse_vector.set(index, INVALID_INDEX);
		--m_next_free_index;
	}

	/**
	 * gets data from sparse vector
	 *
	 * @param  index index
	 * @return      value at the index in the packed vector. If the index
	 *         does not exist, or it is too large, crashes the program.
	 */
	public final T get_data_from_sparse_vector(final int index)
	{
		if (index >= m_sparse_vector.size()) {
			try {
				Logger.lassert(
					"MAJOR ERROR IN PACKEDVECTOR. Index is bigger than the size of sparse vector with get_data_from_sparse_vector function. The error is with packed vector of type: "
					+ m_packed_data.get(0).getClass());
			} catch (ArrayIndexOutOfBoundsException exception) {
				Logger.lassert(
					"MAJOR ERROR IN PACKEDVECTOR. Index is bigger than the size of sparse vector with get_data_from_sparse_vector function.");
			}
		}
		if (m_sparse_vector.get(index) == INVALID_INDEX) {
			try {
				Logger.lassert(
					"MAJOR ERROR IN PACKEDVECTOR. Accessing invalid sparse vector index with get_data_from_sparse_vector function. Error with type: "
					+ m_packed_data.get(0).getClass());
			} catch (ArrayIndexOutOfBoundsException exception) {
				Logger.lassert(
					"MAJOR ERROR IN PACKEDVECTOR. Accessing invalid sparse vector index with get_data_from_sparse_vector function. ");
			}
		}
		return m_packed_data.get(m_sparse_vector.get(index));
	}


	/**
	 * sets data from sparse vector
	 *
	 * @param  index index
	 * @param  value  the value
	 * @return  void
	 */
	public final void set_data_at_sparse_vector(final int index, T val)
	{
		Logger.lassert(
			(index >= m_sparse_vector.size()),
			"MAJOR ERROR IN PACKEDVECTOR. Index is bigger than the size of sparse vector with set_data_at_sparse_vector function.");
		Logger.lassert(
			(m_sparse_vector.get(index) == INVALID_INDEX),
			"MAJOR ERROR IN PACKEDVECTOR. Accessing invalid sparse vector index with set_data_at_sparse_vector function. This is with type "
				+ val.getClass());
		m_packed_data.set(m_sparse_vector.get(index), val);
	}


	/**
	 * get global index from sparse vector
	 *
	 * @param  index index
	 * @return  return the index, as integer. If the index is bigger than
	 *         the packed indicies size, it will crash
	 */
	public final int get_global_index_from_packed_index(final int index)
	{

		Logger.lassert(
			(index >= m_packed_indices.size()),
			"MAJOR ERROR IN PACKEDVECTOR. Index is bigger than the size of packed indices vector with get_global_index_from_packed_index function");
		return m_packed_indices.get(index);
	}

	/**
	 * get sparse vector
	 *
	 * @return  return the entire sparse vector
	 * */
	public final ArrayList<Integer> get_sparse_vector()
	{
		return m_sparse_vector;
	}

	/**
	 * get packed indicies
	 *
	 * @return  return the entire packed indicies vector
	 * */
	public final ArrayList<Integer> get_packed_indicies()
	{
		return m_packed_indices;
	}

	/**
	 * get packed packed data
	 *
	 * @return  return the entire packed data vector
	 * */
	public final ArrayList<T> get_packed_data()
	{
		return m_packed_data;
	}

	/**
	 * get get packed packed data size
	 *
	 * @return  return the integer of the size of the packed data vector,
	 *         will return an error if the packed indicies and the packed
	 *         data are not the same size
	 * */
	public final int get_packed_data_size()
	{
		Logger.lassert(
			(m_packed_data.size() != m_packed_indices.size()),
			"MAJOR ERROR IN PACKEDVECTOR. Packed indices and packed data are not the same size!");
		return m_packed_data.size();
	}
}
