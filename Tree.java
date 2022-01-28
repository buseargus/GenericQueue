package DataStructures.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Tree {

    public class TreeNode {
        public int data;
        public TreeNode leftChild;
        public TreeNode rightChild;
        public int depth;

        public TreeNode() {
            depth = 0;
        }
        public void printNode() {
            System.out.println(data);
        }
    }

    private TreeNode root;
    double totalDepth;
    int maxDepth;
    int[] elementCountForEachDepth;
    int[] sumElementCountForEachDepth;
    int leavesDepthTotal;
    int leavesCount;
    int largestLevel;
    int size;
    int totalNode;
    int totalLeaf;
    int sum;
    ArrayList leavesDepth;
    Queue treeQueue;
    int parentNum;
    int maxValue;
    int sizeNum;

    public Tree() {
        root = null;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void preOrder(TreeNode localRoot) {
        if (localRoot != null) {
            localRoot.printNode();
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    public void inOrder(TreeNode localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            localRoot.printNode();
            inOrder(localRoot.rightChild);
        }
    }

    public void postOrder(TreeNode localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            localRoot.printNode();
        }
    }

    public void insert(int newdata) {
        TreeNode newNode = new TreeNode();
        newNode.data = newdata;
        if (root == null) {
            root = newNode;
        } else {
            TreeNode parent;
            TreeNode current = root;
            while (true) {
                parent = current;
                if (newdata < current.data) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public int treeSize(TreeNode localRoot) {
        if(localRoot != null) {
            sizeNum += 1;
            treeSize(localRoot.leftChild);
            treeSize(localRoot.rightChild);
        }
        return sizeNum;
    }

    //###
    //Average depth
        public void findAverageDepth(TreeNode localRoot, double treeSize) {
            totalDepth = 0;
            depthInfo(localRoot, -1);
            double averageDepth =  totalDepth / treeSize;
            System.out.println("Average depth : " + averageDepth);
        }

        public void depthInfo(TreeNode node, int depth) {
            if (node != null) {
                depth++;
                totalDepth += depth;
                depthInfo(node.leftChild, depth);
                depthInfo(node.rightChild, depth);

            }
    }
    //Average depth
    //###

    //###
    //Largest level
    public void largestLevel(TreeNode localRoot, int treeSize) {
        largestLevel = 0;
        size = 0;
        elementCountForEachDepth = new int[treeSize];
        largestlevelSize(localRoot, -1);
        for (int j = 0; j < treeSize; j++) {
            if (elementCountForEachDepth[j] > size) {
                size = elementCountForEachDepth[j];
                largestLevel = j;
            }
        }
        System.out.println("Largest level : " + largestLevel + " number of elements at this level : " + size );
    }

    public void largestlevelSize(TreeNode node, int depth) {
        if (node != null) {
            depth++;
            elementCountForEachDepth[depth]+=1;
            largestlevelSize(node.leftChild, depth);
            largestlevelSize(node.rightChild, depth);
        }
    }
    //Largest level
    //###

    //###
    //Number of nodes and leaves
    public void levelAndLeaf(TreeNode localRoot) {
        totalNode = 0;
        totalLeaf = 0;
        levelAndLeafInfo(localRoot);
        System.out.println("Nodes : " + totalNode + " leaves : " + totalLeaf);
    }

    public void levelAndLeafInfo(TreeNode node){
        if(node != null) {
            if((node.rightChild == null) && (node.leftChild == null)) {
                totalLeaf += 1;
            }
            totalNode += 1;

            levelAndLeafInfo(node.leftChild);
            levelAndLeafInfo(node.rightChild);
        }
    }
    //Number of nodes and leaves
    //###

    //###
    //Nodes with two children nodes
    public void nodesWithChildrenNodes(TreeNode localRoot) {
        parentNum = 0;
        nodeInfo(localRoot);
        System.out.println("Number of nodes with two children nodes : " + parentNum);
    }

    public void nodeInfo(TreeNode node) {
        if (node != null) {
            if((node.rightChild != null) && (node.leftChild != null)) {
                parentNum += 1;
            }
            nodeInfo(node.leftChild);
            nodeInfo(node.rightChild);
        }
    }
    //Nodes with two children nodes
    //###

    //###
    //Sum of elements
    public void findSum(TreeNode localRoot) {
        sum  = 0;
        sumInfo(localRoot);
        System.out.println("Sum of elements : " + sum);
    }

    public void sumInfo(TreeNode node) {
        if (node != null) {
            sum += node.data;
            sumInfo(node.leftChild);
            sumInfo(node.rightChild);
        }
    }
    //Sum of elements
    //###

    //###
    //Full binary tree check
    public boolean isFullBinary(TreeNode localRoot) {
        leavesDepth = new ArrayList<>();
        fullBinaryInfo(localRoot, -1);
        for (int i = 0; i < leavesDepth.size()-1; i++) {
            for (int j = i+1; j < leavesDepth.size(); j++) {
                if(leavesDepth.get(i) != leavesDepth.get(j)) {
                    return false;
                }
            }
        }
        return true;
    }
    public void fullBinaryInfo(TreeNode node, int depth) {
        if(node != null) {
            depth++;
            if((node.leftChild == null) && (node.rightChild == null)) {
                leavesDepth.add(depth);
            }
            fullBinaryInfo(node.leftChild, depth);
            fullBinaryInfo(node.rightChild, depth);
        }
    }
    //Full binary tree check
    //###

    //###
    //Print levels in order
    public void printLevel(TreeNode localRoot) {
        int i;
        maxDepth = 0;
        findDepth(localRoot, -1);
        for (i = 0; i <= maxDepth; i++) {
            printInfo(localRoot, i);
            System.out.println();
        }
    }

    public void printInfo(TreeNode node, int level) {
        if (node == null) {
            return;
        } if (level == 0) {
            System.out.print(node.data + " ");
        } else if(level > 0) {
            printInfo(node.leftChild, level-1);
            printInfo(node.rightChild, level-1);
        }
    }

    public void findDepth(TreeNode node, int depth) {
        if(node != null) {
            depth++;
            if(depth > maxDepth) {
                maxDepth = depth;
            }
            findDepth(node.leftChild, depth);
            findDepth(node.rightChild, depth);
        }
    }
    //Print levels in order
    //###

    //###
    //Print in ascending order
    public void printTree(TreeNode localRoot) {
        if (localRoot != null ) {
            printTree(localRoot.rightChild);
            localRoot.printNode();
            printTree(localRoot.leftChild);
        }
    }
    //Print in ascending order
    //###

    //###
    //Add to queue in ascending order
    public void addQueue(TreeNode localRoot) {
        treeQueue = new LinkedList();
        addToQueue(localRoot);
        while(!treeQueue.isEmpty()){
            System.out.println(treeQueue.poll());
        }
    }

    public void addToQueue(TreeNode localRoot) {
        if(localRoot != null) {
            addToQueue(localRoot.leftChild);
            treeQueue.add(localRoot.data);
            addToQueue(localRoot.rightChild);
        }
    }
    //Add to queue in ascending order
    //###

    //###
    //Change element
    public void findTree(TreeNode localRoot, int data, int newData){
        if(localRoot != null) {
            if(localRoot.data == data) {
                localRoot.data = newData;
                return;
            }
            findTree(localRoot.leftChild, data, newData);
            findTree(localRoot.rightChild, data, newData);
        }
    }
    //Change element
    //###

    //###
    //Finding the smallest element greater than the specified element
    public void findNode(TreeNode localRoot, int node) {
        maxValue = Integer.MAX_VALUE;
        searchNode(localRoot, node);
        System.out.println(maxValue);
    }

    public void searchNode(TreeNode node, int data) {
        if (node != null) {
            if((node.data > data) && (node.data < maxValue)) {
                maxValue = node.data;
            }
            searchNode(node.leftChild, data);
            searchNode(node.rightChild, data);
        }
    }
    //Finding the smallest element greater than the specified element
    //###

    //###
    //Swapping the left and right children
    public void switchNodes(TreeNode localRoot ) {
        if (localRoot != null) {
            TreeNode temp = localRoot.leftChild;
            localRoot.leftChild = localRoot.rightChild;
            localRoot.rightChild = temp;
            switchNodes(localRoot.leftChild);
            switchNodes(localRoot.rightChild);
        }
    }
    //Swapping the left and right children
    //###

    //###
    //Binary search tree check
    public boolean isBinarySearchTree(TreeNode localRoot) {
        if(localRoot != null) {
            if((localRoot.leftChild.data > localRoot.data) || (localRoot.rightChild.data < localRoot.data)) {
                return false;
            }
            isBinarySearchTree(localRoot.leftChild);
            isBinarySearchTree(localRoot.rightChild);
        }
        return true;
    }
    //Binary search tree check
    //###

    //###
    //Assign depth to field
    public void addDepths(TreeNode localRoot) {
        addDepthInfo(localRoot, -1);
    }

    public void addDepthInfo(TreeNode node, int depth) {
        if(node != null) {
            depth++;
            node.depth = depth;
            addDepthInfo(node.leftChild, depth);
            addDepthInfo(node.rightChild, depth);
        }
    }
    //###
    //Assign depth to field


    public void findTreeInfo(TreeNode rootNode, int treeSize) {
        totalDepth = 0;
        maxDepth = 0;
        elementCountForEachDepth = new int[treeSize];
        sumElementCountForEachDepth = new int[treeSize];
        leavesDepthTotal = 0;
        leavesCount = 0;
        traverseTreeForInfo(rootNode, -1);
        System.out.println("Depth of the tree : " + maxDepth);
        System.out.println("Element counts for each depth: ");
        for (int i = 0; i <= maxDepth; i++) {
            System.out.println("For depth : " + i + "Number of elements :" + elementCountForEachDepth[i] +
                    "sum of elements :" + sumElementCountForEachDepth[i]);
        }
        System.out.println("Average depth of leaves : " + (double) leavesDepthTotal / leavesCount);
    }

    public void traverseTreeForInfo(TreeNode node, int depth) {
        if (node != null) {
            depth++;
            elementCountForEachDepth[depth]++;
            sumElementCountForEachDepth[depth] += node.data;

            if (depth > maxDepth) {
                maxDepth = depth;
            }

            totalDepth += depth;

            if ((node.leftChild == null) && (node.rightChild == null)) {
                leavesCount++;
                leavesDepthTotal += depth;
            }

            traverseTreeForInfo(node.leftChild, depth);
            traverseTreeForInfo(node.rightChild, depth);
        }
    }
}
