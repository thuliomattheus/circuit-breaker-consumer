package projects.medium.circuitbreakerconsumer;

import org.junit.jupiter.api.Test;

class RecursiveFibbonacciTest {

    @Test
    fun mytest() {
        for(i in 1L..10L) {
            System.err.println("Test $i");
            printFibb(i);
        }
    }

    private fun printFibb(n: Long) {
        if(n == 1L) {
            System.err.println("${fibb(n)}")
        } else {
            System.err.print("${fibb(n)} ")
            printFibb(n-1)
        }
    }

    private fun fibb(n: Long): Long {
        if(n == 1L) {
            return 1
        }
        if(n == 2L) {
            return 1
        }
        return fibb(n-1) + fibb(n-2);
    }
}
