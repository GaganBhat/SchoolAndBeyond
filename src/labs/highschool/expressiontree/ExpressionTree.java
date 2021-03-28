package labs.highschool.expressiontree;

/**
 * The ExpressionTree class is responsible for processing a prefix expression
 * into an expression tree and handling evaluation and infix expression
 * conversions.
 *
 * @author Gagan Bhat
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 3
 * Due Date: 3/3/2020
 */

import java.util.Scanner;

public class ExpressionTree
{
	/** Root of the expression tree */
	private ExpressionNode root;


	/** Used to parse a prefix expression */
	private Scanner strScan;

	/** String builder to append the infix expression**/
	private StringBuilder builder;

	/**
	 * Initializes root node to be null, empty tree, and instantiates String Builder
	 */
	public ExpressionTree()
	{
		root = null;
		builder = new StringBuilder();
	}

	/**
	 * This alternate constructor instantiates a scanner based on the prefix supplied.
	 * @param prefix prefix expression to consider for scanner object
	 */
	public ExpressionTree(String prefix)
	{
		this();
		strScan = new Scanner(prefix);
	}

	/**
	 * The setExpression method takes in a prefix expression and builds
	 * and expression tree.
	 * @param prefix prefix expression to consider when calling the helper
	 *               methods.
	 */
	public void setExpression(String prefix)
	{
		strScan = new Scanner(prefix);
		root = buildExpression(prefix);
		traverseAndBuild(root);
	}

	/**
	 * Helper method for setExpression that uses recursion to construct a tree from the
	 * prefix supplied.
	 * @param prefix prefix expression to process and create tree for
	 * @return the root expression node of the expression tree.
	 */
	private ExpressionNode buildExpression(String prefix) {
		String element;
		try{
			element = strScan.next();
		} catch (Exception npe){
			element = "";
		}

		if(element.equalsIgnoreCase(""))
			return null;

		if(isOperator(element))
			return new ExpressionNode(getOperatorType(element),
					buildExpression(prefix),
					buildExpression(prefix));
		else
			return new ExpressionNode(Integer.parseInt(element));
	}

	/**
	 * Evaluates the expression tree whilst adding
	 * up the leaves based on their operator root specified
	 * @return evaluated value of expression tree (prefix notation)
	 */
	public double evaluate()
	{
		if(root == null)
			return 0.0;

		return evaluate(root);
	}

	/**
	 * Helper method overloading that does the brunt work for
	 * evaluating the expression tree whilst adding
	 * up the leaves based on their operator root.
	 * @param node node to calculate expression relative to
	 * @return evaluated value of expression tree (prefix notation)
	 */
	public double evaluate(ExpressionNode node)
	{
		if (node.getLeft() == null && node.getRight() == null)
			return node.getValue();
		else
		{
			double result = 0.0;
			double left = evaluate(node.getLeft());
			double right = evaluate(node.getRight());
			NodeType operator = node.getType();

			switch (operator)
			{
				case ADD: result = left + right; break;
				case SUBTRACT: result = left - right; break;
				case MULTIPLY: result = left * right; break;
				case DIVIDE: result = left / right; break;
				case EXPONENT: result = Math.pow(left,right); break;
				case REMAINDER: result = left % right; break;
			}
			return result;
		}
	}

	/**
	 * The isOperator() method returns if the given parameter
	 * element string is an operator or a number
	 * @param element string with one element from prefix notation
	 * @return true if an operator, false otherwise.
	 */
	private boolean isOperator(String element){
		return element.equalsIgnoreCase(NodeType.ADD.getSymbol())
				|| element.equalsIgnoreCase(NodeType.SUBTRACT.getSymbol())
				|| element.equalsIgnoreCase(NodeType.REMAINDER.getSymbol())
				|| element.equalsIgnoreCase(NodeType.MULTIPLY.getSymbol())
				|| element.equalsIgnoreCase(NodeType.EXPONENT.getSymbol())
				|| element.equalsIgnoreCase(NodeType.DIVIDE.getSymbol());
	}

	/**
	 * Returns the type of operator given an element string
	 * @param element string with one element from prefix notation
	 * @return NodeType enum corresponding to type of operator
	 */
	private NodeType getOperatorType(String element){
		switch (element){
			case "+":
				return NodeType.ADD;
			case "/":
				return NodeType.DIVIDE;
			case "^":
				return NodeType.EXPONENT;
			case "*":
				return NodeType.MULTIPLY;
			case "%":
				return NodeType.REMAINDER;
			case "-":
				return NodeType.SUBTRACT;
		}
		return NodeType.NUMBER;
	}

	/**
	 * Uses local StringBuilder to construct an infix expression
	 * from the existing expression tree for display.
	 * @param node starting node root to branch off from while recurring
	 */
	private void traverseAndBuild(ExpressionNode node) {
		if (node != null) {
			if(node.getLeft() != null && node.getRight() != null)
				builder.append("(");
			traverseAndBuild(node.getLeft());
			if(node.getType() == NodeType.NUMBER)
				builder.append(node.getValue());
			else
				builder.append(" ").append(node.getType().getSymbol()).append(" ");
			traverseAndBuild(node.getRight());
			if(node.getLeft() != null && node.getRight() != null)
				builder.append(")");
		}
	}

	/**
	 * Converts expression tree to a human readable infix notation String form
	 * using the traverseAndBuild helper method.
	 * @return infix notation string from expression tree with parenthesis
	 */
	@Override
	public String toString()
	{
		builder = new StringBuilder(); // reset builder for every call
		if(root==null)
			return "0";
		traverseAndBuild(root);
		return builder.toString();
	}

}
