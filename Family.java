import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * A class that generates and prints a family tree hierarchy to the console based on specification
 *
 * For a given input by specification:
 * "null,0,grandpa|0,1,son|0,2,daugther|1,3,grandkid|1,4,grandkid|2,5,grandkid|5,6,greatgrandkid"
 *
 *
 * Output to console after running this program is:
 *
 * 0,0,grandpa
 * 0,1,son
 *  1,3,grandkid
 *  1,4,grandkid
 * 0,2,daugther
 *   2,5,grandkid
 *      5,6,greatgrandkid
 *
 *
 */
public class FamilyTree {

    public static void main(String[] args) {

        String input = "null,0,grandpa|0,1,son|0,2,daugther|1,3,grandkid|1,4,grandkid|2,5,grandkid|5,6,greatgrandkid";

        Person familyRoot = null;

        String[] personParts = input.split("\\|");
        for (int i=0; i<personParts.length; i++) {
            String[] innerParts = personParts[i].split(",");
            Person p = createPerson(innerParts);
            if ("null".equals(innerParts[0])) {
                familyRoot = p;
            } else {
                addChild(familyRoot, p);
            }
        }
        printFamily(familyRoot);
    }

    private static Person createPerson(String[] values) {
        Person p = new Person();
        p.parentId = values[0];
        p.nodeId = values[1];
        p.nodeName = values[2];
        return p;
    }

    private static void addChild(Person node, Person newPerson) {
        if (node.nodeId.equals(newPerson.parentId)) {
            node.children.add(newPerson);
        } else {
            for (Person child : node.children) {
                addChild(child, newPerson);
            }
        }
    }

    private static void printFamily(Person node) {
        String indent = "";
        int parentId = ("null".equals(node.parentId)) ? 0 : Integer.valueOf(node.parentId);
        for (int i=0; i<parentId; i++) {
            indent += " ";
        }
        System.out.println(indent + node.toString());
        for (Person p : node.children) {
             printFamily(p);
        }
    }
}

class Person {
    String parentId;
    String nodeId;
    String nodeName;
    List<Person> children = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(parentId).append(",").append(nodeId).append(",").append(nodeName);
        return sb.toString();
    }
}
