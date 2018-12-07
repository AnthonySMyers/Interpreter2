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
LL_Table.java
Node.java
Parser.java
Type.java
Iterpreter.java
SCTNode.java
symbolTableEntry.java
README.txt

External Requirements
1. Java must be installed.
2. Must have a properly formatted .txt file with A6 Language code for tokenization

Setup and Installation
N/A

Sample Invocation and Results


Features
This program takes A6 language code in .txt files and executes the code contained in the input file.

Bugs
There is only one bug in the program. If the .txt file does not have the proper spacing between token values, 
the program will not run correctly. 
As long as the .txt file is properly formatted, the program will run with no errors.
