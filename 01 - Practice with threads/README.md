# Practice with threads

This work analyzes the processing time of sequential and parallel algorithms for square matrices multiplications. 

## Getting Started

### Prerequisites

To compile the project, it's needed to install CMake 

```
sudo apt-get install cmake
```

### Installing

```
mkdir -p build
cd build
cmake ..
make
```

## Running the tests

```
cd bin
```
* Sequential main file:
```
./multimat_sequencial x
```
Where 'x' is the dimension of matrix

* Parallel main file:
```
./multimat_concorrente x y
```
Where 'x' is the dimension of matrix and 'y' is the number of threads

## Built With

* [C++](http://en.cppreference.com/w/) - Programming Language
* [CMake](https://cmake.org/) - Automated compilation

## Author

[![Vinicius Campos](https://avatars.githubusercontent.com/Vinihcampos?s=100)<br /><sub>Vinicius Campos</sub>](http://lattes.cnpq.br/4806707968253342)<br />[👀](https://github.com/vinihcampos/concurrent-programming/commits?author=Vinihcampos)
| :---: | 


## License

This project is licensed under the MIT License - see the [LICENSE.md](../LICENSE) file for details

