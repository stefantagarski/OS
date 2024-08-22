#!/bin/bash

if [ $# -eq 0 ]
then
	echo "Insert name of file!"
fi

if [ $# -gt 1 ]
then
	echo "Too many arguments"
fi

output_file="$1"

if [ -e "$output_file" ]
then
	rm "$output_file"
fi


for file in *.txt
do
	if [ -r "$file" ]&&[ ! -w "$file" ]&&[ -x "$file" ]
	then
		cat "$file" >> "$output_file"
		echo >> "$output_file"
	fi
done

#Write a command procedure that will receive one input argument representing the name of a file. The procedure should populate the specified file with the contents of all .txt files in the same directory for which the user has read permissions (no write or execute permissions). So that the content between two files will be separated by a new line.

#You need to check if an input argument is provided. If not, print Insert name of file!
#If more than one input argument is provided, print Too many input arguments!
#In all other situations, save the contents of the filtered files in the specified file.

#example bash script.sh total.txt
