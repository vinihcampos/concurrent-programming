cmake_minimum_required(VERSION 2.8)

project(multimat_sequencial)

include_directories(include)

set (CMAKE_CXX_STANDARD 11)
set (CMAKE_CXX_FLAGS_DISTRIBUTION "-O3")
set (CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/../bin)

file(GLOB SRCS "src/*.cpp")

add_executable(multimat_sequencial ${SRCS})
