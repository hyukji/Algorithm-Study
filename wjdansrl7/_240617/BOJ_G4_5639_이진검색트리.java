package _240617;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_5639_이진검색트리 {
    static class Node {
        int data;
        Node left, right;

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    static Node root;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int data = Integer.parseInt(st.nextToken());
        root = new Node(data, null, null);

        while (true) {
            String str = br.readLine();
            if (str == "" || str == null || str.equals("")) break;

            data = Integer.parseInt(str);
            preOrder(data, root);
        }
        postOrder(root);
    }

    static void postOrder(Node node) {
        if (node.left != null) postOrder(node.left);
        if (node.right!= null) postOrder(node.right);
        System.out.println(node.data);
    }

    static void preOrder(int data, Node node) {
        if (node.data < data) {
            if (node.right != null) preOrder(data, node.right);
            else {
                Node newNode = new Node(data, null, null);
                node.right = newNode;
            }
        } else {
            if (node.left != null) {
                preOrder(data, node.left);
            } else {
                Node newNode = new Node(data, null, null);
                node.left = newNode;
            }
        }
    }
}