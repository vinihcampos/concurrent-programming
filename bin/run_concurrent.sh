#!/bin/bash
for i in 4 8 16 32 64 128 256 512 1024 2048
do
	for k in 2 3 4
	do	
		for j in {1..20}
		do
			./multimat_concorrente $i $k
		done
	done
done
