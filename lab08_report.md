# Lab Report
## Exercise 08: Eight Queens

_Autoren: Dennis Loska, Tony Dorfmeister, Bernhard Zaborniak 06.06.2017_

## 1. Our goal is to write a program to determine if eight queens can be placed on an 8 x 8 chess board without them threatening each other! Start by deciding how to represent a chess board with a data structure. Don’t worry about the colors of the board yet. Write a Chessboard class! What methods will you need?

## 2. Write a method for determining if the current board state contains a queen that is threatening another one. What is the complexity of your method in terms of N, the number of rows on the board? If your algorithm is worse than linear, you might want to look for something better.

## 3. We speak of “backtracking” when we go back to a previous state and try a different branch. Use some coins on a paper chess board to figure out what to do when you reach a state in which one queen is threatened by another. There are iterative, recursive, and random solutions to this problem. Try and implement a recursive solution!

## 4. Now implement a search routine that looks for a state in which the queens don’t threaten each other. If there is a solution, print it to System.out. If there is more than one solution, print them as well. What is the complexity of your algorithm? Does your program work for 10 queens on a 10 x 10 board? 13 on a 13 x 13 board?
