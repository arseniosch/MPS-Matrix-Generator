# MPS-to-Matrices
MPS (Mathematical Programming System) refers to a file format for presenting and archiving linear programming (LP) and mixed integer programming problems. In this program, .mps files are used as input in order to generate (output) the matrices A, b, c, constraints-matrices of an LP or Mixed Integer problem and visualize them as arrays. For example, a sample model written in MPS format is:

    NAME MY_LP
    ROWS
      L ROW1   
      E ROW2 
      G ROW3 
      N OBJ
    COLUMNS
      X1 ROW2 6. OBJ 2.
      X1 ROW3 -1.
      X2 ROW1 2. ROW3 -3.
      X2 ROW2 -1. OBJ -5.
      X3 ROW1 1. ROW3 7.
      X3 ROW2 2. OBJ 1.
      X4 ROW1 -3. OBJ -1.
      X4 ROW3 -2.
    RHS
      RHS1 ROW1 10. ROW3 9. 
      RHS1 ROW2 6.
    ENDATA

The example of (.mps) above will be used as input. The program will generate the following constraints, objective function and the operators in matrices :

<a href="https://www.codecogs.com/eqnedit.php?latex=z&space;=&space;2x{_1}&space;-&space;5x{_2}&space;&plus;&space;x{_3}&space;-&space;x{_4}&space;\\&space;\\&space;s.t.&space;\\&space;\\&space;0x_1&space;&plus;&space;2x_2&space;&plus;&space;x_3&space;-&space;3x_4&space;\leq&space;10&space;\\&space;6x_1&space;-&space;x_2&space;&plus;&space;2x_2&space;&plus;&space;0x_4&space;=&space;6&space;\\&space;9&space;\leq&space;-x_1&space;-&space;3x_2&space;&plus;&space;7x_3&space;-&space;2x_4&space;\\" target="_blank"><img src="https://latex.codecogs.com/gif.latex?z&space;=&space;2x{_1}&space;-&space;5x{_2}&space;&plus;&space;x{_3}&space;-&space;x{_4}&space;\\&space;\\&space;s.t.&space;\\&space;\\&space;0x_1&space;&plus;&space;2x_2&space;&plus;&space;x_3&space;-&space;3x_4&space;\leq&space;10&space;\\&space;6x_1&space;-&space;x_2&space;&plus;&space;2x_2&space;&plus;&space;0x_4&space;=&space;6&space;\\&space;9&space;\leq&space;-x_1&space;-&space;3x_2&space;&plus;&space;7x_3&space;-&space;2x_4&space;\\" title="z = 2x{_1} - 5x{_2} + x{_3} - x{_4} \\ \\ s.t. \\ \\ 0x_1 + 2x_2 + x_3 - 3x_4 \leq 10 \\ 6x_1 - x_2 + 2x_2 + 0x_4 = 6 \\ 9 \leq -x_1 - 3x_2 + 7x_3 - 2x_4 \\" /></a>

Output:

    MY_LP	 (MIN)

    BEGIN MinMax 
    MinMax = [-1]           // Type of problem to be solved : { max : 1 , min : -1}
    END MinMax 
 
 
    BEGIN Eqin 
    Eqin = [-1, 0, 1]      // Type of constraint : { -1 : <= , 0 : = , 1 : >= }
    END Eqin 
 
 
    BEGIN b 
    b = 
    [10.0, 0.0, 9.0]
    END b 
 

    BEGIN C 
    c = 
    [2.0, -5.0, 1.0, -1.0]
    END C 


    BEGIN A 
    A = 
    [ 0.0,  2.0, 1.0, -3.0]
    [ 6.0, -1.0, 2.0,  0.0]
    [-1.0, -3.0, 7.0, -2.0]
    END A 
