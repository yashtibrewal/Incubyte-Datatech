package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.StringCalulator;


class StringCalculatorTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testAdd() {
		int result;
		StringCalulator stringCalulatorObject = new StringCalulator();
		result = stringCalulatorObject.add("");
		assertEquals(0, result);
		result = stringCalulatorObject.add("1");
		assertEquals(1, result);
		result = stringCalulatorObject.add("1,2");
		assertEquals(3, result);
		result = stringCalulatorObject.add("1,2,3");
		assertEquals(6, result);
		result = stringCalulatorObject.add("2,45,1234,0,0,0,0,0,1,2,99");
		assertEquals(1383, result);
		result = stringCalulatorObject.add("1\n2,3\n99");
		assertEquals(105, result);
		result = stringCalulatorObject.add("//;1;2;3");
		assertEquals(6, result);
	}
	
	@Test
	final void testIsDelimeter() {
		StringCalulator stringCalulatorObject = new StringCalulator();
		assertEquals(true, stringCalulatorObject.isDelimeter('\n'));
		assertEquals(true, stringCalulatorObject.isDelimeter(','));
	}
	
}
