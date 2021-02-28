/** Implementation for a union-find data structure with int-type items. */
public class UnionFind {

    /** The union-find data structure. */
    int[] data;

    /** Default constructor. */
    public UnionFind() {
        this(1);
    }

    /**
     * Constructor that takes in an integer.
     * @param n the size of the data structure
     */
    public UnionFind(int n) {
        if (n > 0) {
            this.data = new int[n];
            for (int i = 0; i < n; i++) {
                this.data[i] = -1;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Determine the name of the set containing the specified element.
     * @param i the element whose set we wish to find
     * @return the name of the set containing i
     */
    public int find(int i) {
        if (this.data[i] < 0) {
            return i;
        }
        this.data[i] = this.find(this.data[i]);
        return this.data[i];
    }

    /**
     * Merge two sets if they are not already the same set.
     * @param a an item in the first set to be merged (need not be set name)
     * @param b an item in the second set to be merged (need not be set name)
     */
    public void union(int a, int b) {

        int rootA = this.find(a);
        int rootB = this.find(b);
        int sizeA = this.data[rootA];
        int sizeB = this.data[rootB];

        if (sizeA < sizeB) {
            this.data[rootA] = rootB;
            this.data[rootB] += sizeA;
        } else {
            this.data[rootB] = rootA;
            this.data[rootA] += sizeB;
        }
    }

    /**
     * Returns the number of subsets in the data structure.
     * @return the number of subsets in the data structure
     */
    public int getNumSubsets() {
        int num = 0;
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i] < 0) {
                num++;
            }
        }
        return num;
    }
}
