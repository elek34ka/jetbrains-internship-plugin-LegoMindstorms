import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);

    static final int ALPHABET_SIZE = 26;

    static int[] use_dfs = new int[ALPHABET_SIZE];
    static boolean[][] arr = new boolean[ALPHABET_SIZE][ALPHABET_SIZE];
    static int n;
    static String[] words;
    static boolean[] used = new boolean[ALPHABET_SIZE];

    static boolean dfs(int u) {
        use_dfs[u] = 1;
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (arr[u][i]) {
                if (use_dfs[i] == 0) {
                    if (dfs(i))
                        return true;
                } else if (use_dfs[i] == 1) {
                    return true;
                }
            }
        }
        use_dfs[u] = 2;
        return false;
    }

    static void read() {
        n = in.nextInt();
        in.nextLine();
        words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = in.nextLine();
        }
    }

    static void createGraph() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                for (int k = i + 1; k < n; k++) {
                    if (j >= words[k].length())
                        break;
                    if (words[i].substring(0, j).equals(words[k].substring(0, j))) {
                        if (words[i].charAt(j) != words[k].charAt(j)) {
                            int x = words[i].charAt(j) - 'a';
                            int y = words[k].charAt(j) - 'a';
                            if (arr[y][x]) {
                                System.out.println("Impossible");
                                System.exit(0);
                            }
                            arr[x][y] = true;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }

    static void printGraph() {
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            System.out.print((char) (i + 'a') + " > ");
            for (int j = 0; j < ALPHABET_SIZE; j++) {
                if (arr[i][j])
                    System.out.print((char) (j + 'a'));
            }
            System.out.println();
        }
    }

    static void findCircle() {
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (use_dfs[i] == 0) {
                if (dfs(i)) {
                    System.out.println("Impossible");
                    System.exit(0);
                }
            }
        }
    }

    static String makeAnswer() {
        StringBuilder ans = new StringBuilder();
        for (int o = 0; o < ALPHABET_SIZE; o++) {
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                int k = 0;
                for (int j = 0; j < ALPHABET_SIZE; j++) {
                    if (!used[j] && arr[i][j])
                        k++;
                }
                if (k == 0 && !used[i]) {
                    ans.append((char) (i + 'a'));
                    used[i] = true;
                }
            }
        }
        return ans.reverse().toString();
    }

    public static void main(String[] args) {

        read();

        createGraph();

        findCircle();

        //printGraph();

        System.out.println(makeAnswer());

    }
}
