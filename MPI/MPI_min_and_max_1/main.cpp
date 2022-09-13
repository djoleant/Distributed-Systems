/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ MPI min and max example                                                 │
  └─────────────────────────────────────────────────────────────────────────┘
*/

#include <mpi.h>
#include <iostream>

void main(int argc, char *argv[])
{
  struct
  {
    double value;
    int rank;
  } in, out; // in and out buffers

  int size, rank;
  int root;
  MPI_Init(&argc, &argv);
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);
  MPI_Comm_size(MPI_COMM_WORLD, &size);

  in.value = rank + 1;
  in.rank = rank;

  root = 5;

  MPI_Reduce(&in, &out, 1, MPI_DOUBLE_INT, MPI_MAXLOC, root, MPI_COMM_WORLD);

  if (rank == root)
    std::cout << "Max result is " << out.value << " at " << out.rank << std::endl;

  MPI_Reduce(&in, &out, 1, MPI_DOUBLE_INT, MPI_MINLOC, root, MPI_COMM_WORLD);

  if (rank == root)
    std::cout << "Min result is " << out.value << " at " << out.rank << std::endl;

  MPI_Finalize();
}