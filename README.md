# Fast_Matrix
Operate with matrices through the command line interface

Compile with ```gradle installDist```.
Define an environment variable 

```alias fm='/route_to_cloned_directory/FastMatrix/app/build/install/app/bin/app'```

Run as ```fm -option arg```. Options are

```
      -clean               Cleans all the variables defined.
      -defm=<nameM>        Define and initialize a square matrix of the given
                             name nameM.
      -defv=<nameV>        Define and initialize a vector of the given name
                             nameV.
      -det=<detM>          Compute the determinant of a stored matrix of a
                             given name.
      -dotm=<dotM>         Multiplies a matrix A by another matrix B element by
                             element. The notation is A.B--C
      -dotv=<dotV>         Multiplies a vector v by another vector w and
                             returns a scalar. The notation is v.w
      -editc=<editcM>      Edit a given column c of an already defined matrix
                             M. Notation c@M.
      -editr=<editrM>      Edit a given row r of an already defined matrix M.
                             Notation r@M.
      -findm=<fnameM>      Find and display a stored matrix of a given name.
      -findv=<fnameV>      Find and display a stored vector of a given name.
      -getcol=<getcol>     Gets column c of a given matrix M and stores it in a
                             vector. The notation is c@M--v
      -getdiag=<getDiag>   Gets diagonal of a given matrix M and stores it in a
                             vector. The notation is M--d
      -getrow=<getRow>     Gets row l of a given matrix M and stores it in a
                             vector. The notation is l@M--v
  -h, --help               Show this help message and exit.
      -inv=<inv>           Computes the inverse of a matrix M and stores it in
                             N. Notation M--N
      -prod=<prod>         Multiply a matrix times a vector of the same
                             dimension. Deliver in notation A*v, where A is the
                             matrix and v the vector. The * is mandatory.
      -prodm=<prodm>       Multiplies a matrix A by another matrix B of the
                             same dimensions ans returns another matrix. The
                             notation is A*B--C
      -scalarm=<scM>       Multiplies a matrix M by a scalar s in the notation
                             s*M and saves it in a variable B. The notation is
                             s*M--B
      -scalarv=<scV>       Multiplies a vector v by a scalar s in the notation
                             s*v and saves it in a variable w. The notation is
                             s*v--w
      -solve=<sol>         Solves a system of equations M*x=b. Deliver in
                             notation M*x=b, where M is the matrix, x
                             represents the vector of unknown variables and b
                             the vector of constants. The * and x are mandatory
      -tr=<tr>             Computes the transpose of a matrix M and stores it
                             in N. Notation M--N
      -trace=<trace>       Computes the trace of a given matrix M.
  -V, --version            Print version information and exit.
```
