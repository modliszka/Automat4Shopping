package DecisionTrees;
import weka.core.*;
import java.io.*;
import java.util.*;
/*
 * ID3 (Examples, Target_Attribute, Attributes)
    Create a root node for the tree
    If all examples are positive, Return the single-node tree Root, with label = +.
    If all examples are negative, Return the single-node tree Root, with label = -.
    If number of predicting attributes is empty, then Return the single node tree Root,
    with label = most common value of the target attribute in the examples.
    Otherwise Begin
        A ← The Attribute that best classifies examples (highest Information Gain).
        Decision Tree attribute for Root = A.
        For each possible value, v_i, of A,
            Add a new tree branch below Root, corresponding to the test A = v_i.
            Let Examples(v_i) be the subset of examples that have the value v_i for A
            If Examples(v_i) is empty
                Then below this new branch add a leaf node with label = most common target value in the examples
            Else below this new branch add the subtree ID3 (Examples(v_i), Target_Attribute, Attributes – {A})
    End
    Return Root
 * 
 * */


//tworzenie drzew w Javie
//http://kodatnik.blogspot.com/2010/02/drzewo-ogolna-implementacja.html

class ID3_decistionTree {
    static HashMap<Attribute, int[]> attrClassesCount;
    
    public static void main(String[] args) throws Exception {
      // wczytujemy dane
      Instances instances = new Instances(new FileReader("./src/DecisionTrees/data/teas.arff"));
      
      // wstawiamy instancje z obiektu Instances do ArrayListy zeby pozniej łatwo filtrować
      ArrayList<Instance> insts = new ArrayList<Instance>();
      for (int i=0; i<instances.numInstances(); i++) {
        insts.add(instances.instance(i));
      }
      
      // wstawiamy atrubuty do ArrayListy zeby pozniej je latwo usuwac po kolei
      ArrayList<Attribute> attrs = new ArrayList<Attribute>();
      for (int a=0; a<instances.numAttributes()-1; a++) {
        attrs.add(instances.attribute(a));
      }
      
      // wołamy findBestTree, która rekurencyjnie zbuduje najlepsze drzewo decyzyjne
      Node tree = findBestTree(insts, attrs);
      // wypisujemy wynik
      tree.print(0);
    }
    
    public static Node findBestTree(ArrayList<Instance> insts, ArrayList<Attribute> attrs) {
      // znajdz atrybut o najlepszym gain - przyrost informacji (information gain)
      Attribute bestAttr = null;
      double bestGain = 0.0;
      for (Attribute a : attrs) {
        double gain = calculateGainForAttribute(a, insts);
        if (bestAttr == null || gain > bestGain) {
          bestAttr = a;
          bestGain = gain;
        }
      }
      // dla każdej z wartości znalezionego atrybutu zbuduj pod-drzewa, hashmapa children bedzie zawierac pary ("wartość" -> pod-drzewo)
      HashMap<String, Node> children = new HashMap<String, Node>();
      String value;
      // iterujemy po wartościach atrubytu
      for (int i=0; i<bestAttr.numValues(); i++) {
        value = bestAttr.value(i);
        
        // znajdujemy tylko te instancje ktore zawieraja tą wartość
        ArrayList<Instance> filteredInstances = findInstancesForValue(insts, bestAttr.index(), value);
        if (numClasses(filteredInstances) == 1) { 
        	// tylko "tak" lub tylko "nie", więc wstawiamy liść Leaf i kończymy
          Instance inst = filteredInstances.get(0);
          children.put(value, new Leaf(inst.stringValue(inst.numValues()-1)));
        } else { 
        	// w znalezionych instancjach występują zarówno "tak" jak i "nie"
          if (attrs.size() - 1 > 0) { 
        	  // jesli po odjeciu aktualnego atrybutu lista atrybutow nie będzie pusta to dalej budujemy drzewo rekurencyjnie
            ArrayList<Attribute> newAttrs = (ArrayList<Attribute>)attrs.clone();
            newAttrs.remove(bestAttr);
            Node subtree = findBestTree(filteredInstances, newAttrs);
            children.put(value, subtree);
          }
        }
      }
      // zwracamy nowo utworzone drzewo z najlepszym atrybutem w korzeniu i zbudowanymi poddrzewami
      return new Node(bestAttr, bestGain, children);
    }
    
    // liczymy gain dla podanego atrybutu i danych instancji
    public static double calculateGainForAttribute(Attribute a, ArrayList<Instance> insts) {
      double info2 = 0;
      double info3 = 0;
      int[] totalClassNum = {0, 0};
      for (int v=0; v<a.numValues(); v++) {
        int[] classNum = {0, 0};
        for (Instance i : insts) {
          if (i.stringValue(a.index()).equals(a.value(v))) {
            if (i.stringValue(i.numValues()-1).equals("yes")) {
                classNum[0] += 1;
                totalClassNum[0] += 1;
            } else {
                classNum[1] += 1;
                totalClassNum[1] += 1;
            }
          }
        }
        double info = info(classNum); //liczy entropie dla wartosci
        info2 = info(totalClassNum); // liczy entropie dla calego atrybutu
        info3 = info3(classNum, info, insts.size()); //liczy drugą czesc do gain'a
      }
      return gain(info2, info3);
    }
        
    // szukamy tych instancji ktore zawieraja podaną wartość w danej kolumnie
    public static ArrayList<Instance> findInstancesForValue(ArrayList<Instance> insts, int col, String val) {
      ArrayList<Instance> filtered = new ArrayList<Instance>();
      for (Instance i : insts) {
        if (i.stringValue(col).equals(val)) {
          filtered.add(i);
        }
      }
      return filtered;
    }
    
    // zwracamy liczbe klas (tutaj 1 lub 2) występującą w podanych instancjach
    public static int numClasses(ArrayList<Instance> insts) {
      HashMap<String, Boolean> classes = new HashMap<String, Boolean>();
      for (Instance i : insts) {
        classes.put(i.stringValue(i.numValues()-1), true);
      }
      return classes.keySet().size();
    }
    
    private static double info(int[] data) {
        double sum = (data[0] + data[1]) * 1.0;
        if((data[0] / sum) == 1 || (data[1] / sum) == 1)  
            return 0;
        else
            return entropy(data[0] / sum, data[1] / sum);
    }
    
    private static double info3(int[] data, double entr, int count) { //liczenie 2 czesci do gaina
        double sumi = (data[0] + data[1]) * 1.0;
        return ((sumi/count) * entr);
    }

    private static double entropy(double a, double b) {
        return - a * Math.log(a)/Math.log(2) - b * Math.log(b)/Math.log(2);
    }
    
    private static double gain(double info2, double info3) {
        return info2 - info3;
    }
}
