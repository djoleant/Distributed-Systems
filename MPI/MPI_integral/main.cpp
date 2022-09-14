/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ MPI integral to find PI value                                           │
  └─────────────────────────────────────────────────────────────────────────┘
*/

/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ Sirinu segmenta obelezavamo sa h, vrednost funkcije F(xi) trazimo u     │
  │ xi.                                                                     │
  │ e.g.                                                                    │
  │   N = 10 -> N broj segmenta                                             │
  │   h = 1/10 -> h - velicina segmenta                                     │
  │   p = 5 -> p - broj procesa                                             │
  │                                                                         │
  │   id [0]           [1]           [2]           [3]           [4]        │
  │                                                                         │
  │  sum [f(0.5*h)     [f(1.5*h)     [f(2.5*h)     [f(3.5*h)     [f(2.5*h)  │
  │      +f(5.5*h)]    +f(6.5*h)]    +f(7.5*h)]    +f(8.5*h)]    +f(7.5*h)] │
  │                                                                         │
  │ mypi [sum*h]       [sum*h]       [sum*h]       [sum*h]       [sum*h]    │
  │                                                                         │
  └─────────────────────────────────────────────────────────────────────────┘
 */

#include <mpi.h>
#include <math.h>
#include <iostream>
#define PI25DT 3.1415926653589793238462643

void main(int argc, char *argv[])
{
  int n, myid, numprocs, i;
  double mypi, pi, h, sum, x;

  MPI_Init(&argc, &argv);
  MPI_Comm_size(MPI_COMM_WORLD, &numprocs);
  MPI_Comm_rank(MPI_COMM_WORLD, &myid);

  if (myid == 0)
    std::cin >> n; // vece n => veca tacnost

  MPI_Bcast(&n, 1, MPI_INT, 0, MPI_COMM_WORLD);

  h = 1.0 / (double)n;
  sum = 0.0;

  //
  for (i = myid; i < n; i += numprocs)
  {
    /* numprocs je korak koji se dodaje + 0.5 za drugi deo sume u "sum" */
    x = h * ((double)i + 0.5);
    sum += 4.0 / (1, 0 + x * x); // zadata funckija (u zadatku definisano)
  }

  mypi = h * sum;

  MPI_Reduce(&mypi, &pi, 1, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD);

  if (myid == 0)
  {
    std::cout << "Approximately, pi is " << pi << std::endl;
    std::cout << "Error is " << fabs(pi - PI25DT) << std::endl;
  }

  MPI_Finalize();
}