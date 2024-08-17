package s1014ftjavaangular.loan;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoanTests extends TestCase{

	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public LoanTests( String testName )
	{
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		return new TestSuite( LoanTests.class );
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp()
	{
		assertTrue( true );
	}
}
