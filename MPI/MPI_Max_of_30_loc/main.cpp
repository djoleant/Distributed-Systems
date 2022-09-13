/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ MPI 30 max                                                              │
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
  } in[30], out[30]; // in and out buffers

  int size, rank;
  MPI_Init(&argc, &argv);
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);
  MPI_Comm_size(MPI_COMM_WORLD, &size);

  for (int i = 0; i < 30; i++)
  {
    in[i].value = double(rank + 1);
    in[i].rank = rank;
  }

  MPI_Reduce(in, out, 30, MPI_DOUBLE_INT, MPI_MAXLOC, 0, MPI_COMM_WORLD);

  if (rank == 0)
  {
    for (int i = 0; i < 30; i++)
    {
      std::cout << "Out value " << out[i].value << " at " << out[i].rank << std::endl;
    }
  }

  MPI_Finalize();
}