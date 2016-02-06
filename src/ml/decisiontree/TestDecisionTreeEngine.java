package ml.decisiontree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ml.decisiontree.DecisionTree.Node;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestDecisionTreeEngine {
	private static List<Record> records;
	private static Node root;
	@BeforeClass
	public static void init(){
		records = generateTestData();
		 root = DecisionTreeEngine.INSTANCE.initRoot(records);
	}

	@Test
	public void testCreateDecisionTree() {
		DecisionTree tree = DecisionTreeEngine.INSTANCE.createDecisionTree(records);
	}
	@Test
	public void testInitRoot() {
		
		SampleDistribute sampleDistribute = root.getSampleDistribute();
		assertEquals(sampleDistribute.getNegative(), 2);
		assertEquals(sampleDistribute.getPositive(), 3);
		assertEquals(sampleDistribute.getSample().size(), 5);
		System.out.println(root.getEntropy());
		
		System.out.println(Record.getDeterminingAttributes());
	}
	@Test
	public void testGenerateChilderen() {
		
		Collection<Node> children = DecisionTreeEngine.INSTANCE.generateChildren(root, "Color");
		assertEquals(children.size(), 3);
		Node nodeForBlack = null;
		for(Node node: children){
			if(node.getAttribute().getValue().equals("Black")){
				nodeForBlack = node;
			}
		}
		assertTrue(nodeForBlack != null);
		
		SampleDistribute sampleDistribute = nodeForBlack.getSampleDistribute();
		assertEquals(sampleDistribute.getSample().size(), 3);
		assertEquals(sampleDistribute.getPositive(), 2);
		assertEquals(sampleDistribute.getNegative(), 1);
		
	}
	
	
	


	private static List<Record> generateTestData() {
		List<Record> testRecords = new ArrayList<>();
		List<Attribute> attributes = new ArrayList<>();
		attributes.add(new Attribute("Weight","Heavy"));
		attributes.add(new Attribute("Height","Tall"));
		attributes.add(new Attribute("Color","White"));
		attributes.add(new Attribute("Result","Positive"));
		Record record = new Record(attributes);
		testRecords.add(record);
		
		attributes = new ArrayList<>();
		attributes.add(new Attribute("Weight","Light"));
		attributes.add(new Attribute("Height","Tall"));
		attributes.add(new Attribute("Color","Black"));
		attributes.add(new Attribute("Result","Positive"));
		record = new Record(attributes);
		testRecords.add(record);
		
		attributes = new ArrayList<>();
		attributes.add(new Attribute("Weight","Light"));
		attributes.add(new Attribute("Height","Small"));
		attributes.add(new Attribute("Color","Black"));
		attributes.add(new Attribute("Result","Negative"));
		 record = new Record(attributes);
		testRecords.add(record);
		
		
		attributes = new ArrayList<>();
		attributes.add(new Attribute("Weight","Light"));
		attributes.add(new Attribute("Height","Small"));
		attributes.add(new Attribute("Color","Brown"));
		attributes.add(new Attribute("Result","Negative"));
		 record = new Record(attributes);
		testRecords.add(record);
		
		
		attributes = new ArrayList<>();
		attributes.add(new Attribute("Weight","Light"));
		attributes.add(new Attribute("Height","Small"));
		attributes.add(new Attribute("Color","Black"));
		attributes.add(new Attribute("Result","Positive"));
		 record = new Record(attributes);
		testRecords.add(record);
		return testRecords;
	}
	
	

}
