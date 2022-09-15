/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ MPI Matrix A_mxn and Vector B_n product                                 │
  └─────────────────────────────────────────────────────────────────────────┘
*/

/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │    [*][ ][ ]   [*]   [ ]                                                │
  │ p0 [*][ ][ ] x [ ] = [ ]                                                │
  │    [*][ ][ ]   [ ]   [ ]                                                │
  │                             P0    P1    P2    P0                        │
  │    [ ][*][ ]   [ ]   [ ]    [ ]   [ ]   [ ]   [*]                       │
  │ p1 [ ][*][ ] x [*] = [ ] => [ ] + [ ] + [ ] = [ ]                       │
  │    [ ][*][ ]   [ ]   [ ]    [ ]   [ ]   [ ]   [ ]                       │
  │                                                                         │
  │    [ ][ ][*]   [ ]   [ ]                                                │
  │ p2 [ ][ ][*] x [ ] = [ ]                                                │
  │    [ ][ ][*]   [*]   [ ]                                                │
  └─────────────────────────────────────────────────────────────────────────┘
 */

#include <mpi.h>
#include <math.h>
#include <iostream>

void main(int argc, char *argv[])
{
  int rank, p;
  int m, n; // m -> br kolona, n -> br vrsta

  m = 4;
  n = 3;

  int a[m][n], b[n], i, j;
  int lc[m], c[m], y[m], x[m], z;

  struct
  {
    int val;
    int rank;
  } min, gmin;

  MPI_Status status; // koristicemo ptp za slanje kolona zato nam treba status

  MPI_Init(&argc, &argv);
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);
  MPI_Comm_size(MPI_COMM_WORLD, &p);

  if (rank == 0) // inicijalizacija vrednosti

  {
    for (i = 0; i < m; i++)
    {
      for (j = 0; j < n; j++)
      {
        a[i][j] = i + j;
      }
    }

    for (j = 0; j < n; j++)
    {
      b[j] = 1;
    }
  }

  if (rank == 0) // slanje kolona procesima
  {
    for (i = 0; i < m; i++) // izvlaci se i-ta kolona
    {
      x[i] = a[i][0];
    }
    // zasto OVO IZNAD kad imamo i OVO ISPOD?????????????????

    for (j = 1; j < p; j++)
    {
      for (i = 0; i < m; i++)
      {
        y[i] = a[i][j]; // kolona sa indeksom j se izvlaci
        MPI_Send(y, m, MPI_INT, j, 0, MPI_COMM_WORLD);
      }
    }
  }
  else
  { // ne-nula procesi primaju podatke
    MPI_Recv(x, m, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
  }

  // saljemo svima po element iz b
  MPI_Scatter(&b[0], 1, MPI_INT, &z, 1, MPI_INT, 0, MPI_COMM_WORLD);

  for (i = 0; i < m; i++)
  {
    lc[i] = x[i] * z; // svaki el. kolone mnozi elementom iz b koji je dobio
  }

  min.val = INT_MAX;

  for (i = 0; i < m; i++)
  { // svaki proc. nalazi min el. iz vektora x
    if (x[i] < min.val)
    {
      min.val = x[i];
      min.rank = rank;
    }
  }

  // nad svim min-ovima nadji najmanji i lokaciju gde je i salji u gmin procesa 0
  MPI_Reduce(&min, &gmin, 1, MPI_2INT, MPI_MINLOC, 0, MPI_COMM_WORLD);

  // salji svima ko je gmin jer nam treba da gmin.rank-u saljemo posle sumu
  MPI_Bcast(&gmin, 1, MPI_2INT, 0, MPI_COMM_WORLD);

  // izvrsi MPI_SUM svih lokalnih vektora (lc) i salji procesu g.min.rank
  // u vektor (c) ide rezultat, sa m elemenata
  MPI_Reduce(lc, c, m, MPI_INT, MPI_SUM, g.min.rank, MPI_COMM_WORLD);

  if (rank == gmin.rank) // stampanje rezultata
  {
    for (i = 0; i < m; i++)
    {
      std::cout << "c[" + i + "] = " + c[i] << std::endl;
      std::cout << "gmin = " << gmin.val << std::endl;
    }
  }

  MPI_Finalize();
}