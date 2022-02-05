# ProgrammingProject_Hashing
Implemented a Lexicon structure to store arbitrarily long strings of ASCII chars using Hashing.

Problem Statement : 
•	Lexicon L uses a Hash Table T structure along with an Array A of NULL separated words. 
•	Table T will be organized as a hash-table using collision-resolution by open-addressing.
•	Using quadratic probing for h(k, i) and the choice of the quadratic function is i^2 , so that [h(k,i) = (h0(k) +i^ 2) mod N]. 
•	The keys that will be hashed are English words.
•	The function h’(k) is the sum of the ASCII/Unicode values of the characters minus 2 mod N, where N is the number of slots of T. 
•	The second table, array A is a character array and will store the words maintained in T separated by NULL values \0. 
•	An insertion operation affects T and A. A word w is hashed, an available slot in T is computed and let that slot be ‘t’. In T[t] we store an index to table A. This index is the first location that stores the first character of w. The ending location is the \0 following w in A. New words are appended in A.
•	A deletion will modify T as needed but will not erase w from A.
•	Function Empty, Full, Batch to check for an empty or full table/array and a mechanism to perform multiple operations in batch form.
•	Function Print prints T and its contents i.e. index values to A. In addition it prints the contents of A. It does not print the words of A for deleted words, instead prints stars for every character of a deleted word. 
•	Function Create creates T with N slots, and A with 15N chars and initializes A to spaces.

Input file Contents & scenario:

•	Input file should contain a sequence of commands to execute the program to implement lexicon structure to store words using hash table, if the file is blank, it will generate error as "Blank or Invalid command mention in file! Refer valid command list :10:Insert,11:Delete,12:Search,13:Print,14:Create,15:Comments" & program will terminate.
•	Any other command number or a black line in a file will give us an error:  "Blank or Invalid command mention in file! Refer valid command list" & program exits.
•	Commands 10, 11 & 12 requires 1 argument i.e a word to insert, delete & search respectively. If these arguments are not given it will give an error as "Argument field is empty for command 10 , 11, 12! Enter valid argument!" & program exits.
•	Command 14 requires 1 argument which specifies size of HashTable, if not given or kept blank program will generate an error message as "Table size is not specified!" & then program terminates. Or for a given a non-integer size value, program will generate an error message as "NumberFormatException occurs, Enter Valid Integer Number for Hash Table size" & then program terminates.
•	If This size given as less than or equal to 0, program will generate error as "Error message: Table size should be greater than 0. Enter valid Hash table size!" & program terminates.
•	Initially, command 14 with valid HashTable size as its argument, should be executed before executing any other commands. If any other command is executing before even table is created, program will throw error as : "Table Does not exist! First Create a Table to store lexicon Structure".
•	Once table is already created, If we call command 14 again, program will throw error as "Table is already created!" & it will continue execution on existing table.
•	Command 15, simply do nothing, comments are ignored.


----------------------------------------------------------

Test Scenarios: 
-------
1) TC:

14 11
10 alex
10 tom
10 jerry
15 ready-to-print
13
12 alex
12 tom
12 jerry
12 mary
11 tom
13
10 tom
12 tom
13

Output:


T: 				 A: alex\tom\jerry\
0 : 
1 : 
2 : 
3 : 
4 : 5
5 : 9
6 : 0
7 : 
8 : 
9 : 
10 : 

alex found at slot : 6
tom found at slot : 4
jerry found at slot : 5
mary not found
tom deleted from slot : 4

T: 				 A: alex\***\jerry\
0 : 
1 : 
2 : 
3 : 
4 : 
5 : 9
6 : 0
7 : 
8 : 
9 : 
10 : 

tom found at slot : 4

T: 				 A: alex\***\jerry\tom\
0 : 
1 : 
2 : 
3 : 
4 : 15
5 : 9
6 : 0
7 : 
8 : 
9 : 
10 : 

---------
