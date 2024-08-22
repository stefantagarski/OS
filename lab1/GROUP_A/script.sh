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


