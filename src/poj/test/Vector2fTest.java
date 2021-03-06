package poj.test;
/**
 * Vector2f tests
 *
 * date March 10, 2019
 * @author Haiyang He
 * @version 1.0
 */


import poj.linear.Vector2f;
import org.junit.Test;
import static org.junit.Assert.*;

public class Vector2fTest
{

	private static float EPS = 0.00001f;
	/**
	 *  are vectors equal
	 */
	public static void assertVectorsAreEqual(Vector2f a, Vector2f b)
	{
		assertEquals(a.x, b.x, EPS);
		assertEquals(a.y, b.y, EPS);
	}

	/**
	 *  are vectors equal
	 */
	public static void assertVectorsAreEqual(Vector2f a, float x, float y)
	{
		assertVectorsAreEqual(a, new Vector2f(x, y));
	}

	/**
	 *  comparison test
	 */
	@Test public void compTest()
	{
		{
			Vector2f ab = new Vector2f(1, 0);
			Vector2f ac = new Vector2f(1, 0);

			assertEquals(1f, Vector2f.compt(ab, ac).get(),
				     0.000001f);
		}

		{
			Vector2f ab = new Vector2f(2, 0);
			Vector2f ac = new Vector2f(1, 1);
			assertEquals(.5f, Vector2f.compt(ab, ac).get(),
				     0.000001f);
		}

		{
			Vector2f ab = new Vector2f(1, 0);
			Vector2f ac = new Vector2f(-1, -1);
			assertEquals(-1.0f, Vector2f.compt(ab, ac).get(),
				     0.000001f);
		}
	}


	/**
	 *  projection test
	 */
	@Test public void projTest()
	{
		{
			Vector2f ab = new Vector2f(1, 0);
			Vector2f ac = new Vector2f(1, 0);

			assertVectorsAreEqual(Vector2f.proj(ab, ac).get(), 1,
					      0);
		}

		{
			Vector2f ab = new Vector2f(2, 0);
			Vector2f ac = new Vector2f(1, 1);

			assertVectorsAreEqual(Vector2f.proj(ab, ac).get(), 1f,
					      0);
		}

		{
			Vector2f ab = new Vector2f(1, 0);
			Vector2f ac = new Vector2f(-1, -1);

			assertVectorsAreEqual(Vector2f.proj(ab, ac).get(), -1f,
					      0);
		}
	}
}
