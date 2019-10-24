package checkers.domain;

import checkers.controller.GameCenter;
import checkers.sandbox.SquareState;

public class Model {
	
	public SquareState state[][];
	private GameCenter callBack;

	public void baslat(){
		SquareState ilk[][] = {
				{SquareState.WHITE,SquareState.BLANK,SquareState.WHITE,SquareState.BLANK,SquareState.WHITE,SquareState.BLANK,SquareState.WHITE,SquareState.BLANK},
				{SquareState.BLANK,SquareState.WHITE,SquareState.BLANK,SquareState.WHITE,SquareState.BLANK,SquareState.WHITE,SquareState.BLANK,SquareState.WHITE},
				{SquareState.WHITE,SquareState.BLANK,SquareState.WHITE,SquareState.BLANK,SquareState.WHITE,SquareState.BLANK,SquareState.WHITE,SquareState.BLANK},
				{SquareState.BLANK,SquareState.BLANK,SquareState.BLANK,SquareState.BLANK,SquareState.BLANK,SquareState.BLANK,SquareState.BLANK,SquareState.BLANK},
				{SquareState.BLANK,SquareState.BLANK,SquareState.BLANK,SquareState.BLANK,SquareState.BLANK,SquareState.BLANK,SquareState.BLANK,SquareState.BLANK},
				{SquareState.BLANK,SquareState.BLACK,SquareState.BLANK,SquareState.BLACK,SquareState.BLANK,SquareState.BLACK,SquareState.BLANK,SquareState.BLACK},
				{SquareState.BLACK,SquareState.BLANK,SquareState.BLACK,SquareState.BLANK,SquareState.BLACK,SquareState.BLANK,SquareState.BLACK,SquareState.BLANK},
				{SquareState.BLANK,SquareState.BLACK,SquareState.BLANK,SquareState.BLACK,SquareState.BLANK,SquareState.BLACK,SquareState.BLANK,SquareState.BLACK},
		};
		state = ilk;
	}

	public void bos(){
		SquareState[][] bos = new SquareState[8][8];
		for (int i = 0; i < bos.length; i++) {
			for (int j = 0; j < bos[i].length; j++) {
				bos[i][j] = SquareState.BLANK;
			}
		}
		state = bos;
	}
	
	public void tryMove(Move move) {
		state[move.toX][move.toY]=state[move.fromX][move.fromY];
		state[move.fromX][move.fromY] = SquareState.BLANK;
		if(move.must){
			move.eat = state[(move.toX+move.fromX)/2][(move.toY+move.fromY)/2];
			state[(move.toX+move.fromX)/2][(move.toY+move.fromY)/2] = SquareState.BLANK;
		}
		if(move.toX>move.fromX)
			if(move.toX == 7 && (state[move.toX][move.toY]==SquareState.BLACK || state[move.toX][move.toY]==SquareState.WHITE)){
				state[move.toX][move.toY]=state[move.toX][move.toY].convertKing();
				move.convert = true;
			}
		if(move.toX<move.fromX)
			if(move.toX == 0 && (state[move.toX][move.toY]==SquareState.BLACK || state[move.toX][move.toY]==SquareState.WHITE)){
				state[move.toX][move.toY]=state[move.toX][move.toY].convertKing();
				move.convert = true;
			}
		if(move.nextMustMove != null)
			tryMove(move.nextMustMove);
	}

	public void doMove(Move move) {
		state[move.toX][move.toY]=state[move.fromX][move.fromY];
		state[move.fromX][move.fromY] = SquareState.BLANK;
		if(Math.abs(move.toX-move.fromX)>1){
			move.eat = state[(move.toX+move.fromX)/2][(move.toY+move.fromY)/2];
			state[(move.toX+move.fromX)/2][(move.toY+move.fromY)/2] = SquareState.BLANK;
		}
		if(move.toX>move.fromX)
			if(move.toX == 7 )
				state[move.toX][move.toY]=state[move.toX][move.toY].convertKing();
		if(move.toX<move.fromX)
			if(move.toX == 0 )
				state[move.toX][move.toY]=state[move.toX][move.toY].convertKing();
		if(move.nextMustMove != null)
			doMove(move.nextMustMove);
		else
			callBack.update();
	}
	
	
	public void undoTryMove(Move move) {
		if(move.nextMustMove != null)
			undoTryMove(move.nextMustMove);
		undoTryMoveHelper(move);
	}

	private void undoTryMoveHelper(Move move) {
		state[move.fromX][move.fromY]=state[move.toX][move.toY];
		state[move.toX][move.toY] = SquareState.BLANK;
		if(move.eat != null){
			state[(move.toX+move.fromX)/2][(move.toY+move.fromY)/2] = move.eat;
		}
		if(move.toX>move.fromX)
			if(move.toX == 7 && move.convert)
				state[move.fromX][move.fromY]=state[move.fromX][move.fromY].convertNormal();
		if(move.toX<move.fromX)
			if(move.toX == 0 && move.convert)
				state[move.fromX][move.fromY]=state[move.fromX][move.fromY].convertNormal();
	}
	
	public void setCallback(GameCenter gameCenter){
		this.callBack = gameCenter;
	}
}
