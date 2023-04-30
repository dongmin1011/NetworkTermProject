package NetwrokTermProject.LinkedList;

// 노드 클래스
class Node<T> {
    T data; // 노드의 데이터, 제네릭 타입으로 선언
    Node<T> next; // 다음 노드를 가리키는 링크

    // 생성자
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

// 링크드리스트 클래스
public class LinkedList<T> {
    Node<T> head; // 링크드리스트의 첫 번째 노드

    // 생성자
    public LinkedList() {
        this.head = null;
    }

    // 링크드리스트에 노드 추가
    public void addNode(T data) {
        Node<T> newNode = new Node<T>(data); // 새로운 노드 생성

        // 링크드리스트가 비어있는 경우
        if (head == null) {
            head = newNode; // 헤드 노드로 새로운 노드 설정
        } else {
            Node<T> currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next; // 현재 노드의 다음 노드로 이동
            }
            currentNode.next = newNode; // 현재 노드의 다음 노드로 새로운 노드 설정
        }
    }

    // 링크드리스트에서 노드 삭제
    public void deleteNode(T data) {
        if (head == null) {
            System.out.println("LinkedList is empty.");
            return;
        }

        if (head.data.equals(data)) {
            head = head.next; // 헤드 노드를 다음 노드로 업데이트
            return;
        }

        Node<T> prevNode = head;
        Node<T> currentNode = head.next;
        while (currentNode != null && !currentNode.data.equals(data)) {
            prevNode = currentNode;
            currentNode = currentNode.next;
        }

        if (currentNode != null) {
            prevNode.next = currentNode.next; // 이전 노드의 다음 노드를 현재 노드의 다음 노드로 연결하여 삭제
        } else {
            System.out.println("Node with data " + data + " not found in the LinkedList.");
        }
    }

    // 링크드리스트에서 데이터 업데이트
    public void updateNode(T oldData, T newData) {
        if (head == null) {
            System.out.println("LinkedList is empty.");
            return;
        }

        Node<T> currentNode = head;
        while (currentNode != null && !currentNode.data.equals(oldData)) {
            currentNode = currentNode.next;
        }

        if (currentNode != null) {
            currentNode.data = newData; // 현재 노드의 데이터를 새로운 데이터로 업데이트
        } else {
            System.out.println("Node with data " + oldData + " not found in the LinkedList.");
        }
    }
}
