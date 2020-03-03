package labs.expressiontree;

import java.util.Scanner;

public class ExpressionTree
{
	/** Root of the expression tree */
	private ExpressionNode root;


	/** Used to parse a prefix expression */
	private Scanner strScan;

	public ExpressionTree()
	{
		root = null;
	}

	public ExpressionTree(String prefix)
	{
		root = null;
		strScan = new Scanner(prefix);
	}

	public void setExpression(String prefix)
	{
		strScan = new Scanner(prefix);
		root = buildExpression(prefix);
		inorder(root);
	}

	private ExpressionNode buildExpression(String prefix) {
		String element;
		try{
			element = strScan.next();
		} catch (NullPointerException npe){
			element = "";
		}

		if(element.equalsIgnoreCase(""))
			return null;

		if(isOperator(element))
			return new ExpressionNode(getOperatorType(element), buildExpression(prefix), buildExpression(prefix));
		else
			return new ExpressionNode(Integer.parseInt(element));
	}


	private boolean isOperator(String element){
		return element.equalsIgnoreCase(NodeType.ADD.getSymbol())
				|| element.equalsIgnoreCase(NodeType.SUBTRACT.getSymbol())
				|| element.equalsIgnoreCase(NodeType.REMAINDER.getSymbol())
				|| element.equalsIgnoreCase(NodeType.MULTIPLY.getSymbol())
				|| element.equalsIgnoreCase(NodeType.EXPONENT.getSymbol())
				|| element.equalsIgnoreCase(NodeType.DIVIDE.getSymbol());
	}

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

	public double evaluate()
	{
		return 0.0;
	}

	void inorder(ExpressionNode node) {
		if (node != null) {
			if(node.getLeft() != null && node.getRight() != null)
				System.out.print("(");
			inorder(node.getLeft());
			if(node.getType() == NodeType.NUMBER)
				System.out.print(node.getValue() + "");
			else
				System.out.print(" " + node.getType().getSymbol() + " ");
			inorder(node.getRight());
			if(node.getLeft() != null && node.getRight() != null)
				System.out.print(")");
		}

	}

	@Override
	public String toString()
	{
		if(root==null)
			return "0";
		return "";
	}

}
