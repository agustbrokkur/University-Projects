#Hönnunarskýrsla
##Síðannarverkefni
###Hópur: Míkró Tvíson
Andri Már Marteinsson  
Ágúst Helgi Árnason  
Berglind Dúna Sigurðardóttir  
Íris Björk Snorradóttir  
Magnús Björn Sigurðsson  
Olga Ýr Georgsdóttir  

###About TicTacToe
TicTacToe is a simple game between two players, X and O, who take turns marking the spaces in a 3x3 grid. The player that succeeds in getting three of their mark in a row wins the game, the row can be horizontal, vertical, or diagonal. The following example show a win by the first player, player X:

(Setja inn mynd þar sem X vinnur) 

###Initial design
A class diagram was made for the initial design we had in mind. This diagram gives a good overview over the classes for the game, their attributes, operations and methods. It shows the relationship between the classes and how decision was made about the branches to use. While programming the game the class diagram was used, but with that in mind that it could change. 

###Coding Rules
Coding rules were made that need to be followed while programming the assignment. Coding rules are useful, they reduce the likelihood of misunderstanding and make the code more readable. The following rules are the ones we decided on using and a few examples to explain them better:

#####Class names and function names
- Use upper case letters as word separators, lower case for the rest of a word

- First character in a name is upper case

- No underbars ('_')  
	
Example: 

	ThisIsMyClass();  
	ThisIsMyFunction();
	
#####Variable names
- Use upper case letters as word separators, lower case for the rest of the word

- First character in a name is lower case

- No underbars ('_')  
	
Example: 

	thisIsMyVariable

	
#####Constant names
- All uppercase  
	
Example: 

	public static final int SIZE = 3;

#####Spaces
- Spaces around operators
	
Example: 

	x = i + 1;
		
	
- No spaces at in the beginning and end of parantheses
	
Example: 

	Do: (i + 1)  
	Don't: ( i + 1 ) 	

- Spaces before and after brackets and parantheses
	
Example: 

	Do: x = 2 * (y + 1) - 3;  
	Don't: x = 2 *(y+1)-3; 	
	
#####Loops
- Put space before the loops parantheses

- Put brace associated with a control statement on the next line

- Indented to the same level as the control statement

- Statements within the braces are indented to the next level.

- Newline is after each loop
	
Example: 

	while (x == y)  
	{  
	   Something();  
	   SomethingElse();  
	} 	
	  
	SomethingOutsideTheloop(); 	
	
- else and else if is written on the next line

Example: 

	if (x != 2)   
	{   
	   Something();  
	}  
	else  
	{  
	   SomethingElse();  
	}; 

####  Ordered Lists


###Coding Method
#####Test Driven Development (TDD)
In this project Test Driven Development is used. It is a software development process that relies on the repetition of a very short development cycle. 

#####Brances
Branches makes it easier for members of the group to work on seperate parts of the project and keeps from getting merge conflicts.

#####Markdown
Markdown syntax was used for documentation. 
