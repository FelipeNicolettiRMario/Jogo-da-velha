package edu.fatec.sjc.lp2.jogo;

import java.util.Scanner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor

public class Jogo {
	private Posicionamento[][] board;
	
	public Jogo() {
		setBoard(new Posicionamento[][] {{Posicionamento.VAZIO,Posicionamento.VAZIO,Posicionamento.VAZIO},
				{Posicionamento.VAZIO,Posicionamento.VAZIO,Posicionamento.VAZIO},
				{Posicionamento.VAZIO,Posicionamento.VAZIO,Posicionamento.VAZIO},});
	}
	
	public String checkWin() {
		for(int i=0;i<=board.length;i++) {
			if(board[i][0] != Posicionamento.VAZIO && board[i][2] == board[i][1] && board[i][2] == board[i][0]) {
				return board[i][0].name();
			}
			
			if(board[0][i] != Posicionamento.VAZIO && board[2][i] == board[1][i] && board[2][i] == board[0][i]) {
				return board[0][i].name();
			}
				
		}
		
		if(board[0][0]!=Posicionamento.VAZIO && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return board[0][0].name();
		}
		
		if(board[0][2]!=Posicionamento.VAZIO && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return board[0][2].name();
		}
		return null;
	}
	
	public boolean checkPositions(int x, int y) {
		if(x-1<0 || y-1<0) {
			return false;
		}
		if(x-1>2 || y-1>2) {
			return false;
		}
		return true;
	}
	
	public boolean setPosition(int x,int y,Posicionamento valor) {
		if(checkPositions(x,y)) {
			if(board[x-1][y-1]!= Posicionamento.VAZIO) {
				return false;
			}
			else {
				board[x-1][y-1] = valor;
				return true;
			}
		}
		return false;
	}
	
	public void printBoard() {
		for(int i=0;i<=board.length;i++) {
			for(int j=0;j<=board[i].length;j++) {
				System.out.println(board[i][j].name() + '|');
			}
			System.out.println("---------------------------------");
		}
	}
	
	public void play() {
		int contador = 0;
		Scanner read = new Scanner(System.in);
		while(checkWin() == null) {
			System.out.println("Digite o eixo x e y separado por virgula:");
			String values = read.nextLine();
			String[] axes = values.split(",");
			
			if(contador%2 == 0) {
				if(setPosition(Integer.parseInt(axes[0]),Integer.parseInt(axes[1]),Posicionamento.JOGADOR1)) {
					printBoard();
					checkWin();
					contador++;
				}
			}
			else {
				if(setPosition(Integer.parseInt(axes[0]),Integer.parseInt(axes[1]),Posicionamento.JOGADOR2)) {
					printBoard();
					checkWin();
					contador++;
				}
			}
		}
	}
	
	
}
