#!/bin/bash
#for i in 4 8 16 32 64 128 256 512 1024 2048
#do
#	for k in 5 10 20 30 40 50 60 70 80 90 100 150 200 250 300 350 400 450 500
#	do	
#		for j in {1..20}
#		do
#			./multimat_concorrente $i $k
#		done
#	done
#done

for i in 2048
do
	for j in 20 30 40 50 60 70 80 90 100 150 200 250 300 350 400 450 500
	do
		for k in {1..20}
       	do
           ./multimat_concorrente $i $j
       	done
	done
done
