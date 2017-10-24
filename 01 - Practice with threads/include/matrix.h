#ifndef _MATRIX_
#define _MATRIX_

#include <iostream>

namespace concurrent_programming{

	/**
	* \class Matrix
	*
	* This class provides a manner to representing a square matrix
	* and simple operations, like sum, subtraction and multiplication.
	*
	* @author Vinícius Campos
	* @date 2017/10/06
	* @version 1.0
	*
	*/

	template<typename T>
	class Matrix{
		private:
			T ** data;
			int size = 0;
			int n_threads = 0;


			/**
			*	The auxiliar method used by threads to compute parallel multiplications
			*	
			* 	@param result the matrix that will save the result of matrix computations
			* 	@param a the matrix to multiply
			* 	@param x0 the start point of multiplication
			* 	@param x1 the last point of multiplication
			**/
			void auxiliarMultiply(Matrix<T> & result, const Matrix<T> & a, const int x0, const int x1);

		public:

			/**
			*
			* Constructor of class
			*
			* @param size_ defines the dimension of matrix
			**/
			Matrix(const int & size_);

			/**
			*
			* Other constructor of class
			*
			* @param size_ defines the dimension of matrix
			* @param n_threads_ defines the number of threads
			**/
			Matrix(const int & size_, const int & n_threads_);

			/**
			*
			* Destructor of class
			*
			**/
			~Matrix();

			/**
			*
			* Get the value of marix in position data[row][col]
			*
			* @param row index of row matrix
			* @param col index of col matrix
			**/
			T get(const int & row, const int & col);

			/**
			*
			* Set the value of marix in position data[row][col]
			*
			* @param row index of row matrix
			* @param col index of col matrix
			* @param value the new value to insert in position data[row][col]
			**/
			void set(const int & row, const int & col, T & value);

			/**
			*
			* Get the dimension of matrix
			*
			**/
			int length() const;

			/**
			*
			* Verify if the matrix os empty
			*
			**/
			bool isEmpty();

			/**
			*
			* Realize the parallel multiplication
			*
			* @param a the matrix to multiplying
			**/
			Matrix<T> multiply(const Matrix<T> & a);

			/**
			*
			* Override [] methods to using de data information
			*
			**/
			T *& operator[](const int & i);
			T * operator[](const int & i) const;

			/**
			*	Override + operator to realize sum of two matrixes
			* 	@param a the matrix to sum
			**/
			Matrix<T> operator+(const Matrix<T> & a);

			/**
			*	Override + operator to realize subtraction of two matrixes
			* 	@param a the matrix to sum
			**/
			Matrix<T> operator-(const Matrix<T> & a);

			/**
			*	Override * operator to realize sequential multiplication
			* 	@param a the matrix to multiply
			**/
			Matrix<T> operator*(const Matrix<T> & a);

			/**
			*	Override = operator to realize an assignment
			**/
			Matrix<T> & operator=(const Matrix<T> a);
	};

	/**
	*	Print the matrix
	* 	@param os  Output stream
	* 	@param obj Matrix to be printed
	**/
	template<typename T>
	std::ostream & operator<<(std::ostream & os, const concurrent_programming::Matrix<T> & obj);

}

#include "../src/matrix.inl"
#endif