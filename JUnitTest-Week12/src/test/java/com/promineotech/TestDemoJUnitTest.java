package com.promineotech;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import org.junit.jupiter.params.provider.MethodSource;

class TestDemoJUnitTest {
	
	private TestDemo testDemo;

	@BeforeEach
	void setUp() {
		testDemo = new TestDemo();
		
	}

	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) throws Exception {
		 if (!expectException) {
	            assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
	        } else {
	            assertThatThrownBy(() -> testDemo.addPositive(a, b))
	                    .isInstanceOf(IllegalArgumentException.class);
	        }
	    }
	
	 static Stream<Arguments> argumentsForAddPositive() {
	        return Stream.of(
	            arguments(2, 4, 6, false),
	            arguments(1, 3, 4, false),
	            arguments(-1, 2, 0, true),
	            arguments(0, 0, 0, false)
	        );
	 }
	 
	 @Test
	 void assertThatPairsOfPositiveNumbersAreAddedCorrectly() throws Exception {
	        assertThat(testDemo.addPositive(4, 5)).isEqualTo(9);
	        assertThat(testDemo.addPositive(40, 50)).isEqualTo(90);
	        assertThat(testDemo.addPositive(32, 0)).isEqualTo(32);
	        assertThat(testDemo.addPositive(0, 0)).isEqualTo(0);
	        }
	 
	 
	 @Test
	 void assertThatValueWasTruncatedCorrectly() throws Exception {
		 assertThat(testDemo.truncateDouble(3.99)).isEqualTo(3);
		 assertThat(testDemo.truncateDouble(3.24)).isEqualTo(3);
		 assertThat(testDemo.truncateDouble(0)).isEqualTo(0);
		 assertThat(testDemo.truncateDouble(-1.1)).isEqualTo(-1); 
	 	}
	 
	 @Test
	 void assertThatNumberSquaredIsCorrect() throws Exception {
		 TestDemo mockDemo = spy(testDemo);
		 doReturn(5).when(mockDemo).getRandomInt();
		 int fiveSquared = mockDemo.randomNumberSquared();
		 assertThat(fiveSquared).isEqualTo(25);
	 	}
	 }
