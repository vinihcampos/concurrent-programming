# Concurrent Programming

This repository provides some algorithms for discipline Concurrent Programming of UFRN.

## Getting Started

### Prerequisites

To compiling the project, it's needed to install CMake 

```
sudo apt-get install cmake
```

### Installing

```
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

## Authors

[![Vinicius Campos](https://avatars.githubusercontent.com/Vinihcampos?s=100)<br /><sub>Vinicius Campos</sub>](http://lattes.cnpq.br/4806707968253342)<br />[👀](https://github.com/vinihcampos/concurrent-programming/commits?author=Vinihcampos)
| :---: | 


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

