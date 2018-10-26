package utils;

import java.util.Stack;

import a2.Matrix;
import conf.ViolatedMatrixMultiplicationRulesException;

/**
 * Utility class to split up the <code>CalculationTask<code>s.
 * 
 * @author julian
 *
 */
public final class TaskSplitter {
    // ----------------------------------------------------------------------------------------------

    /**
     * Splits the calculation of the result matrix into several tasks and return
     * them in a stack.
     * 
     * @param matrixA The first matrix
     * @param matrixB The second matrix
     * @return An Stack with the splitted sub tasks.
     */
    public static Stack<CalculationTask> splitTasks(final Matrix matrixA, final Matrix matrixB) {
        try {
            Calculator.checkMatrixMultiplicationRules(matrixA, matrixB);

            Stack<CalculationTask> calculationTasks = new Stack<>();
            int rowNumberA = matrixA.getRowNumber();
            int columnNumberB = matrixB.getColumnNumber();

            int[] scalarA = new int[columnNumberB];
            int[] scalarB = new int[rowNumberA];
            for (int i = 0; i < rowNumberA; i++) {

                scalarA = Calculator.getHorizontalScalar(i, matrixA);
                for (int j = 0; j < columnNumberB; j++) {

                    scalarB = Calculator.getVerticalScalar(j, matrixB);
                    if (scalarA.length != 0 && scalarB.length != 0) {
                        calculationTasks.push(new CalculationTask(i, j, scalarA, scalarB));
                    }
                }
            }
            return calculationTasks;

        } catch (ViolatedMatrixMultiplicationRulesException e) {
            System.out.println(e.getMessage());
            return null;

        }
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Splits the given list into the given amount of sublists.
     * 
     * @param allCalculationTasks The calculation tasks to split
     * @param subListAmount       The amount of lists to create
     * @return An stack of stacks each stack represents one list
     */
    public static Stack<Stack<CalculationTask>> createTaskSublists(
            final Stack<CalculationTask> allCalculationTasks, final int subListAmount) {
        int elementsPerList = allCalculationTasks.size() / subListAmount;
        int elementsPerListRest = allCalculationTasks.size() % subListAmount;
        Stack<Stack<CalculationTask>> subLists = new Stack<Stack<CalculationTask>>();

        for (int i = 0; i < subListAmount; i++) {
            subLists.add(new Stack<CalculationTask>());
            for (int j = 0; j < elementsPerList; j++) {
                subLists.get(i).push(allCalculationTasks.pop());
            }

            if (elementsPerListRest > 0) {
                subLists.get(i).push(allCalculationTasks.pop());
                elementsPerListRest = elementsPerListRest - 1;
            }
        }
        return subLists;

    }

    // ---------------------------------------------------------------------------------------------
}
