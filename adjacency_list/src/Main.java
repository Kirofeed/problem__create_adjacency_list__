import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Path inputFile = Paths.get("input.txt");
        Path outputFile = Paths.get("output.txt");

        int n = 0, m = 0;
        ArrayList<Integer>[] adjacencyLists;

        try (BufferedReader reader = Files.newBufferedReader(inputFile)) {
            // Читаем первую строку
            String[] firstLine = reader.readLine().split(" ");
            n = Integer.parseInt(firstLine[0]);
            m = Integer.parseInt(firstLine[1]);

            // Инициализируем массив списков смежности
            adjacencyLists = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                adjacencyLists[i] = new ArrayList<>();
            }

            // Читаем рёбра
            for (int i = 0; i < m; i++) {
                String line = reader.readLine();
                int spaceIndex = line.indexOf(' ');
                int u = Integer.parseInt(line.substring(0, spaceIndex));
                int v = Integer.parseInt(line.substring(spaceIndex + 1));

                adjacencyLists[u].add(v);
                adjacencyLists[v].add(u);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return;
        }

        // Записываем результат
        try (BufferedWriter writer = Files.newBufferedWriter(outputFile)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                ArrayList<Integer> neighbors = adjacencyLists[i];
                sb.append(neighbors.size());
                for (int neighbor : neighbors) {
                    sb.append(" ").append(neighbor);
                }
                sb.append("\n");
                writer.write(sb.toString());
                sb.setLength(0);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи файла: " + e.getMessage());
        }
    }
}