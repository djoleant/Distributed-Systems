/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ MPI dot (scalar) product                                                │
  └─────────────────────────────────────────────────────────────────────────┘
*/

/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ a dot b = SUM (i = 1 to N) [a_i * b_i] = a_1 * b_1 + ... + a_n * b_n    │
  └─────────────────────────────────────────────────────────────────────────┘
 */

#include <mpi.h>
#include <math.h>
#include <iostream>
#define n 6

void main(int argc, char *argv[])
{
  float a[n], b[n], dot, local_dot = 0;
  int i, n_bar, my_rank, p;

  MPI_Init(&argc, &argv);
  MPI_Comm_size(MPI_COMM_WORLD, &p);
  MPI_Comm_rank(MPI_COMM_WORLD, &myrank);
  n_bar = n / p;

  float *local_a = (float *)malloc(n_bar * sizeof(float));
  float *local_b = (float *)malloc(n_bar * sizeof(float));

  if (my_rank == 0)
  {
    for (i = 0; i < n; i++)
    {
      std::cin >> &a[i];
    }
  }

  MPI_Scatter(a, n_bar, MPI_FLOAT, local_a, n_bar, MPI_FLOAT, 0, MPI_COMM_WORLD);

  if (my_rank == 0)
  {
    for (i = 0; i < n; i++)
    {
      std::cin >> &b[i];
    }
  }

  MPI_Scatter(b, n_bar, MPI_FLOAT, local_b, n_bar, MPI_FLOAT, 0, MPI_COMM_WORLD);

  for (i = 0; i < n_bar; i++)
  {
    local_dot = local_dot + local_a[i] * local_b[i];
  }

  MPI_Reduce(&local_dot, &dot, 1, MPI_FLOAT, MPI_SUM, 0, MPI_COMM_WORLD);

  if (my_rank == 0)
  {
    std::cout << "Dot product is " << dot << std::endl;
  }

  MPI_Finalize();
}