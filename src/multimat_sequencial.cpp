#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include "matrix.h"

using namespace std;
using namespace concurrent_programming;

void populateMatrix(Matrix<int> & a, const int dimension, const string character){
	ifstream file;
	file.open("input/" + character + to_string(dimension) + "x" + to_string(dimension) + ".txt", ios::out);
	
	string line;
	int value;
	stringstream ss;

	getline(file, line);
	for(int i = 0; i < dimension; ++i){
		getline(file, line);
		ss << line;
		for(int j = 0; j < dimension; ++j){
			ss >> a[i][j];
		}
		ss.str(string());
        ss.clear();	
	}

	file.close();
}

int main(int argn, char ** argv){
	 
	if(argn <= 1){
		throw logic_error("It is necessaty to inform the dimensions of matrices!");
	}
	
	cout << argv[1] << endl;

	Matrix<int> a(stoi(argv[1])), b(stoi(argv[1])), c(stoi(argv[1]));

	populateMatrix(a, stoi(argv[1]), "A");
	populateMatrix(b, stoi(argv[1]), "B");

	c = a * b;
	cout << c << endl;	

	return 0;
}