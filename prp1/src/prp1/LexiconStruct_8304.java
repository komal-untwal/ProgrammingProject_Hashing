package prp1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LexiconStruct_8304 {
	
	static int[] T;
	static int[] doubleT;
	static char[] A;
	static char[] doubleA;
	static int Arrayptr=0;
	
	
public static void main(String[] args) throws IOException
 {
	    
	 	
		//validation of command line argument
		if(args.length < 1) {
		    System.out.println("Error, usage: java ClassName inputfile");
			System.exit(1);
		    }
		else
		{
			for(String file : args) 
				batch(file);
		}
		   				 
	
 }
 
 public static void batch(String filename)
 {
	 String cmdList = "10:Insert,11:Delete,12:Search,13:Print,14:Create,15:Comments";
	 int filelength = 0;
	 String[] cmd= new String[2];
	 boolean htSizeflag = false;
	 boolean tableExist = false;
	 
	 Scanner reader;
	try {
		reader = new Scanner(new FileInputStream(filename));
		
		while (reader.hasNextLine()) {    
			 filelength++;
			 reader.nextLine();
		 }

		 
		 String[] commands = new String[filelength];
		 String[] params = new String[filelength];
		 int cmdCounter=0, paramCounter=0;
		 
		 
		 while (reader.hasNextLine()) {    
			 String row= reader.nextLine();
			 cmd= row.split(" ",2);
			 
			 if(cmd[0].equals("14"))
			 {
				 htSizeflag = true;
				 int N=0;
				 try {
					 N=Integer.parseInt(cmd[1]);
				 }catch(Exception e)
				 {
					 htSizeflag = false;
					 System.out.println(e);
					 System.exit(0);
				 }
				 
				 if(htSizeflag)
				 {
					 if(N>0)
					 {
						 commands[cmdCounter++]=cmd[0];
						 params[paramCounter++]=cmd[1];
					 }
					 else
						 System.out.println("Enter valid Hash table size!");
				 }
				 
				 
			 }
			 
			 else if((cmd[0].equals("10")) || (cmd[0].equals("11")) || (cmd[0].equals("12")) || (cmd[0].equals("15")))
			 {
				 commands[cmdCounter++]=cmd[0];
				 params[paramCounter++]=cmd[1];
			 }
			 
			 else if( cmd[0].equals("13"))
			 {
				 commands[cmdCounter++]=cmd[0];
				 params[paramCounter++]= " ";
			 }
			 else
				 System.out.println("Invalid command mention in file! Refer valid command list :" + cmdList);
		 }
		 
		 for(int i= 0 ; i < commands.length; i++){
				
				if(commands[i].equals("14")){
					//tableExist = true;
					
					for (int j = i; j < commands.length; j++) {
						if (commands[j].equals("14") && tableExist == false) {
							tableExist = true; 
							create(params[j]); 
						}
						else if (commands[j].equals("10")) {
							insert(params[j]); 
						} else if (commands[j].equals("11")) {
							delete(params[j]);
						} else if (commands[j].equals("12")) {
							search(params[j]);
						} else if (commands[j].equals("13")) {
							print();
						} else if (commands[j].equals("15")) {
							System.out.println("Comments: " + params[j]);
						} else if (tableExist == true) {
							System.out.println("Table is already created!");
						}else {
							System.out.println("This is not a valid Command! Refer valid command list :" + cmdList);
						}
					}
					break;
				
				}else if(commands[i] == null){
					System.out.println("File contains null command! Refer valid command list :" + cmdList);
					
				}else {
					System.out.println("Table Does not exist! Fisrt Create a Table to store lexicon Structure");
				}
			}
		
	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
	}
	 
	 
	 
 }
 
// ******** Create ************
 
 //creates a Hash table T & array A of size 15 * (table_size)
 public static void create(String size)
 {
	 int table_size = Integer.parseInt(size);
	 T = new int[table_size];
	 A = new char[15 * table_size];
	 
	 for(int i=0;i<table_size; i++)
	 	{ T[i] = -1;}
	 
	 for(int i=0;i<A.length;i++)
	 	{ A[i] = ' '; }
	 
 }
 
 // ** reSize A **
 
public static void doubleASize()
{  
	 int tempindex=0;
	 doubleA = new char[2 * A.length];
	 for(int i=0; i<A.length; i++)
	 {
		 doubleA[i] = A[i];
		 tempindex++;
	 }
	 for(int j = tempindex;j<doubleA.length;j++)
	 {
		 doubleA[j]=' ';
	 }
	 
	 A = doubleA;
}


//** resize both array **

public static void doubleArraySize()
{
	int sum = 0;
	int currentindex=0;
	doubleT = new int[2*T.length];
	for(int j=0; j<doubleT.length;j++)
		{doubleT[j]=-1;}
	doubleASize();
	
	for(int i=0;i<A.length;i++)
	{
		if((int)A[i] != 42)
		{
			if((int)A[i] == 0)
			{
				if(sum != 0) {
				  reInsert(sum,currentindex,doubleT.length);				  
				}
				currentindex= i+1;
				sum=0;
				continue;
			}
			sum= sum + (int)A[i];
		}
		if((int) A[i] == 32)
		  break;
	}
	T= doubleT;
}

// ** reInsert data in T to doubleT
public static void reInsert(int stringValSum,int currentPointer, int doubleTSize)
{
 int hashfunc = 0;
 int rehashfunc = 0;
 
 hashfunc = (stringValSum -2)% doubleTSize;
 for(int i=0;i<doubleTSize;i++)
 {
	 rehashfunc = (hashfunc + (i*i)) % doubleTSize;
	 if(doubleT[rehashfunc]== -1)
	 {
		 doubleT[rehashfunc] = currentPointer;
		 break;
	 }
 }
		
}

//************ Full *******************
public static boolean isFull(String word)
{
	int sum = 0,hashfunc = 0,rehashfunc = 0;
	  for(int i=0;i<word.length();i++)
	  {
		  sum = sum + ((int) word.charAt(i));
	  }
	  
	  hashfunc=(sum-2)%T.length;
	  
	  for(int i=0;i<T.length;i++)
	  {
	 	 rehashfunc = (hashfunc + (i*i)) % T.length;
	 	 if(T[rehashfunc] == -1)
	 	 {
	 		return false;
	 	 }
	  }
	  return true;	
}

 //insert words in Array A
public static void insertWords(String word)
{
  if(isFull(word)) doubleArraySize();
  else
  {
	  for (int i = 0; i < A.length; i++) {
		//32-- ASCII value for blank space	
		  if ((int) A[i] == 32) 
			{ 
				Arrayptr = i;
				break;
			}
		}
		int expectedLen = Arrayptr + word.length();
		
		if (expectedLen >= A.length) { 
			doubleASize();
		}
		
		for (int i = Arrayptr,k= 0; i < expectedLen; i++) {
			if (i >= A.length) {
				doubleASize();
			}
			A[i] = word.charAt(k);
			k++;
		}
		
		//null separated word
		A[expectedLen] = '\0';
  }
  
}

// ************ Insert ******************
 //inserts data in Array A & its reference pointer in hashTable T 
 public static void insert(String data)
 {
	 insertWords(data);
	 
	 //insert reference in  hash table T
	 
	 int sum = 0,hashfunc = 0,rehashfunc = 0;
	  for(int i=0;i< data.length();i++)
	  {
		  sum = sum + ((int) data.charAt(i));
	  }
	  
	  hashfunc=(sum-2)%T.length;
	  
	  for(int i=0;i<T.length;i++)
	  {
	 	 rehashfunc = (hashfunc + (i*i)) % T.length;
	 	 if(T[rehashfunc] == -1)
	 	 {
	 		T[rehashfunc] = Arrayptr;
	 		break;
	 	 }
	  }
	  
 }
 
 //**********Search ***********
 public static void search(String word)
 {
	 if(word.equals(null))
		 System.out.println("Enter valid word to search");
	 else {
	 int sum = 0,hashfunc = 0,rehashfunc = 0;
	 int searchslot = -2;
	 
	  for(int i=0;i< word.length();i++)
	  {
		  sum = sum + ((int) word.charAt(i));
	  }
	  
	  hashfunc=(sum-2)%T.length;
	  
	  for(int i=0;i<T.length;i++)
	  {
		 int counter=0; 
	 	 rehashfunc = (hashfunc + (i*i)) % T.length;
	 	 if(rehashfunc<0) break; //?
	 	 if(T[rehashfunc] != -1)
	 	 {
	 		 for(int j=0;j<word.length();j++)
	 		 {
	 			 if(word.charAt(j) == A[j + T[rehashfunc]])
	 			 {
	 				 counter++;
	 			 }
	 			 else
	 				 break;
	 		 }
	 		 
	 		 if(counter == word.length())
	 		 {
	 			searchslot = T[rehashfunc];
	 			break;
	 		 }

	 	 }
	  }
	  if(searchslot == -2)
		  System.out.println(word + "not found");
	  else
		  System.out.println(word + "found at slot : "+ rehashfunc);
 }}
 
 //***********print *************
 public static void print()
 {
	 System.out.println("T: \t\t\t\t A: ");
	 
	 for(int i=0;i<A.length;i++) 
	 {
		 if((int)A[i] == 32)
			 break;
		 if((int)A[i] == 32)
			 System.out.print("\\");
		 else
			 System.out.print(A[i]);

	 }
	 
	 System.out.println();
	 
	 for(int i=0;i<T.length;i++)
	 {
		 if(T[i] != -1)
		 {
			 System.out.println(i + " : " + T[i]);
		 }
		 else
		 {
			 System.out.println(i + " : ");
		 }
		 
	 }
	 
 }
 
 //**********delete************8
 public static void delete(String word)
 {
	 int sum = 0,hashfunc = 0,rehashfunc = 0;
	 int searchslot = -2;
	 
	  for(int i=0;i< word.length();i++)
	  {
		  sum = sum + ((int) word.charAt(i));
	  }
	  
	  hashfunc=(sum-2)%T.length;
	  
	  for(int i=0;i<T.length;i++)
	  {
		 int counter=0; 
	 	 rehashfunc = (hashfunc + (i*i)) % T.length;
	 	 if(rehashfunc<0) break; //?
	 	 if(T[rehashfunc] != -1)
	 	 {
	 		 for(int j=0;j<word.length();j++)
	 		 {
	 			 if(word.charAt(j) == A[j + T[rehashfunc]])
	 			 {
	 				 counter++;
	 			 }
	 			 else
	 				 break;
	 		 }
	 		 
	 		 if(counter == word.length())
	 		 {
	 			searchslot = T[rehashfunc];
	 			break;
	 		 }

	 	 }
	  }
	  if(searchslot == -2)
		  System.out.println(word + "not found");
	  else
	  {
		  //delete reference from hash table
		  T[rehashfunc]=-1;
		  //replacing chars to be deleted with *
		  while((int)A[searchslot] != 0)
		  {
			  A[searchslot]= '*';
			  searchslot++;
		  }
		  System.out.println(word + "deleted from slot : "+ rehashfunc);
		  
	  }
	  
		 
 }
 
 
}
