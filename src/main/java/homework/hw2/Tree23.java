package homework.hw2;

import java.util.ArrayList;

class Tree23<K extends Comparable<K>, V> {

    private Node<K, V> root;

    Node<K, V> search23(K key) {
        if (root == null) {
            return null;
        }

        Node<K, V> x = root;
        while (true) {
            int cmp = compare(key, x);
            if (cmp == 4) {
                return x;
            }
            if (x.left_child == null) {
                return x;
            }
            x = switch (cmp) {
                case 1 -> x.left_child;
                case 2 -> x.middle_child;
                case 3 -> x.right_child;
                default -> x;
            };
        }

    }

    public int compare(K x, Node<K, V> t) {
        if (x.compareTo(t.key_l) < 0) {
            return 1;
        } else if (x.compareTo(t.key_l) == 0) {
            return 4;
        } else if (t.key_r == null) {
            return 2;
        } else if (x.compareTo(t.key_r) < 0) {
            return 2;
        } else if (x.compareTo(t.key_r) == 0) {
            return 4;
        } else {
            return 3;
        }

    }

    public V get(K key) {
        Node<K, V> x = search23(key);

        if (x == null) return null;

        if (compare(key, x) == 4) {
            if (key.compareTo(x.key_l) == 0) return x.val_l;
            if (key.compareTo(x.key_r) == 0) return x.val_r;
        }

        return null;
    }

    public void put(K key, V value) {
        Node<K, V> p;

        if (root == null) root = new Node<K, V>(key, value);
        else {
            p = search23(key);

            if (compare(key, p) == 4) {
                if (key.compareTo(p.key_l) == 0) p.val_l = value;
                else p.val_r = value;
                return;
            }

            Node<K, V> newNode = new Node<K, V>(key, value);
            newNode.left_child = null;
            newNode.middle_child = null;

            while (true) {
                if (p.key_r == null) {
                    if (newNode.key_l.compareTo(p.key_l) > 0) { //추가할 노드가 더 클 때
                        p.key_r = newNode.key_l;
                        p.val_r = newNode.val_l;
                        p.right_child = newNode.middle_child;
                        if (p.right_child != null) p.right_child.parent = p;
                    } else {
                        p.key_r = p.key_l;
                        p.val_r = p.val_l;
                        p.right_child = p.middle_child;
                        p.key_l = newNode.key_l;
                        p.val_l = newNode.val_l;
                        p.middle_child = newNode.middle_child;
                        if (p.middle_child != null) p.middle_child.parent = p;
                    }
                    for (Node<K, V> cur = p; cur != null; cur = cur.parent) {
                        resetSize(cur);
                    }
                    break;
                } else {
                    Node<K, V> nextp = p.parent;
                    split(p, newNode);
                    if (p == root) {
                        root = newNode;
                        if (root.left_child != null) root.left_child.parent = root;
                        if (root.middle_child != null) root.middle_child.parent = root;
                        break;
                    } else p = nextp;
                }
            }
        }
    }

    public void split(Node<K, V> p, Node<K, V> newNode) {
        K minK, midK, maxK;
        V minV, midV, maxV;
        Node<K, V> c0, c1, c2, c3;
        Node<K, V> right = new Node<K, V>(null, null);

        if (compare(newNode.key_l, p) == 1) {
            minK = newNode.key_l;
            minV = newNode.val_l;
            midK = p.key_l;
            midV = p.val_l;
            maxK = p.key_r;
            maxV = p.val_r;
            c0 = newNode.left_child;
            c1 = newNode.middle_child;
            c2 = p.middle_child;
            c3 = p.right_child;
        } else if (compare(newNode.key_l, p) == 2) {
            minK = p.key_l;
            minV = p.val_l;
            midK = newNode.key_l;
            midV = newNode.val_l;
            maxK = p.key_r;
            maxV = p.val_r;
            c0 = p.left_child;
            c1 = newNode.left_child;
            c2 = newNode.middle_child;
            c3 = p.right_child;
        } else {
            minK = p.key_l;
            minV = p.val_l;
            midK = p.key_r;
            midV = p.val_r;
            maxK = newNode.key_l;
            maxV = newNode.val_l;
            c0 = p.left_child;
            c1 = p.middle_child;
            c2 = newNode.left_child;
            c3 = newNode.middle_child;
        }

        newNode.key_l = midK;
        newNode.val_l = midV;
        right.key_l = maxK;
        right.val_l = maxV;
        p.key_l = minK;
        p.val_l = minV;
        p.key_r = null;
        p.val_r = null;
        p.left_child = c0;
        if (c0 != null) c0.parent = p;
        p.middle_child = c1;
        if (c1 != null) c1.parent = p;
        p.right_child = null;
        right.left_child = c2;
        if (c2 != null) c2.parent = right;
        right.middle_child = c3;
        if (c3 != null) c3.parent = right;

        newNode.left_child = p;
        newNode.middle_child = right;

        resetSize(p);
        resetSize(right);
        resetSize(newNode);
    }

    private void resetSize(Node<K, V> x) {
        if (x == null) return;

        int count = 0; // 기본은 0개로 시작
        if (x.key_l != null) count++; // 왼쪽 방에 키가 있으면 +1
        if (x.key_r != null) count++; // 오른쪽 방에 키가 있으면 +1

        if (x.left_child != null) count += x.left_child.N;
        if (x.middle_child != null) count += x.middle_child.N;
        if (x.right_child != null) count += x.right_child.N;

        x.N = count;
    }


    public void delete(K key) {

        if (root == null) return;
        Node<K, V> p = search23(key);
        Node<K, V> y;

        if (!key.equals(p.key_l) && !key.equals(p.key_r)) return;

        //leaf node가 아닐때 -> inorder successor로 변경
        if (p.left_child != null) {
            if (key.equals(p.key_l)) {
                y = min(p.middle_child);
                K tempK = p.key_l;
                V tempV = p.val_l;
                p.key_l = y.key_l;
                p.val_l = y.val_l;
                y.key_l = tempK;
                y.val_l = tempV;
            } else {
                y = min(p.right_child);
                K tempK = p.key_r;
                V tempV = p.val_r;
                p.key_r = y.key_l;
                p.val_r = y.val_l;
                y.key_l = tempK;
                y.val_l = tempV;
            }
            p = y;
        }

        //leaf node 처리 로직.
        //1. 3노드일 때. 삭제 후 값 이동 로직
        if (p.key_r != null) {
            if (key.equals(p.key_l)) {
                p.key_l = p.key_r;
                p.val_l = p.val_r;
            }
            p.key_r = null;
            p.val_r = null;

            for (Node<K, V> cur = p; cur != null; cur = cur.parent) {
                resetSize(cur);
            }

            return;
        } else {
            p.key_l = null;
            p.val_l = null;
        }

        while (p.key_l == null && p != root) {
            Node<K, V> r = p.parent;
            Node<K, V> q = null;
            if (p == r.left_child) {
                q = r.middle_child;
            } else if (p == r.middle_child) {
                if (r.left_child.key_r != null) {
                    q = r.left_child;
                } else if (r.right_child != null && r.right_child.key_r != null) {
                    q = r.right_child;
                } else {
                    q = r.left_child;
                }
            } else {
                q = r.middle_child;
            }

            if (q.key_r != null) {
                rotate(p,q,r);
                break;
            } else {
                combine(p,q,r);
            }
            p = r;
        }
        if (root.key_l == null && root.left_child != null) {
            root = root.left_child;
            root.parent = null;
        } else if (root.key_l == null) {
            root = null;
        }

        if (root != null) {
            for (Node<K, V> cur = p; cur != null; cur = cur.parent) {
                resetSize(cur);
            }
        }
    }

    protected void rotate(Node<K, V> p, Node<K, V> q, Node<K, V> r) {
        if (p == r.left_child || (p==r.middle_child && q == r.right_child)) {
            if (p==r.left_child) {
                p.key_l = r.key_l;
                p.val_l = r.val_l;
                r.key_l = q.key_l;
                r.val_l = q.val_l;
            }else{
                p.key_l = r.key_r;
                p.val_l = r.val_r;
                r.key_r = q.key_l;
                r.val_r = q.val_l;
            }
            p.middle_child = q.left_child;
            if (p.middle_child != null) p.middle_child.parent = p;
            //q당기기(빌려오기)
            q.key_l = q.key_r;
            q.val_l = q.val_r;
            q.key_r = null;
            q.val_r = null;

            q.left_child = q.middle_child;
            q.middle_child = q.right_child;
            q.right_child = null;
        } else {
            if (p == r.middle_child) { //형제보다 오른쪽
                p.key_l = r.key_l;
                p.val_l = r.val_l;
                r.key_l = q.key_r;
                r.val_l = q.val_r;
            }else {
                p.key_l = r.key_r;
                p.val_l = r.val_r;
                r.key_r = q.key_r;
                r.val_r = q.val_r;
            }
            p.middle_child = p.left_child;
            p.left_child = q.right_child;
            if (p.left_child != null) p.left_child.parent = p;

            q.key_r = null;
            q.val_r = null;
            q.right_child = null;
        }
        resetSize(q);
        resetSize(p);
        resetSize(r);
    }

    protected void combine(Node<K, V> p, Node<K, V> q, Node<K, V> r) {
        if (p == r.right_child) {
            q.key_r = r.key_r;
            q.val_r = r.val_r;
            q.right_child = p.left_child;
            if (q.right_child != null) q.right_child.parent = q;

            r.key_r = null; r.val_r = null;
            r.right_child = null;
        }else {
            if(p == r.left_child){
                p.key_l = r.key_l;
                p.val_l = r.val_l;
                p.key_r = q.key_l;
                p.val_r = q.val_l;

                p.middle_child = q.left_child;
                if (p.middle_child != null) p.middle_child.parent = p;
                p.right_child = q.middle_child;
                if (p.right_child != null) p.right_child.parent = p;

            }else{
                q.key_r = r.key_l;
                q.val_r = r.val_l;
                q.right_child = p.left_child;
                if (q.right_child != null) q.right_child.parent = q;
            }

            if (r.key_r != null) {
                r.key_l = r.key_r;
                r.val_l = r.val_r;
                r.key_r = null;
                r.val_r = null;
                r.middle_child = r.right_child;
                r.right_child = null;
            } else {
                r.key_l = null; r.val_l = null;
                r.middle_child = null;
            }
        }
        resetSize(q);
        resetSize(p);
        resetSize(r);
    }


    protected Node<K, V> min(Node<K, V> x) {
        while (x.left_child != null) x = x.left_child;
        return x;
    }

    public Iterable<K> keys() {
        ArrayList<K> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(Node<K, V> x, ArrayList<K> list) {
        if (x == null) return;
        inorder(x.left_child, list);
        list.add(x.key_l);
        inorder(x.middle_child, list);

        if (x.key_r != null) {
            list.add(x.key_r);
            inorder(x.right_child, list);
        }
    }

    public int depth() {

        int depth = 0;
        Node<K, V> t = root;

        while (t != null) {
            depth++;
            t = t.left_child;
        }

        return depth;
    }

    public boolean contains(K key) {
        return get(key) != null; //완
    }

    public boolean isEmpty() {
        return root == null; //완
    }

    public int size() {
        if (root == null) return 0;
        return root.N; //완
    }

}

class Node<K, V> {
    K key_l, key_r;
    V val_l, val_r;
    Node<K, V> left_child, middle_child, right_child;
    int N;
    Node<K, V> parent;

    public Node(K key, V val) {
        this.key_l = key;
        this.val_l = val;
        this.N = 1;
    }

    public Node(K key, V val, Node<K, V> parent) {
        this.key_l = key;
        this.val_l = val;
        this.parent = parent;
        this.N = 1;
    }
}