

import java.util.Random;


public class UnionMaze {
    Random rand = new Random();
    int n,m,start,end;
    int[] cubical;
    UnionMaze(int row, int col) {
        int index = 0;
        n = row;
        m= col;
        start = 0;
        end = (row*col) - 1;
        cubical = new int[row*col];
        for(int i = 0; i < row; ++i) {
            for(int j = 0; j <col; ++j)
                cubical[index++] = index;


        }
    }


    public int box1() {
        int room = rand.nextInt(m*n);
        return room;
    }
    public int nextBox(int room) {
        int randRoom = rand.nextInt(4)+1;
        switch(randRoom) {
            case 1:
                if((room - n) < 0)
                    room = nextBox(room);
                else
                    room = room - n;
                break;
            case 2:
                if(room%m == 0)
                    room = nextBox(room);
                else
                    --room;
                break;
            case 3:
                if((room + n) >= n*m)
                    room = nextBox(room);
                else
                    room = room + n;
                break;
            case 4:
                if(room + m == m )
                    room = nextBox(room);
                else
                    ++room;
                break;
        }
        return room;

        //return randomRoom;
    }
    public void print() {
        for(int i = 0; i < cubical.length; i++)
            System.out.println(cubical[i]);
    }

    public static void main(String[] args) {
        UnionMaze maze = new UnionMaze(10, 10);
        Uf connector = new Uf(maze.cubical.length);
        int count = 0;
        while(!connector.connected(maze.start, maze.end)) {
            int a= maze.box1();
            int b = maze.nextBox(a);
              connector.union(a, b);
            System.out.println( count + " union of "+( a + " " + b));
            ++count;
        }
        System.out.println("finally connected " + connector.connected(maze.start, maze.end));
    }
}
