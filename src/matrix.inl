#include "matrix.h"
#include <string>
#include <math.h>

template<typename T>
concurrent_programming::Matrix<T>::Matrix(const int & size_) : size{size_}{
	if(size <= 0){
		throw std::logic_error("The matrix can not containt less than one row and column!");
	}

	data = new T*[size];
	for(int i = 0; i < size; ++i){
		data[i] = new T [size];
		for(int j = 0; j < size; ++j){
			data[i][j] = 0;
		}
	}
}

template<typename T>
concurrent_programming::Matrix<T>::~Matrix(){
	if(data != nullptr){
		if(!isEmpty()){
			for(int i = 0; i < size; ++i){
				delete [] data[i];
			}	
		}

		delete [] data;
		size = 0;
	}
}

template<typename T>
T concurrent_programming::Matrix<T>::get(const int & row, const int & col){
	if(row > size - 1 || col > size - 1){
		throw std::logic_error("Out of bounds!");	
	}else{
		return data[row][col];
	}
}

template<typename T>
void concurrent_programming::Matrix<T>::set(const int & row, const int & col, T & value){
	if(row > size - 1 || col > size - 1){
		throw std::logic_error("Out of bounds!");	
	}else{
		data[row][col] = value;
	}		
}

template<typename T>
int concurrent_programming::Matrix<T>::length() const{
	return size;
}

template<typename T>
bool concurrent_programming::Matrix<T>::isEmpty(){
	if(size <= 0){
		return true;
	}else{
		return false;
	}
}

template<typename T>
T * & concurrent_programming::Matrix<T>::operator[](const int & i) {
    if (i < 0 || i >= size){
        throw std::logic_error("Out of bounds.");
    }
    return data[i];
}

template<typename T>
T * concurrent_programming::Matrix<T>::operator[](const int & i) const {
    if (i < 0 || i >= size){
        throw std::logic_error("Out of bounds.");
    }
    return data[i];
}

template<typename T>
concurrent_programming::Matrix<T> concurrent_programming::Matrix<T>::operator+(const Matrix<T> & a){
	if(size != a.length()){
		throw std::logic_error("The matrices do not have the same dimension!");
	}

	Matrix<T> c(size);
	for(int i = 0; i < size; ++i){
		for(int j = 0; j < size; ++j){
			c[i][j] = data[i][j] + a[i][j];
		}
	}

	return c;
}

template<typename T>
concurrent_programming::Matrix<T> concurrent_programming::Matrix<T>::operator-(const Matrix<T> & a){
	if(size != a.length()){
		throw std::logic_error("The matrices do not have the same dimension!");
	}

	Matrix<T> c(size);
	for(int i = 0; i < size; ++i){
		for(int j = 0; j < size; ++j){
			c[i][j] = data[i][j] - a[i][j];
		}
	}

	return c;
}

template<typename T>
concurrent_programming::Matrix<T> concurrent_programming::Matrix<T>::operator*(const Matrix<T> & a){
	if(size != a.length()){
		throw std::logic_error("The matrices do not have the same dimension!");
	}

	Matrix<T> c(size);
	for(int i = 0; i < size; ++i){
		for(int j = 0; j < size; ++j){
			for(int k = 0; k < size; ++k){
				c[i][j] = c[i][j] + data[i][k] * a[k][j];
			}
		}
	}

	return c;
}

template<typename T>
concurrent_programming::Matrix<T> & concurrent_programming::Matrix<T>::operator=(const Matrix<T> a){
	if(a.length() <= 0){
		throw std::logic_error("The matrix can not containt less than one row and column!");
	}else if(data == nullptr){
		size = a.length();
		data = new T*[size];
		for(int i = 0; i < size; ++i){
			data[i] = new T [size];
			for(int j = 0; j < size; ++j){
				data[i][j] = a[i][j];
			}
		}
	}else if(a.length() != size){
		if(!isEmpty()){
			for(int i = 0; i < size; ++i){
				delete [] data[i];
			}	
		}

		delete [] data;
		
		size = a.length();
		data = new T*[size];
		for(int i = 0; i < size; ++i){
			data[i] = new T [size];
			for(int j = 0; j < size; ++j){
				data[i][j] = a[i][j];
			}
		}
	}else{
		for(int i = 0; i < size; ++i){
			for(int j = 0; j < size; ++j){
				data[i][j] = a[i][j];
			}
		}
	}

	return *(this);
}

template<typename T>
std::ostream & concurrent_programming::operator<<(std::ostream & os, const concurrent_programming::Matrix<T> & obj){
	if(obj.length() <= 0){
		os << "Empty matrix!\n";
	}else{
		int * max_columns = new int[obj.length()];
		for(int i = 0; i < obj.length(); ++i){
			max_columns[i] = 0;
		}

		for(int i = 0; i < obj.length(); ++i){
			for(int j = 0; j < obj.length(); ++j){
				std::string s = std::to_string(obj[i][j]);
				max_columns[j] = fmax( max_columns[j], s.size() );
			}	
		}

		for(int i = 0; i < obj.length(); ++i){
			for(int j = 0; j < obj.length(); ++j){
				for(int k = 0; k < max_columns[j] - std::to_string(obj[i][j]).size(); ++k){
					os << " ";
				}
				j != 0 ? os << " " << obj[i][j] : os << obj[i][j];
			}
			os << std::endl;	
		}
	}
	
	return os; 	
}