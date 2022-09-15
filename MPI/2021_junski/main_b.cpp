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