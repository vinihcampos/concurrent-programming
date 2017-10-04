#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <chrono>
#include <ratio>
#include <ctime>
#include "matrix.h"

using namespace std;
using namespace std::chrono;
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

void populateOutputFile(Matrix<int> & a){
	ofstream file;
	file.open("output/C" + to_string(a.length()) + "x" + to_string(a.length()) + ".txt");

	if(file.is_open()){
		file << a.length() << " " << a.length() << endl;
		file << a;
	}

	file.close();	
}

void populateTimeFile(const duration<double> & time_elapsed, const int & dimension){
	ofstream file;
	file.open("time_files/" + to_string(dimension) + "x" + to_string(dimension) + ".txt", ios::app);

	if(file.is_open()){
		file << time_elapsed.count() << " seconds"<< endl;
	}

	file.close();
}

int main(int argn, char ** argv){
	
	steady_clock::time_point start = steady_clock::now();

	if(argn <= 1){
		throw logic_error("It is necessary to inform the dimensions of matrices!");
	}
	
	Matrix<int> a(stoi(argv[1])), b(stoi(argv[1])), c(stoi(argv[1]));

	populateMatrix(a, stoi(argv[1]), "A");
	populateMatrix(b, stoi(argv[1]), "B");

	c = a*b;
	populateOutputFile(c);

	steady_clock::time_point end = steady_clock::now();
	duration<double> time_elapsed = end - start;

	populateTimeFile(time_elapsed, c.length());
	cout << "(" << c.length() << "x" << c.length() << ") Elapsed time: " << time_elapsed.count() << " seconds" << endl;	

	return 0;
}