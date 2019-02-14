package poj.linear;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;
import poj.Logger.*;


public class MatrixTransformations extends Matrix<Float>
{
	public MatrixTransformations(int size)
	{
		super();
		ArrayList<Float> tmp = new ArrayList<Float>();
		for (int i = 0; i < size; ++i) {

			for (int j = 0; j < size; ++j) {
				if (i == j) {
					tmp.add(1.f);
				} else {
					tmp.add(0.f);
				}
			}
		}
		setArray(tmp, size, size);
	}


	/** this is literally matrix multiplication..*/
	public void compose(Matrix<Float> matrixB)
	{

		Logger.lassert(
			(this.cols != matrixB.rows || this.rows == 0
			 || this.cols == 0 || matrixB.rows == 0
			 || matrixB.cols == 0),
			"MAJOR ERROR in matrixMultiplication! A's col number does not match with B's row number");

		ArrayList<Float> tempMatrixC = new ArrayList<Float>(
			Collections.nCopies(this.rows * matrixB.cols, 0.f));
		for (int i = 0; i < this.rows; ++i) {
			for (int j = 0; j < matrixB.cols; ++j) {
				float tempValue = 0;
				for (int m = 0; m < this.cols; ++m) {
					tempValue +=
						this.getDataWithMatrixCord(
							new MatrixCord(i, m))
						* matrixB.getDataWithMatrixCord(
							  new MatrixCord(m, j));
				}
				tempMatrixC.set(i * matrixB.cols + j,
						tempValue);
			}
		}
		this.m_matrix = tempMatrixC;
	}

	public void clearBackToIdentity()
	{
		for (int i = 0; i < this.rows; ++i) {
			for (int j = 0; j < this.cols; ++j) {
				if (i == j) {
					this.m_matrix.set(i * this.cols + j,
							  1.f);
				} else {
					this.m_matrix.set(i * this.cols + j,
							  0.f);
				}
			}
		}
	}

	// swaps data at index a and b without bounds checking
	public void unsafeSwap(int a, int b)
	{
		float tmp = m_matrix.get(a);
		m_matrix.set(a, m_matrix.get(b));
		m_matrix.set(b, tmp);
	}

	public static final boolean matrixEquality(Matrix<Float> A,
						   Matrix<Float> B,
						   float EPSILON)
	{
		Logger.lassert(
			(A.m_matrix.size() != B.m_matrix.size()),
			"MAJOR ERROR in matrixEquality!! the two matrix size are not the same");
		for (int i = 0; i < A.m_matrix.size(); ++i) {
			if (AlmostEqualRelative(A.getDataWithIndex(i),
						B.getDataWithIndex(i),
						EPSILON)) {
				return false;
			}
		}
		return true;
	}

	// TODO 	MAKE UNIT TEST FOR THISSSSSSSSS
	public static final boolean AlmostEqualRelative(float A, float B,
							float EPSILON)
	{
		float diff = Math.abs(A - B);
		A = Math.abs(A);
		B = Math.abs(B);
		float largest = (B > A) ? B : A;
		if ((diff <= largest * EPSILON)
		    && (diff >= largest * -EPSILON)) {
			return true;
		}
		return false;
	}
}
