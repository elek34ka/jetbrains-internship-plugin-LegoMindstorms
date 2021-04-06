import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);

    static int[] use = new int[26];
    static int[][] a = new int[26][26];
    static int n;
    static String[] str;
    static boolean[] used = new boolean[26];

    static boolean dfs(int u) {
        use[u] = 1;
        for (int i = 0; i < 26; i++) {
            if (a[u][i] == 1) {
                if (use[i] == 0) {
                    if (dfs(i))
                        return true;
                } else if (use[i] == 1) {
                    return true;
                }
            }
        }
        use[u] = 2;
        return false;
    }

    static void read() {
        n = in.nextInt();
        in.nextLine();
        str = new String[n];
        for (int i = 0; i < n; i++) {
            str[i] = in.nextLine();
        }
    }

    static void createGraph() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < str[i].length(); j++) {
                for (int k = i + 1; k < n; k++) {
                    if (j >= str[k].length())
                        break;
                    if (str[i].substring(0, j).equals(str[k].substring(0, j))) {
                        if (str[i].charAt(j) != str[k].charAt(j)) {
                            int x = str[i].charAt(j) - 'a';
                            int y = str[k].charAt(j) - 'a';
                            if (a[y][x] == 1) {
                                System.out.println("Impossible");
                                System.exit(0);
                            }
                            a[x][y] = 1;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }

    static void printGraph() {
        for (int i = 0; i < 26; i++) {
            System.out.print((char) (i + 'a') + " > ");
            for (int j = 0; j < 26; j++) {
                if (a[i][j] == 1)
                    System.out.print((char) (j + 'a'));
            }
            System.out.println();
        }
    }

    static void findCircle() {
        for (int i = 0; i < 26; i++) {
            if (use[i] == 0) {
                if (dfs(i)) {
                    System.out.println("Impossible");
                    System.exit(0);
                }
            }
        }
    }

    static String makeAnswer() {
        StringBuilder ans = new StringBuilder();
        for (int o = 0; o < 26; o++) {
            for (int i = 0; i < 26; i++) {
                int k = 0;
                for (int j = 0; j < 26; j++) {
                    if (!used[j])
                        k += a[i][j];
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
