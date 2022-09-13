cmake_minimum_required(VERSION 3.0.0)
project(MPI_product VERSION 0.1.0)

include(CTest)
enable_testing()

add_executable(MPI_product main.cpp)
target_link_libraries(main
  PRIVATE
  MPI_C)

set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
SET(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -fopenmp")

find_package(MPI REQUIRED) #make it REQUIRED, if you want
include_directories(SYSTEM ${MPI_INCLUDE_PATH})
target_link_libraries(my_mpi_target ${MPI_C_LIBRARIES})


include(CPack)
