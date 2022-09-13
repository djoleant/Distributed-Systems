/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ MPI reduce example                                                      │
  └─────────────────────────────────────────────────────────────────────────┘
*/

/*
            ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐ 
  RANK      │  0  │ │  1  │ │  2  │ │  3  │ 
            └─────┘ └─────┘ └─────┘ └─────┘ 
            ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐ 
  SOURCE    │  1  │ │  2  │ │  3  │ │  4  │ 
            └─────┘ └─────┘ └─────┘ └─────┘ 
                                    ┌─────┐ 
  RESULT                            │   24│ 
                                    └─────┘ 
*/

#include <mpi.h>
#include <iostream>

void main(int argc, char *argv[])
{
  int size, rank;
  MPI_Status status;

  MPI_Init(&argc, &argv);
  MPI_Comm_rank(MPI_COMM_WORLD, &myrank);
  MPI_Comm_size(MPI_COMM_WORLD, &size);

  int src, result, root;

  root = 4; //moze biti bilo koji, ne mora 4
  src = rank+1;

  MPI_Reduce(&src, &result, 1, MPI_INT, MPI_PROD, root, MPI_COMM_WORLD);
  if(rank == root) std::cout << "Result is " << result << std::endl;

  MPI_Finalize();
}