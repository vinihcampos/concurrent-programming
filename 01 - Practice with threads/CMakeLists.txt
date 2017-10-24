cmake_minimum_required(VERSION 2.8)

project(multimat_sequencial)
project(multimat_concorrente)

include_directories(include)

set (CMAKE_CXX_STANDARD 11)
set (CMAKE_CXX_FLAGS_DISTRIBUTION "-O3")
set (CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -pthread")
set (CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/../bin)

file(GLOB SRC_SEQ "src/multimat_sequencial.cpp")
file(GLOB SRC_CON "src/multimat_concorrente.cpp")

add_executable(multimat_sequencial ${SRC_SEQ})
add_executable(multimat_concorrente ${SRC_CON})
