# Sodoku-Project
This is a finished Sodoku board generator for the java console 
		This program is designed to create fully solved sudoku boards for a java console.         
    It uses a 4D-int array in order to keep track of each 3x3 grid of numbers within the 3x3 sudoku board grid. Then for the display a ArrayList of Strings is used to create each 3x3 display box. The reason for making it an ArrayList was to be able to concatenate the rows of different boxes to create one large row to be printed one at a time. Each 2D array within the greater main 4D array is turned into its own ArrayList, to be able to be printed and concatenated to the other 2D arrays within the 4D array. An ArrayList of integers is also used in adding numbers to the greater 4D array. In the getValidNum method an ArrayList of digits 1-9 is created then for each given square the method checks the numbers in that same row, column and square and removes the number from the ArrayList if it finds it in one of those squares, then at the very end it returns one of the remaining numbers in the ArrayList (it is randomly chosen) to the fill method in order to determine the number that should be given each square. However if there are no digits left in the ArrayList then the method returns -1 in order to indicate that it is impossible to fill the board and the fill method restarts that square and starts again.  This continues to happen 5 times then it decides that changing the current square would be too time consuming and thus decides to change surrounding squares randomly. If this too doesn’t work then the process is aborted and the entire fill process repeats from the beginning until there are no -1s in the 4D array. Then the 4D array is formatted into ArrayList Strings in the Wrap method and added to other ArrayList Strings in the add function to then finally be printed.  The Change function recursively calls itself until it is able to find a proper change to the surrounding squares that results in a valid board. However if this recursion goes on for too long the process is aborted.  

The main Method:
	The main method is designed to create the original 3x3x3x3 4D ( I like to think of it as a 2D array of a 2D array) array named c (the 4D array is always referred to by a letter as to signify that it is the main array it is named c here because c is the 3rd letter of the alphabet and this is array is a 4 dimensional perfect tesseract with sides being 3). c is continuously filled until check returns 0. When this occurs that means the array is valid and thus is able to be prepared for printing. Then for each out row of the 4D array the 2D arrays of each of the columns of the row are wrapped and added together using the respective methods to create a mega String ArrayList for the row then each row of the mega ArrayList is printed then the main method moves onto the next outer row. Finally with the start time taken at the very beginning of the method the end is now taken after the array is printed then the difference between the two is found and printed as the total time taken in seconds. 


The wrap Method:
	This creates the display for each individual 2D array within c to be able to print it and turns that display into a String ArrayList.

The add Method:
	This concatenates two display ArrayLists created by the Wrap method and turns them into a new String ArrayList.

The fill Method:
	This method tries to fill each position of the 4D array using the getValidNum method but if this doesn't work sequentially the change method is called to change surrounding 2D array’s numbers in order to create a valid sudoku board. However if this too fails it just returns anyway despite being false.

The getValidNum Method:
	This finds a valid number for the position within a 4D array, following the Sudoku rules and returns a random valid number. If there are no valid numbers -1 is returned. 

The change Method:
Attempts to change a random 2D array within the greater 4D array adjacent to the input 2D array.  If this fails then the change method is called again for the current position to change arrays near by itself. If this continues to fail it simply returns a false array. 
The clear Method:
	This sets every number within a 2D array of the 4D array to -1.

The check Method:
	Returns 0 if the 4D array follows Sudoku rules, returns -1 otherwise. 
	

The check method is the main way that I ensure that each board is correct as it will only print if it returns a positive output.  However I individually checked the output of the first 10 Sudoku boards to ensure that they are correct. It is usually obvious as the Array will contain -1 if it is false. This is what the check method looks for.  

The main challenge I faced when making this project was in being able to create the valid array. The fill method would continuously spit out incorrect arrays every 3 or so generations and so to counteract this I added the check method to ensure that the program would never output an incorrect board. This does cost time as the entire process needs to be restarted but since the error only occurs every so often not all that much time is wasted.  
