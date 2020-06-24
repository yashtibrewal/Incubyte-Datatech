package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import dev.StringCalulator;

class StringCalculatorTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testAdd() throws Exception {
		int result;
		String expectedMessage, actualMessage;

		StringCalulator stringCalulatorObject;
		
		stringCalulatorObject= new StringCalulator();
		result = stringCalulatorObject.add("");
		assertEquals(0, result);

		stringCalulatorObject= new StringCalulator();
		result = stringCalulatorObject.add("1");
		assertEquals(1, result);

		stringCalulatorObject= new StringCalulator();
		result = stringCalulatorObject.add("1,2");
		assertEquals(3, result);

		stringCalulatorObject= new StringCalulator();
		result = stringCalulatorObject.add("1,2,3");
		assertEquals(6, result);

		stringCalulatorObject= new StringCalulator();
		result = stringCalulatorObject.add("2,45,1234,0,0,0,0,0,1,2,99");
		assertEquals(1383, result);

		stringCalulatorObject= new StringCalulator();
		result = stringCalulatorObject.add("1\n2,3\n99");
		assertEquals(105, result);

		stringCalulatorObject= new StringCalulator();
		result = stringCalulatorObject.add("//;1;2;3");
		assertEquals(6, result);

		StringCalulator stringCalulatorObjectR5= new StringCalulator();
		Exception exception = assertThrows(Exception.class,()->{
				stringCalulatorObjectR5.add("1,-2,3");
		});
		expectedMessage = "negatives not allowed [-2]";
		actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

		StringCalulator stringCalulatorObjectR6= new StringCalulator();
		exception = assertThrows(Exception.class, () -> {
			stringCalulatorObjectR6.add("1,-2,-3");
		});
		expectedMessage = "negatives not allowed [-2, -3]";
		actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	final void testIsDelimeter() {
		StringCalulator stringCalulatorObject = new StringCalulator();
		assertEquals(true, stringCalulatorObject.isDelimeter('\n'));
		assertEquals(true, stringCalulatorObject.isDelimeter(','));
		assertEquals(false, stringCalulatorObject.isDelimeter(';'));
	}

}
