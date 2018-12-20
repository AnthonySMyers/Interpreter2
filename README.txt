# Interpreter2
CECS 444 Sec 01 5559
Project 3 - AST Interpreter for A6 Grammar
Team TBD
Minhkhoa Vu	minhkhoavu954@gmail.com
Anthony Myers	anthonysmyers@yahoo.com
Juan Espinoza	jaespin30@yahoo.com
Miguel Castorena miguel.angel.castorena@gmail.com

Introduction
This program takes .txt file that contains A6 Language code and produces a AST for the program by parsing the input file. This AST is then
treewalked to produce a Scope Tree(SCT). After the SCT is created, another AST treewalk is performed to execute the AST statements. The
output of this program is the execution of the A6 Language code file.

Algorithm
N/A

Contents
A6_GrammarRules.java
AllNodes.java
Interpreter.java
LL_Table.java
MainApplication.java
MoreTesting.txt
Node.java
OpPair.java
Parser.java
README.txt
ScopeTreeNode.java
Type.java
test1.txt
test2.txt
testCode.txt

External Requirements
1. Java must be installed.
2. Must have a properly formatted .txt file with A6 Language code for tokenization

Setup and Installation
N/A

Sample Invocation and Results
Enter the path of the file: C:\Users\mcast\eclipse-workspace\interp\src\testCode.txt
   Number 1:
  2
   Number 2:
  3
   13         //Final output

testCode.txt Contents:
  prog main {
	print ( " Number 1: " ) ;
	a = input ( int ) ;
	print ( " Number 2: " ) ;
	b = input ( int ) ;
	c = a * a + b * b ;
	print ( c ) ;
  } $
  
Enter the path of the file: C:\Users\mcast\eclipse-workspace\interp\src\test1.txt
  Input radius>
  3.26
  Circumf= 20.4728      //Final output
 
test1.txt Contents:
  prog main { // Find the circumference of a circle.
  pi = 3.14 ;
  print ( " Input radius> " ) ;
  rx = input ( float ) ;
  circum = 2 * pi * rx ;
  print ( " Circumf= " , circum ) ;
  } $
  
Enter the path of the file: C:\Users\mcast\eclipse-workspace\interp\src\test2.txt
 Input legs>
  3
  4
 Hypotenuse= 5          //Final output

test2.txt Contents:
  prog main { // Find the hypotenuse of a right triangle.
    print ( " Input legs> " ) ;
    a = input ( int ) ;
    b = input ( int ) ;
    print ( " Hypotenuse= " , ( a * a + b * b ) ^ 0.5 ) ;
  } $

Features
This program takes A6 language code in .txt files and executes the code contained in the input file.

Bugs
There is only one bug in the program. If the .txt file does not have the proper spacing between token values, 
the program will not run correctly. 
As long as the .txt file is properly formatted, the program will run with no errors.
