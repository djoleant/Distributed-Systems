/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ MPI Hello world program                                                 │
  └─────────────────────────────────────────────────────────────────────────┘
*/

#include <mpi.h>
void main(int argc, char *argv[])
{
    int size, rank;
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    std::cout << "Processor" << rank << ": Hello world!" << std::endl;

    MPI_Finalize();
}