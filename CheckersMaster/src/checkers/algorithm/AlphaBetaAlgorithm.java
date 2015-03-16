package checkers.algorithm;

import java.util.List;

import checkers.domain.CalculationContext;
import checkers.domain.Model;
import checkers.domain.Move;
import checkers.domain.Player;
import checkers.evaluation.IEvaluation;
import checkers.rules.ISuccessor;

/**
 * Alpha-Beta Pruning Algorithm Implementation
 */ 
public class AlphaBetaAlgorithm implements IAlgorithm{

	public Move algorithm(CalculationContext context, Model model, Player whosTurn) {
		if(context == null || model == null) {
			throw new IllegalArgumentException();
		}
		return alphaBeta(context, model, whosTurn, 0, 0, 0, false, false);
	}

	Move alphaBeta(CalculationContext context, Model model, Player whosTurn, int depth, 
				double prunMin, double prunMax, boolean isPrunMinUsed, boolean isPrunMaxUsed) {
		if(context.getDepth()==depth){
			Move move = new Move();
			move.setValue(evaluateModel(context, model));
			return move;
		}
		List<Move> successors = getSuccessors(context, model, whosTurn);
		if(successors.isEmpty()){
			Move move = new Move();
			move.setValue(evaluateModel(context, model));
			return move;
		}
		boolean isAssigned = false;
		double selectedValue = 0;
		Move selectedMove = null;
		for (Move move : successors) {
			model.tryMove(move);
			if(isAssigned){
				if(context.getPlayer() == whosTurn){
					if(isPrunMinUsed){
						prunMin = prunMin > selectedValue ? selectedValue : prunMin;
					}else{
						prunMin = selectedValue;
						isPrunMinUsed = true;
					}
				}else{
					if(isPrunMaxUsed){
						prunMax = prunMax < selectedValue ? selectedValue : prunMax;
					}else{
						prunMax = selectedValue;
						isPrunMaxUsed = true;
					}
				}
			}
			Move minimax = alphaBeta(context, model, whosTurn.opposite(), depth+1, prunMin, prunMax, isPrunMinUsed, isPrunMaxUsed);
			if(!isAssigned){
				isAssigned = true;
				selectedValue = minimax.getValue();
				move.next = minimax;
				selectedMove = move;
			}else{
				if(context.getPlayer() == whosTurn){
					if(minimax.getValue()> selectedValue){
						selectedMove = move;
						move.next = minimax;
						selectedValue = minimax.getValue();
					}
				}else{
					if(minimax.getValue()< selectedValue){
						selectedMove = move;
						move.next = minimax;
						selectedValue = minimax.getValue();
					}
				}
			}
			model.undoTryMove(move);
			if(context.getPlayer() == whosTurn && isPrunMinUsed){
				if(prunMin >= selectedValue){
					selectedMove.setValue(selectedValue);
					return selectedMove;
				}
			}
			if(context.getPlayer() != whosTurn && isPrunMaxUsed){
				if(prunMax <= selectedValue){
					selectedMove.setValue(selectedValue);
					return selectedMove;
				}
			}
		}
		selectedMove.setValue(selectedValue);
		return selectedMove;
	}

	private double evaluateModel(CalculationContext context, Model model) {
		IEvaluation evaluationFunction = context.getEvaluationFunction();
		return evaluationFunction.evaluate(model,context.getPlayer());
	}
	
	private List<Move> getSuccessors(CalculationContext context, Model model, Player whosTurn) {
		ISuccessor successor = context.getSuccessorFunction();
		List<Move> successors = successor.getSuccessors(model, whosTurn);
		return successors;
	}

	@Override
	public String getName() {
		return "Alpha Beta Algorithm";
	}

}
