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
