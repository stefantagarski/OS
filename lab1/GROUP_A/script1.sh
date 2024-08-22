#!/bin/bash

if [ $# -lt 3 ]
then
	echo "Too little arguments"
fi


sum=0

for ((f=1;f<=3;f++))
do
	sum=$((sum + ${!f}))
done

average=$((sum*60/3))

echo "Average execution time: $average"

echo "Count of executions: $#"

if [ $# -gt 5 ]
then
	echo "The testing is done"
else
	echo "More testing is needed"
fi

##TEXT
#Write a command procedure that will receive an arbitrary number of input arguments representing execution times of a program expressed in minutes.

#Example - bash script.sh 5 7 15 8 22 6 

#The procedure should calculate and print the average duration of the program according to the first three executions (5, 7, 15 in the example) represented in seconds, as well as the number of program executions (the number of measurements made/entered).

#If the number of input arguments is grater than or equal to 5, it should print 'The testing is done', while if it is less than 5, it should print 'More testing is needed'.

#The output of the example should be as follows:
#Average execution time: 540
#Count of executions: 6
#The testing is done
