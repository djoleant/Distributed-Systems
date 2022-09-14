/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ MPI Matrix A_nxn and Vector B_n product                                 │
  └─────────────────────────────────────────────────────────────────────────┘
*/

/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │    [*][*][*]   [*]   [*]                                                │
  │ p0 [ ][ ][ ] x [*] = [ ]                                                │
  │    [ ][ ][ ]   [*]   [ ]                                                │
  │                                                                         │
  │    [ ][ ][ ]   [*]   [ ]    [*]                                         │
  │ p1 [*][*][*] x [*] = [*] => [*] p0                                      │
  │    [ ][ ][ ]   [*]   [ ]    [*]                                         │
  │                                                                         │
  │    [ ][ ][ ]   [*]   [ ]                                                │
  │ p2 [ ][ ][ ] x [*] = [ ]                                                │
  │    [*][*][*]   [*]   [*]                                                │
  └─────────────────────────────────────────────────────────────────────────┘
 */

#include <mpi.h>
#include <math.h>
#include <iostream>

void main(int argc, char *argv[])
{
  // local_a[n] => jedan red iz a[n][n]
  // local_c => deo vektora c[n]
  int rank, p, i, j, a[n][n], b[n], local_a[n], local_c, c[n];

  MPI_Init(&argc, &argv);
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);
  MPI_Comm_size(MPI_COMM_WORLD, &p);

  if (rank == 0) // inicijalizacija elemenata matrice i vektora
  {
    for (i = 0; i < n; i++)
    {
      for (j = 0; j < n; j++)
      {
        a[i][j] = i + j;
      }
    }
    for (i = 0; i < n; i++)
    {
      b[i] = 1;
    }
  }

  MPI_Scatter(&a[0][0], n, MPI_INT, local_a, n, MPI_INT, 0, MPI_COMM_WORLD);

  MPI_Bcast(b, n, MPI_INT, 0, MPI_COMM_WORLD;

  local_c=0;
  for(int i =0;i<n;i++){
    local_c += local_a[i] * b[i];
  }

  MPI_Gather(&local_c, 1, MPI_INT,&c[0], 1, MPI_INT, 0, MPI_COMM_WORLD);


  if(rank==0) //stampanje rezultata
  {
    for (i = 0; i < n; i++)
    {
      std::cout << "[" << c[i] << "]" << std::endl;
    }
  }
}