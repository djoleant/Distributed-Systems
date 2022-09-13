/*
  ┌─────────────────────────────────────────────────────────────────────────┐
  │ MPI sum example                                                         │
  └─────────────────────────────────────────────────────────────────────────┘
*/

/*
   ┌─────┐┌─────┐  ┌─────┐┌─────┐  ┌─────┐┌─────┐  ┌─────┐┌─────┐
  0│  5  ││  1  │ 1│  2  ││  3  │ 2│  7  ││  8  │ 3│  4  ││  2  │
   └─────┘└─────┘  └─────┘└─────┘  └─────┘└─────┘  └─────┘└─────┘
                   ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐
                   │           MPI_SUM           │
                   └─────┘ └─────┘ └─────┘ └─────┘
                            ┌─────┐┌─────┐
         RESULT            0│   18││   14│
                            └─────┘└─────┘
*/

#include <mpi.h>
#include <iostream>

void main(int argc, char *argv[])
{
  int size, rank;
  MPI_Status status;

  MPI_Init(&argc, &argv);
  MPI_Comm_rank(MPI_COMM_WORLD, &myrank);
  MPI_Comm_size(MPI_COMM_WORLD, &size);

  int src, result;
  src = rank + 1;

  // SCAN vraca parcijalne vrednosti
  // U procesu [i] je vrednost sume od 0 do i
  // Svi procesi generisu rezultat
  MPI_Scan(&src, &result, 1, MPI_INT, MPI_SUM, MPI_COMM_WORLD);
  std::cout << "Process " << rank << " has result " << result << std::endl;

  MPI_Finalize();
}