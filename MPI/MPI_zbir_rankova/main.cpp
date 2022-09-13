/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ MPI P-T-P sabiranje rankova procesa                                     │
  └─────────────────────────────────────────────────────────────────────────┘
*/

#include <mpi.h>
#include <math.h>

void main(int argc, char *argv[])
{
  int size, rank;
  MPI_Status status;

  MPI_Init(&argc, &argv);
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);
  MPI_Comm_size(MPI_COMM_WORLD, &size);

  int buff, sum;

  for (int i = 0; i < log2(size); i++)
  {
    int temp = (int)pow(2, i + 1);
    if (rank % temp == 0)
    {
      MPI_Recv(&buff, 1, MPI_INT, myrank + temp / 2, 2, MPI_COMM_WORLD, &status);
      sum += buff;
    }

    else if (myrank % temp == temp / 2)
    {
      MPI_Send(&sum, 1, MPI_INT, myrank - temp / 2, 2, MPI_COMM_WORLD);
    }
  }

  if (!rank)
    std::cout << "Sum of ranks is " << sum << std::endl;

  MPI_Finalize();
}