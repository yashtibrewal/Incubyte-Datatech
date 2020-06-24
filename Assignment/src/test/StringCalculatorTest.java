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
	}

}
