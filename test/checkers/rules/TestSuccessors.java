package checkers.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import checkers.domain.Model;
import checkers.domain.Move;
import checkers.domain.Player;
import checkers.sandbox.Board;
import checkers.sandbox.SquareState;

public class TestSuccessors {

	private Successors successors;
	
	@Before
	public void setup(){
		successors = new Successors();
	}
	
	
	//bo� bir tahtada beyaz ta�� test eder...
	@Test
	public void testGetSuccessors1() {
		Model model = new Model();
		model.bos();
		model.state[2][3] = SquareState.WHITE;
		List<Move> moves = successors.getSuccessors(model, Player.WHITE);
		assertEquals(2, moves.size());
		assertEquals(2, moves.get(0).fromX);
		assertEquals(3, moves.get(0).fromY);
		assertEquals(2, moves.get(1).fromX);
		assertEquals(3, moves.get(1).fromY);
		
		assertEquals(3, moves.get(0).toX);
		assertEquals(3, moves.get(1).toX);
		assertTrue( (2==moves.get(0).toY && 4==moves.get(1).toY)^(4==moves.get(0).toY && 2==moves.get(1).toY) );
	}
	

	//bo� bir tahtada beyaz kral� ta�� test eder...
	@Test
	public void testGetSuccessors11() {
		Model model = new Model();
		model.bos();
		model.state[2][3] = SquareState.KING_WHITE;
		List<Move> moves = successors.getSuccessors(model, Player.WHITE);
		assertEquals(4, moves.size());
	}
	
	
	//bo� bir tahtada siyah ta�� test eder...
	@Test
	public void testGetSuccessors12() {
		Model model = new Model();
		model.bos();
		model.state[2][3] = SquareState.BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.BLACK);
		assertEquals(2, moves.size());
		assertEquals(2, moves.get(0).fromX);
		assertEquals(3, moves.get(0).fromY);
		assertEquals(2, moves.get(1).fromX);
		assertEquals(3, moves.get(1).fromY);
		
		assertEquals(1, moves.get(0).toX);
		assertEquals(1, moves.get(1).toX);
		assertTrue( (2==moves.get(0).toY && 4==moves.get(1).toY)^(4==moves.get(0).toY && 2==moves.get(1).toY) );
	}
	
	//bo� bir tahtada siyah kral� ta�� test eder...
	@Test
	public void testGetSuccessors13() {
		Model model = new Model();
		model.bos();
		model.state[2][3] = SquareState.KING_BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.BLACK);
		assertEquals(4, moves.size());
	}
	
	//bo� bir tahtada sol duvar kenar�ndaki beyaz ta�� test eder.
	@Test
	public void testGetSuccessors2() {
		Model model = new Model();
		model.bos();
		model.state[3][0] = SquareState.WHITE;
		List<Move> moves = successors.getSuccessors(model, Player.WHITE);
		assertEquals(1, moves.size());
		assertEquals(3, moves.get(0).fromX);
		assertEquals(0, moves.get(0).fromY);
		assertEquals(4, moves.get(0).toX);
		assertEquals(1, moves.get(0).toY);
	}
	
	//bo� bir tahtada sol duvar kenar�ndaki beyaz kral ta�� test eder.
	@Test
	public void testGetSuccessors21() {
		Model model = new Model();
		model.bos();
		model.state[3][0] = SquareState.KING_WHITE;
		List<Move> moves = successors.getSuccessors(model, Player.WHITE);
		assertEquals(2, moves.size());
	}
	
	//bo� bir tahtada sol duvar kenar�ndaki siyah ta�� test eder.
	@Test
	public void testGetSuccessors22() {
		Model model = new Model();
		model.bos();
		model.state[3][0] = SquareState.BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.BLACK);
		assertEquals(1, moves.size());
		assertEquals(3, moves.get(0).fromX);
		assertEquals(0, moves.get(0).fromY);
		assertEquals(2, moves.get(0).toX);
		assertEquals(1, moves.get(0).toY);
	}
	
	//bo� bir tahtada sol duvar kenar�ndaki siyah kral ta�� test eder.
	@Test
	public void testGetSuccessors23() {
		Model model = new Model();
		model.bos();
		model.state[3][0] = SquareState.KING_BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.BLACK);
		assertEquals(2, moves.size());
	}
	
	//bo� bir tahtada sa� duvar kenar�ndaki beyaz ta�� test eder.
	@Test
	public void testGetSuccessors3() {
		Model model = new Model();
		model.bos();
		model.state[3][7] = SquareState.WHITE;
		List<Move> moves = successors.getSuccessors(model, Player.WHITE);
		assertEquals(1, moves.size());
		assertEquals(3, moves.get(0).fromX);
		assertEquals(7, moves.get(0).fromY);
		assertEquals(4, moves.get(0).toX);
		assertEquals(6, moves.get(0).toY);
	}
	
	//bo� bir tahtada sa� duvar kenar�ndaki beyaz kral ta�� test eder.
	@Test
	public void testGetSuccessors31() {
		Model model = new Model();
		model.bos();
		model.state[3][7] = SquareState.KING_WHITE;
		List<Move> moves = successors.getSuccessors(model, Player.WHITE);
		assertEquals(2, moves.size());
	}

	//bo� bir tahtada sa� duvar kenar�ndaki siyah ta�� test eder.
	@Test
	public void testGetSuccessors32() {
		Model model = new Model();
		model.bos();
		model.state[3][7] = SquareState.BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.BLACK);
		assertEquals(1, moves.size());
		assertEquals(3, moves.get(0).fromX);
		assertEquals(7, moves.get(0).fromY);
		assertEquals(2, moves.get(0).toX);
		assertEquals(6, moves.get(0).toY);
	}
	
	//bo� bir tahtada sa� duvar kenar�ndaki beyaz kral ta�� test eder.
	@Test
	public void testGetSuccessors33() {
		Model model = new Model();
		model.bos();
		model.state[3][7] = SquareState.KING_BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.BLACK);
		assertEquals(2, moves.size());
	}

	
	//bo� bir tahtada beyaz ta�� ve yiyebilece�i siyah ta�� test eder...
	@Test
	public void testGetSuccessors4() {
		Model model = new Model();
		model.bos();
		model.state[2][3] = SquareState.WHITE;
		model.state[3][2] = SquareState.BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.WHITE);
		assertEquals(1, moves.size());
		assertEquals(2, moves.get(0).fromX);
		assertEquals(3, moves.get(0).fromY);
		assertEquals(4, moves.get(0).toX);
		assertEquals(1, moves.get(0).toY);
	}

	//bo� bir tahtada beyaz kral ta�� ve yiyebilece�i siyah ta�� test eder...
	@Test
	public void testGetSuccessors41() {
		Model model = new Model();
		model.bos();
		model.state[2][3] = SquareState.WHITE;
		model.state[3][2] = SquareState.BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.WHITE);
		assertEquals(1, moves.size());
		assertEquals(2, moves.get(0).fromX);
		assertEquals(3, moves.get(0).fromY);
		assertEquals(4, moves.get(0).toX);
		assertEquals(1, moves.get(0).toY);
	}
	
	//bo� bir tahtada beyaz ta�� ve yiyebilece�i siyah ta�� test eder...
	@Test
	public void testGetSuccessors42() {
		Model model = new Model();
		model.bos();
		model.state[2][3] = SquareState.WHITE;
		model.state[3][2] = SquareState.BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.BLACK);
		assertEquals(1, moves.size());
		assertEquals(3, moves.get(0).fromX);
		assertEquals(2, moves.get(0).fromY);
		assertEquals(1, moves.get(0).toX);
		assertEquals(4, moves.get(0).toY);
	}
	

	//bo� bir tahtada siyah kral ta�� ve yiyebilece�i siyah ta�� test eder...
	@Test
	public void testGetSuccessors43() {
		Model model = new Model();
		model.bos();
		model.state[2][3] = SquareState.WHITE;
		model.state[3][2] = SquareState.KING_BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.BLACK);
		assertEquals(1, moves.size());
		assertEquals(3, moves.get(0).fromX);
		assertEquals(2, moves.get(0).fromY);
		assertEquals(1, moves.get(0).toX);
		assertEquals(4, moves.get(0).toY);
	}
	
	//bo� bir tahtada beyaz ta�� ve yiyemeyece�i siyah ta�� test eder...
	@Test
	public void testGetSuccessors5() {
		Model model = new Model();
		model.bos();
		model.state[2][3] = SquareState.WHITE;
		model.state[3][2] = SquareState.BLACK;
		model.state[4][1] = SquareState.BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.WHITE);
		assertEquals(1, moves.size());
		assertEquals(2, moves.get(0).fromX);
		assertEquals(3, moves.get(0).fromY);
		assertEquals(3, moves.get(0).toX);
		assertEquals(4, moves.get(0).toY);
	}
	
	//bo� bir tahtada beyaz ta�� ve yiyemeyece�i siyah ta�� test eder...
	@Test
	public void testGetSuccessors51() {
		Model model = new Model();
		model.bos();
		model.state[2][3] = SquareState.KING_WHITE;
		model.state[3][2] = SquareState.BLACK;
		model.state[4][1] = SquareState.BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.WHITE);
		assertEquals(3, moves.size());
	}
	
	//bo� bir tahtada siyah ta�� ve yiyemeyece�i beyaz ta�� test eder...
	@Test
	public void testGetSuccessors52() {
		Model model = new Model();
		model.bos();
		model.state[2][3] = SquareState.WHITE;
		model.state[3][2] = SquareState.WHITE;
		model.state[4][1] = SquareState.BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.BLACK);
		assertEquals(1, moves.size());
		assertEquals(4, moves.get(0).fromX);
		assertEquals(1, moves.get(0).fromY);
		assertEquals(3, moves.get(0).toX);
		assertEquals(0, moves.get(0).toY);
	}
	
	//bo� bir tahtada siyah kral ta�� ve yiyemeyece�i beyaz ta�� test eder...
	@Test
	public void testGetSuccessors53() {
		Model model = new Model();
		model.bos();
		model.state[2][3] = SquareState.KING_BLACK;
		model.state[3][2] = SquareState.WHITE;
		model.state[4][1] = SquareState.WHITE;
		List<Move> moves = successors.getSuccessors(model, Player.BLACK);
		assertEquals(3, moves.size());
	}
	
	//bo� bir tahtada beyaz ta�� ve yiyemeyece�i siyah ta�� test eder...
	@Test
	public void testGetSuccessors501() {
		Model model = new Model();
		model.bos();
		model.state[2][3] = SquareState.WHITE;
		model.state[3][2] = SquareState.WHITE;
		model.state[4][1] = SquareState.BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.WHITE);
		assertEquals(1, moves.size());
		assertEquals(3, moves.get(0).fromX);
		assertEquals(2, moves.get(0).fromY);
		assertEquals(5, moves.get(0).toX);
		assertEquals(0, moves.get(0).toY);
	}
	
	//bo� bir tahtada beyaz ta�� ve sa�dan yiyece�i siyah ta�� test eder...
	@Test
	public void testGetSuccessors6() {		
		Model model = new Model();
		model.bos();
		model.state[2][3] = SquareState.WHITE;
		model.state[3][4] = SquareState.BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.WHITE);
		assertEquals(1, moves.size());
		assertEquals(2, moves.get(0).fromX);
		assertEquals(3, moves.get(0).fromY);
		assertEquals(4, moves.get(0).toX);
		assertEquals(5, moves.get(0).toY);
	}
	
	//bo� bir tahtada beyaz ta� ve sa�dan yiyece�i siyah ta�� test eder... 
	//sonras�nda king olacak...
	@Test
	public void testGetSuccessors601() {		
		Model model = new Model();
		model.bos();
		model.state[5][4] = SquareState.WHITE;
		model.state[6][3] = SquareState.BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.WHITE);
		assertEquals(1, moves.size());
		assertEquals(5, moves.get(0).fromX);
		assertEquals(4, moves.get(0).fromY);
		assertEquals(2, moves.get(0).toY);
		assertEquals(7, moves.get(0).toX);
	}
	
	//bo� bir tahtada beyaz kral ta�� ve sa�dan yiyece�i siyah ta�� test eder...
	@Test
	public void testGetSuccessors61() {		
		Model model = new Model();
		model.bos();
		model.state[2][3] = SquareState.KING_WHITE;
		model.state[3][4] = SquareState.BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.WHITE);
		assertEquals(1, moves.size());
		assertEquals(2, moves.get(0).fromX);
		assertEquals(3, moves.get(0).fromY);
		assertEquals(4, moves.get(0).toX);
		assertEquals(5, moves.get(0).toY);
	}
	
	//bo� bir tahtada siyah ta�� ve sa�dan yiyece�i beyaz ta�� test eder...
	@Test
	public void testGetSuccessors62() {		
		Model model = new Model();
		model.bos();
		model.state[2][3] = SquareState.WHITE;
		model.state[3][4] = SquareState.BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.BLACK);
		assertEquals(1, moves.size());
		assertEquals(3, moves.get(0).fromX);
		assertEquals(4, moves.get(0).fromY);
		assertEquals(1, moves.get(0).toX);
		assertEquals(2, moves.get(0).toY);
	}
	
	//bo� bir tahtada siyah kral ta�� ve soldan yiyece�i beyaz ta�� test eder...
	@Test
	public void testGetSuccessors63() {		
		Model model = new Model();
		model.bos();
		model.state[2][3] = SquareState.WHITE;
		model.state[3][4] = SquareState.KING_BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.BLACK);
		assertEquals(1, moves.size());
		assertEquals(3, moves.get(0).fromX);
		assertEquals(4, moves.get(0).fromY);
		assertEquals(1, moves.get(0).toX);
		assertEquals(2, moves.get(0).toY);
	}
	//bo� bir tahtada siyah ta�� ve sa�dan yiyece�i beyaz ta�� test eder... sonras�nda king olacak
//	@Test
//	public void testGetSuccessors64() {		
//		Model model = new Model();
//		model.bos();
//		model.state[1][3] = SquareState.WHITE;
//		model.state[2][4] = SquareState.BLACK;
//		List<Move> moves = successors.getSuccessors(model, Player.BLACK);
//		assertEquals(1, moves.size());
//		assertEquals(2, moves.get(0).fromX);
//		assertEquals(4, moves.get(0).fromY);
//		assertEquals(0, moves.get(0).toX);
//		assertEquals(2, moves.get(0).toY);
//		assertTrue(moves.get(0).must);
//		assertTrue(moves.get(0).convert);
//	}
	
	//�oklu yeme durumu
	@Test
	public void testGetSuccessors71() {		
		Model model = new Model();
		model.bos();
		model.state[3][2] = SquareState.WHITE;
		model.state[5][2] = SquareState.WHITE;
		model.state[6][3] = SquareState.BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.BLACK);
		assertEquals(1, moves.size());
		Move move = moves.get(0);
		assertEquals(6, move.fromX);
		assertEquals(3, move.fromY);
		assertEquals(4, move.toX);
		assertEquals(1, move.toY);
		assertNotNull(move.nextMustMove);
		
		assertEquals(4, move.nextMustMove.fromX);
		assertEquals(1, move.nextMustMove.fromY);
		assertEquals(2, move.nextMustMove.toX);
		assertEquals(3, move.nextMustMove.toY);
	}
	
	//pek �oklu yeme durumu
	@Test
	public void testGetSuccessors72() {		
		Model model = new Model();
		model.bos();
		model.state[1][2] = SquareState.WHITE;
		model.state[3][2] = SquareState.WHITE;
		model.state[5][2] = SquareState.WHITE;
		model.state[6][3] = SquareState.BLACK;
		List<Move> moves = successors.getSuccessors(model, Player.BLACK);
		assertEquals(1, moves.size());
		Move move = moves.get(0);
		assertEquals(6, move.fromX);
		assertEquals(3, move.fromY);
		assertEquals(4, move.toX);
		assertEquals(1, move.toY);
		assertNotNull(move.nextMustMove);
		
		assertEquals(4, move.nextMustMove.fromX);
		assertEquals(1, move.nextMustMove.fromY);
		assertEquals(2, move.nextMustMove.toX);
		assertEquals(3, move.nextMustMove.toY);
		
		assertNotNull(move.nextMustMove.nextMustMove);
		
		assertEquals(2, move.nextMustMove.nextMustMove.fromX);
		assertEquals(3, move.nextMustMove.nextMustMove.fromY);
		assertEquals(0, move.nextMustMove.nextMustMove.toX);
		assertEquals(1, move.nextMustMove.nextMustMove.toY);
	}
	
//	//bo� bir tahtada beyaz ta�� ve sa�dan yiyece�i iki siyah ta�� test eder...
//	@Test
//	public void testGetSuccessors7() {
//		Model model = new Model();
//		model.bos();
//		model.state[2][3] = SquareState.WHITE;
//		model.state[3][4] = SquareState.BLACK;
//		model.state[5][6] = SquareState.BLACK;
//		List<Move> moves = successors.getSuccessors(model, Player.WHITE);
//		assertEquals(2, moves.size());
//		assertEquals(2, moves.get(0).fromX);
//		assertEquals(3, moves.get(0).fromY);
//		assertEquals(2, moves.get(1).fromX);
//		assertEquals(3, moves.get(1).fromY);
//		
//		assertTrue( (3==moves.get(0).toX && 6==moves.get(1).toX)^(6==moves.get(0).toX && 3==moves.get(1).toX) );
//		assertTrue( (2==moves.get(0).toY && 7==moves.get(1).toY)^(7==moves.get(0).toY && 2==moves.get(1).toY) );
//	}
}
