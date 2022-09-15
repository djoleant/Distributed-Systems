/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ Priprema ispita iz predmeta Distribuirani sistemi                       │
  │ Ispitni rok: Junski 2021                                                │
  │ 21.06.2021                                                              │
  │                                                                         │
  │ https://blanketi.sicef.info/elfak/pregled/2765                          │
  │                                                                         │
  │ Djordje Antic - Sep 12, 2022                                            │
  └─────────────────────────────────────────────────────────────────────────┘
*/

/*
  ┌─────────────────────────────────────────────────────────────────────────────┐
  │ Mnozenje matrice A_kxm i vektora B_m, rezultat je vektor c                  │
  │                                                                             │
  │ Pronaci i prikazati maks vr elemenata u matrici A                           │
  │ Pronaci sumu elemenata svake vrste matrice A                                │
  │                                                                             │
  │ Master proces salje svakom procesu po l kolona matrice a                    │
  │   (l je zadata konstanta, m je deljivo sa l)                                │
  │ i salje po l elemenata vektora b                                            │
  │                                                                             │
  │ Svi elementi kolona matrice A se salju odjednom                             │
  │ Svi procesi ucestvuju u izracunavanjima potrebim za                         │
  │ generisanje rezultata programa                                              │
  │                                                                             │
  │ Svi rezultati se prikazuju u procesu koji sadrzi maks vr                    │
  │ elemenata u matrici A, nakon raspodele kolona po procesima                  │
  │                       ?????????????????????????????????????                 │
  │                                                                             │
  └─────────────────────────────────────────────────────────────────────────────┘
 */

#include <iostream>
#include <string.h>
#include <mpi.h>

void main(int argc, char *argv[])
{
  struct
  {
    int val;
    int rank;
  } l_max, max; // local and global max

  int rank, size;

  int k, m, l;
  int a[k][m], b[m], local_a[k][m], local_b[m];
  int y[m]; // pomocna
  int x[m]; // pomocna

  int c[m];       // PROVERI dimenziju ovog vektora
  int local_c[l]; // proveri???????
  int sum[m];
  int local_sum[k]; // proveri;

  // hardkodirano:
  k = 10;
  m = 4;
  l = m / size;

  MPI_Init(&argc, &argv);
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);
  MPI_Comm_size(MPI_COMM_WORLD, &size);

  if (rank == 0)
  {
    for (int i = 0; i < k; i++) // inicijalizacija matrice A
    {
      for (int j = 0; j < k; j++)
      {
        a[i][j] = i + j;
      }
    }

    for (int i = 0; i < m; i++) // inicijalizacija vektora B
    {
      b[i] = 1;
    }

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

  MPI_Scatter(b, l, MPI_INT, local_b, l, MPI_INT, 0, MPI_COMM_WORLD);

  for (int i = 0; i < k; i++)
  {
    local_sum[i] = 0;
  }
  for (int i = 0; i < l; i++)
  {
    local_c[i] = 0;
  }

  for (int i = 0; i < l; i++)
  {
    for (int j = 0; j < k; j++)
    {
      local_c[i] += local_a[i + j * l] * local_b[j];
      local_sum[j] += local_a[i + j * l];

      if (local_a[i + j * l] > local_max.value)
      {
        local_max.value = local_a[i + j * l];
      }
    }
  }

  MPI_Reduce(&local_max, &max, 1, MPI_2INT, MPI_MAXLOC, 0, MPI_COMM_WORLD);
  MPI_Bcast(&max, 1, MPI_2INT, 0, MPI_COMM_WORLD);

  MPI_Gather(local_c, l, MPI_INT, c, l, MPI_INT, max.rank, MPI_COMM_WORLD);
  MPI_Reduce(local_sum, sum, k, MPI_INT, MPI_SUM, max.rank, MPI_COMM_WORLD);

  if (rank == max.rank)
  {
    // stampanje...
  }

  MPI_Finalize();
  
}