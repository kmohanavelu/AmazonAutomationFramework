#!/bin/bash
count=0
lsof -i tcp:4723 | awk '{print $2}' | while read -r line ; do
  if [ $count == 0 ]
  then count=count+1
  else kill -9 $line
  fi
done