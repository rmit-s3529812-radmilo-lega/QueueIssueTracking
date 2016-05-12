package JUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Main.Queue;
import Main.QueueEmptyException;
import Main.QueueFullException;

public class Issue {
	
	private Queue testQueue;

	@Before
	public void setUp() throws Exception {
		testQueue = new Queue(1);
	}

	@Test
	public void ExceptionTest() throws QueueFullException, QueueEmptyException {
		
		testQueue.add(1);
		
		try {
			testQueue.add(2);
			fail();
		} catch (QueueFullException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail();
		}
		
		assertEquals(1, testQueue.getFirst());
	}


	@Test
	public void OverwriteTest() throws QueueFullException, QueueEmptyException {
		testQueue.add(1);
		testQueue.add(2);
		
		assertEquals(1, testQueue.getFirst());
	}
}