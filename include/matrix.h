#ifndef _MATRIX_
#define _MATRIX_

#include <iostream>

namespace concurrent_programming{

	template<typename T>
	class Matrix{
		private:
			T ** data;
			int size = 0;
			int n_threads = 0;
			void auxiliarMultiply(Matrix<T> & result, const Matrix<T> & a, const int x0, const int y0, const int x1, const int y1);

		public:

			Matrix(const int & size_);
			Matrix(const int & size_, const int & n_threads_);
			~Matrix();

			T get(const int & row, const int & col);
			void set(const int & row, const int & col, T & value);
			int length() const;
			bool isEmpty();
			Matrix<T> multiply(const Matrix<T> & a);

			Matrix<T> strassen(const Matrix<T> & matrix);

			T *& operator[](const int & i);
			T * operator[](const int & i) const;
			Matrix<T> operator+(const Matrix<T> & a);
			Matrix<T> operator-(const Matrix<T> & a);
			Matrix<T> operator*(const Matrix<T> & a);
			Matrix<T> & operator=(const Matrix<T> a);
	};

	template<typename T>
	std::ostream & operator<<(std::ostream & os, const concurrent_programming::Matrix<T> & obj);

}

#include "../src/matrix.inl"
#endif