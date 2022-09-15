/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ MPI min from example                                                    │
  └─────────────────────────────────────────────────────────────────────────┘
*/

#include <mpi.h>
#include <iostream>
#include <limits.h>

#define a 5
#define b 31
#define x 5

void main(int argc, char *argv[])
{
  struct
  {
    int val;
    int rank;
  } f, c, d, e;

  int rank, p, z;
  int b1 = 0; // brojac pojavljivanja
  int min = INT_MAX;

  MPI_Init(&argc, &argv);
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);
  MPI_Comm_size(MPI_COMM_WORLD, &p);

  for (z = a + 2 * rank; z <= b; z += p * 2)
  {
    if (z % x == 0)
    {
      b1++;
      if (z < min)
        min = z;
    }
  }

  c.val = min;
  c.rank = rank;
  d.val = b1;
  d.rank = rank;

  //rezultat u e.rank -> identifikator procesa koji ima najmanje pojavljivanja
  MPI_Reduce(&d, &e, 1, MPI_2INT, MPI_MINLOC, 0, MPI_COMM_WORLD);
  MPI_Bcast(&e, 1, MPI_2INT, 0, MPI_COMM_WORLD); //saljemo svuda da kazemo de se generise rezultat
  MPI_Reduce(&c, &f, 1, MPI_2INT, MPI_MINLOC, e.rank, MPI_COMM_WORLD);

  if(rank == e.rank)
  {
    std::cout << f.val << " " << f.rank << std::endl;
  }

  MPI_Finalize();

}